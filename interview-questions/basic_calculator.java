package interview_questions;

import java.util.Stack;

/**
 * @description: 224. 基本计算器
 * @author: Qr
 * @create: 2021-08-02 14:23
 *
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 *
 * 示例 1：
 * 输入：s = "1 + 1"
 * 输出：2
 *
 * 示例 2：
 * 输入：s = " 2-1 + 2 "
 * 输出：3
 *
 * 示例 3：
 * 输入：s = "(1+(4+5+2)-3)+(6+8)"
 * 输出：23
 *
 **/
public class basic_calculator {

    Stack<Integer> numStack;
    Stack<Character> opStack;

    //用双栈来做, 碰到的问题就是 类似 2-3+1 这种 变成了 2-4, 如何解决是双栈的问题
    //解决方法： 每次有符号入栈, 先算栈中能算的!!!  即 + 入栈时, 先算2-3=-1, 再-1+1
    public int calculate(String s) {
        int res = 0;
        numStack = new Stack<>();
        //小技巧： numStack先放个0防止栈空
        numStack.push(0);
        opStack = new Stack<>();

        //遍历字符串
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            //1.空格
            if (c.equals(' ')){
                continue;
            }
            //2.数字
            else if (c >= '0' && c <= '9'){
                //存在多位数字的可能
                int start = i;
                int end = i;
                while (end < s.length() && s.charAt(end) >= '0' && s.charAt(end) <= '9'){
                    end++;
                }
                Integer num = Integer.parseInt(s.substring(start,end));
                numStack.push(num);
                i = end-1;
            }
            //3.'('操作符
            else if(c.equals('(')){
                opStack.push(c);
            }
            //4.+ -操作符
            else if (c.equals('+') || c.equals('-')){
                //先算目前栈内能算的
                calTwoNum();
                //把c入栈
                opStack.push(c);
            }
            //5.')'操作符
            else{
                //由于每次有操作符就会计算前一个操作符, 所以()里只有一个操作符
                while (!opStack.peek().equals('(')){
                    calTwoNum();
                }
                //把')' pop出去
                opStack.pop();
            }
        }
        //需要把 最后一个操作符计算一下
        if (!opStack.isEmpty()){
            calTwoNum();
        }
        res = numStack.pop();
        return res;
    }

    //计算栈中上两个数字之和
    public void calTwoNum(){
        if(numStack.size() < 2 || opStack.isEmpty() || opStack.peek().equals('(')){
            return;
        }
        Integer num2 = numStack.pop();
        Integer num1 = numStack.pop();
        Character op = opStack.pop();
        if (op.equals('+')){
            num1 += num2;
        }else {
            num1 -= num2;
        }
        numStack.push(num1);
    }
}
