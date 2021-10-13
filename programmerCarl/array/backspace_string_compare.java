package programmerCarl.array;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @description: 844. 比较含退格的字符串
 * @author: Qr
 * @create: 2021-10-13 14:48
 **/
public class backspace_string_compare {

    //回退的这个操作可以用栈来做, 那么基本思想就是栈来保存, 遇到#则pop
    public boolean backspaceCompare(String s, String t) {
       Deque<Character> stackS = new ArrayDeque<>();
       Deque<Character> stackT = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (!c.equals('#')){
                stackS.push(c);
            }else {
                if (!stackS.isEmpty()){
                    stackS.pop();
                }
            }
        }
        for (int i = 0; i < t.length(); i++) {
            Character c = t.charAt(i);
            if (!c.equals('#')){
                stackT.push(c);
            }else {
                if (!stackT.isEmpty()){
                    stackT.pop();
                }
            }
        }
        //判断两个栈是否相等
        if (stackS.isEmpty() && stackT.isEmpty()){
            return true;    //两都为空
        }else if (stackT.isEmpty() || stackS.isEmpty()){
            return false;   //只有一个为空
        }else {
            if(stackS.size() != stackT.size()){
                return false;
            }
            //逐个比较poo元素
            while (!stackS.isEmpty() && !stackT.isEmpty()){
                if (!stackS.pop().equals(stackT.pop())){
                    return false;
                }
            }
            return true;
        }
    }


    //一个字符是否被回退仅仅取决于其之后的#符, 与其之前的#符无关,  因此可以逆序遍历字符串
    //自己没想到逆序遍历这种方法
    public boolean backspaceCompare_reverse(String s, String t) {
        //代表遍历到当前需要退格次数
        int skipS = 0;
        int skipT = 0;
        int indexS = s.length() - 1;
        int indexT = t.length() - 1;
        while (true){
            //每一轮确定一个有效字符然后比较这两个有效字符
            while(indexS >= 0){
                if (s.charAt(indexS) == '#'){
                    //当前字符为退格,
                    skipS++;
                    indexS--;
                }else if (skipS > 0){
                    //当前字符为普通但还需退格
                    skipS--;
                    indexS--;
                }else {
                    //当前字符为普通且无需退格, 则获得了有效字符
                    break;
                }
            }

            while(indexT >= 0){
                if (t.charAt(indexT) == '#'){
                    //当前字符为退格,
                    skipT++;
                    indexT--;
                }else if (skipT > 0){
                    //当前字符为普通但还需退格
                    skipT--;
                    indexT--;
                }else {
                    //当前字符为普通且无需退格, 则获得了有效字符
                    break;
                }
            }

            //两者走到尽头则退出循环, 之后如果两者都为-1则说明两者一致
            if (indexS < 0 || indexT < 0){
                break;
            }
            //有效字符不一样则直接return false
            if (s.charAt(indexS) != t.charAt(indexT)){
                return false;
            }
            indexS--;
            indexT--;
        }
        //两者都为-1则说明 两者相同, 否则说明长度不一样
        if (indexS == -1 && indexT == -1){
            return true;
        }
        return false;
    }
}
