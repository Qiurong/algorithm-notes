package StackAndQueue;

import java.util.Stack;

/**
 * @description: 150.逆波兰表达式
 * @author: Qr
 * @create: 2021-02-01 14:44
 **/

public class evaluate_reverse_polish_notation {
    public int evalRPN(String[] tokens) {
        int len = tokens.length;
        int finalResult = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            String currChar = tokens[i];

            if (currChar.equals("+")){
                int operator2 = stack.pop();
                int operator1 = stack.pop();
                int tempResult = operator1 + operator2;
                stack.push(tempResult);
            }
            else if(currChar.equals("-")){
                int operator2 = stack.pop();
                int operator1 = stack.pop();
                int tempResult = operator1 - operator2;
                stack.push(tempResult);
            }
            else if(currChar.equals("*")){
                int operator2 = stack.pop();
                int operator1 = stack.pop();
                int tempResult = operator1 * operator2;
                stack.push(tempResult);
            }
            else if(currChar.equals("/")){
                int operator2 = stack.pop();
                int operator1 = stack.pop();
                int tempResult = operator1 / operator2;
                stack.push(tempResult);
            }
            else {
                Integer operator = Integer.valueOf(currChar);
                stack.push(operator);
            }
            if (i == len-1){
                finalResult = stack.peek();
            }
        }
        return finalResult;
    }
}
