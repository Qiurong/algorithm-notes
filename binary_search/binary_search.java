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
        //每次搜索的区间是：[left, right] ---->[left, mid - 1], mid, [mid + 1, right)
        int targetPos = -1;
        int mid;
        //终止条件: left == right + 1, 此时[left, right]区间为空, 所以循环结束后没有其他元素, 不需要进行后处理.
        while(left <= right){
            mid = left + ((right - left) >> 1);
            if(nums[mid] == target){
                targetPos = mid;
                break;
            }
            //在后半部分找
            //[left, right]: mid索引已经判断过了, 决定了后半部分区间是[mid + 1, right]
            else if (nums[mid] < target){
                left = mid + 1;
            }
            //在前半部分找
            //[left, right]: mid索引已经判断过了, 决定了前半部分区间是[left, mid - 1]
            else {
                right = mid - 1;
            }
        }
        return targetPos;
    }

    public static int searchTemplateTwo(int[] nums, int target){
        int left = 0;
        int right = nums.length;
        //对应的区间是[left, right) 左闭右开 ---->[left,mid) [mid + 1, right)
        int targetPos = -1;
        int mid;
        //终止条件: left == right, 此时[left, right)为一个元素, 需要进行后处理
        while(left < right){
            mid = left + ((right - left) >> 1);
            if (nums[mid] < target){
                //在后半部分查找
                //mid已经判断过了, 所以为[mid + 1, right) 而不是[mid, right)
                left = mid + 1;
            }else {
                //在前半部分找
                //mid已经判断过了, 所以前半部分是[left, mid) 右开：mid不再需要去判断
                right = mid;
            }
        }
        //此时定位到只剩下一个元素：left/right元素(left == right).
        //后处理: 需要判断left下标是否越界, 可能此时left == right ==nums.length
        if(left!= nums.length && nums[left] == target){
            targetPos = left;
        }
        return targetPos;
    }

    public static int searchTemplateThree(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        //对应的区间：[left, right]
        int targetPos = -1;
        int mid;
        //终止条件: left + 1 == right, 最终为[left, right / left + 1] 有两个元素
        while (left + 1 < right){
            mid = left + ((right - left) >> 1);
            if (nums[mid] < target){
                //[mid, right]
                left = mid;
            }else{
                //[left, mid]
                right = mid;
            }
        }
        //后处理, 此时 left + 1 == right, [left, right]
        if(nums[left] == target){
            targetPos = left;
        }
        if (nums[right] == target){
            targetPos = right;
        }
        //循环结束后剩余两个元素: left, right元素
        return targetPos;
    }

    public static void main(String[] args) {
        int [] nums = new int[]{1, 1, 1, 1, 1, 1};
        int target = 2;
        //int targetPos = searchTemplateTwo(nums, target);
        int targetPos = searchTemplateThree(nums, target);
    }
}
