package programmerCarl.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: 406. 根据身高重建队列
 * @author: Qr
 * @create: 2021-09-27 15:36
 **/

public class queue_reconstruction_by_height {

    //自定义class以实现按 h升序, h相同的情况k降序
    //相同身高时候, 应该先插入k较大的, 因为k代表了前面有 k个大于等于自身身高的, 先插入较大k代表了前面留k个空位给相同身高的
    class peopleHeight implements Comparable<peopleHeight>{
        int height;
        int prevHigherNum;

        public  peopleHeight(int height,int prevHigherNum){
            this.height = height;
            this.prevHigherNum = prevHigherNum;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getPrevHigherNum() {
            return prevHigherNum;
        }

        public void setPrevHigherNum(int prevHigherNum) {
            this.prevHigherNum = prevHigherNum;
        }

        //按照height升序排序, 相同的情况下按照prevHigherNum降序排序
        @Override
        public int compareTo(peopleHeight o) {
            if (this.height != o.getHeight()){
                return this.height - o.getHeight();
            }else {
                return o.getPrevHigherNum() - this.prevHigherNum;
            }
        }
    }


    public int[][] reconstructQueue(int[][] people) {
        //特判
        if (people == null || people.length == 1){
            return people;
        }
        int len = people.length;
        int[][] res = new int[len][2];
        //用于判断res数组中某一列是否为空即是否访问过
        boolean[] visited = new boolean[len];

        //1.h升序，k降序排序
        List<peopleHeight> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            list.add(new peopleHeight(people[i][0],people[i][1]));
        }
        Collections.sort(list);

        //2.把排序后的list按照第 ki+1个空的位置 插入 res中
        for (peopleHeight p : list) {
            int index = getIndex(visited, p.getPrevHigherNum());
            res[index][0] = p.getHeight();
            res[index][1] = p.getPrevHigherNum();
        }
        return res;
    }

    //传入ki, 返回visited数组中从左到右第ki个非空元素的下标
    public int getIndex(boolean[] visited, int k){
        int index = 0;
        while (index < visited.length && k >= 0){
            if (!visited[index]) {
                k--;
            }
            if (k >= 0){
                index++;
            }
        }
        if (index == visited.length){
            index--;
        }
        visited[index] = true;
        return index;
    }


    //方法2. 按照h降序, k升序
    class structure implements Comparable<structure>{
        int h;
        int k;

        public structure(int h, int k){
            this.h = h;
            this.k = k;
        }
        @Override
        public int compareTo(structure o) {
            if (this.h != o.h){
                return o.h - this.h;
            }else {
                return this.k - o.k;
            }
        }
    }
    public int[][] reconstructQueue_descendingOrder(int[][] people) {
        int len = people.length;
        int[][] res = new int[len][2];
        List<structure> sortedList = new ArrayList<>(len);
        List<structure> reconstructedList = new LinkedList<>();
        //1.构造按h降序k升序的
        for (int i = 0; i < len; i++) {
            sortedList.add(new structure(people[i][0],people[i][1]));
        }
        Collections.sort(sortedList);
        //2.遍历sortedList,按照每一个的k进行插入
        for (structure s:sortedList) {
            reconstructedList.add(s.k,s);
        }
        int i =0;
        for (structure s: reconstructedList) {
            res[i][0] = s.h;
            res[i][1] = s.k;
            i++;
        }
        return res;
    }
}
