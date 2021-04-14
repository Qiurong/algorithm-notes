package sliding_window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @description: 438. 找到字符串中所有字母异位词
 * @author: Qr
 * @create: 2021-04-14 18:41
 **/
public class find_all_anagrams_in_a_string {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length()){
            return res;
        }
        char[] source = s.toCharArray();
        char[] target = p.toCharArray();
        HashMap<Character,Integer> targetCount = new HashMap<>();
        HashMap<Character,Integer> winCount = new HashMap<>();
        int left = 0;
        int right = left + target.length - 1;

        for (int i = 0; i < target.length; i++) {
            putVal(targetCount,target[i]);
        }

        for (int i = left; i <= right ; i++) {
            putVal(winCount,source[i]);
        }

        while (left <= source.length-target.length){
            if (winCount.equals(targetCount)){
                res.add(left);
            }
            removeVal(winCount, source[left]);
            left++;
            right++;
            //窗口右端点到头了，直接结束
            if (right >= source.length) {
                return res;
            }
            putVal(winCount, source[right]);
        }

        return res;
    }

    public void putVal(HashMap<Character, Integer> map, char c){
        if (!map.containsKey(c)){
            map.put(c,1);
        } else {
          map.put(c,map.get(c) + 1);
        }
    }

    public void removeVal(HashMap<Character,Integer> map, char c){
        if (map.containsKey(c)){
            if (map.get(c) == 1){
                map.remove(c);
            }
            else {
                map.put(c,map.get(c)-1);
            }
        }
    }
}
