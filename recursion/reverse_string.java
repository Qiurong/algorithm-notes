package recursion;

/**
 * @description: 344. 反转字符串
 * @author: Qr
 * @create: 2021-04-12 10:09
 **/
public class reverse_string {
    //遍历到中点, 交换[i]和[len-i-1]
    public void reverseString_traverse(char[] s) {
        int len = s.length;
        int mid = len/2;
        if (len == 0  || len == 1){
            return;
        }
        for (int i = 0; i < mid; i++) {
            swap(s,i,len-i-1);
        }
    }


    //递归解决问题
    //[0,n-1]  分解为 [1,...n-2]  每一步交换当前序列的第一位和最后一位
    //递归终止条件: 序列长度为1
    public void reverseString_recursion(char[] s){
        recursion(s,0,s.length-1);

    }

    public void recursion(char[] s, int start, int end){
        if (start >= end){
            return;
        }
        swap(s,start,end);
        recursion(s,start+1,end-1);
    }

    //交换a和b的值
    public void swap(char[] s,int i, int j){
        char temp = s[j];
        s[j] = s[i];
        s[i] = temp;
    }
}
