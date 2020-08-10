package com.tom.spistudy;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;

/**
 * @author WangTao
 */
public class ExtendsLoadService<T> {

    private static final String PREFIX = "META-INF/tom/";

    //默认加载
    private static String cachedDefaultName;

    private final Class<T> service;

    private  volatile T instance;

    private static final Pattern NAME_SEPARATOR = Pattern.compile("\\s*[,]+\\s*");

    private static Set<String> cachedNames = new HashSet<>();

    private final ClassLoader loader;


    private ConcurrentMap<String,Class<?>> cachedClass = new ConcurrentHashMap<>(16);

    private static final ConcurrentMap<Class<?>, ExtendsLoadService<?>>
            CACHED_EXTENDS_LOAD_SERVICE = new ConcurrentHashMap<>(16);


    private ExtendsLoadService(Class<T> svc) {
        this.service = svc;
        loader =  ClassLoader.getSystemClassLoader();
    }



    public static <T> ExtendsLoadService<T> load(Class<T> service) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        return ExtendsLoadService.load(service, cl);
    }

    public static <S> ExtendsLoadService<S> load(Class<S> service,
                                            ClassLoader loader){
        return getExtensionLoader(service);
    }



    private void loadFile() throws Exception {
        String fileName = PREFIX + service.getName();
        Enumeration<URL> iterable =  ClassLoader.getSystemResources(fileName);
        if (iterable != null) {
            while (iterable.hasMoreElements()) {
                URL url = iterable.nextElement();
                try(BufferedReader  br = new BufferedReader (new InputStreamReader(url.openStream()))){
                    String str = null;
                    while((str = br.readLine()) != null) {
                        if (StringUtils.isEmpty(str) || !str.contains("=") ||
                                str.charAt(0) == '#'){
                            continue;
                        }
                        String key = str.split("=")[0].trim();
                        String clazzName = str.split("=")[1].trim();
                        boolean flag = StringUtils.isEmpty(cachedDefaultName)
                                ||  (!StringUtils.isEmpty(cachedDefaultName) && key.equals(cachedDefaultName));
                        if (flag){
                            Class<?> clazz = Class.forName(clazzName, true, loader);
                            //没有实现service接口
                            if (!service.isAssignableFrom(clazz)) {
                                throw new IllegalStateException("Error when load extension class(interface: " +
                                        service + ", class line: " + clazz.getName() + "), class "
                                        + clazz.getName() + "is not subtype of interface.");
                            }
                            if (!cachedClass.containsKey(key)) {
                                cachedClass.put(key, clazz);
                            }
                        }
                    }

                }
            }

        }

    }



    private static <T> ExtendsLoadService<T> getExtensionLoader(Class<T> type) {
        if (type == null){
            throw new IllegalArgumentException("Extension type == null");
        }

        // 必须是接口
        if (!type.isInterface()) {
            throw new IllegalArgumentException("Extension type(" + type + ") is not interface!");
        }
        // 必须包含 @MySPI 注解
        if (!withExtensionAnnotation(type)) {
            throw new IllegalArgumentException("Extension type(" + type +
                    ") is not extension, because WITHOUT @" + MySPI.class.getSimpleName() + " Annotation!");
        }
        cachedDefaultName =type.getAnnotation(MySPI.class).value();

        if (StringUtils.isEmpty(cachedDefaultName)) {
            throw new IllegalArgumentException("Extension type(" + type +
                    ") is  extension, but WITHOUT @" + MySPI.class.getSimpleName() + " must have value!");
        }

        // 获得接口对应的拓展点加载器
        ExtendsLoadService<T> loader = (ExtendsLoadService<T>) CACHED_EXTENDS_LOAD_SERVICE.get(type);
        if (loader == null) {
            CACHED_EXTENDS_LOAD_SERVICE.putIfAbsent(type, new ExtendsLoadService<>(type));
            loader = (ExtendsLoadService<T>) CACHED_EXTENDS_LOAD_SERVICE.get(type);
        }
        return loader;
    }

    private static <T> boolean withExtensionAnnotation(Class<T> type) {
        return type.isAnnotationPresent(MySPI.class);
    }

    private Map<String, Class<?>> loadExtensionClasses() throws Exception {
        // 通过 @SPI 注解，获得默认的拓展实现类名
        final MySPI defaultAnnotation = service.getAnnotation(MySPI.class);
        if (defaultAnnotation != null) {
            cachedDefaultName = defaultAnnotation.value();
        }
        // 从配置文件中,加载
        loadFile();
        return cachedClass;
    }

    public T getAdaptiveExtension() throws IllegalAccessException, InstantiationException {
        T t = (T) cachedClass.get(cachedDefaultName);
        if (t  == null){
            try {
                loadExtensionClasses();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return  (T) cachedClass.get(cachedDefaultName).newInstance();

    }
}
