package sort_algorithms;

/**
 * @description:
 * @author: Qr
 * @create: 2021-03-03 09:10
 **/
public class bubbleSort {
    //比较相邻两个元素，第一个比第二个大则交换次序。最终得到一个升序数组
    public int[] bubbleSort(int[] nums){
        //i=0的话会发生下标越界, i = 0, j=len-1,j+1 = len
        for (int i = 1; i < nums.length; i++) {
            //加一个标记, 若为true,本轮迭代没有交换则已经有序, 排序完成
            boolean flag = true;
            //每一轮都把一个最大的值放在了末尾. 所以下一轮迭代时为[0, len - i -1]
            for (int j = 0; j < nums.length - i; j++) {
                if (nums[j] > nums[j + 1]){
                    //用异或操作来交换数值
                    nums[j] = nums[j] ^ nums[j+1];
                    nums[j+1] = nums[j] ^ nums[j+1];
                    nums[j] = nums[j+1] ^ nums[j];

                    flag = false;
                }
            }
            if (flag){
                break;
            }
        }
        return nums;
    }
}
