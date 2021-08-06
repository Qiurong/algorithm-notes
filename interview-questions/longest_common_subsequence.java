package interview_questions;

/**
 * @description: 1143. 最长公共子序列
 * @author: Qr
 * @create: 2021-08-06 15:28
 *
 * 给定两个字符串text1 和text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 *
 * 一个字符串的子序列是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 *
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 *
 * 示例 1：
 *
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 *
 * 示例 2：
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 *
 * 示例 3：
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 *
 **/
public class longest_common_subsequence {
    //基本思想： 扫描s1每个元素，在扫描到每个元素的时候，从左到右扫描一遍S2的每个元素
    //   s1[i] == s2[j], 那么最长 =  s1[0..i-1] 和 s2[0..j-1] +1
    //                   否则最长 =  MAX{s1[0..i-1]和 s2[0...j], S1[0..i]和s2[0...j-1]}
    //动态规划： dp[i][j]: s1[0..i-1] 和 s2[0..j-1]的最长公共子序列
    //         初始值: dp[i][j] i=0||j=0 代表 空字符串和另一个字符串的最长公共子序列长度，为0
    //         dp[i][j] = dp[i-1][j-1] +1   if s1[i-1] == s2[j-1]
    //                    Max{dp[i][j-1](上一个), dp[i-1][j](上一行)}
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0){
            return 0;
        }
        int m = text1.length();
        int n = text2.length();
        char[] c1 = text1.toCharArray();
        char[] c2 = text2.toCharArray();
        int[][] dp = new int[m+1][n+1];
        for (int i = 1; i < m+1; i++) {
            for (int j = 1; j < n+1; j++) {
                if (c1[i-1] == c2[j-1]){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else {
                    dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
                }
            }
        }
        return dp[m][n];
    }
}
