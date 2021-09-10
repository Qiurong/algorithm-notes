package array;

/**
 * @description: 二维跳跃游戏
 * @author: Qr
 * @create: 2021-09-10 11:57
 * 二维数组nums[][],  数组上的元素代表了从当前位置可以跳跃几步, 方向只能是向右或者向下, 数组元素为0则代表当前数组位置不可达,
 * 求从数组左上角跳到右下角最小的跳跃次数.
 * 例：  3 2 2
 *      0 1 0
 *      1 1 1
 * 最小跳跃次数：2  从[0][0](3) -->[2][1](1) --> [2][2] 1
 **/
public class jumpGameAdvanced {

    //思路跟跳跃游戏||一致, 从起点开始去跳跃, 每步获取当前可以跳跃的所有范围, 遍历这个范围, 获取下一步可以跳跃的最远坐标
    public static int jump(int[][] nums){
        //特判
        if (nums.length == 1 && nums[0].length == 1){
            return 0;
        }
        int jump = 0;
        int maxNum = nums.length * nums[0].length;
        int endX = nums.length - 1;
        int endY = nums[0].length - 1;
        //用两个数组来存储x坐标和y坐标
        int[] x = new int[maxNum];
        int[] y = new int[maxNum];
        //第几个元素
        int pos = 0;
        int prevPos = pos;
        //数组添加元素的下标
        int index = 0;
        int end = 0;
        int[][] isAdded = new int[nums.length][nums[0].length];
        isAdded[0][0] = 1;
        while (true){
            jump++;
            //遍历每步到边界
            while (pos <= end){
                int currX = x[pos];
                int currY = y[pos];
                int currJump = nums[currX][currY];
                //1.算出当前坐标可以到达的坐标范围并将其放入数组  范围：以当前坐标可以到达的范围为一个上三角
                for (int i = currX; i <= currX + currJump; i++) {
                    for (int j = currY; j <= currX + currY + currJump - i; j++) {
                        //到达终点
                        if (i >= endX && j >= endY){
                            return jump;
                        }
                        //舍去为0的
                        if (i <= endX && j <= endY && nums[i][j] == 0){
                            continue;
                        }
                        //把当前有效范围放入数组
                        //这里出现Bug: 重复把当前结点放入了pos数组
                        if (i <= endX && j <= endY && !exist(i,j,isAdded)){
                            index++;
                            x[index] = i;
                            y[index] = j;
                            isAdded[i][j] = 1;
                        }
                    }
                }
                pos++;
            }
            end = index;
            //上一步跟这一步走到的边界相同则不可达
            if (prevPos == pos){
                break;
            }
            prevPos = pos;
        }
        return -1;
    }

    //判断nums[]数组中某个元素是否已经加入
    public static boolean exist(int i, int j, int[][] added){
        return  added[i][j] == 1;
    }
    public static void main(String[] args) {
        int[][] nums = {{3,1,1},{0,2,0},{1,0,0},{0,1,1}};
        int steps = jump(nums);
        System.out.println(steps);
    }
}
