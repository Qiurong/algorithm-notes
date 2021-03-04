package sort_algorithms;

/**
 * @description: 快速排序
 * @author: Qr
 * @create: 2021-03-03 09:08
 **/

public class quickSort {
    //快速排序: 选取一个元素作为基准, 进行分区操作：把<基准的元素放到左边, 把>基准的元素放到右边. 再递归的去对左右两边进行快排.
    //每一次分区操作都把基准放在了它该在的位置.
    //总结：  归排：先切, 切到不可再切进行归并排序(merge),不断回溯.
    //       快排: 先排, 把基准放到该在的位置, 再递归的去排左右两边. 没有回溯的过程.
    //而且都用了分治的思想, 只是先分后合, 另一个在分的过程了完成了对应的操作, 最后不需要在进行合并过程.
    public int[] quickSort(int[] nums){
        quickSortWithRecursion(nums,0, nums.length - 1);
        return nums;
    }
    public void quickSortWithRecursion(int[] nums, int start, int end){
        if (start >= end){
            return;
        }
        int pivot = partionFuncOne(nums, start, end);
        quickSortWithRecursion(nums,start, pivot - 1);
        quickSortWithRecursion(nums, pivot + 1, end);
    }
    //分区操作: 选定第一个元素为基准, 完成基准归位操作. 返回基准在数组中的下标
    public int partionFuncOne(int[] nums,int start, int end){
        //选取第一个元素为基准
        int base = nums[start];
        //选取两个指针i,j用于后续遍历
        int i = start;
        int j = end;

        //这里的逻辑是: 从后往前找到第一个 < base的,swap, i++, 此时基准在j
        //           从前往后找到第一个 > base的, swap, j--, 此时基准在i
        //           结束后: i==j, 基准在i/j. 但这样swap的次数太多.
        while (i != j){
            //从后往前找到第一个 < base的
            while (i < j && nums[j] >= base){
                j--;
            }
            if (i != j){
                swap(nums,i,j);
                i++;
            }
            //从前往后找到第一个 > base的
            while (i < j && nums[i] <= base){
                i++;
            }
            if (i != j){
                swap(nums,i,j);
                j--;
            }
        }
        return i;
    }

    //从后向前找到第一个 < base的, 从前往后找到第一个 > base的, swap;
    //  最后swap(i, start). 整个过程中基准值只在最后swap了一次
    public int partionFuncTwo(int[] nums, int start, int end){
        int base = nums[start];
        int i = start;
        int j = end;
        while (i != j){
            while (i < j && nums[j] >= base){
                j--;
            }
            while (i < j && nums[i] <= base){
                i++;
            }
            if (i != j){
                swap(nums,i,j);
            }
        }
        //最后还需要swap一下基准的位置
        swap(nums,start,i);
        return i;
    }

    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
     }
}
