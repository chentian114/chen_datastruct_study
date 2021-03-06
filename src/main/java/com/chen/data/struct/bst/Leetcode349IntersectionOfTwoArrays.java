package com.chen.data.struct.bst;

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

        BSTSet<Integer> bstSet = new BSTSet<>();
        for (int i = 0 ; i < nums1.length ; i++){
            bstSet.add(nums1[i]);
        }

        List<Integer> resultList = new ArrayList<>();
        for (int i = 0 ; i < nums2.length ; i++){
            if(bstSet.contains(nums2[i])){
                resultList.add(nums2[i]);
                bstSet.remove(nums2[i]);
            }
        }

        int[] result = new int[resultList.size()];
        for (int i = 0 ; i < resultList.size() ; i++){
            result[i] = resultList.get(i);
        }
        return result;
    }


}
