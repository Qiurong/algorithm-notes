package programmerCarl.array;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @description: 904. 水果成篮
 * @author: Qr
 * @create: 2021-10-14 11:00
 **/
public class fruit_into_baskets {

    //暴力解法: 枚举每棵树作为开始, 计算可以收集到的两种元素的最大值
    //时间复杂度: O(n^2)
    public int totalFruit(int[] fruits) {
        int totalSum = 0;

        for (int i = 0; i < fruits.length; i++) {
            int fruitOne = fruits[i];
            int currSum = 1;
            int j = i+1;
            //先找到第二种水果
            while (j < fruits.length && fruits[j] == fruitOne){
                currSum++;
                j++;
            }
            if (j >= fruits.length){
                totalSum = Math.max(totalSum,currSum);
                break;
            }
            int fruitTwo = fruits[j];
            while (j < fruits.length){
                if (fruits[j] == fruitOne || fruits[j] == fruitTwo){
                    currSum++;
                }else {
                    break;
                }
                totalSum = Math.max(totalSum,currSum);
                j++;
            }
        }
        return totalSum;
    }

    //滑动窗口, 左闭右闭原则  需要注意初始值且是先++在add
    //滑动窗口思想好想，但窗口每次的移动和各种处理，容易错误
    public int totalFruit_win(int[] fruits){
        //用hashMap去存取窗口内的水果种类
        //k:水果种类  v:窗口中该种类水果的数目
        HashMap<Integer,Integer> fruitTypes = new HashMap<>();
        int left = 0;
        int totalSum = 0;
        int right = 0;
        addToMap(fruitTypes,fruits[right]);
        //每一轮 首先扩展窗口右边界至极限(窗口内包含三种水果), 然后窗口左边界收缩1
        while (left <= right && right < fruits.length){
            //1. 先右移窗口至满足条件
            while (right < fruits.length){
                //先++ 再add
                right++;
                if (right == fruits.length){
                    right--;
                    break;
                }
                addToMap(fruitTypes, fruits[right]);
                //当加入第三种水果时, 需要收缩有边界且移除该种水果
                if (fruitTypes.size() == 3){
                    removeFromMap(fruitTypes,fruits[right]);
                    right--;
                    break;
                }
            }
            if (fruitTypes.size() <= 2){
                totalSum = Math.max(totalSum,right-left+1);
            }
            //2.窗口左移
            removeFromMap(fruitTypes,fruits[left]);
            left++;
        }
        return totalSum;
    }

    public void addToMap(HashMap<Integer,Integer> map, int fruit){
        if (map == null){
            return;
        }
        map.put(fruit, map.getOrDefault(fruit,0) + 1);
    }


    public void removeFromMap(HashMap<Integer,Integer> map, int fruit){
        if (map == null){
            return;
        }
        if (map.containsKey(fruit)){
            int num = map.get(fruit);
            if (num == 1){
                map.remove(fruit);
            }else {
                map.put(fruit,num-1);
            }
        }
    }
}
