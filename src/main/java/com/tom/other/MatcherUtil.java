package com.tom.other;

/**
 * @author WangTao
 * 字符串的zengzhe匹配
 */
public class MatcherUtil {

    private MatcherUtil(){

    }

    /**
     *
     * @param str  待匹配的字符串
     * @param pattern  匹配字符串格式 暂时只支持 * . * 匹配0个或多个 . 匹配一个字符串
     */
    public static boolean match(String str, String pattern){
        if (str == null || pattern == null){
            return false;
        }
        int strIndex = 0;
        int patternIndex =0;
        return matcherCore(str,strIndex,pattern,patternIndex);
    }


    private static boolean matcherCore(String str,int strIndex,
                                       String pattern,int patternIndex){
        int strLength = str.length();
        int patternLength = pattern.length();
        if (patternIndex == patternLength){
            return strIndex == strLength;
        }
        //
        if(patternIndex + 1 < patternLength
                && pattern.charAt(patternIndex+1) == '*'){
            if (strIndex < strLength &&
                    (pattern.charAt(patternIndex) == str.charAt(strIndex)
                    || pattern.charAt(patternIndex) == '.')){
                return matcherCore(str, strIndex, pattern, patternIndex + 2) ||
                        matcherCore(str, strIndex + 1, pattern, patternIndex);
            }else {
                return matcherCore(str, strIndex, pattern, patternIndex + 2);
            }
        }
        //  str  aa  pattern aac
        if (strIndex != strLength &&
                (pattern.charAt(patternIndex) == str.charAt(strIndex)
                        || pattern.charAt(patternIndex) == '.')) {
            return matcherCore(str, strIndex + 1, pattern, patternIndex + 1);
        }
        return false;
    }

    public static void main(String[] args) {
        String str ="abc";
        String pattern = "a.c";
        System.out.println(MatcherUtil.match(str,pattern));
    }
}
