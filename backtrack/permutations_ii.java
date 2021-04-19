package backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 47. 全排列 II
 * @author: Qr
 * @create: 2021-04-19 09:55
 **/
public class permutations_ii {

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    boolean[] visit;

    //保证填数字时 重复数字只被填写一次，即：从左向右第一个未被填过的数字
    //剪枝条件: 当前元素和前一个元素值相同  且 前一个元素未被用过 (决策树中同一层)
    public List<List<Integer>> permuteUnique(int[] nums) {
        visit = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums);
        return res;
    }

    public void backtrack(int[] nums){
        if (path.size() == nums.length){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            //剪枝条件：同一层中前一位已经被读取过了
            if (visit[i] || i > 0 && nums[i] == nums[i-1] && !visit[i-1]){
                continue;
            }
            path.add(nums[i]);
            visit[i] = true;
            backtrack(nums);
            visit[i] = false;
            path.remove(path.size()-1);
        }
    }
}
