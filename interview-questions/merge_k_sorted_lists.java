package interview_questions;

import java.util.PriorityQueue;

/**
 * @description: 23. 合并K个升序链表
 * @author: Qr
 * @create: 2021-08-02 10:32
 *
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 示例 1：
 *
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 *
 * 示例 2：
 * 输入：lists = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：lists = [[]]
 * 输出：[]
 **/

//建立compare关系
class val2Index implements Comparable<val2Index>{
    int nodeVal;
    int listIndex;

    public val2Index(int nodeVal, int listIndex) {
        this.nodeVal = nodeVal;
        this.listIndex = listIndex;
    }

    @Override
    public int compareTo(val2Index v) {
        if (nodeVal != v.nodeVal){
            return nodeVal - v.nodeVal;
        }else {
            return listIndex - v.listIndex;
        }
    }
}

public class merge_k_sorted_lists {
    //这题想到了用一个数据结构去存取 k个链表的头, 但同时需要满足两个条件：
    // 1.这个数据结构按照val进行链表头的排序
    // 2.能直接取出数据结构中的第一个元素, 即最小val的链表头
    // 想到了堆，但忘了优先队列这个数据结构正好满足这个场景, 用node.val作为优先级, 每次直接取出队列第一个元素, 然后把该元素对应的链表的下一个元素入队.
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummyNode = new ListNode();
        ListNode curr = dummyNode;
        PriorityQueue<val2Index> queue = new PriorityQueue<>();
        //1.初始化
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null){
                val2Index v = new val2Index(lists[i].val,i);
                queue.add(v);
            }
        }
        //2.遍历队列
        while (!queue.isEmpty()){
            //1.取出队头元素v
            val2Index v = queue.poll();
            curr.next = new ListNode(v.nodeVal);
            curr = curr.next;
            //2.把v的下一节点加入队列并更新 listNode的head
            if (lists[v.listIndex].next != null){
                lists[v.listIndex] = lists[v.listIndex].next;
                val2Index nextV = new val2Index(lists[v.listIndex].val, v.listIndex);
                queue.add(nextV);
            }
        }
        return dummyNode.next;
    }
}
