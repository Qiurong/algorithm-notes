package dynamic_programming;

import java.util.ArrayList;

/**
 * @description: 面试题 05.03. 翻转数位
 * @author: Qr
 * @create: 2021-04-02 14:44
 **/
public class reverse_bits_lcci {
    public int reverseBits(int num) {
        String numStr = Integer.toBinaryString(num);

        int maxOnes = 0;    //最长连续1的个数
        ArrayList<Integer> indexOfZeroes = new ArrayList();  //原数组中0的下标
        boolean hasZero = false;                    //原数组中是否有下标1

        //在前面加上 -1 用于方便计算
        indexOfZeroes.add(-1);
        for (int i = 0; i < numStr.length(); i++) {
            if (numStr.charAt(i) == '0'){
                hasZero = true;
                indexOfZeroes.add(i);
            }
        }
        //在后面加上 length用于方便计算
        indexOfZeroes.add(numStr.length());

        if (!hasZero){
            maxOnes = numStr.length() + 1;
            return Math.min(maxOnes, 32);
        }

        for (int i = 1; i < indexOfZeroes.size() - 1; i++) {
            maxOnes = Math.max(maxOnes, indexOfZeroes.get(i+1) - indexOfZeroes.get(i-1) - 1);
        }
        return maxOnes;
    }
}
