package List;

/**
 * @description: 给你一个链表和一个特定值 x ，请你对链表进行分隔，使得所有小于 x 的节点都出现在大于或等于 x 的节点之前。
 * @author: Qr
 * @create: 2021-01-25 15:18
 **/
//初始思路:新建一条链表,遍历一遍把所有小于x的元素拿出来放入新链表,遍历完成后把旧链表链接到新链表上
//       时间复杂度: O(n), 空间复杂度: O(n)
//       难点:单链表中, 如何在遍历的同时把当前元素删除，再加一个变量.
//总结: 看了官方解答之后, 与我思想不同在于, 官方多开辟了一个链表的内存空间,用一大一小链表(而我是只开辟小的,大的从原来的删除)
//      很明显,多开辟一个链表要简单得多.
public class partition_list {
    /*
    public ListNode partition(ListNode head, int x) {
        if (head == null){
            return null;
        }
        ListNode newPreHead = new ListNode();
        ListNode newHead = newPreHead;
        ListNode PreHead = new ListNode(-1, head);
        ListNode curr = PreHead;
        ListNode currAfterDel = PreHead;
        while(curr.next != null){
            if(curr.next.val < x){
                //把当前元素赋值给新链表
                newHead.next = curr.next;
                //把新链表指针后移
                newHead = newHead.next;
                //把当前元素从链表中抽取掉(这边实现不了)
                ListNode temp = currAfterDel.next;
                currAfterDel.next = currAfterDel.next.next;

                currAfterDel = currAfterDel.next;
            }
            curr = curr.next;
        }
        //把旧链表剩余部分链接上去
        newHead.next = PreHead.next == null ? null : PreHead.next;
        return newPreHead.next;
        }
     */

    public ListNode partition(ListNode head, int x){
        ListNode preSmall = new ListNode(-1);
        ListNode small = preSmall;
        ListNode preLarge = new ListNode(-1);
        ListNode large = preLarge;
        while(head != null){
            if(head.val < x){
                small.next = head;
                small = small.next;
            }else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        //把large链表链接到small链表上,同时把
        small.next = preLarge.next;
        large.next = null;
        return preSmall.next;
    }
}
