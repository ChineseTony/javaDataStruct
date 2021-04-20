package com.tom.oj;


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author tom
 * @link https://pintia.cn/problem-sets/15/problems/714
 */
public class Graph {
    private static final int MAX_LEN = 10;
    private static  int edge;
    private static  int nNumber;
    private static boolean[] visited = new boolean[MAX_LEN];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int v = scanner.nextInt();
        nNumber = n;
        edge = v;
        int[][] graph = new int[n][n];
        //创建图
        for (int i = 0; i < v; i++) {
            int j = scanner.nextInt();
            int k = scanner.nextInt();
            graph[j][k] = 1;
            graph[k][j] = 1;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]){
                System.out.print("{");
                dfs(graph,i);
                System.out.print(" }");
                System.out.println();
            }
        }
        for (int i = 0; i < MAX_LEN; i++) {
            visited[i] = false;
        }
        System.out.println("#####");
        for (int i = 0; i < n; i++) {
            if (!visited[i]){
                System.out.print("{");
                bfs(graph,i);
                System.out.print(" }");
                System.out.println();
            }
        }

    }


    private static void dfs(int[][] graph,int i){
        visited[i] = true;
        System.out.print(" "+i);
        for (int j = 0; j < nNumber; j++) {
            if (!visited[j] && graph[i][j] != 0){
                dfs(graph,j);
            }
        }
    }

    private static void bfs(int[][] graph,int i){
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(i);
        visited[i] = true;
        while (!queue.isEmpty()){
            int index = queue.poll();
            System.out.print(" "+index);
            for (int j = 0; j < nNumber; j++) {
                if (!visited[j] && graph[index][j] != 0){
                    visited[j] = true;
                    queue.offer(j);
                }
            }
        }

    }
}
