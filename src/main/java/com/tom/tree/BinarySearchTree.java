package com.tom.tree;

import java.util.*;

/**
 *
 * @author WangTao
 * 二分搜索树
 * 中序遍历为有序序列
 */
@SuppressWarnings("unchecked")
public class BinarySearchTree<Key extends Comparable,Value> {


    private Node root;

    private int lenght;

    public BinarySearchTree(){
        root = null;
        lenght = 0;
    }

    public int getLenght() {
        return lenght;
    }

    public boolean isEmpty(){
        return  lenght == 0;
    }


    public Value search(Key key){
        return search(root,key);
    }


    public void insertBSF(Key  key,Value value){
        root = insertBSF(root, key,value);
    }

    public Value getMin(){
        if (lenght == 0){
            throw new RuntimeException("tree is empty");
        }
        return getMin(root);
    }



    public Value getMax(){
        if (lenght == 0){
            throw new RuntimeException("tree is empty");
        }
        return getMax(root);
    }

    public Value getCommonParent(Key u,Key v){
        return getCommonParent(root,u,v);
    }


    //求二分搜索树的最近公共祖先元素
    private Value getCommonParent(Node root,Key u,Key v){
        if (root == null){
            return null;
        }
        while(root!=null){
            Key k = root.key;
            //p.q都大于root
            if(u.compareTo(k) > 0 && v.compareTo(k)>0){
                root=root.right;
            }else if(u.compareTo(k) < 0 && v.compareTo(k)<0){
                root=root.left;
            }
            else{
                return root.value;
            }
        }
        return null;
    }

    public Value removeMin(){
        if (lenght == 0){
            throw new RuntimeException("tree is empty");
        }
        Value min = getMin();
        root = removeMin(root);
        return min;
    }

    public Value removeMax(){
        if (lenght == 0){
            throw new RuntimeException("tree is empty");
        }
        Value max = getMax();
        root = removeMax(root);
        return max;
    }

    //判断keys 是不是二分搜索树的后序遍历
    public  boolean veryfiySquenceofBST(Key[] keys){
        if (keys == null){
            return false;
        }
        if (keys.length == 1){
            return true;
        }
        return veryfiySquenceofBST(keys,0,keys.length-1);
    }


    // 判断后序遍历数组是不是有二分搜索树
    public  boolean veryfiySquenceofBST(Key[] keys,int start,int end){
        if(start >= end){
            return true;
        }
        int i = start;
        //招到比根节点大的数
        while(keys[i].compareTo(keys[end]) < 0){
            ++i;
        }
        //判断所有右子树是不是大于 根节点
        for(int j=i;j<end;j++){
            if(keys[j].compareTo(keys[end])<0){
                return false;
            }
        }
        //递归调用 左右子树
        return veryfiySquenceofBST(keys,start,i-1) && veryfiySquenceofBST(keys,i,end-1);
    }



    //
    private Node pre=null;
    //保存最后一个节点
    private Node lastLeft=null;
    /**
     * 将二分搜索树转换为双向链表
     * @param node
     * @return
     */
    private Node convert(Node node) {
        if(node==null){
            return null;
        }
        convert(node.left);
        //改变指针
        node.left=pre;
        if(pre!=null){
            pre.right=node;
        }
        //保存之前的节点
        pre=node;
        //保存最后一个节点
        lastLeft=lastLeft==null?node:lastLeft;
        convert(node.right);
        //返回值
        return lastLeft;
    }

    public Node convertToList(){
        if (root == null){
            return null;
        }
        Node temp = convert(root);
        while (temp != null && temp.left != null){
            temp = temp.left;
        }
        //返回第一个节点
        return temp;
    }

    //遍历转换之后的双向链表
    public void travle(){
        Node first = convertToList();
        while (first!=null){
            System.out.print(first.value+"--->");
            first = first.right;
        }
        System.out.println();
    }





    //中序遍历递归调用
    public void order(){
        order(root);
    }

    //后序遍历递归实现
    public void postOrder(){
        postOrder(root);
    }

 /*   public void reverse(){
        reverse(root);
    }*/

    //前序遍历
    public void inder(){
        inder(root);
    }

    //前序非递归实现
    public void inderBSF(){
        inderBSF(root);
    }

    //层次遍历
    public void level(){
        level(root);
    }

    //中序遍历非递归实现
    public void  travelBSF(){
        travelBSF1(root);
    }


    //返回的value暂时还有问题 删除算法已经成功
    public Value delete(Key key){
        Node p = root;
        Node pParent = null;
        Value value = null;
        while (p != null && !p.key.equals(key)){
            pParent = p;
            if (key.compareTo(p.key) > 0){
                p = p.right;
            }else {
                p = p.left;
            }
        }
        if (p == null){
            return null;
        }
        if (p.left != null && p.right != null){
            Node minParent = p;
            Node min = p.right;
            while (min.left != null){
                minParent = min;
                min = min.left;
            }
            value = p.value;
            p.key = min.key;
            p.value = min.value;
            pParent = minParent;
            p = min;
        }
        value = p.value;

        // 删除的是根节点
        if (pParent == null){
            root = null;
        }
        else if(pParent.left == p) {
            pParent.left = p.left;
            p.left = null;
        }else{
            pParent.right = p.right;
            p.right = null;
        }

        lenght--;
        return value;
    }

