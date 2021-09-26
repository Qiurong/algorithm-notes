package programmerCarl.array;

/**
 * @description: 704. 二分查找
 * @author: Qr
 * @create: 2021-09-26 11:06
 *
 **/
public class binary_search {
    //升序数组 寻找 target， 返回 index 或 -1
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0){
            return -1;
        }
        int low = 0;
        int high = nums.length - 1;
        int index = recursion(nums, target, 0, nums.length-1);
        return index;
    }

    public int recursion(int[] nums, int target, int low, int high){
        if (low > high){
            return -1;
        }
        int mid = (low + high) / 2;
        if (nums[mid] == target){
            return mid;
        }else if (nums[mid] > target){
            return recursion(nums, target, low, mid-1);
        }else {
            return recursion(nums, target,mid+1, high);
        }
    }

    //非递归写法
    public int search_NonRecusion(int[] nums, int target){
        if (nums == null || nums.length == 0){
            return -1;
        }
        int low = 0;
        int high = nums.length - 1;
        int index = -1;
        //这里错误： low <= high
        while (low <= high){
            int mid = (low + high) / 2;
            if (nums[mid] == target){
                index = mid;
                break;
            }else if (nums[mid] < target){
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }
        return index;
    }
}
