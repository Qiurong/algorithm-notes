package recursion;


import java.util.Stack;

/**
 * @description: 234. 回文链表
 * @author: Qr
 * @create: 2021-04-12 22:36
 **/



public class palindrome_linked_list {
    //判断回文最直接的方法就是双指针head和tail从两端开始遍历直到中间
    //对于单链表，难点就在于如何反向遍历。
    //递归为我们提供了一种方法，递归反向迭代, 同时使用函数外的变量向前迭代。
//    public void reverse_traversal_recursion(ListNode head){
//        if (head != null){
//            //先递归到子问题，然后在遍历当前结点
//            reverse_traversal_recursion(head.next);
//            System.out.println(head.val);
//        }
//    }

    private ListNode frontPointer; //用于从前向后迭代

    public boolean isPalindrome(ListNode head) {
        frontPointer = head;
        return recursion(head);
    }


    //currentNode用于从后往前迭代
    public boolean recursion(ListNode currentNode){
        //递归终止条件
        if (currentNode != null){
            if (!recursion(currentNode.next)){
                return false;
            }
            if (frontPointer.val != currentNode.val){
                return false;
            }
            frontPointer = frontPointer.next;
        }
        return true;
    }

    //方法2：反转后半部们链表，然后将前半部分和反转后的后半部分进行比较，比较完恢复后半部分
    public boolean reverseLastHalf(ListNode head){
        if (head == null || head.next == null){
            return true;
        }

        ListNode fast = head;
        ListNode slow = head;
        //快慢指针寻找链表中点mid,
        //结束后，fast指针为null, slow指针为链表中部/中部向下取整
        while(fast != null){
            fast = fast.next;
            if (fast != null){
                fast = fast.next;
            }
            if (fast != null){
                slow = slow.next;
            }
        }
        //第一部分和第二部分
        ListNode lastHalfHead = slow.next;
        ListNode firstHalfHead = head;
        ListNode reversedLastHalfHead = reverseList(lastHalfHead);
        ListNode tail = reversedLastHalfHead;
        while (firstHalfHead != null && reversedLastHalfHead != null){
            if (firstHalfHead.val != reversedLastHalfHead.val){
                return false;
            }
            firstHalfHead = firstHalfHead.next;
            reversedLastHalfHead = reversedLastHalfHead.next;
        }
        //再把链表还原
        slow.next = reverseList(tail);
        return true;
    }

    //反转链表
    public ListNode reverseList(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
