package com.tom.interview;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class EnumUtils {

    private static final Map<Object, Object> key2EnumMap = new ConcurrentHashMap<>();

    private static final Set<Class> enumSet = ConcurrentHashMap.newKeySet();


    /**
     * 带缓存的获取枚举值方式
     *
     * @param enumType    枚举类型
     * @param keyFunction 根据枚举类型获取key的函数
     * @param key         带匹配的Key
     * @param <T>         枚举泛型
     * @return 枚举类型
     */
    public static <T extends java.lang.Enum<T>> Optional<T>
            getEnumWithCache(Class<T> enumType, Function<T, Object> keyFunction, Object key) {

        if (!enumSet.contains(enumType)) {
            // 不同的枚举类型相互不影响
            synchronized (enumType) {
                if (!enumSet.contains(enumType)) {
                    // 添加枚举
                    enumSet.add(enumType);
                    // 缓存枚举键值对
                    for (T enumThis : enumType.getEnumConstants()) {
                        // 避免重复
                        String mapKey = getKey(enumType, keyFunction.apply(enumThis));

                        key2EnumMap.put(mapKey, enumThis);
                    }

                }
            }
        }
        return Optional.ofNullable((T) key2EnumMap.get(getKey(enumType, key)));
    }

    /**
     * 获取key
     * 注：带上枚举路径避免不同枚举的Key 重复
     */
    public static <T extends java.lang.Enum<T>> String getKey(Class<T> enumType, Object key) {

        return enumType.getName().concat(key.toString());
    }

    /**
     * 不带缓存的获取枚举值方式
     *
     * @param enumType    枚举类型
     * @param keyFunction 根据枚举类型获取key的函数
     * @param key         带匹配的Key
     * @param <T>         枚举泛型
     * @return 枚举类型
     */
    public static <T extends java.lang.Enum<T>> Optional<T>
        getEnum(Class<T> enumType, Function<T, Object> keyFunction, Object key) {
            for (T enumThis : enumType.getEnumConstants()) {
                if (keyFunction.apply(enumThis).equals(key)) {
                    return Optional.of(enumThis);
                }
            }
            return Optional.empty();
    }
}