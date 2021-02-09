package com.tom.queue;


import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * @author wangtao
 */
public class CountStudents {

    public static int countStudents(int[] students, int[] sandwiches) {
        int len = students.length;
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            queue.offer(students[i]);
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = len-1; i >= 0; i--) {
            stack.push(sandwiches[i]);
        }
        int count = 0;
        while (!queue.isEmpty() && !stack.isEmpty()){
            int front = queue.poll();
            if(front == stack.peek()){
                stack.pop();
            }else {
                queue.offer(front);
            }
            count ++;
            //灵机一动，循环1000次以上 还没结束 说明死循环了
            if (count > 1000) {
                return queue.size();
            }
        }

        return 0;

    }

    public int countStudents2(int[] students, int[] sandwiches) {
        int[] cnt = new int[2];
        for (int student : students){
            cnt[student]++;
        }
        for (int sandwich : sandwiches) {
            if (cnt[sandwich] > 0) {
                cnt[sandwich]--;
            }else{
                //  学生没有跟栈元素相同的 跳出循环
                break;
            }
        }
        return cnt[0] + cnt[1];
    }

    public static void main(String[] args) {
        // 1 1  0  1
        // 0 1 1
        int[] students =new int[]{1,1,1,0,0,1};
        int[] sandwiches =new int[]{1,0,0,0,1,1};
        System.out.println(countStudents(students,sandwiches));
    }
}
