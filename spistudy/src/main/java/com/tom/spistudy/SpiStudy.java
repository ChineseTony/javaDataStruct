package com.tom.spistudy;

import com.tom.spistudy.service.SpiService;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author WangTao
 */
public class SpiStudy {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        ServiceLoader<SpiService> serviceLoader = ServiceLoader.load(SpiService.class);
        Iterator<SpiService> it = serviceLoader.iterator();
        while (it.hasNext()){
            SpiService service = it.next();
            System.out.println(service.say("tom"));
        }
        System.out.println(SpiStudy.class.getName());

        ExtendsLoadService<SpiService> extendsLoadService = ExtendsLoadService.load(SpiService.class);
        SpiService service = extendsLoadService.getAdaptiveExtension();
        System.out.println(service.say("hello"));
    }
}
