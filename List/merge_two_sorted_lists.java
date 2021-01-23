package List;

/**
 * @description: 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * @author: Qr
 * @create: 2021-01-22 17:20
 **/
//思路：新建一个链表，循环吧两个链表的元素加入到新链表中。
public class merge_two_sorted_lists {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null){
            return null;
        }
        ListNode head = new ListNode();
        ListNode curr = head;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                curr.val = l1.val;
                l1 = l1.next;
            }else{
                curr.val = l2.val;
                l2 = l2.next;
            }
            //这边||是精华，两者只要有一个不为空，就新建next结点
            //两者皆空，则不新建节点
            //若两者只有一个不为空,则在后面吧不为空的连表连接上去
            if(l1 != null || l2 != null){
                curr.next = new ListNode();
                curr = curr.next;
            }
        }
        //将l1和l2剩余元素添加到升序链表中
        while(l1 != null){
            curr.val = l1.val;
            l1 = l1.next;
            if(l1 != null){
                curr.next = new ListNode();
                curr = curr.next;
            }
        }
        while(l2 != null){
            curr.val = l2.val;
            l2 = l2.next;
            if(l2 != null){
                curr.next = new ListNode();
                curr = curr.next;
            }
        }
        return head;
    }
}
