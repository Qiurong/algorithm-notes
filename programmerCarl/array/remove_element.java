package programmerCarl.array;

/**
 * @description: 27. 移除元素
 * @author: Qr
 * @create: 2021-10-11 21:02
 **/
public class remove_element {

    //思路：扫描一遍数组, 同时带一个从后往前的尾指针, 遇到元素 == val 则与尾指针所指指针swap
    public int removeElement(int[] nums, int val) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int right = nums.length - 1;
        for (int left = 0; left <= right; left++) {
            if (nums[left] == val){
                //从后向前找到第一个非val元素, 把它放前面
                while (right > left && nums[right] == val){
                    right--;
                }
                nums[left] = nums[right];
                right--;
            }
        }
        return right+1;
    }

    //第二种: 相比于我的右指针逆序遍历, 可以用更容易理解的快慢指针来做
    //快指针用于遍历, 遇到非val的元素 写入慢指针所在位置
    //最差情况下： 整个数组没有val, 那么快慢指针各遍历一次, 我的方法 快慢指针加起来只遍历一次
    public int removeEle(int[] nums, int val){
        if (nums == null || nums.length == 0){
            return 0;
        }
        int slow = 0;
        int quick = 0;
        while (quick < nums.length){
            if (nums[quick] != val){
                nums[slow] = nums[quick];
                slow++;
            }
            quick++;
        }
        return slow;
    }
}
