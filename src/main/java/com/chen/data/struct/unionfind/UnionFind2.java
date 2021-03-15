package com.chen.data.struct.unionfind;

/**
 * @desc 基于数组实现 Quick Union 并查集
 * @Author Chentian
 * @date 2021/3/15
 */
public class UnionFind2 implements UnionFind{

    private int[] parent;

    public UnionFind2(int num){
        if(num <=0 ){
            throw new IllegalArgumentException("num is error!");
        }

        // 初始化, 每一个parent[i]指向自己, 表示每一个元素自己自成一个集合
        parent = new int[num];
        for (int i = 0; i < parent.length ; i++){
            parent[i] = i;
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

        // 不断去查询自己的父亲节点, 直到到达根节点
        // 根节点的特点: parent[p] == p
        while (parent[p] != p){
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

        parent[pParent] = parent[qParent];
    }

    @Override
    public int getSize() {
        return parent.length;
    }
}
