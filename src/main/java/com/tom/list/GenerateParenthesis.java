package com.tom.list;




import java.util.ArrayList;
import java.util.List;

/**
 * @author tom
 */
public class GenerateParenthesis {


    public static List<String> generateParenthesis(int n) {
        List<String> tmp = new ArrayList<>();
        if (n <= 0){
            return  tmp;
        }
        StringBuilder trace = new StringBuilder();
        dfs(n,n,trace, tmp);
        return tmp;
    }

    private static void dfs(int left,int right,StringBuilder
            trace,List<String> tmp){
        if (left < 0 || right < 0){
            return;
        }
        //左括号一定大于右括号  ）） （（
        if (right < left){
            return;
        }
        if (left == 0 && right == 0){
            tmp.add(new String(trace));
            return;
        }
        trace.append("(");
        dfs(left-1,right,trace,tmp);
        trace.deleteCharAt(trace.length()-1);

        trace.append(")");
        dfs(left,right-1,trace,tmp);
        trace.deleteCharAt(trace.length()-1);
    }


    public static void main(String[] args) {
        generateParenthesis(3)
                .forEach(System.out::println);
    }
}
