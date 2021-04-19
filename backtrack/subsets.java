package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 78. 子集
 * @author: Qr
 * @create: 2021-04-18 11:12
 **/
public class subsets {
    List<List<Integer>> res = new ArrayList<>();    //最后的结果
    List<Integer> path = new ArrayList<>();         //决策树上的路径

    public List<List<Integer>> subsets(int[] nums) {
        backtrack(0,nums);
        return res;
    }

    //组合/子集问题从start开始
    public  void backtrack(int start,int[] nums){
        //这里对于所有path上的路径都需要，所以不存在结束条件
        res.add(new ArrayList<Integer>(path));
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);                 //做出选择
            backtrack(i+1,nums);         //递归到下一层, 标识下一个选择列表的开始位置即 当前元素i 的下一元素 i+1
            path.remove(path.size()-1); //撤销选择
        }
    }
}
