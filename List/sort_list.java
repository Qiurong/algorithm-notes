package List;

/**
 * @description: 给你链表的头结点,请将其按升序排列并返回排序后的链表 。
 * @author: Qr
 * @create: 2021-01-25 16:50
 **/
//思路: 参考官方后给出归并排序解法.
/*
    归并排序伪代码:
    mergeSort(array, startPos, endPos){
        if(startPos >= endPos){
            return;
        }
        int mid = (startPos + endPos) >>> 1;
        mergeSort(array, startPos, mid);
        mergeSort(array, mid + 1, endPos);
        merge(array,startPos,mid,endPos){
            tempArray = new int[endPos - startPos + 1];
            int i = startPos;
            int j = mid + 1;
            int n = 0;
            while(i<=mid && j<= endPos){
                if(array[i] < array[j]){
                    tempArray[n++] = array[i++];
                }else{
                    tempArray[n++] = array[j++];
                }

            }
            while(i <= mid){
                tempArray[n++] = array[i++];
            }
            while(j <= endPos){
                tempArray[n++] = array[j++];
            }
            for(int m = 0; m < n;m++){
                array[m + bgn] = tempArray[m];
            }
        }
    }

 */


public class sort_list {
    //自顶向下归并排序
    //归并排序(递归):1.以中点拆分(用快慢指针去寻找中点)
    //             2.两个有序链表merge()
    public ListNode sortList_Easy(ListNode head) {
        //tail为排序链表的尾部元素的next元素
        ListNode newHead = sortListUpToBottom(head,null);
        return newHead;
    }
    //tail不是最后一个元素,而是最后一个元素的next元素,不然如果是尾部元素的话无法在刚开始传参给tail
    public ListNode sortListUpToBottom(ListNode head,ListNode tail){
        //递归结束条件: 没有元素/一个元素
        if(head == null){
            return head;
        }
        //只有一个元素的情况下直接返回，该元素就是head元素，同时要把该元素切割出去即(head.next = null)
        if(head.next == tail){
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = head;
        //快慢指针来寻找中点,但需要注意的是这里的Mid并不是中间元素,而是中间元素的next元素
        //偶数个,mid = 中隔线的next, 这样正好[head,mid]与[mid,tail]元素个数相等(再次提醒tail为尾部元素的next)
        //奇数个,mid = 中间元素的next,这样[head,mid]包含了中间元素(即多一个元素)
        while(fast != tail){
            fast = fast.next;
            slow = slow.next;
            if(fast != tail){
                fast = fast.next;
            }
        }
        //mid为链表尾部元素的next指针
        ListNode mid = slow;
        //对应的是[head,mid-1]
        ListNode l1 = sortListUpToBottom(head,mid);
        //对应的是[mid,tail-1] tail为尾部元素的next元素
        ListNode l2 = sortListUpToBottom(mid,tail);
        ListNode l  = merge(l1,l2);
        return l;
    }
    public ListNode merge(ListNode l1, ListNode l2){
        ListNode dummyNode = new ListNode(0);
        ListNode curr = dummyNode;
        while(l1 != null && l2!= null){
            if(l1.val < l2.val){
                curr.next = l1;
                l1 = l1.next;
            }else{
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        curr.next = l1 == null ? l2 : l1;
        return dummyNode.next;
    }
}
