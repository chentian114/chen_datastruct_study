package com.chen.data.struct.avl;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc 两个数组的交集
 * @Author Chentian
 * @date 2021/3/6
 * https://leetcode-cn.com/problems/intersection-of-two-arrays/
 */
public class Leetcode349IntersectionOfTwoArrays {


    public int[] intersection(int[] nums1, int[] nums2) {

        AVLTreeSet<Integer> avlSet = new AVLTreeSet<>();
        for (int i = 0 ; i < nums1.length ; i++){
            avlSet.add(nums1[i]);
        }

        List<Integer> resultList = new ArrayList<>();
        for (int i = 0 ; i < nums2.length ; i++){
            if(avlSet.contains(nums2[i])){
                resultList.add(nums2[i]);
                avlSet.remove(nums2[i]);
            }
        }

        int[] result = new int[resultList.size()];
        for (int i = 0 ; i < resultList.size() ; i++){
            result[i] = resultList.get(i);
        }
        return result;
    }


}
