package sort_algorithms;

/**
 * @description: 归并排序
 * @author: Qr
 * @create: 2021-03-03 09:56
 **/
public class mergeSort {
    //思想: 不断二分拆解直至不能拆解, 合并两个有序数组
    public int[] mergeSort(int[] nums){
        mergeSortWithRecurison(nums, 0, nums.length - 1);

        return nums;
    }

    public void mergeSortWithRecurison(int[] nums, int start, int end){
        if (start >= end){
            return ;
        }
        int mid = start + ((end-start) >> 1);
        mergeSortWithRecurison(nums, start, mid);
        mergeSortWithRecurison(nums, mid + 1, end);
        merge(nums,start, mid, end);
    }
    public void merge(int[] nums, int start, int mid, int end){
        int[] sorted = new int[end - start + 1];
        int i = start;
        int j = mid + 1;
        int m = 0;
        while (i <= mid && j <= end){
            if (nums[i] < nums[j]){
                sorted[m++] = nums[i++];
            }else {
                sorted[m++] = nums [j++];
            }
        }
        while (i <= mid){
            sorted[m++] = nums[i++];
        }
        while (j <= end){
            sorted[m++] = nums[j++];
        }
         for (int k = 0; k < sorted.length; k++) {
            //注意这里nums下标是k + start.
            nums[k + start] = sorted[k];
        }
    }
}
