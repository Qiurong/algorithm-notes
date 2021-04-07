package heap;

import java.util.PriorityQueue;

/**
 * @description: 剑指 Offer 40. 最小的k个数
 * @author: Qr
 * @create: 2021-04-06 12:01
 **/
public class topK {
    //用堆获取topK
    //时间复杂度：O(nlogn) 空间复杂度：O(n)
    public int[] getLeastNumbers_Heap(int[] arr, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int [] res = new int[k];

        for (int i = 0; i < arr.length; i++) {
            //add操作的时间复杂度O(logn)
            heap.add(arr[i]);
        }
        for (int i = 0; i < k; i++) {
            res[i] = heap.poll();
        }
        return res;
    }
}
