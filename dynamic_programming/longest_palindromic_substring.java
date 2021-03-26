package dynamic_programming;

/**
 * @description: 5. 最长回文子串
 * @author: Qr
 * @create: 2021-03-25 23:10
 **/
public class longest_palindromic_substring {
    public String longestPalindrome(String s) {
        int len = s.length();
        char[] chars = s.toCharArray();
        int maxLen = 0;
        int startIndex = 0;
        int endIndex = 0;
        //[i][j]: [i,j]为回文. f(i, j)
        //状态转移方程： f(i, j) = f(i+1, j-1) & [i] == [j] (i<=j)
        //边界条件：f(i,j) == true(i==j),  f(i,j) = [i] == [j](j-i=1)  即长度为1和长度为2的情况。
        boolean[][] isPalindromic = new boolean[len][len];
        //这个循环的写法要好好仔细研究，按照传统写法会出现计算某一状态时，其子状态还没有计算的情况。
        //这个写法从动态来理解是 矩形上三角+按照一列一列的从上到下计算
        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                if (i == j){
                    isPalindromic[i][j] = true;
                }
                else if(j - i == 1 && chars[i] == chars[j]){
                    isPalindromic[i][j] = true;
                    if (maxLen < 1){
                        maxLen = 1;
                        startIndex = i;
                        endIndex = j;
                    }
                }
                else if (j-i > 1 && isPalindromic[i+1][j-1] && chars[i] == chars[j])
                {
                    isPalindromic[i][j] = true;
                    if (maxLen < j-i){
                        maxLen = j - i;
                        startIndex = i;
                        endIndex = j;
                    }
                }
            }
        }
        //subString(startIndex, endIndex)的第endIndex不返回
        return s.substring(startIndex,endIndex + 1);
    }
}
