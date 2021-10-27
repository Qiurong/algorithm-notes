package programmerCarl.list;

/**
 * @description:
 * @author: Qr
 * @create: 2021-10-27 20:34
 **/
public class remove_linked_list_elements {

    //思路：这题就是一个遍历，然后删除结点，删除结点的时候需要吧 prev和 next相连，那么为了防止head就要被删除，增设dummyNode结点
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyNode = new ListNode(-1,head);
        ListNode curr = head;
        ListNode prev = dummyNode;
        //迭代遍历链表
        while (curr != null){
            if (curr.val == val){
                prev.next = curr.next;
                //删除curr的情况下 prev不动
            }else {
                prev = curr;
            }
            curr = curr.next;
        }
        return dummyNode.next;
    }
}
