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
        backtrack(candidates.length-1,candidates,target);
        return res;
    }


    //每一步去更新target值和可选择的数组范围
    //问题：出现了重复解
    public void backtrack(int end,int[] nums,int target){
        if (target == 0){
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i <= end; i++) {
            if (nums[i] > target){
                continue;
            }
            path.add(nums[i]);
            target = target - nums[i];
            int nextEnd = getIndex(nums,target);
            backtrack(nextEnd,nums,target);
            target += nums[i];
            path.remove(path.size()-1);
        }
    }

    //返回数组中 <= Num的最小值
    public int getIndex(int[] sortedNums,int num){
        int index = 0;
        while (index < sortedNums.length && sortedNums[index] < num){
            index++;
        }
        return index;
    }
}
