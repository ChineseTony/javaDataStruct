package com.tom.webflux;


import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.tom.webflux.controller.MoonController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author WangTao
 */
@Configuration
public class AllRouters {

    @Bean
    RouterFunction<ServerResponse> userRouter(MoonController handler) {
        return nest(
                // 相当于类上面的 @RequestMapping("/user")
                path("/user"),
                // 下面的相当于类里面的 @RequestMapping
                // 得到所有用户
                route(GET("/"), handler::getAllUser));
                        // 创建用户;
    }
}
