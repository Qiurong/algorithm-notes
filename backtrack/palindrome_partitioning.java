package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 131. 分割回文串
 * @author: Qr
 * @create: 2021-04-21 14:13
 **/
public class palindrome_partitioning {

    static List<List<String>> res;
    static List<String> path;

    //这次联系侧重练习回溯，所以判断回文这块就不用动态规划去做了
    public static List<List<String>> partition(String s) {
        res = new ArrayList<>();

        if (s == null || s.length() == 0){
            return res;
        }
        path = new ArrayList<>();
        backtrack(0,s);
        return res;
    }

    public static void backtrack(int start,String s){
        if (start == s.length()){
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < s.length(); i++) {
            String subStr = s.substring(start,i+1);
            if (!isPalindrome(subStr)){
                continue;
            }
            path.add(subStr);
            backtrack(i+1,s);
            path.remove(path.size()-1);

        }
    }

    public static boolean isPalindrome(String s){
        if (s == null || s.length() == 0){
            return false;
        }
        boolean isPal = true ;
        int start = 0;
        int end = s.length() - 1;
        while (start < end){
            if (s.charAt(start) != s.charAt(end)){
                isPal = false;
            }
            start++;
            end--;
        }
        return isPal;
    }

    public static void main(String[] args) {
        partition("aab");
    }
}
