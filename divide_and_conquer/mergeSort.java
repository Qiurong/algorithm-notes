package divide_and_conquer;

/**
 * @description:归并排序
 * @author: Qr
 * @create: 2021-01-05 14:45
 **/

/**
 * 归并排序
 * 基本思想：采用分治法。将  已经有序  的子序列合并，得到完全有序的子序列
 *         即不断切割至每个子序列只有1个, 此时子序列已经有序，将这两个只有1个元素的子序列合并
 */
public class mergeSort {

    public static void MergeSort(int [] arrays, int bgn, int end){
        //1.结束条件：切割到每个部分只有一个元素时终止递归
        if(bgn >= end){
            return;
        }

        //2.分段处理
        int mid = (bgn + end) >>> 1;
        MergeSort(arrays, bgn, mid);
        MergeSort(arrays, mid+1, end);

        //3.合并结果：
        Merge(arrays, bgn, mid, end);
    }

    //Merge first数组[bgn, mid] last数组[mid+1, end]
    public static void Merge(int[] arrays, int bgn, int mid, int end){
        int [] tempArrays = new int[end - bgn + 1];
        //n:tempArrays下标, i:first数组下标, m:last数组下标
        int n = 0;
        int i = bgn;
        int m = mid+1;
        while (i <= mid & m<= end){
            if(arrays[i] < arrays[m]){
                tempArrays[n++] = arrays[i++];
            }else{
                tempArrays[n++] = arrays[m++];
            }

        }
        //把两个数组的剩余元素放入tempArrays中
        while(i <= mid){
            tempArrays[n++] = arrays[i++];
        }
        while(m <= end){
            tempArrays[n++] = arrays[m++];
        }

        //把tempArrays存储的有序值放回到arrays[bgn, end]中
        for(int j = 0; j< tempArrays.length; j++){
            arrays[j + bgn] = tempArrays[j];
        }
        return;
    }
}
