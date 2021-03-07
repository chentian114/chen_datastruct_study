package com.chen.data.struct.heap;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc  前K个高频元素
 * @Author Chentian
 * @date 2021/3/7
 * https://leetcode-cn.com/problems/top-k-frequent-elements/
 */
public class LeetCode347TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0 ; i < nums.length ; i++){
            map.merge(nums[i],1,(a,b) -> a+b);
        }

        MinHeap<Node> minHeap = new MinHeap<>();
        int cnt = 0 ;
        for (Integer key : map.keySet()){
            if(cnt < k){
                minHeap.heapPush(new Node(key,map.get(key)));
            }else {
                Node peek = minHeap.peek();
                if(peek.num < map.get(key)){
                    minHeap.replace(new Node(key,map.get(key)));
                }
            }
            cnt++;
        }

        int[] result = new int[k];
        for (int i = 0 ; i < k ; i++){
            result[i] = minHeap.heapPop().data;
        }

        return result;
    }

    class Node implements Comparable<Node>{
        int data;
        int num;
        public Node(int data,int num){
            this.data = data;
            this.num = num;
        }

        @Override
        public int compareTo(Node o) {
            return this.num - o.num;
        }
    }


}
