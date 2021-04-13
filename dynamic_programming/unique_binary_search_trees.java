package recursion;

/**
 * @description: 96. 不同的二叉搜索树
 * @author: Qr
 * @create: 2021-04-12 15:56
 **/
public class unique_binary_search_trees {
    public int numTrees(int n) {

        //num[i] = 1...i的不同二叉搜索树的种
        //num[0]不用
        int num[] = new int [n + 1];
        //初始化
        num[0] = 1; //0也初始化为1，不防止后面计算

        //根节点为1...n
        //以根节点i去切割，左边为 1..i, 右边为i+1,...n
        for (int i = 1; i <= n; i++) {
            int currNum = 0;
            for (int j = 1; j <= i; j++) {
                int leftNum = num[j-1];
                int rightNum = num[i-j];
                currNum  += leftNum * rightNum;
            }
            num[i] = currNum;
        }

        return num[n];
    }
}
