package recursion;


/**
 * @description:
 * @author: Qr
 * @create: 2021-04-12 22:36
 **/



public class palindrome_linked_list {
    //判断回文最直接的方法就是双指针head和tail从两端开始遍历直到中间
    //对于单链表，难点就在于如何反向遍历。
    //递归为我们提供了一种方法，递归反向迭代, 同时使用函数外的变量向前迭代。
//    public void reverse_traversal_recursion(ListNode head){
//        if (head != null){
//            //先递归到子问题，然后在遍历当前结点
//            reverse_traversal_recursion(head.next);
//            System.out.println(head.val);
//        }
//    }

    private ListNode frontPointer; //用于从前向后迭代

    public boolean isPalindrome(ListNode head) {
        frontPointer = head;
        return recursion(head);
    }


    //currentNode用于从后往前迭代
    public boolean recursion(ListNode currentNode){
        //递归终止条件
        if (currentNode != null){
            if (!recursion(currentNode.next)){
                return false;
            }
            if (frontPointer.val != currentNode.val){
                return false;
            }
            frontPointer = frontPointer.next;
        }
        return true;
    }
}
