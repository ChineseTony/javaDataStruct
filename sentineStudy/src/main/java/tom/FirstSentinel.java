package tom;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.concurrent.NamedThreadFactory;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FirstSentinel {
    private static final Logger LOGGER = LoggerFactory.getLogger(FirstSentinel.class);


    private static final String SOURCE_NAME = FirstSentinel.class.getName();

    private static final int COUNT = 100;
    private static  Entry entry;

    static {
        initFlowRules();
        entry = null;
    }

    public static void main(String[] args)  {
        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(
                        10,100,
                        2, TimeUnit.SECONDS,
                        new LinkedBlockingDeque<>(200),
                        new NamedThreadFactory("tom"),
                        new ThreadPoolExecutor.AbortPolicy()
                );

        for (int i = 0; i < COUNT; i++) {
            executor.execute(FirstSentinel::flow);
            if (i == 50){
                try {
                    Thread.sleep(1000);
                    LOGGER.info("sleep 1s");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            executor.awaitTermination(5,TimeUnit.MICROSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (!executor.isShutdown()){
                executor.shutdown();
            }
        }

    }


    public static void flow(){
        try {
            entry = SphU.entry(SOURCE_NAME);
        }catch (BlockException e){
            LOGGER.error("block");
        }finally {
            if (entry != null){
                entry.close();
            }
        }
    }


    private static void initFlowRules(){
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource(SOURCE_NAME);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(20);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}
