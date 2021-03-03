package sort_algorithms;

/**
 * @description: 插入排序
 * @author: Qr
 * @create: 2021-03-03 10:00
 **/
public class insertionSort {
    //思路：每次将一个元素插入一个有序数组. 刚开始认为第一个元素就是有序数组, 不断把之后的元素迭代插入前面的有序数组
    public int[] insertionSort(int[] nums){
        int len = nums.length;
        //[0]为初始有序数组, 从[1]开始往后迭代 将[i]插入有序数组[0, i).
        //插入的操作包含移动局部数组的操作, 因此对于数组可以直接从后向前迭代, 直接在比较的过程中同时完成移动数组的操作.
        for (int i = 1; i < len; i++){
            int currElement = nums[i];
            //在每一轮的迭代过程中, 将[i]依次与之前的元素进行比较.
            //在比较的过程中, 如果元素大于[i], 就将该元素向后移动. 当迭代到比[i]小的元素, 迭代停止, 最后把[i]放在这个位置上.
            int j = i;
            while (j > 0 && nums[j-1] > currElement){
                nums[j] = nums[j-1];
                j--;
            }
            nums[j] = currElement;
        }
        return nums;
    }
}
