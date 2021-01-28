package List;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @description: 判断链表中是否有环
 * @author: Qr
 * @create: 2021-01-28 11:11
 **/

public class linked_list_cycle {
    //简单版本: 用一个数组存储链表元素,最后判断数组是否有元素,时间复杂度O(n^2), 空间复杂度0(n)
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
    //同样是遍历所有节点,到每个节点的时侯判断之前有没有相同的结点,但更好的方法是用哈希表.
    //遍历过程中,用哈希表去存储每一个结点,如果某个节点已经在哈希表中存在, 则说明有环
    //时间复杂度: O(n), 空间复杂度: O(n)
    public boolean hasCycle_hashSet(ListNode head){
        Set<ListNode> nodeHashSet = new HashSet<>();
        while (head != null){
            if(!nodeHashSet.add(head)){
                return true;
            }
            head = head.next;
        }
        return false;
    }
}
