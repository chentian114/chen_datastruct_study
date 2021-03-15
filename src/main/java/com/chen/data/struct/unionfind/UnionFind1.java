package com.chen.data.struct.unionfind;

/**
 * @desc 基于数组实现 Quick Find 并查集
 * @Author Chentian
 * @date 2021/3/15
 */
public class UnionFind1 implements UnionFind{

    private int[] data ;

    public UnionFind1(int num){
        if(num <=0 ){
            throw new IllegalArgumentException("num is error!");
        }
        data = new int[num];
        for (int i = 0 ; i < data.length ; i++){
            data[i] = i;
        }
    }

    @Override
    public boolean isConnected(int p, int q) {

        return find(p) == find(q);
    }

    private int find(int p){

        if(p < 0 || p >= data.length)
            throw new IllegalArgumentException("p is out of bound.");
        return data[p];
    }

    @Override
    public void unionElements(int p, int q) {
        int pParent = find(p);
        int qParent = find(q);
        if(pParent == qParent){
            return;
        }

        // 合并过程需要遍历一遍所有元素, 将两个元素的所属集合编号合并
        for (int i = 0 ; i < data.length ; i++){
            if(data[i] == qParent){
                data[i] = pParent;
            }
        }
    }

    @Override
    public int getSize() {
        return data.length;
    }
}
