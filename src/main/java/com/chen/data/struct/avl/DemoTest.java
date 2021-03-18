package com.chen.data.struct.avl;

import com.chen.data.struct.bst.Map;

/**
 * @desc
 * @Author Chentian
 * @date 2021/3/16
 */
public class DemoTest {

    public static void main(String[] args) {

        int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12};

        AVLTree<Integer> avl = new AVLTree<>();
        for (int i = 0 ; i < arr.length ; i++){
            avl.add(arr[i]);
        }
        System.out.println(avl);

        System.out.println(avl.contains(5));
        System.out.println(avl.contains(10));
        System.out.println(avl.minimum());
        System.out.println(avl.maximum());

        avl.remove(10);
        System.out.println(avl);
        avl.remove(5);
        System.out.println(avl);

        System.out.println(avl.levelOrder());

        testByComparator(500000,5,50,5);

        testAVLTreeMap();

    }

    private static void testAVLTreeMap() {
        System.out.println("-------------------");
        Map<Integer,Integer> map = new AVLTreeMap<>();
        int[] data = {5,32,7,15,1};
        for (int i = 0 ; i< data.length ; i++){
            map.add(data[i],data[i]);
        }
        System.out.println(map);

        map.remove(19);
        System.out.println(map);

        System.out.println(map.remove(3));
        System.out.println(map);

        testMapByComparator(500000,5,50,5);
    }

    private static void testMapByComparator(int testTimes,int maxSize,int maxValue,int maxMethodSize){

        boolean result = false;
        for (int i = 0 ; i < testTimes; i++){

            Map<Integer,Integer> map = new AVLTreeMap<>();
            java.util.Map<Integer,Integer> correctMap = MapAlgorithmComparator.comparator();

            int[] data = MapAlgorithmComparator.generateRandomData(maxSize,maxValue);
            MapAlgorithmComparator.MapMethodEnum[] methodEnums = MapAlgorithmComparator.generateRandomMethod(maxMethodSize);

            result = MapAlgorithmComparator.isEqual(map, correctMap, methodEnums, data);
            if(!result){
                return;
            }
        }
        if(result){
            System.out.println("----------->comparator is ok!<---------------");
        }
    }

    private static void testByComparator(int testTimes,int maxSize,int maxValue,int maxMethodSize){

        boolean result = false;
        for (int i = 0 ; i < testTimes; i++){

            AVLTree<Integer> bst = new AVLTree<>();
            CorrectAVLTree<Integer,Object> correctBST = AVLAlgorithmComparator.comparator();

            int[] data = AVLAlgorithmComparator.generateRandomData(maxSize,maxValue);
            AVLAlgorithmComparator.AVLMethodEnum[] methodEnums = AVLAlgorithmComparator.generateRandomMethod(maxMethodSize);

            result = AVLAlgorithmComparator.isEqual(bst, correctBST, methodEnums, data);
            if(!result){
                return;
            }
        }
        if(result){
            System.out.println("----------->comparator is ok!<---------------");
        }
    }
}
