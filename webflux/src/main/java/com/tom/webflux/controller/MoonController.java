package com.tom.webflux.controller;

import com.tom.webflux.User;
import com.tom.webflux.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import java.util.stream.IntStream;


/**
 * @author WangTao
 */

@RestController()
public class MoonController {

    private final UserRepository repository;

    public MoonController(UserRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/test1")
    public String test1(){
        return createString();
    }

    @GetMapping("/test2")
    public Mono<String> test2(){
        Mono<String> stringMono = Mono.fromSupplier(this::createString);
        return stringMono;
    }

    public Mono<ServerResponse>
                getAllUser(ServerRequest request) {
        return ok().contentType(APPLICATION_JSON_UTF8)
                .body(this.repository.findAll(), User.class);
    }



    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(
            @PathVariable("id") String id) {
        // deletebyID 没有返回值, 不能判断数据是否存在
        // this.repository.deleteById(id)
        return this.repository.findById(id)
                // 当你要操作数据, 并返回一个Mono 这个时候使用flatMap
                // 如果不操作数据, 只是转换数据, 使用map
                .flatMap(user -> this.repository.delete(user).then(
                        Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



    @GetMapping(value = "/flux",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> flux(){
        Flux<String> tmp = Flux.fromStream(IntStream.range(1,10).mapToObj(i -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "flux data--->"+i;
        }));
        return tmp;
    }

    private String createString(){
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "webflux";
    }
}
