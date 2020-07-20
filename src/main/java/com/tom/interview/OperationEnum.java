package com.tom.interview;

/**
 * @see java.util.concurrent.TimeUnit
 * @see com.alibaba.fastjson.parser.Feature
 * @author WangTao
 */
public enum OperationEnum {
        //加法
        PLUS {
            @Override
            double eval(double x, double y) { return x + y; }
        },
        //减法
        MINUS {
            @Override
            double eval(double x, double y) { return x - y; }
        },
        //乘法
        TIMES {
            @Override
            double eval(double x, double y) { return x * y; }
        },
        //除法
        DIVIDED_BY {
            @Override
            double eval(double x, double y) { return x / y; }
        };

        // Each constant supports an arithmetic operation
        abstract double eval(double x, double y);

        public static void main(String args[]) {
            double x = Double.parseDouble(args[0]);
            double y = Double.parseDouble(args[1]);
            for (OperationEnum op : OperationEnum.values()){
                System.out.println(x + " " + op + " " + y +
                        " = " + op.eval(x, y));
            }
        }
}
