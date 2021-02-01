package StackAndQueue;

import java.util.Stack;

/**
 * @description: 155.最小栈: 设计一个支持push, pop, top操作并且在常数时间内检索到最小元素的栈
 * @author: Qr
 * @create: 2021-02-01 14:10
 **/
//思路：用一个额外的栈最小栈来存储当前栈中的最小值
public class min_Stack {

    /** initialize your data structure here. */
    Stack<Integer> mainStack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();
    public min_Stack() {
        mainStack = new Stack<>();
        minStack = new Stack<>();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        mainStack.push(x);
        int minStack_top = minStack.peek();
        if(minStack_top > x){
            minStack.push(x);
        }else {
            minStack.push(minStack_top);
        }
    }

    public void pop() {
        if(!mainStack.isEmpty()){
            mainStack.pop();
            minStack.pop();
        }
    }

    public int top() {
        return mainStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
