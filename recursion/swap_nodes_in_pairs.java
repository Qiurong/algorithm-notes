package recursion;

import java.util.List;

/**
 * @description: 24. 两两交换链表中的节点
 * @author: Qr
 * @create: 2021-04-12 12:53
 **/

class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}




public class swap_nodes_in_pairs {

    public ListNode swapPairs_recursion(ListNode head) {
        return recursion(head);
    }
    public ListNode recursion(ListNode head){
        //递归终止条件
        if (head == null || head.next == null){
            return head;
        }
        //下次递归的起始点
        ListNode nextHead = head.next.next;
        //新的头节点
        ListNode newHead = head.next;
        newHead.next = head;
        head.next = recursion(nextHead);
        return newHead;
    }

    public  ListNode swapPairs_traverse(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode curr = dummyHead;
        while (curr != null && curr.next != null && curr.next.next != null){
            ListNode temp = curr.next;
            curr.next = temp.next;
            temp.next = temp.next.next;
            curr.next.next = temp;
            curr = temp;
        }
        return dummyHead.next;
    }
}
