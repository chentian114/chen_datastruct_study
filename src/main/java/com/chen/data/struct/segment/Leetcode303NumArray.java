package com.chen.data.struct.segment;

/**
 * @author: Chentian
 * @date: Created in 2021/3/12 6:41
 * @desc 区域和检索 - 数组不可变
 * https://leetcode-cn.com/problems/range-sum-query-immutable/
 */
public class Leetcode303NumArray {


    private int[] data;
    private int[] tree;


    public Leetcode303NumArray(int[] nums) {
        if(nums.length == 0){
            return;
        }
        data = new int[nums.length];
        for (int i = 0 ; i < nums.length ; i++){
            data[i] = nums[i];
        }
        tree = new int[4*nums.length];
        buildSegmentTree(0,0,data.length-1);
    }

    private void buildSegmentTree(int nodeIndex, int left, int right){
        if(left == right){
            tree[nodeIndex] = data[left];
            return;
        }

        int mid = left + (right - left)/2;
        int leftChild = leftChild(nodeIndex);
        int rightChild = rightChild(nodeIndex);

        buildSegmentTree(leftChild, left, mid);
        buildSegmentTree(rightChild,mid+1, right);

        tree[nodeIndex] = tree[leftChild] + tree[rightChild];
    }
    private int leftChild(int index){
        return 2*index + 1;
    }

    private int rightChild(int index){
        return 2*index + 2;
    }

    public int sumRange(int i, int j) {

        return sumRange(0,0,data.length-1,i,j);
    }

    private int sumRange(int nodeIndex, int left, int right, int queryL, int queryR){
        if(left == queryL && right == queryR){
            return tree[nodeIndex];
        }

        int mid = left + (right - left)/2;
        int leftChild = leftChild(nodeIndex);
        int rightChild = rightChild(nodeIndex);
        if(queryL > mid){
            return sumRange(rightChild, mid+1, right, queryL, queryR);
        }
        else if(queryR <= mid){
            return sumRange(leftChild, left, mid, queryL,queryR);
        }
        else {
            int leftVal = sumRange(leftChild, left, mid, queryL, mid);
            int rightVal = sumRange(rightChild, mid+1 , right, mid+1, queryR);
            return leftVal + rightVal;
        }
    }

    public static void main(String[] args) {

        Leetcode303NumArray numArray = new Leetcode303NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(numArray.sumRange(0, 2)); // return 1 ((-2) + 0 + 3)
        System.out.println(numArray.sumRange(2, 5)); // return -1 (3 + (-5) + 2 + (-1))
        System.out.println(numArray.sumRange(0, 5)); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))

    }
}
