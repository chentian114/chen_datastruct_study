package com.chen.data.struct.bst;

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
        BSTMap<Integer,Integer> bstMap = new BSTMap<>();
        BSTSet<Integer> bstSet = new BSTSet<>();
        for (int i = 0 ; i < nums1.length ; i++){
            if(bstMap.get(nums1[i]) == null){
                bstMap.add(nums1[i],1);
            }else {
                int num = bstMap.get(nums1[i]);
                bstMap.set(nums1[i], num+1);
            }
            bstSet.add(nums1[i]);
        }

        List<Integer> resultList = new ArrayList<>();
        for (int i = 0 ; i < nums2.length ; i++){
            if(bstMap.contains(nums2[i])){
                resultList.add(nums2[i]);
                Integer num = bstMap.get(nums2[i]);
                num--;
                if(num == 0){
                    System.out.println("----------------------------");
                    System.out.println("Before:"+bstMap.inOrder());
                    bstMap.remove(nums2[i]);
                    System.out.println("remove:"+nums2[i]);
                    System.out.println("After:"+bstMap.inOrder());
                    System.out.println("----------------------------");

                }else {
                    bstMap.set(nums2[i],num);
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
        int[] nums1 = {54,93,21,73,84,60,18,62,59,89,83,89,25,39,41,55,78,27,65,82,94,61,12,38,76,5,35,6,51,48,61,0,47,60,84,9,13,28,38,21,55,37,4,67,64,86,45,33,41};
        int[] nums2 = {17,17,87,98,18,53,2,69,74,73,20,85,59,89,84,91,84,34,44,48,20,42,68,84,8,54,66,62,69,52,67,27,87,49,92,14,92,53,22,90,60,14,8,71,0,61,94,1,22,84,10,55,55,60,98,76,27,35,84,28,4,2,9,44,86,12,17,89,35,68,17,41,21,65,59,86,42,53,0,33,80,20};

        int[] intersect = new Leetcode350IntersectionOfTwoArrays().intersect(nums1, nums2);
        System.out.println(Arrays.toString(intersect));
    }

}
