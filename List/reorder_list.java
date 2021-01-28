package List;


/**
 * @description: 重排链表: (LO,L1,L2,L3,...,Ln-2,Ln-1,Ln)---->(L0,Ln,L1,Ln-1,L2,Ln-2,L3.....)
 * @author: Qr
 * @create: 2021-01-27 22:52
 **/
/*
    思路: 取出[L1,L2,L3,...Ln-2,Ln-1,Ln], 把链表从中间断开,后半部分反转，即变成了
    [L1,L2,L3,..Ln/2]
    [Ln,Ln-1,Ln-2,....Ln/2+1]，把这两者合并
    [Ln,L1,Ln-1,L2,Ln-2,L3,....Ln/2+1,Ln/2]
    最后再把L0加上去.
 */
public class reorder_list {
    public void reorderList(ListNode head){
        if(head != null){
            //前半部分链表头
            ListNode l1Head = head.next;
            //快慢指针寻找链表中点
            ListNode fast = head;
            ListNode slow = head;
            //循环结束后: fast指向表尾next元素, slow为去除L1之后的链表的中点 / 中点next元素
            while(fast != null){
                fast = fast.next;
                if(fast != null){
                    slow = slow.next;
                    fast = fast.next;
                }
            }
            ListNode l2Head = slow.next;
            //前半部分表尾断开
            slow.next = null;
            ListNode reversed = reverList(l2Head);
            //合并两个链表
            ListNode dummyNode = new ListNode(-1,l1Head);
            ListNode curr = dummyNode;
            Boolean linkL2 = true;
            while(l1Head != null && reversed != null){
                if(linkL2){
                    curr.next = reversed;
                    reversed = reversed.next;
                }else{
                    curr.next = l1Head;
                    l1Head = l1Head.next;
                }
                curr = curr.next;
                linkL2 = !linkL2;
            }
            curr.next = l1Head == null? reversed : l1Head;
            head.next = dummyNode.next;
        }
    }
    public ListNode reverList(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode dummyNode = new ListNode(-1,head);
        ListNode prev = null;
        ListNode curr = dummyNode.next;
        ListNode next = null;
        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
