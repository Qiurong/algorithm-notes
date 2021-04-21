package backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: 93. 复原 IP 地址
 * @author: Qr
 * @create: 2021-04-21 15:19
 **/
public class restoreIpAddresses {

    static List<String> res;
    static List<Integer> path;

    //总结:这题回溯的难度在于: 1.对于IP的遍历 i<start+3上
    //                     2.在到第四层时直接判断不用再往深层递归了
    public static List<String> restoreIpAddresses(String s) {
        res = new LinkedList<>();
        path = new ArrayList<>();

        if (s.length() < 4 || s.length() > 12){
            return res;
        }

        backtrack(0, s);
        return res;
    }

    public static void backtrack(int start,String s){
        //找到了正确答案
        if (path.size() == 4 && start == s.length()){
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < path.size() ;i++) {
                StringBuilder currNum = new StringBuilder();
                int num = path.get(i);
                currNum.append(num);
                currNum.append(".");
                str.append(currNum);
            }
            str.deleteCharAt(str.length()-1);
            res.add(str.toString());
            return;
        }
        //达到深度4但不是答案说明不需要在探测
        if (path.size() == 4){
            return;
        }

        //每个IP最多为3位
        for (int i = start; i < start + 3 && i < s.length(); i++) {
            //截取了前三个 直接判断剩余字符
            if (path.size() == 3){
                //剩余字符合法
                if (isValid(s,i,s.length()-1)){
                    //直接把剩余字符加入到path中
                    path.add(Integer.parseInt(s.substring(i,s.length())));
                    backtrack(s.length(),s);
                    path.remove(path.size()-1);
                    //break 回溯到上层
                    break;
                }else{
                    break;
                }
            }
            //不合法则剪枝
            if (!isValid(s,start,i)){
                continue;
            }

            path.add(Integer.parseInt(s.substring(start,i+1)));
            backtrack(i+1,s);
            path.remove(path.size()-1);
        }
    }

    //check s是否为 “0” - “255”
    public static boolean isValid(String s, int begin, int end){
        if (end - begin > 2){
            return false;
        }
        //先检查是否存在首位为0的情况
        if (s.charAt(begin) == '0' && begin != end){
            return false;
        }
        Integer value = Integer.parseInt(s.substring(begin,end+1));
        if (value >= 0 && value <= 255){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        restoreIpAddresses("240102");
    }
}
