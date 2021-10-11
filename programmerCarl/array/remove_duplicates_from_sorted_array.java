package programmerCarl.array;

/**
 * @description: 26. 删除有序数组中的重复项
 * @author: Qr
 * @create: 2021-10-11 21:34
 **/
public class remove_duplicates_from_sorted_array {

    //快慢指针
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int slow = 0;
        int fast = 0;
        while (fast < nums.length){
            //重复元素选择最后一个
            while (fast < nums.length-1 && nums[fast] == nums[fast+1]){
                fast++;
            }
            nums[slow] = nums[fast];
            slow++;
            fast++;
        }
        return slow;
    }
}
