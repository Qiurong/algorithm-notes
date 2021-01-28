package List;

import java.util.ArrayList;

/**
 * @description: 判断链表中是否有环
 * @author: Qr
 * @create: 2021-01-28 11:11
 **/

public class linked_list_cycle {
    //简单版本: 用一个数组存储链表元素,最后判断数组是否有元素,时间复杂度O(n^3), 空间复杂度0(n)
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null){
            return false;
        }
        Boolean isCycled = false;
        ArrayList<ListNode> arrayList = new ArrayList();
        ListNode curr = head.next;
        //遍历链表,每遍历一个元素存储一个val
        while(curr != null){
            arrayList.add(head);
            head = head.next;
            if(arrayList.contains(curr)){
                isCycled = true;
                return isCycled;
            }
            curr = curr.next;
        }
        return isCycled;
    }
}
