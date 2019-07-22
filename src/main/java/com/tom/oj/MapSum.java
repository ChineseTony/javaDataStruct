package com.tom.oj;

import java.util.TreeMap;

/**
 * @author WangTao
 *
 * 实现一个 MapSum 类里的两个方法，insert 和 sum。
 *
 * 对于方法 insert，你将得到一对（字符串，整数）的键值对。字符串表示键，整数表示值。如果键已经存在，那么原来的键值对将被替代成新的键值对。
 *
 * 对于方法 sum，你将得到一个表示前缀的字符串，你需要返回所有以该前缀开头的键的值的总和。
 *
 * 示例 1:
 *
 * 输入: insert("apple", 3), 输出: Null
 * 输入: sum("ap"), 输出: 3
 * 输入: insert("app", 2), 输出: Null
 * 输入: sum("ap"), 输出: 5
 *
 */
public class MapSum {

    private Node root;

    public MapSum() {
        root = new Node();
    }

    public void insert(String key, int val) {
        Node cur = root;
        int length = key.length();
        for (int i = 0; i < length; i++) {
            char c = key.charAt(i);
            if (cur.next.get(c) == null){
                cur.next.put(c,new Node());
            }
            cur = cur.next.get(c);
        }
        cur.value = val;
    }

    public int sum(String prefix) {
        Node cur = root;
        int length = prefix.length();
        for (int i = 0; i < length; i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null){
                return 0;
            }
            cur = cur.next.get(c);
        }
        //找到cur前缀为prefix的值 递归调用
        return sum(cur);
    }

    private int sum(Node node){

        int res = node.value;
        for(char c: node.next.keySet()) {
            res += sum(node.next.get(c));
        }
        return res;
    }

    private class Node{

        public int value;
        public TreeMap<Character, Node> next;

        public Node(int value){
            this.value = value;
            next = new TreeMap<>();
        }

        public Node(){
            this(0);
        }
    }
}
