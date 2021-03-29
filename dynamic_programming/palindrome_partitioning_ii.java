package dynamic_programming;

import java.lang.management.ManagementFactory;
import java.util.Stack;

/**
 * @description: 132. 分割回文串 II
 * @author: Qr
 * @create: 2021-03-15 11:24
 **/
public class palindrome_partitioning_ii {

    static int minCutTimes = Integer.MAX_VALUE;

    //计算出所有方案的切割次数, 返回最小的.(超时)
    public int minCut_brutalForce(String s) {
        if (s.length() == 1){
            return 0;
        }
        char[] chars = s.toCharArray();
        //用栈去存储切割次数
        Stack<Integer> cutTimes = new Stack<>();
        cutTimes.push(0);
        backtrack(chars,0,cutTimes);
        //迭代次数比切割次数多一
        minCutTimes--;
        return minCutTimes;
    }

    public void backtrack(char[] chars,int startIndex,Stack<Integer> cutTimes){
        if (startIndex >= chars.length){
            minCutTimes = Math.min(minCutTimes,cutTimes.peek());
            return;
        }
        for (int i = startIndex; i < chars.length; i++) {
            if (!is_palindrome(chars,startIndex,i)){
                continue;
            }
            else {
                int currCutTimes = cutTimes.peek();
                currCutTimes++;
                cutTimes.push(currCutTimes);
                backtrack(chars,i+1,cutTimes);
                cutTimes.pop();
            }
        }
    }

    public boolean is_palindrome(char[] chars, int startIndex, int endIndex){
        while (startIndex < endIndex){
            if (chars[startIndex] != chars[endIndex]){
                return false;
            }
            startIndex++;
            endIndex--;
        }
        return true;
    }

    //动态规划处理回文串问题 + 分割次数问题
    //时间：O(n^2) 空间:O(n^2)
    public int minCut_DP(String s){
        int len = s.length();
        if (len == 1){
            return 0;
        }
        int minCutTimes = 0;
        char[] chars = s.toCharArray();
        //动态规划预处理
        boolean dp[][] = new boolean[len][len];
        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                if (i == j){
                    dp[i][j] = true;
                }
                else if(j-i == 1 & chars[i]==chars[j]){
                    dp[i][j] = true;
                }else if (j-i > 1 & dp[i+1][j-1] & chars[i]==chars[j]){
                    dp[i][j] = true;
                }
            }
        }
        //动态规划去记录以i为结尾的最小分割次数f(i) [本质是对枚举的优化]
        //f(i) = min{f(j)} + 1 (  0<=j<i & dp[j+1][j] 即 [j+1,i]为回文 )  依次枚举, 得到以i为结尾的最小分割次数f(i)
        int currMinCutTimes[] = new int[len];
        for (int i = 0; i < len; i++) {
            //给数组赋初值
            currMinCutTimes[i] = Integer.MAX_VALUE;
            if (dp[0][i]){
                currMinCutTimes[i] = 0;
            }
            else {
                for (int j = 0; j < i; j++) {
                    if (dp[j+1][i]){
                        currMinCutTimes[i] = Math.min(currMinCutTimes[i], currMinCutTimes[j] + 1);
                    }
                }
            }
        }
        return currMinCutTimes[len - 1];
    }

}
