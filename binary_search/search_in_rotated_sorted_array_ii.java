package binary_search;

/**
 * @description: 81. 搜索旋转排序数组 II
 * @author: Qr
 * @create: 2021-03-02 13:51
 **/
public class search_in_rotated_sorted_array_ii {
    public boolean search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int mid;
        while (start < end) {
            mid = start + ((end - start) >> 1);
            if (nums[mid] == target) {
                return true;
            }
            //mid > target：需要去往小于mid的部分
            else if (nums[mid] > target) {
                //通过end 与 mid的大小关系来嵌套比较
                if (nums[end] == nums[mid]) {
                    end --;
                }
                //end > mid: 小于mid的部分 在 mid之前
                else if (nums[end] > nums[mid]) {
                    end = mid;
                }
                //end < mid: 小于mid的部分 可能在mid之前,也可能在mid之后。需要分情况讨论
                else {
                    //通过end 与 target的大小关系来嵌套比较
                    if(nums[end] == target){
                        return true;
                    }
                    //end > target: 小于mid的部分 在后部分
                    else if (nums[end] > target){
                        start = mid + 1;
                    }
                    //end < target: 小于mid的部分 在前部分
                    else {
                        end = mid;
                    }
                }
            }
            //mid < target：需要去往大于mid的部分
            else {
                //通过end 与 mid的关系嵌套比较
                if (nums[end] == nums[mid]){
                    end --;
                }
                //end < mid: 大于mid的部分在后面
                else if(nums[end] < nums[mid]){
                    start = mid + 1;
                }
                //end > mid: 大于mid的部分可能在前面, 也可能在后面 需要嵌套比较 end 与 target 关系
                else {
                    if (nums[end] == target){
                        return true;
                    }
                    //end > target + end > mid +  mid < target:  target属于[mid+1, end]
                    else if (nums[end] > target){
                        start = mid + 1;
                    }
                    //end < target + end > mid + mid < target: target属于[start, mid]
                    else {
                        end = mid;
                    }
                }
            }
        }
        return nums[start] == target ? true : false;
    }
}
