package array;

/**
 * @description: 283. 移动零
 * @author: Qr
 * @create: 2021-07-21 16:32
 *
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 * 输入: [0,1,0,3,2]
 * 输出: [1,3,2,0,0]
 *
 * 说明:
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 **/
public class move_zeroes {
    //思想：双指针从左到右遍历, 遇到非0和 0 swap, swap完之后需要更新两个指针
    public void moveZeroes(int[] nums) {
        //数组中第一个O的下标
        int firstZeroIndex = 0;
        //数组中第一个还未移动的 非0的下标
        int firstNumIndex = 0;
        //赋初值
        while (firstZeroIndex < nums.length && nums[firstZeroIndex] != 0){
            firstZeroIndex++;
        }
        while (firstNumIndex < nums.length && nums[firstNumIndex] == 0){
            firstNumIndex++;
        }
        //特判：全是0  全是非0
        if (firstNumIndex == nums.length || firstZeroIndex == nums.length){
            return;
        }

        //开始从左到右遍历
        while (firstNumIndex < nums.length && firstZeroIndex < nums.length){
            //swap之前要确保0 在 非0 前面
            if (firstZeroIndex < firstNumIndex){
                swap(nums,firstZeroIndex,firstNumIndex);
                //更新两个指针
                while (firstZeroIndex < nums.length && nums[firstZeroIndex] != 0){
                    firstZeroIndex++;
                }
                while (firstNumIndex < nums.length && nums[firstNumIndex] == 0){
                    firstNumIndex++;
                }
            }else {
                //0在 非0 后面, 寻找下一个非0
                firstNumIndex++;
                while (firstNumIndex < nums.length && nums[firstNumIndex] == 0){
                    firstNumIndex++;
                }
            }

        }
    }

    //参考官方解答依旧是双指针, 相对于我的方法简单好多
    //left: 已经处理过的序列的下一个元素, 为多个0的第一个0
    //right: 待处理的序列头部   其实就相当于我的第一个非0
    //性质:  左指针到右指针左边均为0, 每次交换的时候都是把右指针的非零和  左指针即第一个零  swap, swap完了之后left++,又指向了第一个0, 也是处理完的序列下一个元素
    public void moveZeroes_optimized (int[] nums){
        int left = 0;
        int right = 0;
        //右指针不断右移
        while (right < nums.length){
            //右指针非0, 需要把非0数 swap到前面去
            if (nums[right] != 0){
                swap(nums,left,right);
                //left右移
                left++;
            }
            right++;
        }
    }
    public void swap(int[] nums, int p1, int p2){
        int temp = nums[p1];
        nums[p1] = nums[p2];
        nums[p2] = temp;
    }


}
