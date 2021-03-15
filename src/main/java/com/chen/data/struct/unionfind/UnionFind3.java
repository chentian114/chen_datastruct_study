package com.chen.data.struct.unionfind;

/**
 * @desc 基于 size 优化 Quick Union 并查集
 * @Author Chentian
 * @date 2021/3/15
 */
public class UnionFind3 implements UnionFind{

    private int[] parent ;
    //表示以i为根的集合中元素个数
    private int[] size;

    public UnionFind3(int num){
        if(num <=0 ){
            throw new IllegalArgumentException("num is error!");
        }
        parent = new int[num];
        size = new int[num];
        for (int i = 0 ; i < num ; i++){
            parent[i] = i;
            size[i] = 1 ;
        }
    }

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

        // 根据两个元素所在树的元素个数不同判断合并方向
        // 将元素个数少的集合合并到元素个数多的集合上
        if(size[pParent] > size[qParent]){
            parent[qParent] = parent[pParent];
            size[pParent] += size[qParent];
        }
        else {
            parent[pParent] = parent[qParent];
            size[qParent] += size[pParent];
        }

    }

    @Override
    public int getSize() {
        return parent.length;
    }
}
