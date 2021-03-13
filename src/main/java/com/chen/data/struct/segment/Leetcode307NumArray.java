package com.chen.data.struct.segment;

/**
 * @author: Chentian
 * @date: Created in 2021/3/12 6:41
 * @desc 区域和检索 - 数组可修改
 * https://leetcode-cn.com/problems/range-sum-query-mutable/
 */
public class Leetcode307NumArray {


    private int[] data;
    private int[] tree;


    public Leetcode307NumArray(int[] nums) {
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

    public void update(int index, int val) {
        if(data == null || data.length == 0){
            return;
        }

        update(0, 0, data.length-1, index, val);

    }

    private void update(int nodeIndex, int left, int right, int index, int val){
        if(left == right && left == index){
            data[index] = val;
            tree[nodeIndex] = val;
            return;
        }

        int mid = left + (right - left)/2;
        int leftChild = leftChild(nodeIndex);
        int rightChild = rightChild(nodeIndex);
        if(index > mid){
            update(rightChild, mid+1, right, index, val);
        }
        else {
            update(leftChild, left, mid, index,val);
        }
        tree[nodeIndex] = tree[leftChild] + tree[rightChild];
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
        Leetcode307NumArray numArray = new Leetcode307NumArray(new int[]{1, 3, 5});
        System.out.println(numArray.sumRange(0, 2)); // 返回 9 ，sum([1,3,5]) = 9
        numArray.update(1, 2);   // nums = [1,2,5]
        System.out.println(numArray.sumRange(0, 2)); // 返回 8 ，sum([1,2,5]) = 8
    }
}
