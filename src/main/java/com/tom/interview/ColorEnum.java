package com.tom.interview;

import java.util.Objects;
import java.util.Optional;

/**
 * @author WangTao
 * 枚举类
 * public final enum com/tom/interview/ColorEnum extends java/lang/Enum
 * 不可继承 无法实例化
 * 查看字节码 本质是数组映射
 * 枚举类不支持 clone
 * 【强制】二方库里可以定义枚举类型，
 * 参数可以使用枚举类型，但是接口返回值不允许使用枚举类型或者包含枚举类型的 POJO 对象。
 *
 * @see java.util.concurrent.TimeUnit
 *
 */
public enum ColorEnum {
    //红色
    RED(1,"红色"),
    //黑色
    BLACK(2,"黑色"),
    //白色
    WHITE(3,"白色"),
    ;
    ColorEnum(int value,String message) {
        this.value = value;
        this.message = message;
    }


    private final String message;

    private int value;

    public String getMessage() {
        return message;
    }

    public static ColorEnum getEnum(int value) {
        for (ColorEnum coinEnum : ColorEnum.values()) {
            if (coinEnum.value == value) {
                return coinEnum;
            }
        }
        return null;
    }



    @Override
    public String toString() {
        return "ColorEnum{" +
                "message='" + message + '\'' +
                ", value=" + value +
                '}';
    }

    public static void main(String[] args) {
        ColorEnum[] values = ColorEnum.values();
        values[0] = ColorEnum.BLACK;
        printColorValues(values);
        System.out.println("=====>");
        printColorValues(ColorEnum.values());
        Optional<ColorEnum>  colorEnum = EnumUtils.getEnum(ColorEnum.class,( colorEnum1 -> colorEnum1.value),1);
        System.out.println(colorEnum.get());
        System.out.println(EnumUtils.getKey(ColorEnum.class,1));

    }

    public static void printColorValues(ColorEnum[] arr){
        for(ColorEnum color:arr){
            System.out.println(color);
        }
    }
}
