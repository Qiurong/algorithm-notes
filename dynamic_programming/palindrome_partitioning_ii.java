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


}
