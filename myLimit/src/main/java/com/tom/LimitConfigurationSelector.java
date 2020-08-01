package com.tom;


import com.tom.enums.LimitType;
import com.tom.properties.LimitProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.NonNull;

import java.lang.reflect.Method;
import java.util.Map;

public class LimitConfigurationSelector implements ImportSelector {

    private static final Logger logger = LoggerFactory.getLogger(LimitConfigurationSelector.class);




    @Override
    @NonNull
    public String[] selectImports(@NonNull AnnotationMetadata importingClassMetadata) {
        Map<String,Object> annotationMap = importingClassMetadata.
                getAnnotationAttributes(EnableLimit.class.getName());
        LimitType type = (LimitType) annotationMap.get("type");
        String[] importClass;
        switch (type){
            case CUSTOMER:
                importClass = new String[]{LimitProperties.class.getName()};
                break;
            case IP:
                importClass = new String[]{LimitProperties.class.getName()};
                break;
            default:
                importClass = new String[]{LimitProperties.class.getName()};
                break;
        }
//        String limitType = "";
//        try {
//            Class clazz = Class.forName(importClass[0]);
//            Method method = clazz.getMethod("getLimitType");
//            limitType = (String) method.invoke(applicationContext.getBean(LimitProperties.class));
//        } catch (Exception e) {
//            logger.error("错误信息----{}",e.getMessage());
//        }
        logger.info("选择了---->{}限流模式",type);
        return importClass;
    }


}
