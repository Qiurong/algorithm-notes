package List;


import java.util.ArrayList;
import java.util.List;

/**
 * @description: 反转一个单链表
 * @author: Qr
 * @create: 2021-01-21 17:07
 **/
//思路：用一个递归去做，不断往回回溯,回溯的过程就能一步步的从后往前去赋值next指针
public class reverse_linked_list {
    //自己错误版本
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode prev = reverseList(head.next);
        //错在不知道这里怎么处理
        prev.next = head;
        return reverseList(head.next);
    }

    //递归正确版本
    public static ListNode reverseListWithrecursion(ListNode head){
        //1.递归终止条件
        if(head == null || head.next == null){
            return head;
        }
        ListNode newHead = reverseListWithrecursion(head.next);
        //2.单步处理部分，也就是这部分之前没想通
        //原本是nk--->nk+1, 首先 nk.next.next = nk 即 nk+1.next指向nk(此时nk.next指向nk+1,nk+1.next指向nk),需要令nk.next置空
        head.next.next = head;
        head.next = null;
        //newhead在整个过程中都没有变过，始终为原表尾
        return newHead;
    }

    //测试一下自己的一个想法:链表完成了反转的功能但最后返回的是表尾(原表头)
    //区别在于一个是始终返回原表尾，一个是递归向前返回当前元素
    //因此后者可以用newhead.next
    public ListNode reverListTest(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode newHead = reverListTest(head.next);
        newHead.next = head;
        head.next = null;
        return head;
    }

    //第二种遍历解法：
    //一开始想不出来是不知道怎么去处理, 其实就是需要两个额外变量prev和next去保存遍历中的前后结点
    public static ListNode reverListWithTraverse(ListNode head){
        if(head == null){
            return head;
        }
        //用于遍历中存储上一个结点，初值为Null,始终是curr的上一个节点,curr一开始为head,所以为null
        ListNode prev = null;
        //用于遍历中暂时存储下一个结点
        ListNode next = null;
        ListNode curr = head;
        while(curr != null){
            //1.保留下一结点，不然后面没法引用下一个元素
            next = curr.next;
            //2.改变curr next指针,让curr next指向前一个元素
            curr.next = prev;
            //3.将prev和curr往后移
            prev = curr;
            curr = next;
        }
        //最后curr指向原表尾元素, curr为原表尾元素下一元素
        return prev;
    }

    public static void main(String[] args) {
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4,node5);
        ListNode node3 = new ListNode(3,node4);
        ListNode node2 = new ListNode(2,node3);
        ListNode node1 = new ListNode(1,node2);
        ListNode node = reverListWithTraverse(node1);
    }
}
