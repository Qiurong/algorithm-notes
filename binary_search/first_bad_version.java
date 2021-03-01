package binary_search;

/**
 * @description: 278. 第一个错误的版本
 * @author: Qr
 * @create: 2021-03-01 13:58
 **/
public class first_bad_version {
    /* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */
    //这里给出一个代码的demo
    boolean isBadVersion(int version){
        //判断奇偶, version奇数则返回true
        return (version & 1) == 1;
    }
    //这题的本质是一个左区间二分查找, 找到对应的值后不断像左边去缩
    public int firstBadVersion(int n) {
        long start = 1;
        //坑壁题目,又用边界值
        long end = n + 1L;
        long mid;
        while (start < end){
            mid = start + ((end - start) >> 1);
            if (isBadVersion((int) mid) == true){
                end = mid;
            }else {
                start = mid + 1;
            }
        }
        return (int) end;
    }
}
