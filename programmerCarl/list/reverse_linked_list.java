package programmerCarl.list;

/**
 * @description: 206. 反转链表
 * @author: Qr
 * @create: 2021-10-20 22:18
 **/
public class reverse_linked_list {
    public ListNode reverseList(ListNode head) {
        /*if (head == null || head.next == null){
            return head;
        }*/
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null){
            ListNode next = curr.next;
            curr.next = prev;
            //先更新prev,再更新 curr
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public ListNode reverseList_recursion(ListNode head){
        ListNode reversed = recursion(head);
        return reversed;
    }

    //递归的思路：   假设  curr 之后的 全部反转了(curr没有反转)
    //在这一步要做的就是  curr.next.next = curr (反转curr本身)
    public ListNode recursion(ListNode curr){
        //递归三部曲第一步：确定最后的终止条件
        if (curr == null || curr.next == null){
            return curr;
        }
        //先递归的去反转 next
        ListNode reversedNext = recursion(curr.next);
        //在每一层的递归做两件事：
        //1.先让 next的next指针指向curr 即反转 curr本身
        curr.next.next = curr;
        //2.同时断开 curr的next指针
        curr.next = null;
        //最后return 递归回来的值  即 最后一个结点
        return reversedNext;
    }

    //思考：自己这遍做的时候一直在思考，递归的每一层做什么，当时自己在思考 递归返回的值 需要跟当前层的值进行一个 关联, 例如设置next等操作
    //但实际上 递归做的事， 每层把当前结点反转(下一节点的next指向当前结点)，断开当前结点的next指针， 然后不断递归逐步返回在整个过程中没有处理的reversedNext
    //先递归，再操作。  表明整个过程是一个从后向前迭代的的执行过程
}
