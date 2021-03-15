package com.chen.data.struct.unionfind;

import java.util.HashSet;
import java.util.Set;

/**
 * @desc 省份数量
 * @Author Chentian
 * @date 2021/3/15
 * https://leetcode-cn.com/problems/number-of-provinces/
 */
public class Leetcode547NumberOfProvinces {

    public int findCircleNum(int[][] isConnected) {
        int[] parent = new int[isConnected.length];
        int[] rank = new int[isConnected.length];

        for (int i = 0 ; i < isConnected.length ; i++){
            parent[i] = i;
            rank[i] = 1;
        }

        for (int i = 0 ; i < isConnected.length ; i++){
            for (int j = 0 ; j < isConnected[i].length; j++){
                if(isConnected[i][j] == 1){
                    union(parent,rank,i,j);
                }
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0 ; i < parent.length ; i++){
            int pRoot = find(parent,i);
            set.add(pRoot);
        }

        return set.size();
    }


    private int find(int[] parent, int p){

        while (parent[p] != p){
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    private void union(int[] parent, int[] rank, int p, int q){
        int pRoot = find(parent, p);
        int qRoot = find(parent, q);
        if(pRoot == qRoot){
            return;
        }


        if(rank[pRoot] > rank[qRoot]){
            parent[qRoot] = pRoot;
        }
        else if(rank[pRoot] < rank[qRoot]){
            parent[pRoot] = qRoot;
        }
        else {
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;
        }
    }

}
