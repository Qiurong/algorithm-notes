package programmerCarl.array;

/**
 * @description: 34. 在排序数组中查找元素的第一个和最后一个位置
 * @author: Qr
 * @create: 2021-10-07 15:38
 **/
public class find_first_and_last_position_of_element_in_sorted_array {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        int start = -1;
        int end = -1;
        if (nums == null || nums.length == 0){
            res[0] = start;
            res[1] = end;
            return res;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (target == nums[mid]){
                //从mid开始两边伸展找到边界
                start = mid;
                end = mid;
                while (start >= 0 && nums[start] == target){
                    start--;
                }
                while (end < nums.length && nums[end] == target){
                    end++;
                }
                start += 1;
                end -= 1;
                break;
            }else if (target > nums[mid]){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        res[0] = start;
        res[1] = end;
        return res;
    }
}
