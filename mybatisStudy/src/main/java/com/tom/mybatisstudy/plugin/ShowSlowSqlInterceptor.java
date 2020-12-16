package com.tom.mybatisstudy.plugin;


import java.sql.Statement;

import cn.hutool.core.date.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.executor.statement.StatementHandler;

import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;


@Intercepts({
        @Signature(
                type = StatementHandler.class, method = "query"
                , args = {Statement.class, ResultHandler.class}),
        @Signature(
                type = StatementHandler.class, method = "update"
                ,args = {Statement.class}
        ),
        @Signature(
                type = StatementHandler.class, method = "batch"
                ,args = {Statement.class}
        )
        }
)

/**
 * @author wangtao 自定义插件
 */
public class ShowSlowSqlInterceptor implements Interceptor {

    private static final Logger logger = LoggerFactory.
            getLogger(ShowSlowSqlInterceptor.class);

    private int limitTime;



    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        long startTime = System.currentTimeMillis();
        StatementHandler handler = (StatementHandler)invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(handler);
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        try {
            return invocation.proceed();
        }finally {
            long endTime = System.currentTimeMillis();
            long times = (endTime - startTime);
            if (times > limitTime * 1000){
                BoundSql boundSql = handler.getBoundSql();
                List<ParameterMapping> parameterMappings = new ArrayList<>(boundSql.getParameterMappings());
                Object parameterObject = boundSql.getParameterObject();
                String sql = boundSql.getSql();
                if (parameterMappings.isEmpty() && parameterObject == null) {
                    logger.info("parameterMappings is empty or parameterObject is null");
                }else {
                    Configuration configuration = mappedStatement.getConfiguration();
                    TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
                    String parameter = "null";
                    MetaObject newMetaObject = configuration.newMetaObject(parameterObject);
                    for (ParameterMapping parameterMapping : parameterMappings) {
                        if (parameterMapping.getMode() == ParameterMode.OUT) {
                            continue;
                        }
                        String propertyName = parameterMapping.getProperty();
                        if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                            parameter = getParameterValue(parameterObject);
                        } else if (newMetaObject.hasGetter(propertyName)) {
                            parameter = getParameterValue(newMetaObject.getValue(propertyName));
                        } else if (boundSql.hasAdditionalParameter(propertyName)) {
                            parameter = getParameterValue(boundSql.getAdditionalParameter(propertyName));
                        }
                        sql = sql.replaceFirst("\\?", parameter);
                    }
                }
                logger.info("执行sql--->{},花费{}ms",sql,times);
            }
        }

    }

    private static String getParameterValue(Object param) {
        if (param == null) {
            return "null";
        }
        if (param instanceof Number) {
            return param.toString();
        }
        String value = null;
        if (param instanceof String) {
            value = param.toString();
        } else if (param instanceof Date) {
            DateUtil.format((Date) param, "yyyy-MM-dd HH:mm:ss");
        } else {
            value = param.toString();
        }
        return value;
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {
        String limitTime = properties.getProperty("limitTime");
        this.limitTime = Math.max(Integer.parseInt(limitTime), 0);
    }
}
