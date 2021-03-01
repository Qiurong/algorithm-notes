package binary_search;

/**
 * @description: 33. 搜索旋转排序数组
 * @author: Qr
 * @create: 2021-03-01 17:22
 **/
public class search_in_rotated_sorted_array {
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int mid;
        while(start < end){
            mid = start + ((end - start) >> 1);
            if (nums[mid] == target){
                return mid;
            }
            else if (nums[mid] < target){
                //这时候需要去往比nums[mid]大的部分. 但需要根据情况讨论是去前半部分还是后半部分
                if (nums[end] == target){
                    return end;
                }
                //nums[end] < nums[mid], 此时 mid 之前不存在比mid大的数字
                else if (nums[end] < nums[mid]){
                    start = mid + 1;
                }else {
                    //nums[end] > nums[mid],
                    //nums[end] > nums[mid]的前提下, 需要去往比num[mid]大的部分,前后两部分都有可能比nums[mid]大 5 1 3
                    //num[end] > target，target属于[mid, end]
                    if (nums[end] > target){
                        start = mid + 1;
                    }
                    else {
                        //nums[end] < target, target属于前面
                        end = mid;
                    }
                }
            }
            //nums[mid] > target, 此时需要去比nums[mid]小的部分
            else {
                if (nums[end] == target){
                    return end;
                }
                //此时 mid之后不存在比mid小的数字
                else if (nums[end] > nums[mid]){
                    end = mid;
                }else {
                    //num[end]<nums[mid]
                    //此时比nums[mid]小的部分,两边都可能存在
                    //nums[end] < nums[mid]的前提下, 且nums[end] < nums[target]:去前面
                    if (nums[end] < target){
                        end = mid;
                    }else {
                        //nums[end] < nums[mid]的前提下, 且nums[end] > nums[target]: 来后面
                        start = mid + 1;
                    }
                }
            }
        }
        return nums[start] == target ? start : -1;
    }
}
