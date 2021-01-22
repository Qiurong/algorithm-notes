package List;

/**
 * @description: 反转从位置m到n的链表。一趟扫描完成反转
 * @author: Qr
 * @create: 2021-01-22 16:38
 **/
//思路：用迭代法则，进行一次遍历，遍历到m-n元素时反转,同时最后在将连接到原链表上去即完成两个next指针的赋值
// 需要额外变量记录m-1,m元素。(n和n+1为反转时的prev，next指针)
public class reverse_linked_list_ii {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode prevmNode = null;
        ListNode mNode = null;
        ListNode curr = head;
        ListNode prev = null;
        ListNode next = null;
        for(int i = 1; curr != null; i++){
            //记录m-1元素
            if(i == m-1){
                prevmNode = curr;
            }
            if (i == m){
                //记录m元素
                mNode = curr;
                //开始反转
                while(i <= n){
                    next = curr.next;
                    curr.next = prev;
                    prev = curr;
                    curr = next;
                    i++;
                }
                //反转完成后此时prev为n元素, next为n+1元素
                // 将(n......m)连接成(m-1,n.....m,n+1)
                //置原m元素的next为 n+1元素
                mNode.next = next;
                //置m前一个元素的next为原n元素
                //若prevM结点为空，则此时相当于从第一个结点开始反转,需要返回prev结点即(n....m,n+1)
                if(prevmNode == null){
                    return prev;
                }
                prevmNode.next = prev;
                break;
            }
            curr = curr.next;
        }
        return head;
    }
}
