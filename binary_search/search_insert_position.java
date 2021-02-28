package binary_search;

/**
 * @description: 35. 搜索插入位置
 * @author: Qr
 * @create: 2021-02-28 16:38
 **/
public class search_insert_position {
    //这题可以用模板2来做, 最终不断夹出一个最终的位置
    public int searchInsert(int[] nums, int target) {
        //[left, right)
        int left = 0;
        int right = nums.length;
        int mid;
        //最终[left, right) left == right
        while(left < right){
            mid = left + ((right - left) >> 1);
            //这边必须先判断<, 否则碰上nums[mid] == target的情况 left = mid + 1,就会把最终的下标错过
            //对于nums[mid] == target的情况，先判断 < 的话，则区间变为[left, mid)
            //                             先判断 > 的话，则区间变为[mid + 1, right) 错误
            if (nums[mid] < target){
                //后半部分：[mid + 1, right)
                left = mid + 1;
            }else {
                //前半部分：[left, mid)
                right = mid;
            }
        }
        int targetPos = left;
        return targetPos;
    }
}
