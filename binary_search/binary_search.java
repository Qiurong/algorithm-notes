package binary_search;

/**
 * @description: 704. 二分查找
 * @author: Qr
 * @create: 2021-02-26 17:19
 **/
public class binary_search {
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int targetPos = -1;
        int mid = (start + end) >> 1;
        while(start <= end){
            if(nums[mid] == target){
                targetPos = mid;
                break;
            }
            else if (nums[mid] > target){
                end = mid;
            }
            else {
                start = mid;
            }
            mid = (start + end) >> 1;
        }
        return targetPos;
    }
}
