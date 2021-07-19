package binary_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @description: 101. 对称二叉树
 * @author: Qr
 * @create: 2021-07-19 15:39
 * 给定一个二叉树，检查它是否是镜像对称的。
 **/
public class symmetric_tree {

    //思路：层序遍历，然后吧每一层的值存下来，每一层进行判断是否镜像
    public boolean isSymmetric(TreeNode root) {
        boolean isSym = true;
        //0个或1个节点
        if (root == null || (root.left == null && root.right == null)){
            return isSym;
        }
        //用于存储层序遍历节点的队列
        Queue<TreeNode> levelNode = new LinkedList<>();
        //存储每一层节点val的list
        List<Integer> levelVal = new ArrayList<>();
        TreeNode curr = root;
        levelNode.add(curr);
        while (!levelNode.isEmpty()){
            int currNum = levelNode.size();
            //把下一层节点加入到队列中
            for (int i = 0; i < currNum; i++) {
                //1.取出队列首元素curr
                curr = levelNode.poll();
                //2.吧curr的左右结点加入队列
                if (curr != null){
                    levelNode.add(curr.left);
                    levelVal.add(curr.left == null ? null : curr.left.val);
                    levelNode.add(curr.right);
                    levelVal.add(curr.right == null ? null : curr.right.val);
                }
            }
            //判断队列是否镜像
            isSym = isSymmetricLevel(levelVal);
            //每层判断完了需要清除val队列
            levelVal.clear();
            if (isSym == false){
                break;
            }
        }
        return isSym;
    }

    /**
     * 判断当前层是否对称
     * @param levelVal
     * @return
     */
    public boolean isSymmetricLevel(List<Integer> levelVal){
        int start = 0;
        int end = levelVal.size()-1;
        boolean flag = true;
        while(start < end){
            flag = isEqual(levelVal.get(start),levelVal.get(end));
            start++;
            end--;
            if (flag == false){
                break;
            }
        }
        return flag;
    }

    /**
     * 判断a和b是否相等.   a和b都为null,则相等
     * @param a
     * @param b
     * @return
     */
    public boolean isEqual(Integer a, Integer b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null){
            return false;
        }
        return a.equals(b);
    }

    //2.用递归来做  树为镜像：a==b && a.left == b.right && a.right == b.left
    public boolean isSymmetric_recursion(TreeNode root){
        return recursion(root,root);
    }

    public boolean recursion(TreeNode a, TreeNode b){
        if (a == null && b == null){
            return true;
        }
        if (a == null || b == null){
            return false;
        }
        return (a.val == b.val) && recursion(a.left,b.right) && recursion(a.right,b.left);
    }

    //3.仿照递归的思路用迭代实现
    public boolean isSymmetric_iteration(TreeNode root){
        if (root == null){
            return true;
        }
        return iteration(root.left,root.right);
    }

    public boolean iteration(TreeNode a, TreeNode b){
        boolean isSymmetric = true;
        Queue<TreeNode> left = new LinkedList<>();
        Queue<TreeNode> right = new LinkedList<>();
        left.add(a);
        right.add(b);
        while (!left.isEmpty() && !right.isEmpty()){
            TreeNode currLeft = left.poll();
            TreeNode currRight = right.poll();
            if (currLeft == null && currRight == null){
                continue;
            }
            if (currLeft == null || currRight == null){
                isSymmetric = false;
                break;
            }
            if (currLeft.val != currRight.val){
                isSymmetric = false;
                break;
            }
            //a.left == b.right && a.right == b.left --> 第一个队列加left,第二个队列加right; 第一个队列加right,第二个队列加left
            left.add(currLeft.left);
            right.add(currRight.right);

            left.add(currLeft.right);
            right.add(currRight.left);
        }
        return isSymmetric;
    }
}
