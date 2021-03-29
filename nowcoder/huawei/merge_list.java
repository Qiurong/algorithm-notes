package nowcoder.huawei;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @description: 牛客网HG8:合并表记录
 * @author: Qr
 * @create: 2021-03-29 19:27
 **/
public class merge_list {

    //用于指明keys数组中的key个数
    static int size = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int num = in.nextInt();
            int[] keys = new int[num];
            HashMap<Integer,Integer> hashMap = new HashMap<>();
            for (int i = 0; i < num; i++) {
                int key = in.nextInt();
                int value = in.nextInt();
                if (hashMap.get(key)==null){
                    hashMap.put(key,value);
                    insert(keys,key);
                }
                else {
                    hashMap.put(key,value+hashMap.get(key));
                }
            }
            for (int i = 0; i < size; i++){
                System.out.print(keys[i] + " " + hashMap.get(keys[i]));
                System.out.println("");
            }
        }
    }

    public static void insert(int[] keys, int key){
        if (size == 0){
            keys[0] = key;
        } else {
            int j = 0;
            while (j < size && keys[j] < key){
                j++;
            }
            //移动[]
            for (int k = size; k > j ; k--) {
                keys[k] = keys[k-1];
            }
            keys[j] = key;
        }
        size++;
    }
}
