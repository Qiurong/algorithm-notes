package List;

/**
 * @description:
 * @author: Qr
 * @create: 2021-01-21 10:26
 **/
public class remove_duplicates_from_sorted_list {

    /**问题：1.用一个变量val来存储链表中不一样元素的值。（突然发现val的集合就是最后链表中元素值得集合）
     *      问题：val初始值是什么？ + val何时替换
     *      但其实根本不需要额外的变量去存储这个val值，直接用current.val与current.next.val去比较就好了
     *      2.空指针问题。
     *      解决方法：在遍历/用到这个元素之前先进行判空
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode listhead = head;
        if(head == null){
            return  head;
        }
        int val = head.val;
        //遍历链表
        while(head != null){
            if(val != head.val){
                val = head.val;
            }
            if(head.next != null){
                if (val == head.next.val){
                    head.next = head.next.next;
                    //这一步是关键点, 当前元素值等于下一元素值时,只需删除下一元素，不要移动指针至下一元素(当前元素就不会被删除)
                    //否则会少删除元素
                    continue;
                }
            }
            head = head.next;
        }
        return listhead;
    }
    //官方解答
    public ListNode deleteDuplicates_offical(ListNode head){
        ListNode current = head;
        while(current != null && current.next != null){
            if(current.val == current.next.val){
                current.next = current.next.next;
            }else{
                current = current.next;
            }
        }
        return  head;
    }
}
