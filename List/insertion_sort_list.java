package List;

/**
 * @description: 147. 对链表进行插入排序
 * @author: Qr
 * @create: 2021-03-03 15:22
 **/
public class insertion_sort_list {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        //加上dumpNode方便最后返回head结点
        ListNode dummyNode = new ListNode(Integer.MIN_VALUE,head);
        //从第二个结点开始迭代
        ListNode curr = head.next;
        ListNode lastSorted = head;
        while (curr != null){
            //从头开始寻找插入结点的位置. 插入操作可以从后向前迭代, 也可以从前向后迭代.
            //链表结构决定了其只从前向后迭代, 找到该插入的地方, 把元素插入进去.
            //当前元素的前面是一个有序列表, lastSorted > curr 则说明需要将curr插入到前面的有序序列中
            if (lastSorted.val > curr.val){
                //把curr从链表中移除
                lastSorted.next = curr.next;
                curr.next = null;

                //从头开始遍历, 找到其插入位置迭代到curr结点.
                ListNode startNode = dummyNode;
                //向后找到第一个大于curr的结点
                while (startNode.next.val <= curr.val){
                    startNode = startNode.next;
                }
                //插入curr
                curr.next = startNode.next;
                startNode.next = curr;

                //往后迭代. 不需要改变lastSorted.
                curr = lastSorted.next;
            }
            //不需要变动
            else {
                lastSorted = curr;
                curr = curr.next;
            }
        }
        return dummyNode.next;
    }
}
