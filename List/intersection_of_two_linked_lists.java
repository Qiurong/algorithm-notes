package List;

import com.sun.org.apache.bcel.internal.generic.LUSHR;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description: 160. 相交链表
 * @author: Qr
 * @create: 2021-07-21 11:18
 **/
public class intersection_of_two_linked_lists {
    //思路: 用一个set保存两个链表遍历过的node, 在每次遍历前 check set中是否已经有该结点
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> nodeSet = new HashSet<>();
        ListNode currA = headA;
        ListNode currB = headB;
        ListNode intersection = null;
        //遍历两个链表
        while (currA != null || currB != null) {
            //1.先check set是否包含结点, 包含则返回对应的结点
            if (check(nodeSet,currA,currB)){
                if (currA != null && nodeSet.contains(currA)){
                    intersection = currA;
                    break;
                }
                if (currB != null && nodeSet.contains(currB)){
                    intersection = currB;
                    break;
                }
            }else {
                //2. 遍历两个链表.
                //需要先判是不是到达尾部, 没到达的话先判断是否包含
                if (currA != null){
                    //包含则返回交点
                    if (nodeSet.contains(currA)){
                        intersection = currA;
                        break;
                    }else {
                        //不包含则加入set并遍历下一个结点
                        nodeSet.add(currA);
                        currA = currA.next;
                    }
                }
                if (currB != null){
                    if (nodeSet.contains(currB)){
                        intersection = currB;
                        break;
                    }else {
                        nodeSet.add(currB);
                        currB = currB.next;
                    }
                }
            }
        }
        return intersection;
    }

    /**
     * check nodeSet中是否包含a或b, 如果a和b为null, 则不check
     * @param nodeSet
     * @param a
     * @param b
     * @return
     */
    public boolean check(Set<ListNode> nodeSet, ListNode a, ListNode b){
        if (nodeSet.isEmpty()){
            return false;
        }
        boolean containsA = false;
        boolean containsB = false;
        if (a != null){
            containsA = nodeSet.contains(a);
        }
        if (b != null){
            containsB = nodeSet.contains(b);
        }
        return containsA || containsB;
    }

    //其实不用这么麻烦, 完全可以直接先遍历链表A, 然后把所有元素加入set, 然后遍历链表B, 判断是否存在
    public ListNode getIntersectionNode_set(ListNode headA, ListNode headB){
        //特判
        if (headA == null || headB == null){
            return null;
        }
        ListNode intersectionNode = null;
        ListNode currA = headA;
        ListNode currB = headB;
        Set<ListNode> nodeSet = new HashSet<>();
        //1.把所有链表A的结点放入set中
        while (currA != null){
            nodeSet.add(currA);
            currA = currA.next;
        }
        //2.遍历链表B 查看set是否包含
        while (currB != null){
            if (nodeSet.contains(currB)){
                intersectionNode = currB;
                break;
            }
            currB = currB.next;
        }
        return intersectionNode;
    }

    //第二种方法: 很像之前有个跑步追的一题 两个指针先分别指向headA和headB,走到当前链表的尾部后指向另一个链表的头部
    //          在这个过程中, 如果存在交点, 则两个指针指向该交点, 如果最后都为null, 则说明没有
    public ListNode getIntersectionNode_twoPointer(ListNode headA, ListNode headB){
        //特判
        if (headA == null || headB == null){
            return null;
        }
        ListNode p1 = headA;
        ListNode p2 = headB;
        //遍历, 当p1 p2都为null则停止遍历
        while (p1 != null || p2 !=null){
            //相交点
            if (p1 == p2){
                return p1;
            }
            p1 = p1.next;
            p2 = p2.next;
            //p1到表尾且p2不为空, 则p1指向另一链表
            if (p1 == null && p2 != null){
                p1 = headB;
            }
            if (p2 == null && p1 != null){
                p2 = headA;
            }
        }
        return null;
    }
}
