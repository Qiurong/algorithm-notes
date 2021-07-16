package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 22. 括号生成
 * @author: Qr
 * @create: 2021-07-16 14:21
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：["()"]
 *
 **/
public class generate_Parenthesis {

    List<String> resList;

    /**
     * 思路：这是一个回溯问题，剪枝条件就是已选的列表中1的数目大于0.
     */
    public List<String> generateParenthesis(int n) {
        if (n == 0){
            return null;
        }
        resList = new ArrayList<>();
        StringBuilder currRes = new StringBuilder();
        backtrack(0,0,currRes,n);
        return resList;
    }

    /**
     * 回溯模板：
     *     if 满足结束条件:
     *         result.add(路径)
     *         return
     *
     *     for 选择 in 选择列表:
     *     做选择
     *     backtrack(路径, 选择列表)
     *     撤销选择
     *
     *  具体到这题来说：特殊性在于每一步选择的时候只能选( 或 ),选择列表也不方便表示, 所以无法去写for循环遍历，而是根据剪枝条件进行做选择的分类讨论。
     *  合法性：首先使用的左右括号个数都要 小于 n, 另一方面来说要保证已经使用的右括号个数 小于 左括号。
     *  做选择: 首先整个逻辑都在于先加入 ( ,加不了的情况下根据合法性加入 ) .
     *          1.left < n 即还有左括号可以选： 下一步可以选左,
     *          2.right < left: 下一步可以选右
     *
     * @param left    当前左括号使用个数
     * @param right   当前右括号使用个数
     * @param currRes 当前拼接起来的字符串
     * @param n       左右括号可使用个数
     */
    public void backtrack(int left, int right ,StringBuilder currRes, int n){
        //1.结束条件:
        if (currRes.length() == n * 2){
            //深拷贝
            String res = new String(currRes);
            resList.add(res);
        }
        //2. 左括号还可以选, 下一步选左括号
        if (left < n){
            currRes.append("(");
            backtrack(left+1,right,currRes,n);
            currRes.deleteCharAt(currRes.length()-1);
        }
        //3. 右括号个数<左括号, 下一步选右括号
        if (right < left){
            currRes.append(")");
            backtrack(left,right+1,currRes,n);
            currRes.deleteCharAt(currRes.length()-1);
        }
    }

}
