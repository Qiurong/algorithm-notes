分治法：一种非常重要的算法思想甚至是哲学思想，其总结而言就是分而治之。
      		把一个大问题拆分成很多形式相似的小问题，最后求解出这些小问题的解之后在一步步合并为原问题的解。
      		拆分成小问题这一步骤可以很好的用递归去进行实现。



其套路总结为：

1. 寻找递归终止条件(拆分为最简单的问题，无法再进行拆分）

2. 分段处理(递归语句)

   2.1. 如何拆分（如何递归）

   2.2. 拆分过程中所需要的操作

3. 合并结果（merge）
