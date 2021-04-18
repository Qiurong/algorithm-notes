package backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 90. 子集 II
 * @author: Qr
 * @create: 2021-04-18 14:44
 **/
public class subsets_ii {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        //先排序
        Arrays.sort(nums);
        backtrack(0,nums);
        return res;
    }

    public void backtrack(int start, int[] nums){
        res.add(new ArrayList<Integer>(path));

        for (int i = start; i < nums.length; i++) {
            //当前被选中的数字与上一个数字相同则剪枝
            if (i > start && nums[i] == nums[i-1]){
                continue;
            }
            path.add(nums[i]);
            backtrack(i+1,nums);
            path.remove(path.size() - 1);
        }
    }
}
