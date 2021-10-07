package programmerCarl.array;

/**
 * @description: 35. 搜索插入位置
 * @author: Qr
 * @create: 2021-10-07 10:43
 **/
public class search_insert_position {

    public static int searchInsert(int[] nums, int target) {
        //判空
        if (nums == null || nums.length == 0){
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        int index = -1;
        while (left <= right){
            int mid = left + ((right - left) >> 1);
            if (target == nums[mid]){
                index = mid;
                break;
            }else if (target < nums[mid]){
                //前半部分
                right = mid - 1;
            }else {
                //后半部分
                left = mid + 1;
            }
            if (left > right){
                if (right < 0 || left >= nums.length){
                    index = right < 0 ?  0 : nums.length;
                } else if (target < nums[right]){
                    index = right;
                }else if (target > nums[right]){
                    index = right + 1;
                }else {
                    index = left + 1;
                }
                break;
            }
        }
        return index;
    }

    //本质： 在一个有序数组中找  第一个大于等于target  的下标
    //更好的方法：最后找不到的情况下left就是插入位置. 前提条件 使用的是[left, right]
    //在一直找不到的情况下, 最后一次循环分为两种:
    /**
     * 1. left == right 此时[left,right] 同一个元素, mid == left == right
     *                  若 target < nums[mid] --> right = mid -1, 循环结束。targetPos应该为left, 因为target < [left].
     *                  若 target > nums[mid] --> left = mid + 1, 循环结束。targetPos应该为left, 因为[right] < target < [left].
     * 2. left == right - 1, 此时[left, right] 两个元素, mid == left
     *                  若 target < nums[mid] --> right = mid - 1,循环结束. targetPos为left, 因为target < [left]
     *                  若 target > nums[mid] --> left = mid + 1, 此时 left == right. 进入情况1
     */
    public int searchInsert_optimized(int[] nums, int target){
        int left = 0;
        int right = nums.length -1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (target == nums[mid]){
                left = mid;
                break;
            }else if (target > nums[mid]){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return left;
    }
}
