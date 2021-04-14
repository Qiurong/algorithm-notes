package sliding_window;

import java.util.HashMap;

/**
 * @description: 567. 字符串的排列
 * @author: Qr
 * @create: 2021-04-14 10:27
 **/
public class permutation_in_string {
    //本质上是暴力解法，从左向右遍历，如果以i为开始的子序列
    public boolean checkInclusion(String s1, String s2) {
        boolean isIncluded = false;
        char[] s1Chars = s1.toCharArray();
        char[] s2Chars = s2.toCharArray();
        //key: s1的字符   val:s1中这个字符的个数
        HashMap<Character,Integer> map = new HashMap<>();
        //滑动窗口的开始指针和结束指针
        int i = 0;
        int j = s1Chars.length - 1;

        for (int k = 0; k < s1Chars.length; k++) {
            if (!map.containsKey(s1Chars[k])){
                map.put(s1Chars[k],1);
            }else {
                map.put(s1Chars[k],map.get(s1Chars[k]) + 1);
            }
        }

        //窗口滑动, i滑动到 [s2.length - s1.length]
        while (i <= s2.length() - s1.length()){
            //滑动窗口的第一个元素为s1的元素之一则进入区间
            //判断[i,j]是否满足，不满足则i++
            if (map.containsKey(s2Chars[i])){
                //start用于向右探测, 取值[i,j]
                int start = i;
                HashMap<Character,Integer> winMap = new HashMap<>();
                //窗口左侧开始向后滑动
                while (start <= j){
                    //吧s2Chars[i]放入窗口中的map里
                    if (!winMap.containsKey(s2Chars[start])){
                        winMap.put(s2Chars[start],1);
                    }else {
                        winMap.put(s2Chars[start], winMap.get(s2Chars[start])+1);
                    }
                    //窗口内不满足要求，跳出当前循环窗口右侧向右滑动
                    if (!map.containsKey(s2Chars[start]) || map.get(s2Chars[start]) < winMap.get(s2Chars[start])){
                        //窗口向右滑1
                        i++;
                        j = i + s1.length() - 1;
                        break;
                    }
                    if(start == j){
                        isIncluded = true;
                        return true;
                    }
                    start++;
                }
            }
            else {
                i++;
                j++;
            }
        }

        return isIncluded;
    }

    //改为固定长度为s1的滑动窗口,时间复杂度变为O(n)
    public boolean checkInclusion_slidingWindow(String s1,String s2){
        if (s1.length() > s2.length()){
            return false;
        }
        boolean isIncluded = false;
        char[] s1Chars = s1.toCharArray();
        char[] s2Chars = s2.toCharArray();
        //用HashMap来存储字符以及其出现的次数
        HashMap<Character,Integer> s1Count = new HashMap<>();
        HashMap<Character,Integer> winCount = new HashMap<>();
        //滑动窗口的左右指针
        int left = 0;
        int right = 0;

        //完成s1的字符统计, 后续也就是winCount与s1Count相同就找到了s1的排列
        for (int i = 0; i < s1Chars.length; i++) {
            putChar(s1Count,s1Chars[i]);
        }
        //初始化winCount
        while (right <= left + s1Chars.length- 1){
            putChar(winCount,s2Chars[right]);
            right++;
        }
        right--;
        //从左开始向右滑动 left最多滑到[s2.length - s1.length]
        while(left <= s2Chars.length - s1Chars.length){
            if (s1Count.equals(winCount)){
                isIncluded = true;
                return true;
            }else {
            //每右移一格, 出一个进一个
                //右移窗口
                //先删left元素再left++
                removeChar(winCount,s2Chars[left]);
                left++;
                //先right++再放入[right]元素
                right++;
                if (right == s2Chars.length){
                    return isIncluded;
                }
                putChar(winCount,s2Chars[right]);
            }
        }

        return isIncluded;
    }

    //把字符放入map中,  map中存储字符以及字符个数
    public void putChar(HashMap<Character,Integer> map, char c){
        if(!map.containsKey(c)){
            map.put(c,1);
        }else {
            map.put(c,map.get(c)+1);
        }
    }

    //把字符从map中拿出来.
    public void removeChar(HashMap<Character,Integer> map, char c){
        if (!map.containsKey(c)){
            return;
        }else if (map.get(c) == 1){
            map.remove(c);
        }
        else {
            map.put(c,map.get(c)-1);
        }
    }
}
