package List;

/**
 * @description: 61. 旋转链表
 * @author: Qr
 * @create: 2021-04-24 17:14
 **/
public class rotate_list {
    //找到倒数第k+1个元素, 然后 倒数第k+1.next = null, 表尾元素.next = head,
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || k == 0){
            return head;
        }

        int length = 0;
        ListNode fast = head;
        ListNode slow = head;
        //先 k%length
        while (fast != null){
            fast = fast.next;
            length++;
        }
        k = k % length;
        if (k == 0){
            return head;
        }

        //获取链表倒数第K+1个结点
        fast = head;
        while (k != 0){
            fast = fast.next;
            k--;
        }
        while (fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        //此时slow为倒数k+1个元素, fast为表尾
        ListNode newHead = slow.next;
        slow.next =null;
        fast.next = head;
        return newHead;
    }
}
