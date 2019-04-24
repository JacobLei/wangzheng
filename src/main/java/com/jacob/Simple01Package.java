package com.jacob;


public class Simple01Package {
    /**
     * 背包中物品总重量的最大值
     * @param weight 每个物品的重量数组
     * @param n 物品的个数
     * @param maxWight 背包可承受的最大重量
     * @return 背包中物品总重量的最大值
     */
    public int getMaxWeightInPackage(int[] weight, int n, int maxWight){
        int res = 0;
        Boolean[][] state = new Boolean[n][maxWight+1]; // 先申请状态数组，即上面的表格的初始化状态

        // 对第0行进行特殊处理，手动标记
        state[0][0] = true;             //  将第0号物品不放入背包
        state[0][weight[0]] = true;     // 将第0号武平放入背包

        // 依次对剩下的物品进行决策
        for (int i=1; i<n; i++){
            // 将第i号物品不放入背包
            for (int j=0; j<=maxWight; j++){
                if ( state[i-1][j] != null && state[i-1][j] == true){
                    state[i][j] = state[i-1][j];
                }
            }

            // 将第i号物品放入背包
            for (int j=0; j<= (maxWight-weight[i]); j++){
                if(state[i-1][j] != null && state[i-1][j] == true)
                    state[i][j+weight[i]] = true;
            }
        }

        // 在最后一行从后向前遍历
        for (int i=maxWight; i>=0; i--){
            if(state[n-1][i] != null && state[n-1][i] == true){
                res = i;
                break;
            }
        }
        return res;
    }

    public int getMaxWeightInPackage2(int[] weight, int n, int maxWight) {
        int res = 0;
        Boolean[] state = new Boolean[maxWight+1]; // 先申请状态数组

        // 对第0行进行特殊处理，手动标记
        state[0] = true;             //  将第0号物品不放入背包
        state[weight[0]] = true;     // 将第0号武平放入背包

        // 依次对剩下的物品进行决策
        for (int i = 1; i < n; i++) {
            for (int j=maxWight-weight[i]; j>=0; j--){  // 把第i个物品放入背包中
                if (state[j] != null && state[j] == true)
                    state[j+weight[i]] = true;
            }
        }

        // 在最后一行从后向前遍历
        for (int i=maxWight; i>=0; i--){
            if(state[i] != null && state[i] == true){
                res = i;
                break;
            }
        }

        return res;
    }
    public static void main(String[] args) {
        Simple01Package simple01Package = new Simple01Package();
        int[] weight = new int[]{2, 2, 4, 6, 3};
        int res = simple01Package.getMaxWeightInPackage2(weight, 5, 9);
        System.out.println(res);
    }
}
