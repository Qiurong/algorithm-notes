package dynamic_programming;

/**
 * @description: 70. 爬楼梯
 * @author: Qr
 * @create: 2021-03-12 16:35
 **/

public class climbing_stairs {
    /* 从后往前迭代：最后一步跨了一个台阶，最后一步跨了两个台阶。
    f(n) = f(n-1) + f(n-2) n>=2
     */
    //递归版本
    public int climbStairs_recursion(int n) {
        if (n == 1){
            return 1;
        }
        if (n == 2){
            return 2;
        }
        return climbStairs_recursion(n-1) + climbStairs_recursion(n-2);
    }

    public int climbStairs_iteration(int n){
        if (n == 1){
            return 1;
        }
        if (n == 2){
            return 2;
        }
        int [] nums = new int[n];
        nums[0] = 1;
        nums[1] = 2;
        for (int i = 2; i < n; i++) {
            nums[i] = nums[i-1] + nums[i-2];
        }
        return nums[n-1];
    }

    //滚动数组思想把空间复杂度降到O(1)
    public int climbStairs_optimized(int n){
        if (n == 1){
            return 1;
        }
        if (n == 2){
            return 2;
        }
        //滚动数组中的第一个状态.初始值为f(1)
        int num_TwoStepsAway = 1;
        //滚动数组中的第二个状态.初始值为f(2)
        int num_OneStepsAway = 2;
        int temp = 0;
        for (int i = 3; i <= n; i++) {
            temp = num_OneStepsAway;
            num_OneStepsAway = num_OneStepsAway + num_TwoStepsAway;
            num_TwoStepsAway = temp;
        }
        return num_OneStepsAway;
    }
}
