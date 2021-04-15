package sliding_window;

import java.util.HashMap;
import java.util.Set;

/**
 * @description: 76. 最小覆盖子串
 * @author: Qr
 * @create: 2021-04-15 09:10
 **/
public class minimum_window_substring {
    public String minWindow(String s, String t) {
        //特殊情况: s.length<=t.length
        if (s.length() < t.length()){
            if (s.equals(t)){
                return s;
            }
            else {
                return new String("");
            }
        }

        //滑动窗口往右滑动, 开始时候left = 0, right为最小的下标使得[left,right]包含t
        //left++, 如果移除left之后的窗口不包含t, 则right右移使得满足目标; 否则right不动
        //一直右移到left == source.length - target.length()
        char[] source = s.toCharArray();
        char[] target = t.toCharArray();
        StringBuilder res = new StringBuilder();
        HashMap<Character,Integer> targetCount = new HashMap<>();
        HashMap<Character,Integer> winCount = new HashMap<>();
        int left = 0;
        int right = 0;

        //初始化targetCount
        for (int i = left; i < target.length; i++) {
            putVal(targetCount,target[i]);
        }

        //初始化right到包含t的最小右边界
        while (right < source.length){
            putVal(winCount,source[right]);
            if (sourceContainsTarget(winCount,targetCount)) {
                res = new StringBuilder(s.substring(left,right+1));
                break;
            }
            right++;
        }
        //right越界则不存在
        if (right == source.length){
            return new String("");
        }

        //窗口每次左边界右移一格
        while(left<= source.length - target.length && right < source.length){
            char toRemove = source[left];
            left++;
            removeVal(winCount,toRemove);
            //根据窗口右移一格之后窗口是否还满足条件进行讨论
            if (!sourceContainsTarget(winCount,targetCount)){
                //right需要扩充到下一个[right] == toRemove
                int originRight = right;
                right++;
                while (right< source.length && source[right] != toRemove){
                    if (right < source.length){
                        putVal(winCount,source[right]);
                    }
                    right++;
                }
                if (right == source.length){
                    return res.toString();
                }
                putVal(winCount,source[right]);
                if (res.length() > (right -left + 1)){
                    res = new StringBuilder(s.substring(left,right+1));
                }
            }else{
                if (res.length() > (right -left + 1)){
                    res = new StringBuilder(s.substring(left,right+1));
                }
            }
        }
        return res.toString();
    }

    public void putVal(HashMap<Character,Integer> map, char c){
        if (!map.containsKey(c)){
            map.put(c,1);
        }else {
            map.put(c,map.get(c)+1);
        }
    }

    public void removeVal(HashMap<Character,Integer> map, char c){
        if (map.containsKey(c)){
            if (map.get(c) == 1){
                map.remove(c);
            }else {
                map.put(c,map.get(c)-1);
            }
        }
    }

    public boolean sourceContainsTarget(HashMap<Character,Integer>source, HashMap<Character,Integer>target){
        boolean isContained = true;
        Set<Character> targetKeys = target.keySet();
        for (Character c: targetKeys) {
            if (!source.containsKey(c) || source.get(c) < target.get(c)){
                isContained = false;
            }
        }
        return isContained;
    }

}
