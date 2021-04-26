package List;

/**
 * @description: 2. 两数相加
 * @author: Qr
 * @create: 2021-04-26 09:22
 **/
public class add_two_numbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(0);
        ListNode curr = dummyNode;
        ListNode curr1 = l1;
        ListNode curr2 = l2;
        int carry = 0;  //进位

        while(curr1 != null && curr2 != null){
            int currVal = curr1.val +  curr2.val + carry;
            carry = currVal / 10;
            currVal = currVal % 10;
            ListNode node = new ListNode(currVal);
            curr.next = node;
            curr = curr.next;
            curr1 = curr1.next;
            curr2 = curr2.next;
        }

        //把剩余位链接上去
        while (curr1 != null){
            int currVal = curr1.val + carry;
            carry = currVal/10;
            currVal = currVal % 10;
            ListNode node = new ListNode(currVal);
            curr.next = node;
            curr = curr.next;
            curr1 = curr1.next;
        }
        while (curr2 != null){
            int currVal = curr2.val + carry;
            carry = currVal/10;
            currVal = currVal % 10;
            ListNode node = new ListNode(currVal);
            curr.next = node;
            curr = curr.next;
            curr2 = curr2.next;
        }

        //最后的进位不要忘了
        if (carry != 0){
            ListNode node = new ListNode(carry);
            curr.next = node;
            curr = curr.next;
        }

        ListNode resHead = dummyNode.next;
        //断掉dummyNode
        dummyNode.next = null;
        //逆转结果链表
        ListNode res = reverseList(resHead);
        return res;
    }

    //逆转一个链表
    public ListNode reverseList(ListNode head){
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
