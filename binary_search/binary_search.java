package binary_search;

/**
 * @description: 704. 二分查找
 * @author: Qr
 * @create: 2021-02-26 17:19
 **/
public class binary_search {
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int targetPos = -1;
        int mid;
        while(left <= right){
            mid = left + ((right - left) >> 1);
            if(nums[mid] == target){
                targetPos = mid;
                break;
            }
            //在前半部分找
            else if (nums[mid] > target){
                right = mid - 1;
            }
            //在后半部分找
            else {
                left = mid + 1;
            }
        }
        return targetPos;
    }

    public static int searchTemplateTwo(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        int targetPos = -1;
        int mid;
        return targetPos;
    }

    public static int searchTemplateThree(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        int targetPos = -1;
        int mid;
        return targetPos;
    }

}
