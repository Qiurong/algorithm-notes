package backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 39. 组合总和
 * @author: Qr
 * @create: 2021-04-19 19:20
 **/
public class combination_sum {

    List<List<Integer>> res;
    List<Integer> path;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        res = new ArrayList<>();
        path = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(0,candidates.length-1,candidates,target);
        return res;
    }


    //每一步去更新target值和可选择的数组范围
    //问题：出现了重复解. 解决方法：每轮不是从0开始迭代，而是从start开始迭代，同时下一轮的迭代起点为i (每个数可以被选中无数次)
    //从[start,end]中去组合target
    public void backtrack(int start, int end,int[] nums,int target){
        if (target == 0){
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i <= end; i++) {
            if (nums[i] > target){
                continue;
            }
            path.add(nums[i]);
            target = target - nums[i];
            int nextEnd = getIndex(nums,target);
            //这里因为每个数可以取无数次, 所以下次回溯的起点为i
            backtrack(i,nextEnd,nums,target);
            target += nums[i];
            path.remove(path.size()-1);
        }
    }

    //返回数组中 <= Num的最大下标
    public int getIndex(int[] sortedNums,int num){
        int index = 0;
        while (index < sortedNums.length && sortedNums[index] <= num){
            index++;
        }
        index--;
        return index;
    }
}
