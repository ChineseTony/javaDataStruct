package com.tom.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;


/**
 * @author WangTao
 */
public class MyPropertyCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        MultiValueMap<String, Object>
                attributes = metadata.getAllAnnotationAttributes(MyConditional.class.getName());
        String environmentName = (String) attributes.getFirst("environment");
        Environment myEnvironment = context.getEnvironment();
        String[] activeProfiles = myEnvironment.getActiveProfiles();
//        System.out.println("active"+ Arrays.toString(activeProfiles));
        for (int i = 0; i < activeProfiles.length; i++) {
            if (!StringUtils.isEmpty(environmentName) &&
                    environmentName.equals(activeProfiles[i])){
                System.out.printf("选择%s环境\n",environmentName);
                return true;
            }
        }

        return false;
    }
}
