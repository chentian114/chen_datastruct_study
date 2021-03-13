package com.chen.data.struct.segment;

import java.util.Random;

/**
 * @author: Chentian
 * @date: Created in 2021/3/9 21:12
 * @desc
 */
public class DemoTest {
    public static void main(String[] args) {

        Integer[] nums = {-2,0,3,-5,2,-1};
        testSegment1(nums);

        testSegment2(nums);

        testSegment3();

        Integer[] datas = SegmentAlgorithmComparator.generateRandom(10000, 30);
        int testTimes = 10000;
        System.out.print("segment1 batchUpdate:");
        benchMark(testTimes,new SegmentTree1<>(datas,(a,b)->a+b));
        System.out.print("segment2 batchUpdate:");
        benchMark(testTimes,new SegmentTree2<>(datas,(a,b)->a+b));
        System.out.print("segment3 batchUpdate:");
        benchMark(testTimes,new SegmentTree3<>(datas,(a,b)->a+b));

    }

    private static void benchMark(int testTimes, SegmentTree<Integer> segmentTree){
        long start = System.currentTimeMillis();
        Random random = new Random();
        for (int i = 0 ; i < testTimes ; i++){
            int index1 = random.nextInt(segmentTree.getSize());
            int index2 = random.nextInt(segmentTree.getSize());
            int num = random.nextInt(10);

            if(index1 > index2) {
                segmentTree.batchUpdate(index2,index1,num);
            }else {
                segmentTree.batchUpdate(index1,index2,num);
            }

            int index3 = random.nextInt(segmentTree.getSize());
            int index4 = random.nextInt(segmentTree.getSize());
            if(index3 > index4){
                segmentTree.query(index4,index3);
            }else {
                segmentTree.query(index3,index4);
            }

        }

        long end = System.currentTimeMillis();
        System.out.println("time costs:"+(end-start)+" ms");
    }

    private static void testSegment1(Integer[] nums) {
        SegmentTree1<Integer> segTree = new SegmentTree1<>(nums, (a , b) -> a+b);
        System.out.println(segTree);

        System.out.println(segTree.query(0,2));
        System.out.println(segTree.query(2,5));
        System.out.println(segTree.query(0,5));

        segTree.set(0,3);
        System.out.println(segTree);

        System.out.println("-------------------");

        segTree.batchUpdate(0,1,1);
        System.out.println(segTree);
        System.out.println("--------------------");
        testByComparator1(100000,10,20,20);
    }

    private static void testSegment2(Integer[] nums) {
        CorrectSegmentTree<Integer> corrTree = new CorrectSegmentTree<>(nums, (a, b) -> a+b);

        System.out.println(corrTree);

        System.out.println(corrTree.query(0,2));
        System.out.println(corrTree.query(2,5));
        System.out.println(corrTree.query(0,5));

        corrTree.set(0,3);
        System.out.println(corrTree);

        System.out.println("-------------------");
        SegmentTree2<Integer> segTree2 = new SegmentTree2<>(nums, (a, b) -> a+b);
        System.out.println(segTree2);

        System.out.println(segTree2.query(0,2));
        System.out.println(segTree2.query(2,5));
        System.out.println(segTree2.query(0,5));

        segTree2.set(0,3);
        System.out.println(segTree2);

        testByComparator2(100000,10,20,20);
    }

    private static void testSegment3() {
        System.out.println("------------------------");

        Integer[] data4 = {0,0,4};
        SegmentTree<Integer> segmentTree3 = new SegmentTree3<>(data4,(a, b)->a+b);
        System.out.println(segmentTree3);
        segmentTree3.batchUpdate(0,2,5);
        System.out.println(segmentTree3);
        segmentTree3.set(1,3);
        System.out.println(segmentTree3);

        CorrectSegmentTree<Integer> corrSeg = new CorrectSegmentTree<>(data4,(a,b)->a+b);
        corrSeg.batchUpdate(0,2,5);
        System.out.println(corrSeg);
        corrSeg.set(1,3);
        System.out.println(corrSeg);

        System.out.println("-----------");

        testByComparator3(100000,20,20,20);

    }


    private static void testByComparator1(int testTimes, int maxSize, int maxValue, int maxMethodSize){

        boolean result = false;
        for (int i = 0 ; i < testTimes; i++){

            Integer[] data = SegmentAlgorithmComparator.generateRandom(maxSize, maxValue);
            SegmentAlgorithmComparator.SegmentMethodEnum[] segmentMethodEnums = SegmentAlgorithmComparator.generateRandomMethod(maxMethodSize);

            Merger<Integer> merge = (a,b) -> a+b;
            SegmentTree<Integer> segmentTree = new SegmentTree1<>(data,merge);
            CorrectSegmentTree<Integer> correctSegmentTree = SegmentAlgorithmComparator.comparator(data,merge);

            result = SegmentAlgorithmComparator.isEqual(segmentTree,correctSegmentTree,segmentMethodEnums,data);
            if(!result){
                return;
            }
        }
        if(result){
            System.out.println("----------->comparator is ok!<---------------");
        }
    }


    private static void testByComparator2(int testTimes, int maxSize, int maxValue, int maxMethodSize){

        boolean result = false;
        for (int i = 0 ; i < testTimes; i++){

            Integer[] data = SegmentAlgorithmComparator.generateRandom(maxSize, maxValue);
            SegmentAlgorithmComparator.SegmentMethodEnum[] segmentMethodEnums = SegmentAlgorithmComparator.generateRandomMethod(maxMethodSize);

            Merger<Integer> merge = (a,b) -> a+b;
            SegmentTree<Integer> segmentTree = new SegmentTree2<>(data,merge);
            CorrectSegmentTree<Integer> correctSegmentTree = SegmentAlgorithmComparator.comparator(data,merge);

            result = SegmentAlgorithmComparator.isEqual(segmentTree,correctSegmentTree,segmentMethodEnums,data);
            if(!result){
                return;
            }
        }
        if(result){
            System.out.println("----------->comparator is ok!<---------------");
        }
    }

    private static void testByComparator3(int testTimes, int maxSize, int maxValue, int maxMethodSize){

        boolean result = false;
        for (int i = 0 ; i < testTimes; i++){

            Integer[] data = SegmentAlgorithmComparator.generateRandom(maxSize, maxValue);
            SegmentAlgorithmComparator.SegmentMethodEnum[] segmentMethodEnums = SegmentAlgorithmComparator.generateRandomMethod(maxMethodSize);

            Merger<Integer> merge = (a,b) -> a+b;
            SegmentTree<Integer> segmentTree = new SegmentTree3<>(data,merge);
            CorrectSegmentTree<Integer> correctSegmentTree = SegmentAlgorithmComparator.comparator(data,merge);

            result = SegmentAlgorithmComparator.isEqual(segmentTree,correctSegmentTree,segmentMethodEnums,data);
            if(!result){
                return;
            }
        }
        if(result){
            System.out.println("----------->comparator is ok!<---------------");
        }
    }
}
