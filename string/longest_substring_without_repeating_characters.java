package string;

import java.util.HashMap;

/**
 * @description: 3. 无重复字符的最长子串
 * @author: Qr
 * @create: 2021-04-29 12:22
 **/
public class longest_substring_without_repeating_characters {

    /**
     *  从左向右扫描, 下一个不重复则加入窗口right++,左边不动；
     *              重复则窗口right++, 左边增加到重复下标加1.
     *  判重和获取重复下标的操作用hashMap来记录
     *  整个过程中获取活动窗口的最大值为最大非重复子串。
     */
    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        //用于记录s中的字母和下标
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        //把string转为char数组, 获取字符比较好写
        char[] chars = s.toCharArray();

        while (right < chars.length){
            if (map.containsKey(chars[right])){
                int originLeft = left;
                left = map.get(chars[right]) + 1;
                for (int i = originLeft; i < left; i++) {
                    map.remove(chars[i]);
                }
            }
            map.put(chars[right],right);
            maxLen = Math.max(maxLen,right-left+1);
            right++;
        }
        return maxLen;
    }
}
