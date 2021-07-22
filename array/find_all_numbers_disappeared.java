package array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description: 448. 找到所有数组中消失的数字
 * @author: Qr
 * @create: 2021-07-22 14:14
 *
 * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
 *
 * 示例 1：
 * 输入：nums = [4,3,2,7,8,2,3,1]
 * 输出：[5,6]
 *
 * 示例 2：
 * 输入：nums = [1,1]
 * 输出：[2]
 *
 **/
public class find_all_numbers_disappeared {
    //最简单的方法：把nums中所有数字存入set中, 然后遍历[1,n] set中不存在的数字就加入res list中
    //时间: O(2n) 空间:0(n)
    public List<Integer> findDisappearedNumbers_set(int[] nums) {
        List<Integer> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        int n = nums.length;
        //1.放入set
        for (int i = 0; i < n; i++) {
            set.add(nums[i]);
        }
        //2.寻找set中不存在的
        for (int i = 1; i <= n; i++) {
            if (!set.contains(i)){
                res.add(i);
            }
        }
        return res;
    }


    //奇技淫巧: 原来的方法需要额外的空间来作为哈希表, 可以通过对原数组修改来实现代替哈希表的方式
    //具体做法: 遍历数组, 对于数组中的每一个元素nums[i]=x, 将nums[x-1] 加n, 这样一圈下来数组中存在的数字对应的元素(这些元素下标=数字)均>n,
    //         再次遍历，如果nums[i]<=n, 那么说明不存在i+1
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int x = 0;
        int n = nums.length;
        //1.第一遍遍历数组,
        for (int i = 0; i < n; i++) {
            //这里可能碰到 nums[i]已经大于n, 所以需要求余
            x = (nums[i]-1) % n;
            nums[x] += n;
        }
        //第二遍遍历数组
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n){
                res.add(i+1);
            }
        }
        return res;
    }
}
