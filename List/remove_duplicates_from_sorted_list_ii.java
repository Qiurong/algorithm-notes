package List;

/**
 * @description: 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 * @author: Qr
 * @create: 2021-01-21 13:52
 **/

/**
 * 区别于上一个删除元素的题：需要将重复元素的首元素也需要删除。
 * 思路:用一个元素lastNode用于记录每次几个相同元素的上一元素，这样lastNode.next = 下一个与之前元素不相同的元素，从而删除了所有相同的元素
 * 问题: 1.lastNode初始值
 *      2.lastNode在循环如何赋值
 *      这种方法下的话不太好处理lastNode的赋值与更新。
 *      最后答案也是错的
 */
public class remove_duplicates_from_sorted_list_ii {
    public ListNode worong_deleteDuplicates(ListNode head){
        ListNode currentNode = head;
        //lastNode初始值为null
        ListNode lastNode = null;

        while(currentNode != null && currentNode.next != null){

            if(currentNode.val != currentNode.next.val){
                lastNode = currentNode;
                currentNode = currentNode.next;
            }
            else{
                while(currentNode.next!= null && currentNode.val == currentNode.next.val){
                    currentNode.next = currentNode.next.next;
                }
                if(lastNode == null){
                    lastNode = currentNode.next;
                    head = currentNode.next;
                }else{
                    lastNode.next = currentNode.next;
                }
                currentNode = currentNode.next;
            }
        }
        return head;
    }
    //链表中最常采用的思想之一：用dummy Node来作为head结点的上一个结点（用于head会被删除的情况中），最后返回dummy.next
    //链表常用套路：创建一个currentNode，赋初值为head,然后循环遍历对链表做出调整,但这题head可能会被删除，所以要付初值为dummyNode
    public ListNode deleteDuplicates(ListNode head){
        if(head == null){
            return head;
        }
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode currentNode = dummyNode;
        while(currentNode.next !=null && currentNode.next.next != null){
            if(currentNode.next.val == currentNode.next.next.val){
                //记录相同元素的值, 这里其实采用了我解上一题采用的思想即用一个额外的变量来存储需要删除元素的值
                int toDelval = currentNode.next.val;
                //找到第一个不相同的元素,并把next指针赋给它
                while(currentNode.next != null && currentNode.next.val == toDelval ){
                    currentNode.next = currentNode.next.next;
                }
            }else{
                currentNode = currentNode.next;
            }
        }
        return dummyNode.next;
    }

    //用两个变量prev和curr 更容易理解
    public ListNode deleteDuplicates_ii(ListNode head) {
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        //上一个非重复的结点
        ListNode prev = dummyNode;
        //下一个非重复的结点
        ListNode curr = head;

        //遍历每个结点
        while(curr != null){
            //对于重复结点就一直跳到下一个结点，然后再去重新迭代
            if (curr.next != null && curr.val  == curr.next.val){
                int val = curr.val;
                while (curr != null && curr.val == val) {
                    curr = curr.next;
                }
                //遍历到表尾
                if (curr == null){
                    prev.next = curr;
                }
            }else{  //只有curr不是重复结点才能进行连接
                prev.next = curr;
                prev = curr;
                if (curr != null){
                    curr = curr.next;
                }
            }
        }
        return dummyNode.next;
    }
}
