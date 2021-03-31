package com.tom.meituan;


import java.util.Scanner;

/**
 * 时间限制： 3000MS
 * 内存限制： 1048576KB
 * 题目描述：
 * 小美得到了一款游戏——斗龙。小美拥有两个技能，每个技能都能秒杀掉一条龙，但是要付出相应的MP值，第一个技能需要c1点MP值，第二个技能需要c2点MP值。只要MP足够，小美可以使用无限次技能。
 *
 * 小美即将遇到 n 条龙，如果不使用技能，她和第 i 条龙的战斗结果是T或者F，而如果使用任何一个技能战斗结果都是T。T表示小美成功打败龙，而F表示小美被龙打败。如果小美被龙连续打败三次，那小美就会输掉游戏。请你帮忙计算小美最少需要多少点 MP才能通关。
 *
 *
 *
 * 输入描述
 * 第一行三个数 n, c1, c2。(1 ≤ n ≤ 100000，1 ≤ c1, c2 ≤ 1000000000)。
 *
 * 第二行 n 个字符，第 i 个字符 si 代表小美与第 i 场战斗的结果。si 是 T 代表小美打败龙，si 是 F 代表小美被龙打败。
 *
 * 输出描述
 * 输出一个数，代表小美最少需要的MP值。
 *
 *
 * 样例输入
 * 10 7 3
 * FTFFFTFFFF
 * 样例输出
 * 6
 */
public class Meituan2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int counts = scanner.nextInt();
        int c1 = scanner.nextInt();
        int c2 = scanner.nextInt();
        String tmp = scanner.next();
//        统计没有技能 FFF大于3的情况，插入数据中计算 MP

    }
}
