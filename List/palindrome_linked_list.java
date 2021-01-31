package List;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 回文链表
 * @author: Qr
 * @create: 2021-01-29 10:22
 **/
//思路: 回文数组是判断[i] == [n-i-1],但链表不能这么做
public class palindrome_linked_list {
    //空间: 0(n),时间: O(n)
    //把链表装入数组,判断回文数组问题
    public boolean isPalindrome_ConvertToArray(ListNode head) {
        if(head == null || head.next == null){
            return true;
        }
        ArrayList<Integer> nodeValArray = new ArrayList<>();
        while(head != null){
            nodeValArray.add(head.val);
            head = head.next;
        }
        int size = nodeValArray.size();
        int j = (size >>> 1);
        for (int i = 0; i < j; i++) {
            if(!nodeValArray.get(i).equals(nodeValArray.get(size-1-i))){
                return false;
            }
        }
        return true;
    }

    //空间: 0(1), 时间：0(n)
    //思路: 先快慢指针找到中间节点(寻找的同时也翻转前半部分链表),找到中间元素后,翻转前保存next结点,
    //      然后开始两个指针遍历后半部分链表与翻转后的前半部分链表
    public boolean isPalindrome_FastAndSlowPointer(ListNode head){
        if(head == null || head.next == null){
            return true;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        ListNode curr = head;
        int length = 0;
        //获取链表长度
        while (curr != null){
            length++;
            curr = curr.next;
        }
        //寻找中点
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            if(fast != null){
                slow = slow.next;
            }
        }
        ListNode lastHalfHead = null;
        //根据链表长度来区分lastHalfhead的赋值
        if(length % 2 == 0){
            lastHalfHead = slow.next;
        }else {
            lastHalfHead = slow.next.next;
        }
        slow.next = null;
        //把第一个子序列逆序
        ListNode firstHalfHead = reverseList(head);
        //比较两个子序列
        while (firstHalfHead!= null && lastHalfHead != null){
            //这里要比较值
            if (firstHalfHead.val != lastHalfHead.val){
                return false;
            }
            firstHalfHead = firstHalfHead.next;
            lastHalfHead = lastHalfHead.next;
        }
        return true;
    }
    public ListNode reverseList(ListNode head){
        ListNode curr = head;
        ListNode prev = null;
        ListNode next = null;
        while (curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
    //官方回答的快慢指针法更好, 不再是反转前半部分而是反转后半部分.
    //好在： 可以认为链表为奇数个时,中间元素属于前半部分, 在比较时忽略前半部分多出来的中间元素. 也就省略了我的方法中根据不同情况来给后半部分赋值的操作.
    public boolean isPalindrome_FastAndSlowPointer_OfficialAnswer(ListNode head) {
        if(head == null || head.next == null){
            return true;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        while(fast != null){
            fast = fast.next;
            if(fast != null){
                slow = slow.next;
                fast = fast.next;
            }
        }
        ListNode lastHalf = slow.next;
        slow.next = null;
        ListNode lastHalfReversed = reverseList(lastHalf);
        while(head != null && lastHalfReversed != null){
            if(head.val != lastHalfReversed.val){
                return false;
            }
            head = head.next;
            lastHalfReversed = lastHalfReversed.next;
        }
        return true;
    }
}
