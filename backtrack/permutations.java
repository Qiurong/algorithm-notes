package backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 46. 全排列
 * @author: Qr
 * @create: 2021-04-18 16:08
 **/
public class permutations {

    //根据题目要求，不能填写已经标记过的数，因此用一个标记数组来标记是否填过
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    boolean[] visit;

    public List<List<Integer>> permute(int[] nums) {
        visit = new boolean[nums.length];
        backtrack(nums);
        return res;
    }

    //每一步遍历所有元素, 所以不需要start形参
    public void backtrack(int[] nums){
        //达到递归深度，返回
        if (path.size() == nums.length){
            res.add(new ArrayList<>(path));
            return;
        }

        //排列问题每一步的选择列表都是从0开始的，而组合/子集问题从start开始
        for (int i = 0; i < nums.length; i++) {
            //visit则剪枝
            if (visit[i]){
                continue;
            }
            path.add(nums[i]);
            visit[i] = true;
            backtrack(nums);
            visit[i] = false;
            path.remove(path.size() - 1);
        }
    }
}
