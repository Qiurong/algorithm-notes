package divide_and_conquer;

/**
 * @description: 快速排序
 * @author: Qr
 * @create: 2021-01-06 17:16
 **/

/**
 * 快速排序
 * 基本思想：每一轮用数组最左边的元素作为基准，进行分区操作，分区操作后：所有在基准左边的元素 < 基准值 < 所有在基准右边的值
 *         在递归对[left, pivot], [pivot+1, right]进行快排
 * 分区操作：1.设left位置的值为pivot,
 *         2.从右边j找第一个小于pivot的元素，从左边i找第一个大于pivot的元素，交换两个元素，直至i==j
 *         3.归位pivot(交换pivot和i的元素)
 */
public class quickSort {

    public static int[] QuickSort(int []nums){
        func(nums, 0, nums.length - 1);
        return nums;
    }

    public static void func(int []nums, int left, int right){

        //1.寻找递归终止条件
        if(left < right){
            //2.分段处理
            //2.1: 大问题是如何分解到小问题的，分解的
            int pivotPos = partition(nums, left, right);
            //2.2: 递归语句
            func(nums, left, pivotPos - 1);
            func(nums, pivotPos + 1, right);
            //3.合并结果：由于此问题是数组，合并的操作已经在2.1完成，不需要额外去合并
        }
    }

    //分区
    public static int partition(int[] nums, int left, int right){
        int pivot = nums[left];
        int i = left;
        int j = right;
        while(i != j){
            //从右边开始找到第一个小于基准值的元素
            //小于对应的反面是 >=
            while(nums[j] >= pivot && i < j){
                j--;
            }
            //从左边找到第一个大于基准值的元素
            while(nums[i] <= pivot && i < j){
                i++;
            }
            if(i < j){
                //数组交换值需要引用传递
                swap(nums, i, j);
            }
        }
        //循环结束，此时i/j为基准位置，需要把基准值归位
        swap(nums, left, i);
        return i;
    }

    public static void swap(int [] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
