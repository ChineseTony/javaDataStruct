package com.tom.study;

import org.benf.cfr.reader.api.CfrDriver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangtao
 * @date 2021/5/23
 */
public class DecomplieUtil {

    private DecomplieUtil(){

    }

    public static void decompile(String source, String targetPath) throws IOException {
        Long start = System.currentTimeMillis();
        // source jar
        List<String> files = new ArrayList<>();
        String path =
                Thread.currentThread().getContextClassLoader ().getResource("").getPath();
        String classPath = path+source.replace(".","/")+".class";
        files.add(classPath);

        // target dir
//        HashMap<String, String> outputMap = new HashMap<>();
//        outputMap.put("outputdir", targetPath);
//        OptionsImpl options = new OptionsImpl(outputMap);
//        CfrDriver cfrDriver = new CfrDriver.Builder().withBuiltOptions(options).build();

//        OutputSinkFactory mySink = new OutputSinkFactory() {
//            @Override
//            public List<SinkClass> getSupportedSinks(SinkType sinkType,
//                                                     Collection<SinkClass> collection) {
//                return Collections.singletonList(SinkClass.STRING);
//            }
//
//            @Override
//            public <T> Sink<T> getSink(SinkType sinkType, SinkClass sinkClass) {
//                return sinkType == SinkType.JAVA ? System.out::println : ignore -> {};
//            }
//        };

//        CfrDriver cfrDriver = new CfrDriver.Builder().withOutputSink(mySink).build();

        CfrDriver cfrDriver = new CfrDriver.Builder().build();
        cfrDriver.analyse(files);
        Long end = System.currentTimeMillis();
        System.out.println(String.format("decompiler time: %dms", (end - start)));
    }
}
