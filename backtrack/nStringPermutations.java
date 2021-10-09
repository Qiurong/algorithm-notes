package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 输入 string数组, 输出这个数组中所有字符串的全排列
 * @author: Qr
 * @create: 2021-10-08 11:46
 *  ["Java", "Python", "Go"]
 *  输出: "JPG", "JPo", "JyG", ... "ano"
 **/
public class nStringPermutations {


    public static void func(String[] strs){
        //1.特判
        if(strs == null || strs.length == 0 || strs.length == 1){
            System.out.println(strs);
        }
        int len = strs.length;
        //每次计算两重循环
        int end = 2;
        List<String> tempRes = new ArrayList<String>();
        //赋初值
        for(Character c1 : strs[0].toCharArray()){
            for(Character c2 : strs[1].toCharArray()){
                String tempStr = c1.toString();
                tempStr = tempStr.concat(c2.toString());
                tempRes.add(tempStr);
            }
        }
        while(end < len){
            int currSize = tempRes.size();
            for(int i = 0; i < currSize; i++){
                String currStr = tempRes.get(0);
                tempRes.remove(0);
                String originStr = strs[end];
                for(Character c2: originStr.toCharArray()){
                    currStr = currStr.concat(c2 + "");
                    tempRes.add(currStr);
                    currStr = currStr.substring(0,currStr.length()-1);
                }
            }
            end++;
        }
        for(String str : tempRes){
            System.out.println(str);
        }
    }
    public static void main(String[] args) {
        String[] strs = {"Java", "Python", "Go"};
        func_map(strs);
    }

    //第二种方法. 回溯去做
    public static void func_backtrack(String [] strs){
        StringBuilder path = new StringBuilder();
        List<String> res = new ArrayList<>();
        backTrack(strs,0,0,path,res);
    }

    /**
     *
     * @param strs string数组
     * @param charIndex 当前string的字符下标
     * @param strIndex  数组中第i个下标
     * @param path      暂时结果
     * @param res
     */
    public static void backTrack(String[] strs, int charIndex, int strIndex, StringBuilder path, List<String> res){
        //1.结束条件
        if (path.length() == strs.length){
            res.add(new String(path));
            System.out.println(new String(path));
            return;
        }
        //选出当前字符串中一个字符
        for (int i = charIndex; i < strs[strIndex].length(); i++) {
            //无剪枝
            //做选择
            path.append(strs[strIndex].charAt(i));
            backTrack(strs,0,strIndex+1,path,res);
            //撤销选择
            path.deleteCharAt(path.length()-1);
        }
    }


    //第三种方法：映射. 可以把每个string取得下标映射成为一个数字.
    //{Java, Python, Go}
    //JPG: 000 JPo:001 ...ano:351
    public static void func_map(String[] strs){
        int len = strs.length;
        int permutationNum = 1;
        //range数组, 每一位为当前string的长度
        int[] ranges = new int[len];
        for (int i = 0; i < len; i++) {
            ranges[i] = strs[i].length();
            permutationNum *= ranges[i];
        }

        for (int i = 0; i < permutationNum; i++) {
            StringBuilder currStr = new StringBuilder();
            int currNum = i;
            //从后往前
            for (int j = ranges.length - 1; j >= 0; j--) {
                int currBit = currNum % ranges[j];
                currNum = currNum / ranges[j];
                currStr.insert(0,strs[j].charAt(currBit));
            }
            System.out.println(currStr);
        }
    }
}
