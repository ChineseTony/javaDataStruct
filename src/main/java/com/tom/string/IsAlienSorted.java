package com.tom.string;


/**
 * @author tom
 */
public class IsAlienSorted {

    public boolean isAlienSorted(String[] words, String order) {
        int len = words.length;
        for (int i = 0; i < len - 1; i++) {
            if (!isAlien(words[i], words[i + 1], order)) {
                return false;
            }
        }
        return true;
    }


    private boolean isAlien(String s1, String s2, String order) {
        int len1 = s1.length();
        int len2 = s2.length();
        int i = 0;
        int j = 0;
        while (i < len1 && j < len2) {
            if (order.indexOf(s1.charAt(i))
                    > order.indexOf(s2.charAt(j))) {
                return false;
            } else if (order.indexOf(s1.charAt(i))
                    < order.indexOf(s2.charAt(j))) {
                return true;
            }
            i++;
            j++;
        }
        if (i == len1 && j == len2) {
            return true;
        } else if (i == len1) {
            return true;
        } else {
            return false;
        }
    }


    public static String modifyString(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '?') {
                //前面一个字符  如果当前是第0个的话 字符就为‘ ’
                char ahead = i == 0 ? ' ' : chars[i - 1];
                //后面一个字符  如果当前是最后一个的话 字符就为‘ ’
                char behind = i == chars.length - 1 ? ' ' : chars[i + 1];
                //从a开始比较  如果等于前面或者后面的话 就+1
                char temp = 'a';
                while (temp == ahead || temp == behind) {
                    temp++;
                }
                //找到目标字符后 做替换
                chars[i] = temp;
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        String[] words = new String[]{
                "word", "row"
        };
        String order = "worldabcefghijkmnpqstuvxyz";

        System.out.println(new IsAlienSorted().isAlienSorted(words,
                order));
        System.out.println(modifyString("?zs"));


    }
}
