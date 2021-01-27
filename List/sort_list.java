package List;

import java.util.List;

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
    //总结: 用递归的方法时间复杂度O(nlogn), 空间复杂度O(logn),可以用迭代的方法代替递归从而减低空间复杂度

    //自底向上排序: 本质上就是把递归cut的方法改为迭代
    //            cut: 用一个subLength表示每轮需要cut的子链表长度, 不断x2,迭代cut(sublength初始值为1，终值为listlength)
    //            merge: merge每轮cut之后sublength的子链表
    //            第一轮直接把链表cut成单个元素,然后[1,2],[2,3]...合并, sublengthx2,[1-2,3-4][5-6,7-8]合并....
    public ListNode sortListBottomToUp(ListNode head){
        if(head == null){
            return head;
        }
        //链表长度
        int listLength = 0;
        ListNode temp = head;
        while(temp != null){
            listLength++;
            temp = temp.next;
        }
        //每轮cut的子链表长度
        int subLength = 1;
        //每一轮的cut与merge
        ListNode dummyNode = new ListNode(-1,head);
        //sublength>=listlength时停止循环
        while(subLength < listLength ){
            //原链表头部
            ListNode prev = dummyNode;
            ListNode curr = prev.next;
            //当前轮cut和merge
            while(curr != null){
                ListNode h1 = curr;
                //往后遍历sublength-1,是为了遍历到list1的尾部
                for (int i = 0; i < subLength-1 && curr.next != null; i++) {
                    curr = curr.next;
                }
                //此时curr为list1的尾部, 需要断开, h2为curr.next
                ListNode h2 = curr.next;
                //把list1尾部断开
                curr.next = null;
                //再去寻找h2尾部并把尾部断开
                curr = h2;
                //curr可能为null(list1.length < subLength
                for (int i = 0; i < subLength - 1 && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }
                //此时curr为list2尾部// 链表尾部(list2.length < subLength)
                ListNode nextRoundHead = null;
                if(curr != null){
                    nextRoundHead = curr.next;
                    //把list2尾部切割
                    curr.next = null;
                }
                ListNode merged = merge(h1,h2);
                //prev为当前merge好的两个sublist的dummyNode
                prev.next = merged;
                //把prev移动至subList的尾部,为了下一轮进行prev.next = merged(吧前一个subList与后一个subList连接起来)
                while(prev.next != null){
                    prev = prev.next;
                }
                //进行下一轮
                curr = nextRoundHead;
            }
            //为下一轮迭代准备
            subLength = subLength << 1;
        }
        return dummyNode.next;
    }
    //自定向下方法: 吧递归cut的过程换成了迭代cut,最难的部分是两层循环的时侯如何去进行链表的操作,涉及到遍历、寻找、断开等等操作
}
