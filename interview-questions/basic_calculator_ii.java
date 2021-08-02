package interview_questions;

import java.util.HashMap;
import java.util.Stack;

/**
 * @description: 227. 基本计算器 II
 * @author: Qr
 * @create: 2021-08-02 17:04
 *
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * 整数除法仅保留整数部分。
 *
 * 示例 1：
 * 输入：s = "3+2*2"
 * 输出：7
 *
 * 示例 2：
 * 输入：s = " 3/2 "
 * 输出：1
 *
 * 示例 3：
 * 输入：s = " 3+5 / 2 "
 * 输出：5
 *
 **/
public class basic_calculator_ii {

    Stack<Integer> numStack;
    Stack<Character> opStack;
    HashMap<Character,Integer> opWeight;

    //基本思想: 操作符两两比较优先级, 优先级大的进行计算.
    //具体做法：两个栈, 遍历到当前操作符b时, 比较操作符b与栈中操作符a优先级,
    // b > a: 计算b, 把结果压入栈中
    // 否则: 计算a, 把结果压入栈中
    public int calculate(String s) {
        numStack = new Stack<>();
        opStack = new Stack<>();
        opWeight = new HashMap<>();
        //初始化操作符权重
        opWeight.put('+',1);
        opWeight.put('-',1);
        opWeight.put('*',2);
        opWeight.put('/',2);
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            Character curr = s.charAt(i);
            //1. 空格
            if (curr.equals(' ')){
                continue;
            }
            //2. 数字
            else if (curr >= '0' && curr <= '9'){
                int endIndex = findNextNum(s,i);
                i = endIndex;
            }
            //3. 操作符
            else {
                if (opStack.isEmpty()){
                    opStack.push(curr);
                }
                else {
                    Character prev = opStack.peek();
                    if (calPrev(prev,curr)){
                        prev = opStack.pop();
                        Integer num2 = numStack.pop();
                        Integer num1 = numStack.pop();
                        Integer tempRes = calTwoNum(num1,num2,prev);
                        numStack.push(tempRes);
                        opStack.push(curr);
                    }else {
                        //找到下一个操作数
                        while (!(s.charAt(i) >= '0' && s.charAt(i) <='9')){
                            i++;
                        }
                        int endIndex = findNextNum(s,i);
                        Integer num2 = numStack.pop();
                        Integer num1 = numStack.pop();
                        Integer tempRes = calTwoNum(num1,num2,curr);
                        numStack.push(tempRes);
                        i = endIndex;
                    }
                }
            }
        }
        if (opStack.isEmpty()){
            res = numStack.pop();
        }else {
            Character op = opStack.pop();
            Integer num2 = numStack.pop();
            Integer num1 = numStack.pop();
            res = calTwoNum(num1,num2,op);
        }
        return res;
    }

    /**
     * 找到字符串s startIndex下标开始的数字 并把它压入栈中
     * @param s
     * @param startIndex: 数字的开始下标
     * @return 数字的结束下标
     */
    public int findNextNum(String s, int startIndex){
        int endIndex = startIndex;
        while (endIndex < s.length() && s.charAt(endIndex) >= '0' && s.charAt(endIndex) <= '9'){
            endIndex++;
        }
        Integer nextNum = Integer.parseInt(s.substring(startIndex,endIndex));
        numStack.push(nextNum);
        return endIndex - 1;
    }


    /**
     * 比较 prev和curr两个操作符的优先级
     * 在两者相同时, 返回true
     * @param prev
     * @param curr
     * @return
     */
    public boolean calPrev(Character prev,Character curr){
        return opWeight.get(prev) >= opWeight.get(curr);
    }


    public int calTwoNum(Integer num1, Integer num2, Character operator){
        if (operator.equals('+')){
            return num1 + num2;
        }
        else if (operator.equals('-')){
            return num1 - num2;
        }
        else if (operator.equals('*')){
            return num1 * num2;
        }
        else {
            return num1 / num2;
        }
    }
}
