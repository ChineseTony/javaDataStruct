package com.tom.map;

import com.tom.list.MyLinkedListMap;
import com.tom.util.FileOperation;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author WangTao
 */
public class MyLinkedListMapTest {
    private final String prejudice = "src/main/java/com/tom/pride-and-prejudice.txt";

    @Test
    public void countWord(){
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(prejudice, words)) {
            System.out.println("Total words: " + words.size());

            MyMap<String, Integer> map = new MyLinkedListMap<>();
//            MyMap<String, Integer> map = new MyBinarySearchTreeMap<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        System.out.println();
    }
}
