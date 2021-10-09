package backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 剑指 Offer 38. 字符串的排列
 * @author: Qr
 * @create: 2021-10-09 11:02
 **/
public class stringPermutation {
    List<String> res;
    StringBuffer path;
    boolean[] visited;

    //对于一个s中有重复字符的去重做法： a:s中字符先排序   b.填入字符时保证对于重复字符 只填写第一个
    public String[] permutation(String s) {
        res = new ArrayList<>();
        path = new StringBuffer();
        visited = new boolean[s.length()];
        char[] chars = s.toCharArray();
        //a.字符排序
        Arrays.sort(chars);
        backTrack(chars);
        String[] strs = new String[res.size()];
        int index = 0;
        for (String str : res) {
            strs[index++] = str;
        }
        return strs;
    }

    public void backTrack(char[] chars){
        //1.回溯结束条件
        if (path.length() == chars.length){
            res.add(new String(path));
            return;
        }
        //2.做选择
        //这里i是从0开始遍历做选择
        for (int i = 0; i < chars.length; i++) {
            //b.重复字符只取第一个
            if (visited[i] || (i > 0 && chars[i-1] == chars[i] && !visited[i-1]) ){
                continue;
            }
            path = path.append(chars[i]);
            visited[i] = true;
            backTrack(chars);
            //撤销选择
            visited[i] = false;
            path.deleteCharAt(path.length()-1);
        }
    }

}
