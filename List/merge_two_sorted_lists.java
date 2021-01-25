package List;

/**
 * @description: 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * @author: Qr
 * @create: 2021-01-22 17:20
 **/
//思路：新建一个链表，循环吧两个链表的元素加入到新链表中。
public class merge_two_sorted_lists {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null){
            return null;
        }
        ListNode head = new ListNode();
        ListNode curr = head;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                curr.val = l1.val;
                l1 = l1.next;
            }else{
                curr.val = l2.val;
                l2 = l2.next;
            }
            //这边||是精华，两者只要有一个不为空，就新建next结点
            //两者皆空，则不新建节点
            //若两者一个为空一个不为空的情况下(两者初始都不为空),新建了curr.next且curr为一个待赋值的元素
            if(l1 != null || l2 != null){
                curr.next = new ListNode();
                curr = curr.next;
            }
        }
        //将l1和l2剩余元素添加到升序链表中
        while(l1 != null){
            curr.val = l1.val;
            l1 = l1.next;
            if(l1 != null){
                curr.next = new ListNode();
                curr = curr.next;
            }
        }
        while(l2 != null){
            curr.val = l2.val;
            l2 = l2.next;
            if(l2 != null){
                curr.next = new ListNode();
                curr = curr.next;
            }
        }
        return head;
    }
    //思考总结: 这题其实可以用不到O(n)的时间复杂度去完成.
    // 主要问题在于: 1.链表的构造与赋值不熟悉(curr.next什么时候构造,循环赋值容易出现空指针或者最后多出一个元素)
    //             2.l1/l2剩余元素赋值不需要遍历，但单纯curr = l1/l2又不对
    //再次尝试第二次版本
    public ListNode mergeTwoLists_secondEdition(ListNode l1, ListNode l2){
        //针对l1 l2不都为非空的小trick
        if(l1 == null){
            return l2;
        }else if(l2 == null){
            return l1;
        }
        //这里使用dummyNode作为head前一个元素，最后返回dummyNode.next
        ListNode dummyNode = new ListNode();
        ListNode curr = dummyNode;
        while(l1 != null && l2 != null){
            curr.next = new ListNode();
            curr = curr.next;
            if(l1.val < l2.val){
                curr.val = l1.val;
                l1 = l1.next;
            }else{
                curr.val = l2.val;
                l2 = l2.next;
            }
        }
        if(l1 != null){
            curr.next = l1;
        }
        if(l2 != null){
            curr.next = l2;
        }
        return dummyNode.next;
    }
    //思考总结: 这次优化了算法, 能让l1和l2剩余的链表直接连上去(不在需要遍历), 但执行速度还不是最优解...没考虑到只有一个非空的情况下直接链接就好

    //迭代解决问题,相比于我自己的第二版写的更加简洁
    public ListNode mergeTwoListsWithIteration(ListNode l1, ListNode l2){
        ListNode dummyNode = new ListNode();
        ListNode curr = dummyNode;
        while(l1 != null && l2 != null){
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

    //递归解决该问题
    public ListNode mergeTwoListsWithRecursion(ListNode l1, ListNode l2){
        if(l1 == null){
            return l2;
        } else if (l2 == null) {
            return l1;
        }else if(l1.val < l2.val){
            l1.next = mergeTwoListsWithRecursion(l1.next,l2);
            return l1;
        }else{
            l2.next = mergeTwoListsWithRecursion(l1, l2.next);
            return l2;
        }
    }

}
