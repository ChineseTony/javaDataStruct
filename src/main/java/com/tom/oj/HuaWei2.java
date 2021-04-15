package com.tom.oj;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HuaWei2 {

    private static int min = 0;
    private static List<List<Integer>> tmp = new ArrayList<>();

    public static int jump(int[] nums) {
        int position = nums.length - 1;
        int steps = 0;
        while (position > 0) {
            for (int i = 0; i < position; i++) {
                if (i + nums[i] >= position) {
                    position = i;
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        int[] arr = new int[count];

        for (int j = 0; j < count; j++) {
            arr[j] = scanner.nextInt();
        }
        System.out.println(jump(arr));

    }
}