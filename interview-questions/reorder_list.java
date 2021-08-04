package interview_questions;

import java.util.List;

/**
 * @description: 143. 重排链表
 * @author: Qr
 * @create: 2021-08-03 20:22
 *
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 *
 * L0→ L1→ … → Ln-1→ Ln
 * 请将其重新排列后变为：
 * L0→Ln→L1→Ln-1→L2→Ln-2→ …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1:
 * 输入: head = [1,2,3,4]
 * 输出: [1,4,2,3]
 *
 * 示例 2:
 * 输入: head = [1,2,3,4,5]
 * 输出: [1,5,2,4,3]
 **/
public class reorder_list {
    //思想：快慢指针取链表中部，然后用慢指针吧后半部分的链表反转,
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null){
            return;
        }
        ListNode quick = head.next;
        ListNode slow = head;
        //1.快慢指针移动
        while (quick != null){
            quick = quick.next;
            if (quick != null){
                quick = quick.next;
            }
            slow = slow.next;
        }
        //此时quick指向需要反转的后半部分的head
        quick = slow.next;
        ListNode reversed = reverseList(quick);
        //断开后半部分
        slow.next= null;
        //slow代表前半部分的头
        slow = head.next;
        ListNode curr = head;
        while (slow != null && reversed != null){
            curr.next = reversed;
            curr = curr.next;
            reversed = reversed.next;
            curr.next = slow;
            curr = curr.next;
            slow = slow.next;
        }
        if (slow != null){
            curr.next = slow;
        }
        if (reversed != null){
            curr.next = reversed;
        }
    }

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