    public Value deleteMin(){
        if (root == null){
            throw new RuntimeException("tree is empty");
        }
        Value value;
        if (root.left == null && root.right == null){
            value = root.value;
            root = null;
        }else if (root.right != null && root.left == null){
            Node temp = root;
            value = temp.value;
            root = root.right;
            //从树中断开
            temp.right = null;
        }else {
            Node pre = root;
            Node cur = root.left;
            while (cur.left != null) {
                pre = cur;
                cur = cur.left;
            }

            if (cur.right != null) {
                value = cur.value;
                pre.left = cur.right;
                cur.right = null;
            } else {
                value = cur.value;
                pre.left = null;
            }
        }
        lenght--;
        return value;
    }

    public void remove(Key key){
        root = remove(root,key);
    }

    //之字形遍历树
    public void zTravle(){
        zTravle(root);
    }

    private void zTravle(Node root){
        if (root == null){
            return;
        }
        Stack<Node>[] stacks = new Stack[2];
        int cur = 0;
        int next = 1;
        stacks[cur].push(root);
        while (!stacks[cur].isEmpty() || !stacks[next].isEmpty()){
            Node tmp = stacks[cur].pop();
            System.out.println(tmp.value);
            if (cur == 0){
                //偶数层是从右往左 根据栈的特性
                if (tmp.left != null){
                    stacks[next].push(tmp.left);
                }
                if (tmp.right != null){
                    stacks[next].push(tmp.right);
                }
            }else {
                if (tmp.right != null){
                    stacks[next].push(tmp.right);
                }
                if (tmp.left != null){
                    stacks[next].push(tmp.left);
                }
            }
            //当前栈为空 互换
            if (stacks[cur].isEmpty()){
                cur = 1 - cur;
                next = 1 - next;
            }

        }

    }


    //找到从根到叶子节点值为expectedSum 输出出来
    public void findPath(int expectedSum){
        if (root == null){
            return;
        }
        int currentSum=0;
        List<Integer> list = new ArrayList<>();
        findPath(root,list,currentSum,expectedSum);
    }

    private void findPath(Node root,List path,int currentSum,int expectedSum){
//        currentSum += root.value;
        boolean isLeaf = root.left == null && root.right == null;
        //是叶子节点 并且 根到叶子节点的值和expectedSum 相等
        if (currentSum == expectedSum && isLeaf){
//            打印
            for (Object o:path) {
                System.out.print(o+"\t");
            }
            System.out.println();
        }
        if (root.left != null){
            findPath(root.left,path,currentSum,expectedSum);
        }
        if (root.right != null){
            findPath(root.right,path,currentSum,expectedSum);
        }
        //回溯删除最后一个元素
        path.remove(path.size()-1);

    }


    private Node remove(Node node,Key key){
            if (node == null){
                return null;
            }
            if (key.compareTo(node.key) < 0){
                node.left = remove(node.left,key);
                return node;
            }else if (key.compareTo(node.key) > 0){
                node.right = remove(node.right,key);
                return node;
            }else {
                //删除 节点值为key 的值
                if (node.left == null){
                    Node rightNode = node.right;
                    node.right = null;
                    lenght--;
                    return rightNode;
                }
                if (node.right == null){
                    Node leftNode = node.left;
                    node.left = null;
                    lenght--;
                    return leftNode;
                }
                Node succsor = getM(root.right);
                succsor.right = removeMin(node.right);
                succsor.left = node.left;
                node.left = node.right = null;
                return succsor;
            }
    }


    private Node getM(Node node){
        if (node.left == null){
            return node;
        }
        return getM(node.left);
    }


    private Value getMin(Node node){
        if (node.left == null){
            return node.value;
        }
        Node tmp = node.left;
        while (tmp.left != null){
            tmp = tmp.left;
        }
        return tmp.value;
    }



    //层次遍历
    private void level(Node node){
        if (node == null){
            return;
        }
        Queue<Node> queue = new ArrayDeque<>(10);
        queue.offer(node);
        while (!queue.isEmpty()){
            Node node1 = queue.poll();
            System.out.format("key:{%s}------->value:{%s}\n",node1.key,node1.value);
            if (node1.left != null){
                queue.offer(node1.left);
            }
            if (node1.right != null){
                queue.offer(node1.right);
            }
        }
    }



    private void travelBSF1(Node node){
        if (node != null){
            Stack<Node> stack = new Stack<>();
            Node p = node;
            while (!stack.isEmpty() || p != null){
                while (p  != null){
                    stack.push(p);
                    p = p.left;
                }
                if (!stack.isEmpty()){
                    p = stack.pop();
                    System.out.println(p.key+"----->"+p.value);
                    p = p.right;
                }
            }
        }
    }

