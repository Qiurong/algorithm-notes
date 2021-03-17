package dynamic_programming;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @description: 131. 分割回文串
 * @author: Qr
 * @create: 2021-03-15 11:22
 **/
public class palindrome_partitioning {

    //回溯法不断去深度遍历, 遇到截取的符合则递归深度, 不复合的剪枝(continue)
    //两层循环：外层for循环用于穷举一个一个遍历
    //        内层深度递归判断是否符合
    public List<List<String>> partition(String s){
        //记录截取路径上的字符
        ArrayList<String> path = new ArrayList<>();
        //最后的结果
        List<List<String>> palindrome_Strings = new ArrayList<>();
        int len = s.length();
        if (len == 0){
            return palindrome_Strings;
        }
        char[] chars = s.toCharArray();
        backtrack(chars, 0,path,palindrome_Strings);
        return palindrome_Strings;
    }

    public void backtrack(char[] chars,int startIndex,ArrayList<String> path, List<List<String>> palindrome_Strings){
        //递归终止条件
        if (startIndex >= chars.length){
            //这里需要深拷贝
            palindrome_Strings.add(new ArrayList<>(path));
            return;
        }
        //外层遍历, 一个个去截断原字符串
        for (int i = startIndex; i < chars.length; i++) {
            //截出来的[startIndex,i]为回文, 则把[startIndex,i]加入到path中
            if (!is_palindrome(chars,startIndex,i)){
                continue;

            }else {
                String str = new String(chars,startIndex,i - startIndex + 1);
                //把截断出来的str放入path中
                path.add(str);
                //去迭代判断[i+1,end]
                backtrack(chars,i+1, path, palindrome_Strings);
                //回溯的同时把路径上的字符弹出
                path.remove(path.size() - 1);
            }
        }
    }

    //判断[startIndex,endIndex]是否回文.
    public boolean is_palindrome(char[] chars,int startIndex, int endIdex){
        while (startIndex < endIdex){
            if (chars[startIndex] != chars[endIdex]){
                return false;
            }
            startIndex++;
            endIdex--;
        }
        return true;
    }

}
