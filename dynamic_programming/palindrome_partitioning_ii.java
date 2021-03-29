package dynamic_programming;

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

    //使用动态规划预处理
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
        //贪心法去找每一个序列的最大回文子序列
        //贪心的方法：找出以startIndex为开始的最大回文序列，在不断迭代
        //错误。
        int startIndex = 0;
        int endIndex = len - 1;
        while (startIndex < len){
            for (int j = endIndex; j >= startIndex; j--){
                if (dp[startIndex][j]){
                    startIndex = j + 1;
                    if (startIndex < len){
                        minCutTimes++;
                    }
                    break;
                }
            }
        }
        return minCutTimes;
    }

}
