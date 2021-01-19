package divide_and_conquer;

/**
 * @description:给定一个非空二叉树，返回其最大路径和。
 * @author: Qr
 * @create: 2021-01-08 13:55
 **/

/**题目描述：本题中，路径被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 *
 *首先需要定义路径：任意结点出发，经过父子结点连接，到达  任意结点  的序列。
 *根据路径的定义：对于任意一个结点，它可以向 上，左下，右下 三个方向走。
 *自己的第一思考是：  maxPathSum(Node) = Max(maxPathSum(lChild), maxPathSum(rChild)) + val;
 *                 这种想法适用于求从树中任意一个结点往下延展val和的最大值, 错在两个地方: 1. 路径可以为左子结点->父结点->右子结点
 *                                                                           2. val可以<0,无法处理val<0的情况。
 *                                                   |
 *                                                   a
 *                                                 /   \
 *                                                b     c
 * 正确的想法是：自下而上, 从叶子结点开始,往上迭代,抽象出一个函数maxGain(Node),该函数为当前结点向上层的最大贡献值
 *            maxGain: 以该结点为出发节点 的最大路径值,
 *            maxGain(Node) = 0, Node为空结点
 *                          = val, Node为叶节点
 *                          = val + MAX(leftGain, rightGain)。
 *            计算出maxGain(Node)之后, 我们也就得到了以 Node为起点的向下遍历的最大路径和 即 Node可以向上层贡献的值
 *            因为maxGain()是用于向上层计算最大路径和时提供最大贡献值的，所以不能左右子树都遍历（否则出现了分叉）
 *
 *            遍历树中每一个结点，获取包喊该节点的最大路径和，同时维护一个全局变量用于计算树中的最大路径和。
 *                   currentPathSum(a) =            a.val + maxGain(b) + maxGain(c)
 *                                             MAX( a.val + maxGain(b)                                  );
 *                                                  a.val + maxGain(c)
 *                       对此可以简化为判断 maxGain(b)与maxGain(c)是否大于0,大于则加上。
 *                                      = a.val + MAX(0, maxGain(b)) + MAX(0, maxGain(C)).
 *            因为最大路径和不一定为以根节点开始，所以需要一个全局变量记录更新最大路径和。
 *
 *
 **/




public class binaryTreeMaximumPathSum {

    int maxPathSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root){
        maxGain(root);
        return maxPathSum;
    }

    public int maxGain(TreeNode root){
        if(root == null){
            return 0;
        }

        int leftGain = Math.max(maxGain(root.left), 0);
        int rightGain = Math.max(maxGain(root.right), 0);

        int currentPathSum = root.val + leftGain + rightGain;

        maxPathSum = Math.max(maxPathSum, currentPathSum);

        return root.val + Math.max(leftGain, rightGain);

    }


}
