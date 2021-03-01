package binary_search;

/**
 * @description: 154. 寻找旋转排序数组中的最小值 II
 * @author: Qr
 * @create: 2021-03-01 15:08
 **/
public class find_minimum_in_rotated_sorted_array_ii {

    //相比上一题多了可能存在重复元素.
    //原来的模板完全不可用,因为存在nums[mid] == nums[end]
    public int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        int mid;
        while (start < end){
            mid = start + ((end - start) >> 1);
            //其实本质都是迭代之后往哪半部分去转的问题，但相比之前存在了nums[start] nums[mid] nums[end]三者可能的问题
            //解决方法：对于相等的情况 继续取其中点比较
            //小的升序数组在后半部分
            if (nums[mid] > nums[end]){
                start = mid + 1;
            }
            //最小值肯定在前半部分
            else if (nums[mid] < nums[end]){
                end = mid;
            }
            //nums[mid] == nums[end]
            /*
                本来的想法: 这种情况下需要继续探讨区分出两种情况来决定往前半部分迭代还是后半部分迭代
                区分的方法：去比较nums[start]和nums[end]的大小：start小, 则迭代前半部分; end小, 则迭代后半部分
                但当遇到nums[start] == nums[end]时无法去做了,
                想法是取前半部分的前半部分的mid值与后半部部分的mid值, 又一次当这两者相等时候，就没法进行了。
                官方题解：nums[mid] == nums[end], 则说明nums[end]肯定能在前面找到替代品, 直接把区间向前缩一即可。
                真的是完全没想到...这个做法简单清晰又明了
             */
            else{
                end --;
            }
        }
        return nums[start];
    }
}
