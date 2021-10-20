package programmerCarl.hash;

import java.util.HashMap;

/**
 * @description: 242. 有效的字母异位词
 * @author: Qr
 * @create: 2021-10-20 12:00
 **/
public class vali_anagram {

    //hashMap存储字符及其出现次数
    public boolean isAnagram(String s, String t) {
        HashMap<Character,Integer> sCount = new HashMap<>();
        HashMap<Character,Integer> tCount = new HashMap<>();
        for (Character c: s.toCharArray()) {
            sCount.put(c, sCount.getOrDefault(c,0) + 1);
        }

        for (Character c: t.toCharArray()) {
            //加一层判断
            if (!sCount.containsKey(c)){
                return false;
            }else {
                tCount.put(c, tCount.getOrDefault(c,0) + 1);
            }
        }
        //判断两个map是否相同
        if (sCount.size() != tCount.size()) {
            return false;
        }
        for (Character key: sCount.keySet()) {
            if (!tCount.containsKey(key) || !tCount.get(key).equals(sCount.get(key)) ){
                return false;
            }
        }
        return true;
    }

    //更好的写法, 一个map来做，第一个往map里面加count，第二个往map里面减count
    public boolean isAnagram_optimized(String s, String t){
        if (s.length() != t.length()){
            return false;
        }
        HashMap<Character, Integer> count = new HashMap<>();
        for (Character c: s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0 ) + 1);
        }
        for (Character c: t.toCharArray()) {
            //在两字符串长度相同的前提下, 若两个字符串 字符频次不一样, 这里必定有某个字符频次<0
            count.put(c, count.getOrDefault(c,0) - 1);
            if (count.get(c) < 0){
                return false;
            }
        }
        return true;
    }
}
