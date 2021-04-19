package backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @description: 46. 全排列
 * @author: Qr
 * @create: 2021-04-18 16:08
 **/
public class permutations {

    //难点：回溯的下一步如何处理。
    //根据题目要求，不能填写已经标记过的数，因此用一个标记数组来标记是否填过
    static List<List<Integer>> res = new ArrayList<>();
    static List<Integer> path = new ArrayList<>();
    static boolean[] visit;

    public static List<List<Integer>> permute(int[] nums) {
        visit = new boolean[nums.length];
        Arrays.fill(visit,false);
        backtrack(0,nums);
        return res;
    }
    //index代表当前取得下标
    public static void backtrack(int depth, int[] nums){
        if (depth == nums.length){
            res.add(new ArrayList<>(path));
            return;
        }

        //每次去遍历所有数组
        for (int i = 0; i < nums.length; i++) {
            //搜索到没有visit的则visit
            if (!visit[i]){
                path.add(nums[i]);
                visit[i] = true;
                //这里更新时更新depth参数
                backtrack(depth+1,nums);
                visit[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }

}
