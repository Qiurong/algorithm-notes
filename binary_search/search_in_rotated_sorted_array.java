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
            //第一层：根据nums[mid] 与 target的 关系去比较
            if (nums[mid] == target){
                return mid;
            }
            //mid < target: 需要去比mid大的部分
            else if (nums[mid] < target){
                //第二层：根据 num[end] 与 nums[mid] 的关系来去到比mid大的部分
                //end < mid：[start, mid]严格升序, 比mid大的部分在[mid + 1, end]之间.
                if (nums[end] < nums[mid]){
                    start = mid + 1;
                }else {
                    //end > mid:  [mid, end]严格升序, 最小值在[start, mid]之间,
                    //升序数组：[mini, mid], [mid, end], [start, mini -1]
                    //target其实属于[mid, end] [start, mini-1]这两个区间其一。
                    //根据a[end] 与 target 比较结果决定往哪个区间去迭代。
                    if (nums[end] == target){
                        return end;
                    }
                    //num[end] > target，target属于[mid, end]
                    else if (nums[end] > target){
                        start = mid + 1;
                    }
                    else {
                        //nums[end] < target, target属于[start, mini-1]
                        end = mid;
                    }
                }
            }
            //nums[mid] > target, 此时需要去比nums[mid]小的部分
            else {
                //第二层：根据nums[end] 与 [mid]的关系去到比[mid]小的部分
                //end > mid: [mid, end]严格升序, 去往[start,mid]部分
                if (nums[end] > nums[mid]){
                    end = mid;
                }else {
                    //end < mid：[start, mid]严格升序,最小值在[mid, end]之间
                    //升序数组：{[mini, end], [start, mid], [mid, mini -1]}
                    //target其实属于[start, mid], [mini, end]之间
                    //根据[end] 与 target 比较结果去迭代。
                    if (nums[end] == target){
                        return end;
                    }
                    //[end] < target: target属于[start, mid]
                    else if (nums[end] < target){
                        end = mid;
                    }else {
                        //[end] > target: target属于[mini,end]
                        start = mid + 1;
                    }
                }
            }
        }
        return nums[start] == target ? start : -1;
    }
}
