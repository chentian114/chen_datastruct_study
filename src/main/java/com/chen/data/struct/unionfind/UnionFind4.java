package com.chen.data.struct.unionfind;

/**
 * @desc 基于 rank 优化 Quick Union 并查集
 * @Author Chentian
 * @date 2021/3/15
 */
public class UnionFind4 implements UnionFind{

    // parent[i]表示第i个元素所指向的父节点
    private int[] parent;
    //rank[i]表示以i为根的集合所表示的树的层数
    private int[] rank;

    public UnionFind4(int num){
        if(num <=0 ){
            throw new IllegalArgumentException("num is error!");
        }
        parent = new int[num];
        rank = new int[num];
        for (int i = 0 ; i < num ; i++){
            parent[i] = i;
            rank[i] = 1;
        }
    }

    // 查看元素p和元素q是否所属一个集合
    // O(h)复杂度, h为树的高度
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // 查找过程, 查找元素p所对应的集合编号
    // O(h)复杂度, h为树的高度
    private int find(int p){
        if(p < 0 || p >= parent.length)
            throw new IllegalArgumentException("p is out of bound.");

        while(parent[p] != p){
            p = parent[p];
        }
        return p;
    }

    // 合并元素p和元素q所属的集合
    // O(h)复杂度, h为树的高度
    @Override
    public void unionElements(int p, int q) {
        int pParent = find(p);
        int qParent = find(q);
        if(pParent == qParent){
            return;
        }

        // 根据两个元素所在树的rank不同判断合并方向
        // 将rank低的集合合并到rank高的集合上
        if(rank[pParent] > rank[qParent]){
            parent[qParent] = pParent;
        }
        else if(rank[pParent] < rank[qParent]){
            parent[pParent] = qParent;
        }
        else {
            parent[pParent] = qParent;
            rank[qParent] += 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }
}
