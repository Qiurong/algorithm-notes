package StackAndQueue;

import java.util.Stack;

/**
 * @description: 232. 用栈实现队列
 * @author: Qr
 * @create: 2021-02-23 17:18
 **/
public class implement_queue_using_stacks {
    class MyQueue {
        //用两个栈来实现,main栈和spare栈
        //spareStack作为辅助
        Stack<Integer> mainStack;
        Stack<Integer> spareStack;
        public MyQueue() {
            mainStack = new Stack<>();
            spareStack = new Stack<>();
        }

        public void push(int x) {
            if (mainStack.isEmpty()){
                mainStack.push(x);
            }
            else {
                while (!mainStack.isEmpty()){
                    spareStack.push(mainStack.pop());
                }
                spareStack.push(x);
                while (!spareStack.isEmpty()){
                    mainStack.push(spareStack.pop());
                }
            }
        }

        public int pop() {
            return mainStack.pop();
        }

        public int peek() {
            return mainStack.peek();
        }

        public boolean empty() {
            return mainStack.isEmpty();
        }
    }
}
