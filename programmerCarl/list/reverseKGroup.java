package programmerCarl.list;



/**
 * @description: k个一组反转链表
 * @author: Qr
 * @create: 2021-10-21 10:14
 *
 * 12345  k = 3
 * 321 54
 **/

public class reverseKGroup {

    //一个链表长度固定n，k个一组进行反转. 不足k的也需要反转
    //自己写的磕磕绊绊的代码
    //本质是记录 上一组翻转之后的尾节点, 然后在当前组反转完后 进行两组之间的连接
    public static ListNode reverseList(ListNode head, int k){
        if (head == null || head.next == null){
            return head;
        }
        ListNode prev = null;
        ListNode curr = head;
        //当前组的上一组反转后的结点
        ListNode prevKReversedEnd = null;
        //最后返回的结果
        ListNode reversedHead = null;
        while (curr != null){
            //临时结点 记录当前k组的开头 即 反转后的结点
            ListNode temp = curr;
            //反转k个
            for (int i = 0; i < k; i++) {
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
                //加一层判断, 当前剩余链表结点个数不足k个
                if (curr == null){
                    break;
                }
            }
            //k个一组反转完成之后
            //prevKReversedEnd说明为第一组反转
            if (prevKReversedEnd == null){
                reversedHead = prev;
            }else {
                //吧上一组结尾跟这一组反转后的头进行连接
                prevKReversedEnd.next = prev;
            }
            prevKReversedEnd = temp;
            prev = null;
        }
        return reversedHead;
    }

    //k个一组, 不足k个的不反转
    //迭代法: 首先按照k个一组，进行分类， 组内结点反转非常简单， 但难点在于如何吧当前组和上一组链接起来.
    //需要解决的问题： 1. 判断k个一组，不足k个不予反转
    //              2. 组内进行反转
    //              3.反转完成后 reversedHead跟前面一组的tail连接上，
    //              4.反转完成后 reversedTail跟后面一组未反转的head连接上
    //做法： 增设一个dummyNode， (a)这样可以第一组反转之后，上一组tail为dummyNode
    //                        (b) 最后return dummyNode.next
    public ListNode reverseList_iteration(ListNode head, int k){
        if (head == null || head.next == null){
            return head;
        }
        //增设的dummyNode结点
        ListNode dummyNode = new ListNode(-1,head);
        //迭代反转链表所用的curr和prev
        ListNode curr = head;
        ListNode prev = null;
        //上组反转之后的tail
        ListNode preReversedTail = dummyNode;
        while (curr != null){
            //1.验证当前是否有k个结点
            ListNode end = curr;
            for (int i = 0; i < k; i++) {
                //遍历到最后一组, 不足k个结点
                if (end == null){
                    return dummyNode.next;
                }
                end = end.next;
                //遍历完之后end为下一组的head
            }
            //2.迭代反转
            //先记录下反转之后的tail
            ListNode reversedTail = curr;
            for (int i = 0; i < k; i++) {
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
                //遍历完之后prev为reversedHead, curr为下一组head
            }
            //吧前一组与当前组接上
            preReversedTail.next = prev;
            //吧当前组的与下一组连接上
            reversedTail.next = curr;
            //更新上一组的tail
            preReversedTail = reversedTail;
            //更新prev
            prev = null;
        }
        return dummyNode.next;
    }
    //总结： 这题的难点就在于 如何设计去进行反转， 反转之后 当前组与前一组  当前组与下一组的连接，  在这之中需要设置几个额外变量来记录，并且如何更新这些变量
    //      就比如反转之后的head与tail 与原来的head与tail完全不同，  同时prev作为反转后的head, 在进入下一次迭代时需要置空.
    //      链表的题目还是要多画图，自己去纸上模拟
}
