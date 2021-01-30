package List;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 回文链表
 * @author: Qr
 * @create: 2021-01-29 10:22
 **/
//思路: 回文数组是判断[i] == [n-i-1],但链表不能这么做
public class palindrome_linked_list {
    //空间: 0(n),时间: O(n)
    //把链表装入数组,判断回文数组问题
    public boolean isPalindrome_ConvertToArray(ListNode head) {
        if(head == null){
            return true;
        }
        ArrayList<Integer> nodeValArray = new ArrayList<>();
        while(head != null){
            nodeValArray.add(head.val);
            head = head.next;
        }
        int size = nodeValArray.size();
        int j = (size >>> 1);
        for (int i = 0; i < j; i++) {
            if(!nodeValArray.get(i).equals(nodeValArray.get(size-1-i))){
                return false;
            }
        }
        return true;
    }
}
