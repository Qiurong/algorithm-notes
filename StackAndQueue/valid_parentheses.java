package StackAndQueue;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @description: 20. 有效的括号
 * @author: Qr
 * @create: 2021-06-17 17:37
 **/
public class valid_parentheses {
    public boolean isValid(String s) {
        if (s == null || s.isEmpty()){
            return true;
        }
        int len = s.length();
        if (len % 2 != 0){
            return false;
        }
        int i = 0;
        boolean valid = true;
        Stack<Character> stack = new Stack<>();
        while (i < len){
            Character currChar = s.charAt(i);
            if (currChar.equals(')') || currChar.equals(']') || currChar.equals('}')){
                if (stack.isEmpty()){
                    valid = false;
                    break;
                }
                Character leftChar = stack.pop();
                if (!isPair(leftChar,currChar)){
                    valid = false;
                    break;
                }
            } else {
                stack.push(currChar);
            }
            i++;
        }
        if (!stack.isEmpty()){
            valid = false;
        }
        return valid;
    }

    public boolean isPair(Character leftC, Character rightC){
        boolean pairOne = leftC.equals('(') && rightC.equals(')');
        boolean pairTwo = leftC.equals('[') && rightC.equals(']');
        boolean pairThree = leftC.equals('{') && rightC.equals('}');
        return pairOne || pairTwo || pairThree;
    }


    //跟官方题解思路基本一致，但在如何处理左右括号配对的问题上，官网用map来进行配对，非常巧妙
    public  boolean isValidWithMap(String s){
        if (s == null || s.isEmpty()){
            return true;
        }
        boolean valid = true;
        int len = s.length();
        if(len % 2 != 0){
            return false;
        }
        Map<Character,Character> map = new HashMap<>();
        map.put(')','(');
        map.put(']','[');
        map.put('}','{');
        Stack<Character> stack = new Stack<>();
        int i = 0;
        while (i < s.length()){
            Character currChar = s.charAt(i);
            if (map.containsKey(currChar)){
                if (stack.isEmpty()){
                    valid = false;
                    break;
                }
                Character leftChar = stack.pop();
                if (!leftChar.equals(map.get(currChar))){
                    valid = false;
                    break;
                }
            }else {
                stack.push(currChar);
            }
            i++;
        }
        //最后栈非空则说明还有未配对的左括号
        if (!stack.isEmpty()){
            valid = false;
        }
        return valid;
    }

}
