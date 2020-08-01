package com.tom.springredis.resolver;

import com.tom.springredis.annotations.LoginRequired;
import com.tom.springredis.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;




public class LoginRequiredArgumentResolver implements HandlerMethodArgumentResolver {
    private Logger log = LoggerFactory.getLogger(LoginRequiredArgumentResolver.class);

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(LoginRequired.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        LoginRequired loginRequired = methodParameter.getParameterAnnotation(LoginRequired.class);
        String sessionKey = loginRequired.sessionKey();
        Object object = RedisUtil.containsKey(sessionKey);
        if (object == null) {
            log.error("接口 {} 非法调用！", methodParameter.getMethod().toString());
            throw new RuntimeException("请先登录！");
        }
        return object;
    }
}
