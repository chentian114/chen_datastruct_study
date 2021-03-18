package com.chen.data.struct.avl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @desc 两个数组的交集 II
 * @Author Chentian
 * @date 2021/3/6
 * https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/
 */
public class Leetcode350IntersectionOfTwoArrays {

    public int[] intersect(int[] nums1, int[] nums2) {
        AVLTreeMap<Integer,Integer> avlMap = new AVLTreeMap<>();
        AVLTreeSet<Integer> avlSet = new AVLTreeSet<>();
        for (int i = 0 ; i < nums1.length ; i++){
            if(avlMap.get(nums1[i]) == null){
                avlMap.add(nums1[i],1);
            }else {
                int num = avlMap.get(nums1[i]);
                avlMap.set(nums1[i], num+1);
            }
            avlSet.add(nums1[i]);
        }

        List<Integer> resultList = new ArrayList<>();
        for (int i = 0 ; i < nums2.length ; i++){
            if(avlMap.contains(nums2[i])){
                resultList.add(nums2[i]);
                Integer num = avlMap.get(nums2[i]);
                num--;
                if(num == 0){
                    avlMap.remove(nums2[i]);
                }else {
                    avlMap.set(nums2[i],num);
                }
            }
        }

        int[] result = new int[resultList.size()];
        for (int i = 0 ; i < resultList.size() ; i++){
            result[i] = resultList.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};

        int[] intersect = new Leetcode350IntersectionOfTwoArrays().intersect(nums1, nums2);
        System.out.println(Arrays.toString(intersect));
    }

}
