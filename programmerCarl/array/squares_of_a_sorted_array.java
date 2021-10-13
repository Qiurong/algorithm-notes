package programmerCarl.array;

/**
 * @description: 977. 有序数组的平方
 * @author: Qr
 * @create: 2021-10-13 22:02
 **/
public class squares_of_a_sorted_array {
    //思想：双指针法. 双指针赋初始值为数组中绝对值最小的元素下标, 然后往两边遍历
    //双指针法初始值是个需要确定的问题, 一般而言都是数组第一个元素, 但844逆序遍历初始值就为末尾, 这题在中间
    public int[] sortedSquares(int[] nums) {
        int[] res = new int[nums.length];
        int minIndex = 0;
        //先扫描一遍, 找出其中绝对值最小的元素下标
        for (int k = 0; k < nums.length; k++) {
            if (Math.abs(nums[k]) <= Math.abs(nums[minIndex])){
                minIndex = k;
            }
        }
        int i = minIndex;
        int j = minIndex + 1;
        int m = 0;
        while (i >= 0 && j < nums.length){
            if (Math.abs(nums[i]) < Math.abs(nums[j])){
                res[m++] = nums[i] * nums[i];
                i--;
            }else {
                res[m++] = nums[j] * nums[j];
                j++;
            }
        }
        while (i >= 0){
            res[m++] = nums[i] * nums[i];
            i--;
        }
        while (j < nums.length){
            res[m++] = nums[j] * nums[j];
            j++;
        }
        return res;
    }

    //第三种方法： 数组的元素平方肯定在两端，越往中间平方越小
    //那么可以 两指针指向两端, 往中间缩小. 大的元素逆序放入结果数组; 这样就不用最后在处理其中一半没遍历完的问题
    //还是那个问题, 左右指针的初始值决定了不同的思维方式
    public int[] sortedSquares_optimized(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        int[] res = new int[nums.length];
        int k = res.length - 1;
        while (i <= j){
            if (Math.abs(nums[i]) > Math.abs(nums[j])){
                res[k--] = nums[i] * nums[i];
                i++;
            }else {
                res[k--] = nums[j] * nums[j];
                j--;
            }
        }
        return res;
    }
}
