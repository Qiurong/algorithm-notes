package StackAndQueue;

import java.util.Stack;

/**
 * @description: 394. 字符串解码
 * @author: Qr
 * @create: 2021-02-02 08:58
 **/
public class decode_string {
    //思路：两个栈：1个记录数字的栈，1个字符的栈，每次碰到"]"则字符栈pop,数字栈pop出字符的次数
    //第一次做不出来的原因就是不知道如何处理嵌套的情况：其实跟逆波兰表达式一样，吧暂时的计算结果保存在栈中就行了。
    //而我本来的想法是用一个tempStr去保留计算结果，无法处理。
    public String decodeString(String s) {
        if (s == null){
            return null;
        }
        //字符栈可能会暂存计算结果，所以需要是StringBuilder
        Stack<String> chars = new Stack<>();
        Stack<Integer> num = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            //获取当前字符
            Character curr = s.charAt(i);
            //数字则压入num栈
            if(Character.isDigit(curr)){
                //需要一直往后遍历，数字可能不止一位
                StringBuilder numStr = new StringBuilder();
                int j = i;
                while(s.charAt(j) != '['){
                    numStr.append(curr);
                    j++;
                    curr = s.charAt(j);
                }
                i = j - 1;
                num.push(Integer.parseInt(numStr.toString()));
            }
            //']'则出栈
            else if (curr == ']'){
                //重复k次
                int k = num.pop();
                StringBuilder tempCharSequence = new StringBuilder();
                //
                String currChar = chars.pop();
                while(!currChar.equals("[")){
                    tempCharSequence.insert(0,currChar);
                    currChar = chars.pop();
                }
                StringBuilder tempStr = new StringBuilder();
                for (int j = 0; j < k; j++) {
                    tempStr.append(tempCharSequence);
                }
                //吧暂时结果压入栈中
                chars.push(tempStr.toString());
            }
            //'['和字符压栈
            else {
                chars.push(String.valueOf(curr));
            }
        }
        StringBuilder str = new StringBuilder();
        while(!chars.isEmpty()){
            str.insert(0,chars.pop());
        }
        String finalStr = str.toString();
        return finalStr;
    }
}
