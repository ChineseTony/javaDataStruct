package com.tom.trie;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author WangTao
 * 字典树
 */
public class Trie {

    private Node root;

    private int size;

    public Trie(){
        root = new Node();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    //非递归实现 在字典树添加 字符串
    public void add(String word){
        Node cur = root;
        int length = word.length();
        for (int i = 0; i < length; i++) {
            char c= word.charAt(i);
            //插入到字典树中
            if (cur.next.get(c) == null){
                cur.next.put(c,new Node());
            }
            cur = cur.next.get(c);
        }
        //之前字典树没有这个单词
        if (!cur.isWord){
            cur.isWord = true;
            size++;
        }

    }

    public boolean delete(String word){
        if ("".equals(word)){
            return false;
        }
        return delete(root,word,0);
    }

    private boolean delete(Node node,String word,int index){
        if (index == word.length()){
            if (node.isWord){
                node.isWord = false;
                size--;
                return true;
            }
            return false;
        }
        char c = word.charAt(index);
        //不包含c
        if(!node.next.containsKey(c)) {
            return false;
        }
        boolean ret = delete(node.next.get(c),word,index+1);
        //关键部分 看当前节点是不是叶子节点 递归调用删除
        Node nextNode = node.next.get(c);
        if(!nextNode.isWord && nextNode.next.size() == 0) {
            node.next.remove(word.charAt(index));
        }
        return ret;
    }


    public void addDiGui(String word){
        addDiGui(root,word,0);
    }

    //查找以prefix前缀的单词的
    public boolean isPrefix(String prefix){
        Node cur = root;
        int length = prefix.length();
        for (int i = 0; i < length ; i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null){
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }


    public boolean isPrefixDiGui(String prefix){
        return isPrefixDiGui(root,prefix,0);
    }

    public boolean search(String word) {
        return search(root, word, 0);
    }

    public boolean contains(String word){
        Node cur = root;
        int length = word.length();
        for (int i = 0; i < length; i++) {
            char c= word.charAt(i);
            //插入到字典树中
            if (cur.next.get(c) == null){
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }




    private boolean isPrefixDiGui(Node node,String prefix,int index){
        if (index == prefix.length()){
            return true;
        }
        char c = prefix.charAt(index);

        if (node.next.get(c) == null){
            return false;
        }
        return isPrefixDiGui(node.next.get(c),prefix,index+1);
    }


    // 查看字典树中是否存在单词word，从局部看就是查看一个节点中是否有一个字母；
    private boolean search(Node node, String word, int index) {
        if (index == word.length()) {
            return node.isWord;
        }

        Character c = word.charAt(index);
        if (node.next.get(c) == null) {
            return false;
        }
        return search(node.next.get(c), word, index + 1);
    }


    private void addDiGui(Node node,String word,int index){
        if (index == word.length()){
            //递归出口
            if (!node.isWord){
                node.isWord=true;
                size++;
                return;
            }
            return;
        }
        Character c = word.charAt(index);
        if (node.next.get(c) == null){
            node.next.put(c,new Node());
        }
        addDiGui(node.next.get(c), word, index + 1);
    }


    /**
     * searchRegx(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 .
     * 可以表示任何一个字母。
     * @param word
     * @return
     */
    public boolean searchRegx(String word) {
        return match(root,word,0);
    }


    private boolean match(Node node,String word,int index){
        if (index == word.length()){
            return node.isWord;
        }
        char c = word.charAt(index);
        if (c != '.') {
            if (node.next.get(c) == null){
                return false;
            }
            return match(node.next.get(c),word,index+1);
        }else {
            //循环遍历下一个节点值的
            for(char nextChar: node.next.keySet()){
                if (match(node.next.get(nextChar),word,index+1)){
                    return true;
                }
            }
            return false;
        }
    }



    public String longestWord(String[] words) {
        Trie trie = new Trie();
        for (String s:words){
            trie.add(s);
        }


        return "";
    }

    public List<String> getLastWord(){
        List<String> tmp = new ArrayList<>();
        getLastWord(root,tmp, "");
        tmp.sort( (s1,s2) -> {
            int length1 = s1.length();
            int length2 = s2.length();
            if (length1 > length2){
                return -1;
            }else if (length1 < length2){
                return 1;
            }else {
                return s1.compareTo(s2);
            }

        });
        return tmp;
    }

    private void getLastWord(Node node,List<String> tmp,String s ){
        if (node == null || (node != root && !node.isWord)){
            return ;
        }else if (node.next.size() == 0){
            tmp.add(s);
        }else {
            Node tmpNode = node;
            for (Map.Entry<Character,Node> entry:tmpNode.next.entrySet()){
                getLastWord(entry.getValue(),tmp,s + entry.getKey());
            }
        }
    }


    private class Node{
        //标志是不是单词结尾
        public boolean isWord;
        public Map<Character,Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }

    }
}
