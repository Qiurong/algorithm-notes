package heap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * @description: 264. 丑数 II
 * @author: Qr
 * @create: 2021-04-07 11:10
 **/


/**
 *  整个思路是提前把n的最大值1690个丑数按序存储在数组中，需要第n个则返回[n-1] O(1)
 *  丑数的计算方法是：第一个数1，则 1*2, 1*3, 1*4。[1,2,3,4]
 *                 然后第二个2, 则 2*2, 2*3, 2*4 [1,2,3,4,4,6,8] .......
 *  在计算过程中要解决两个问题：1.排序  2.去重
 *  2.去重很简单，直接用一个hashSet即可
 *  1.排序：排序可以用一个堆去做，每次弹出堆顶元素i到丑数数组中，同时入堆i*2, i*3, i*4
 *
 *  时间复杂度：外层循环 O(1690)
 *            内存循环 1次poll操作 和 3次add操作
 *  空间复杂度： hashset O(1690), 数组 O(1690), heap O(1690).
 */
class UglyNumWithHeap{
    int [] sortedUglyNums = new int[1690];

    UglyNumWithHeap(){
        int index = 0;  //丑数数组的下标
        PriorityQueue<Long> heap = new PriorityQueue<>();
        HashSet<Long> hashSet = new HashSet<>();
        //初始化堆
        heap.add(1L);

        while(index < sortedUglyNums.length){
            Long uglyNum = heap.poll();
            sortedUglyNums[index++] = Math.toIntExact(uglyNum);
            if (index == sortedUglyNums.length){
                break;
            }

            Long uglyNumTwo = uglyNum * 2;
            if (!hashSet.contains(uglyNumTwo)){
                hashSet.add(uglyNumTwo);
                heap.add(uglyNumTwo);
            }
            Long uglyNumThree = uglyNum * 3;
            if (!hashSet.contains(uglyNumThree)){
                hashSet.add(uglyNumThree);
                heap.add(uglyNumThree);
            }
            Long uglyNumFive = uglyNum * 5;
            if (!hashSet.contains(uglyNumFive)){
                hashSet.add(uglyNumFive);
                heap.add(uglyNumFive);
            }

        }
    }
}



public class ugly_number_ii {
    public static UglyNumWithHeap uglyNumWithHeap = new UglyNumWithHeap();


    //堆的方法是先存再排，每次计算出当前i的下三个丑数，然后放到堆里排序
    public int nthUglyNumber_Heap(int n) {
        int res[] = uglyNumWithHeap.sortedUglyNums;
        return res[n-1];
    }

    //DP的方法是先排在存，每次取当前i的 下三个丑数的最小，然后放入数组
    //三个指针p2, p3, p5；dp[i] = min{[p2]*2, [p3]*3, [p5]*5}, 然后对应的 指针++
    public int nthUglyNumber_DP(int n){
        ArrayList<Long> sortedUglyNums = new ArrayList<>();
        sortedUglyNums.add(1L);
        int p2 = 0;
        int p3 = 0;
        int p5 = 0;
        while (sortedUglyNums.size() < n){
            //选择 三个数字中最小值的写法
            //Math.min(a,b) : return a <=b ? a: b
            Long nextUglyNum = Math.min(Math.min(sortedUglyNums.get(p2)*2, sortedUglyNums.get(p3)*3), sortedUglyNums.get(p5)*5);
            sortedUglyNums.add(nextUglyNum);

            //nextUglyNum可能由 p2, p3, p5一个或多个得出
            //那么对应的nextUglyNum加入数组后, 需要把所有p2, p3, p5都++
            if (nextUglyNum.equals(sortedUglyNums.get(p2)*2)) {
                p2++;
            }
            if (nextUglyNum.equals(sortedUglyNums.get(p3)*3)) {
                p3++;
            }
            if (nextUglyNum.equals(sortedUglyNums.get(p5)*5)) {
                p5++;
            }
        }
        return Math.toIntExact(sortedUglyNums.get(n - 1));
    }


}
