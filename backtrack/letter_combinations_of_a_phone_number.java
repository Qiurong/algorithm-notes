package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 17. 电话号码的字母组合
 * @author: Qr
 * @create: 2021-04-19 20:24
 **/
public class letter_combinations_of_a_phone_number {

    char[] two = {'a','b','c'};
    char[] three = {'d','e','f'};
    char[] four = {'g','h','i'};
    char[] five = {'j','k','l'};
    char[] six = {'m','n','o'};
    char[] seven = {'p','q','r','s'};
    char[] eight = {'t','u','v'};
    char[] nine = {'w','x','y','z'};

    public List<String> letterCombinations(String digits) {
        List<String> res =  new ArrayList<>();
        if (digits == null || digits.length() == 0){
            return res;
        }

        int num = Integer.parseInt(digits);
        int[] nums = new int[digits.length()];
        for (int i = nums.length - 1; i >= 0; i--) {
            nums[i] = num % 10;
            num /= 10;
        }
        char[] temp = convert(nums[0]);
        for (int i = 0; i < temp.length; i++) {
            res.add(String.valueOf(temp[i]));
        }
        //这里用了一个性质： 3个集合的笛卡尔积 = ((1,2)的笛卡尔积) 与 3的笛卡尔积
        for (int i = 1; i < nums.length; i++) {
            List<String> currRes = new ArrayList<>();
            temp = convert(nums[i]);
            for (String str: res) {
                for (char c: temp) {
                    String newStr = str + c;
                    currRes.add(newStr);
                }
            }
            res.clear();
            for (String str: currRes) {
                res.add(str);
            }
            currRes.clear();
        }
        return res;
    }

    public char[] convert(int num){
        switch (num){
            case 2:
                return two;
            case 3:
                return three;
            case 4:
                return four;
            case 5:
                return five;
            case 6:
                return six;
            case 7:
                return seven;
            case 8:
                return eight;
            case 9:
                return nine;
            default:
                return null;
        }
    }

    //优化
    //1.用一个string数组代替多个char数组，后期取值为str[i - '2'];
    String[] chars = {
        "abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"
    };
    List<String> res;
    StringBuilder builder;

    public List<String> letterCombinations_optimized(String digits){
        res = new ArrayList<>();
        if(digits.length()==0 || digits == null){
            return res;
        }

        builder = new StringBuilder();
        backtrack(0,digits);
        return res;
    }

    /**
     *
     * @param index: digits的下标
     * @param digits: 题目中的digits
     */
    public void backtrack(int index,String digits){
        if (index == digits.length()){
            res.add(builder.toString());
            return;
        }
        //获取当前digits的start下标对应的字符串
        String currStr = chars[digits.charAt(index) - '2'];

        //遍历字符串. 对于当前index对应的字符串的每个字符，递归调用计算下一个index对应的字符串的每个字符进行拼接
        for (int i = 0; i < currStr.length(); i++) {
            builder.append(currStr.charAt(i));
            backtrack(index+1,digits);
            builder.deleteCharAt(builder.length()-1);
        }
    }
}
