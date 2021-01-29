package List;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description: 返回环形链表的入环的第一个结点
 * @author: Qr
 * @create: 2021-01-28 16:13
 **/
public class linked_list_cycle_ii {
    //用哈希表来做, 时间: O(n), 空间: O(n)
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null){
            return null;
        }
        Set<ListNode> nodeSet = new HashSet<>();
        while(head != null){
            if(!nodeSet.add(head)){
                return head;
            }
            head = head.next;
        }
        return null;
    }

    //快慢指针来做, 时间:O(n) 空间:O(1)两个指针
    //1.快慢指针同时出发，当他们在环内相遇的时候,把fast放回头部,置fast速度为1,他们最终会在入环点相遇.
    public ListNode detectCycle_FastAndSlowPointer(ListNode head){
        if(head == null || head.next == null){
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        Boolean hasCycle = false;
        while(fast != null && fast.next != null){
            //先移动
            fast = fast.next.next;
            slow = slow.next;
            //后判等
            if(slow == fast){
                hasCycle = true;
                //把fast置于head
                fast = head;
                break;
            }
        }
        ListNode cycleEntry = null;
        if(hasCycle){
            while(fast != slow){
                fast = fast.next;
                slow = slow.next;
            }
            cycleEntry = slow;
        }
        return cycleEntry;
    }

    //重构从而减少了一个boolean和ListNode的空间
    public ListNode detectCycle_FastAndSlowPointer_Refactor(ListNode head){
        if(head == null || head.next == null){
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        //先判断有没有环
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            //有环: 置fast于头部,一步一步移动直到入环点
            if(slow == fast){
                fast = head;
                while(fast != slow){
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }
}
