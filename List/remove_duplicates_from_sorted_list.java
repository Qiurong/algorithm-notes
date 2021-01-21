package List;

/**
 * @description:
 * @author: Qr
 * @create: 2021-01-21 10:26
 **/
public class remove_duplicates_from_sorted_list {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode listhead = head;
        if(head == null){
            return  head;
        }
        int val = head.val;
        while(head != null){
            if(val != head.val){
                val = head.val;
            }
            if(head.next != null){
                if (val == head.next.val){
                    head.next = head.next.next;
                    //这一步是关键点, 当前元素值等于下一元素值时,只需删除下一元素，不要移动指针至下一元素(下一元素就不会被删除)
                    //否则会少删除元素
                    continue;
                }
            }
            head = head.next;
        }
        return listhead;
    }
}
