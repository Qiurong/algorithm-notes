package List;

/**
 * @description: 19. 删除链表的倒数第 N 个结点
 * @author: Qr
 * @create: 2021-04-25 20:47
 **/
public class remove_nth_node_from_end_of_list {
    //快慢指针实现
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //加一个dummyNode
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;

        ListNode fast = dummyNode;
        ListNode slow = dummyNode;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        //让slow指向倒数第n+1个元素, fast指向表尾元素
        while (fast != null && fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        ListNode nextNode = slow.next;
        slow.next = slow.next.next;
        nextNode.next = null;
        return dummyNode.next;
    }
}
