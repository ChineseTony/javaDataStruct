package com.tom.string;


import java.util.ArrayList;
import java.util.List;

/**
 * @author wangtao
 * @link https://leetcode-cn.com/problems/partition-labels/
 *
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-labels
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PartitionLabels {

    private PartitionLabels(){

    }

    public static List<String> partitionLabels(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() == 0){
            return result;
        }
        //保存最后一个字母下标
        int[] last = new int[26];
        int length = s.length();
        for (int i = 0; i < length; i++) {
            last[s.charAt(i) - 'a'] = i;
        }
        int start = 0, end = 0;
        for (int i = 0; i < length; i++) {
            //保存最大的下标
            end = Math.max(end, last[s.charAt(i) - 'a']);
            //进行截取
            if (i == end){
                result.add(s.substring(start,end+1));
                start = end + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String s ="defegdehijhklij";
        partitionLabels(s).forEach(System.out::println);

    }
}
