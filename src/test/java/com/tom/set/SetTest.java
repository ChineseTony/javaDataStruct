package com.tom.set;

import com.tom.util.FileOperation;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WangTao
 */
public class SetTest {

    private final String prejudice = "src/main/java/com/tom/pride-and-prejudice.txt";
    private final String twoCities = "src/main/java/com/tom/a-tale-of-two-cities.txt";

    @Test
    public void set(){
        System.out.println("Pride and Prejudice");

        List<String> words1 = new ArrayList<>();
        if(FileOperation.readFile(prejudice, words1)) {
            System.out.println("Total words: " + words1.size());

            MySet<String> set1 = new MyLinkedListSet<>();
            for (String word : words1)
                set1.add(word);
            System.out.println("Total different words: " + set1.getSize());

            System.out.println();


            System.out.println("A Tale of Two Cities");

            ArrayList<String> words2 = new ArrayList<>();
            if(FileOperation.readFile(twoCities, words2)){
                System.out.println("Total words: " + words2.size());

                MySet<String> set2 = new MyLinkedListSet<>();
                for(String word: words2)
                    set2.add(word);
                System.out.println("Total different words: " + set2.getSize());
            }
        }

    }
}