    private Value search(Node node,Key key){
        if (node == null){
            return null;
        }
        if (key.compareTo(node.key) < 0){
            return search(node.left,key);
        }else if (key.compareTo(node.key) > 0){
            return search(node.right,key);
        }else {
            return node.value;
        }
    }


    private void inder(Node node){
        if (node != null){
            System.out.println(node.key+"----->"+node.value);
            order(node.left);
            order(node.right);
        }
    }

    private void inderBSF(Node node){
        if (node != null){
            Stack<Node> stack = new Stack<>();
            Node p = node;
            stack.push(p);
            while (!stack.isEmpty()){
                p = stack.pop();
                System.out.println(p.key+"----->"+p.value);
                if (p.right != null){
                    stack.push(p.right);
                }
                if (p.left != null){
                    stack.push(p.left);
                }
            }

        }
    }

    private void postOrder(Node node){
        if (node != null){
            postOrder(node.left);
            postOrder(node.right);
            System.out.format("key:{%s}------->value:{%s}\n",node.key,node.value);
        }

    }


    private Node removeMax(Node node){
        if (node.right == null){
            Node leftNode = node.left;
            node.left = null;
            lenght --;
            return leftNode;
        }
        node = removeMax(node.right);
        return node;
    }

    private Node removeMin(Node node){
        if (node.left == null){
            Node rightNode = node.right;
            node.right = null;
            lenght --;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public List<Node> findFarestLeaf(){
        List<Node> path = new ArrayList<>();
        List<Node> longestPath = findFarestLeaf(root, path);
        return longestPath;
    }

    private List<Node> findFarestLeaf(Node root,List<Node> path){
        if (root == null){
            return path;
        }
        List<Node> curr = new ArrayList<>();
        curr.addAll(path);
        curr.add(root);
        List<Node> left = findFarestLeaf(root.left, path);
        List<Node> right = findFarestLeaf(root.right, path);
        return left.size() > right.size() ? left : right;
    }

    public boolean iscousins(Key x,Key y){
        return iscousins(this.root,x,y);
    }

    //判读 x和y是不是 堂兄弟
    private boolean iscousins(Node root,Key x,Key y){
        if (root == null){
            return false;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            boolean xExist = false;
            boolean yExist = false;
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                assert cur != null;
                if (x.equals(cur.key)){
                    xExist = true;
                }
                if (y.equals(cur.key)){
                    yExist = true;
                }
                //互为兄弟节点
                if (cur.left != null && cur.right != null){
                    if (cur.left.key.equals(x) && cur.right.key.equals(y)){
                        return false;
                    }
                    if (cur.left.key.equals(y) && cur.right.key.equals(x)){
                        return false;
                    }
                }
                if (cur.left != null){
                    queue.offer(cur.left);
                }
                if (cur.right != null){
                    queue.offer(cur.right);
                }
//                节点在同一层 并且不是兄弟节点
                if (xExist && yExist){
                    return true;
                }
            }
        }
        return false;

    }

    //判断tree1是不是tree2的子结构
    public boolean HasSubTree(Node tree1,Node tree2){
        boolean result = false;
        if (tree1 != null && tree2 != null){
            if (tree1.value == tree2.value){
                result = Tree1HavaTree2(tree1,tree2);
            }
            if (!result){
                HasSubTree(tree1.left,tree2);
            }
            if (!result){
                HasSubTree(tree2.left,tree2);
            }
        }
        return result;
    }


    private boolean Tree1HavaTree2(Node root1,Node root2){
        if (root2 == null){
            return true;
        }
        if (root1 == null){
            return false;
        }
        if (root1.value != root2.value){
            return false;
        }
        return Tree1HavaTree2(root1.left,root2.left)
                && Tree1HavaTree2(root2.right,root2.right);
    }






    private Value getMax(Node node){
        if (node.right == null){
            return node.value;
        }
        Node tmp = node.right;
        while (tmp.right != null){
            tmp = tmp.right;
        }
        return tmp.value;
    }


    private Node insertBSF(Node node,Key  key,Value value){
        if(node == null){
            lenght++;
            node = new Node(key,value);
            return node;
        }
        if((node.key.compareTo(key)) > 0){
            node.left  =   insertBSF(node.left,key,value);
        }else if ((node.key.compareTo(key)) < 0){
            node.right = insertBSF(node.right,key,value);
        } else {
            node.value = value;
        }
        return node;

    }

/*    private void reverse(Node node){
        if (node == null ){
            return;
        }
        if (node.left == null && node.right == null){
            return;
        }
        Node temp = node.right;
        node.right = temp.left;
        node.left = temp;
        reverse(node.left);
        reverse(node.right);
    }*/


    private void order(Node node){
        if (node != null){
            order(node.left);
            System.out.println(node.key+"----->"+node.value);
            order(node.right);
        }
    }


    private class Node{
        Key key;
        Value value;
        Node left;
        Node right;

        public Node(Key key,Value value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }

        public Node(Key key){
            this.key = key;
            left = null;
            right = null;
        }

    }
}
