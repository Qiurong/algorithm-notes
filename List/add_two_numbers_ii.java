package List;

import java.util.Stack;

/**
 * @description: 445. 两数相加 II
 * @author: Qr
 * @create: 2021-04-26 10:29
 **/
public class add_two_numbers_ii {
    //思路: 两数相加肯定从末尾开始加起, 那就把两数的链表先反转，再相加，再把相加的结果反转。
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode reversedL1 = reverseList(l1);
        ListNode reversedL2 = reverseList(l2);
        ListNode dummyNode = new ListNode(0);   //用于作为结果的dummyNode
        ListNode curr = dummyNode;
        int carry = 0;                              //进位

        //逐位相加
        while(reversedL1 != null && reversedL2 != null){
            int currVal = reversedL1.val + reversedL2.val + carry;
            carry = currVal / 10;
            currVal = currVal % 10;
            ListNode node = new ListNode(currVal);
            curr.next = node;
            curr = curr.next;
            reversedL1 = reversedL1.next;
            reversedL2 = reversedL2.next;
        }

        //链接L1和L2剩余位
        while(reversedL1 != null){
            int currVal = reversedL1.val + carry;
            carry = currVal / 10;
            currVal = currVal % 10;
            ListNode node = new ListNode(currVal);
            curr.next = node;
            curr = curr.next;
            reversedL1 = reversedL1.next;
        }
        while(reversedL2 != null){
            int currVal = reversedL2.val + carry;
            carry = currVal / 10;
            currVal = currVal % 10;
            ListNode node = new ListNode(currVal);
            curr.next = node;
            curr = curr.next;
            reversedL2 = reversedL2.next;
        }

        //最后别忘了链接进位
        if (carry != 0){
            ListNode node = new ListNode(carry);
            curr.next = node;
        }

        //反转结果链表
        ListNode head = dummyNode.next;
        //断开dummyNode
        dummyNode.next = null;
        ListNode res = reverseList(head);
        return res;

    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    //不用链表反转。  逆序想到用栈！！来做
    public ListNode addTwoNumbers_advanced(ListNode l1, ListNode l2){
        Stack<Integer> l1Stack = new Stack<>();
        Stack<Integer> l2Stack = new Stack<>();
        int carry = 0;
        while (l1 != null){
            l1Stack.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null){
            l2Stack.push(l2.val);
            l2 = l2.next;
        }

        ListNode head = null;     //结果链表的头指针
        while (!l1Stack.isEmpty() || !l2Stack.isEmpty() || carry != 0){
            int currVal = carry;
            if (!l1Stack.isEmpty()){
                currVal += l1Stack.pop();
            }
            if (!l2Stack.isEmpty()){
                currVal += l2Stack.pop();
            }
            carry = currVal / 10;
            currVal = currVal % 10;
            ListNode node = new ListNode(currVal);
            //这里逆序 链接 链表是第一遍没写出来的点
            node.next = head;
            head = node;
        }
        return head;
    }


}
