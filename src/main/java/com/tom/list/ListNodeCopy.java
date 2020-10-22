package com.tom.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListNodeCopy {

    public Node copyRandomList(Node head) {
        if(head == null){
            return null;
        }
        List<Node> tmp = new ArrayList<>();
        Node p = head;
        List<Node> random = new ArrayList<>();
        List<Node> all = new ArrayList<>();
        while (p != null){
            Node node = new Node(p.val);
            tmp.add(node);
            all.add(p);
            random.add(p.random);
            p = p.next;
        }
        int len = tmp.size();
        for (int i = 0; i < len; i++) {
            //连接
            if (i != len-1){
                tmp.get(i).next = tmp.get(i+1);
            }
            if (random.get(i) != null){
                int index = all.indexOf(random.get(i));
                tmp.get(i).random = tmp.get(index);
            }
        }
        return tmp.get(0);
    }

    public Node copyRandomList2(Node head) {
        if(head == null){
            return null;
        }
        Node node  = head;
        Map<Node,Node> map = new HashMap<>();
        while(node != null){
            Node clone = new Node(node.val);
            map.put(node,clone);
            node = node.next;
        }
        node = head;
        while(node != null){
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }
        return map.get(head);
    }
}


class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
