package programmerCarl.array;

import java.util.HashMap;
import java.util.jar.JarEntry;

/**
 * @description: 76. 最小覆盖子串
 * @author: Qr
 * @create: 2021-10-14 15:34
 **/
public class minimum_window_substring {

    //滑动窗口, 用map保存窗口内字符数量.
    //每一轮先right拓展至符合条件, 然后left++
    //时间复杂度: O(n)  ( 如果比较两个hashMap是否相等的操作为O(1) )
    public String minWindow(String s, String t) {
        //最后返回字串的左右下标
        int leftIndex = -1;
        int rightIndex = -1;
        int subStringLen = Integer.MAX_VALUE;
        HashMap<Character, Integer> targetMap = new HashMap<>();
        HashMap<Character, Integer> winMap = new HashMap<>();
        for (Character c: t.toCharArray()) {
            addToMap(targetMap,c);
        }
        int left = 0;
        int right = 0;
        //初始化
        addToMap(winMap,s.charAt(left));
        while (left <= right && right < s.length()){
            //1.先拓展右边界
            while (right < s.length()){
                if (isContained(targetMap, winMap)){
                    break;
                }
                right++;
                if (right == s.length()){
                    right--;
                    break;
                }
                addToMap(winMap,s.charAt(right));
            }
            //2.更新此轮 最小字串
            if (isContained(targetMap, winMap)){
                if (subStringLen > (right -left + 1)){
                    subStringLen = right - left + 1;
                    leftIndex = left;
                    rightIndex = right;
                }
            }
            //3.缩小左边界
            removeFromMap(winMap,s.charAt(left));
            left++;
        }
        //没找到
        if (subStringLen == Integer.MAX_VALUE){
            return "";
        }else {
            return s.substring(leftIndex,rightIndex+1);
        }
    }

    public void addToMap(HashMap<Character,Integer> map, Character c){
        if (map != null){
            map.put(c, map.getOrDefault(c,0) + 1);
        }
    }

    public void removeFromMap(HashMap<Character,Integer> map, Character c){
        if (map != null && map.containsKey(c)){
            if (map.get(c) == 1){
                map.remove(c);
            }else {
                map.put(c, map.get(c) - 1);
            }
        }
    }

    //计算并返回 窗口中的字串是否包含了t的所有字符
    public boolean isContained(HashMap<Character, Integer> targetMap, HashMap<Character, Integer> winMap){
        if (winMap == null || targetMap == null || winMap.size() < targetMap.size()){
            return false;
        }
        for (Character key: targetMap.keySet()) {
            if (!winMap.containsKey(key)){
                return false;
            }else if (winMap.get(key) < targetMap.get(key)){
                return false;
            }
        }
        return true;
    }
}
