package nowcoder.meituan;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

/**
 * @description: 美团4.4笔试第一题
 *          题目：一天，小美在写英语作业时，发现了一个十分优美的字符串：这个字符串没有任何两个字符相同。
 *              于是，小美随手写下了一个字符串，她想知道这个字符串的的所有子序列，有多少个是优美的。由于答案可能会很大，输出对20210101取模后的结果。
 *              一个字符串的子序列定义为：原字符串删除0个或多个字符后剩下的字符保持原有顺序拼接组成的字符串为原串的子序列。
 *              如：ab是acba的子序列，但bc则不是。在本题中。空串也为原串的子序列。
 *              两个子序列不相同，当且仅当他们对应原串的下标不相同。如aab则含有两个子序列ab。
 * @author: Qr
 * @create: 2021-04-09 13:02
 **/

/**
 * 首先我们先考虑一个字符串本身字符无重复的情况 a1a2a3...an，那么求他们的子序列个数即为: 2^n
 *               这个2^n的计算方法： 对于每一个位有取和不取两种情况，那么就for循环，每一位2种情况最后即可得到2^n（类似于排列组合问题）
 * 然后我们在考虑有重复字符的字符串的计算方法. 我们以aab举例子，其有a,a,b,a b, ab, null六种结果（a b和 ab是两个不同的子序列）
 * 计算方法：遍历时，第一个a有取或不取两种情况(2)，遇到第二个a，即aa的情况。aa只有三种取值：1取2不取，2留1不取，都不取。
 *  aaa的情况：1取23不取，2取13不取，3取12不取，都不取。即 3+1。
 *  所以aab为： a:3 b:2 3*2 = 6
 *  所以针对重复字符的字符串，那些重复字符的取值情况为他们的个数+1，然后对于不重复的字符为1+1. 累乘。
 *
 */
public class elegant_string {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()){
            String str = in.nextLine(); //原字符串
            HashMap<Character,Integer> charNums = new HashMap<>();  //存储字符个数的map
            Long res = 1L;              //最后的结果

            //用一个map存储字符串中每个字符的个数
            for (int i = 0; i < str.length(); i++) {
                Character currChar = str.charAt(i);
                if (!charNums.containsKey(currChar)){
                    charNums.put(currChar,1);
                }else {
                    charNums.put(currChar,charNums.get(currChar)+1);
                }
            }

            Set<Entry<Character, Integer>> entrySet = charNums.entrySet();
            for(Entry<Character, Integer> entry: entrySet){
                res = res * (entry.getValue() + 1);
                res = res % 20210101;
            }
            System.out.println(res);
        }
    }
}
