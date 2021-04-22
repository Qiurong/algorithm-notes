package List;

/**
 * @description: 剑指 Offer 22. 链表中倒数第k个节点
 * @author: Qr
 * @create: 2021-04-22 17:46
 **/
public class getKthFromEnd {
    public ListNode getKthNodeFromEnd(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;
        //1.让fast先走k步
        while (k > 0){
            fast = fast.next;
            k--;
        }
        //2.fast和slow同时走。fast走到Null(length-k)步, slow走到倒数第k个元素。
        while (fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
