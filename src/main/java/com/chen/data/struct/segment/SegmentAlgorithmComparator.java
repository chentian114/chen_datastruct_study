package com.chen.data.struct.segment;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: Chentian
 * @date: Created in 2021/3/11 7:30
 * @desc
 */
public class SegmentAlgorithmComparator {

    public enum SegmentMethodEnum{
        getSize,get,query,set,batchUpdate
    }

    public static CorrectSegmentTree<Integer> comparator(Integer[] nums, Merger<Integer> merger){
        CorrectSegmentTree<Integer> correctSegmentTree = null;
        try {
            correctSegmentTree =new CorrectSegmentTree<>(nums, merger);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(Arrays.toString(nums));
        }
        return correctSegmentTree;
    }


    public static Integer[] generateRandom(int maxSize, int maxValue){
        Random random = new Random();
        int randSize = random.nextInt(maxSize);
        while (randSize == 0){
            randSize = random.nextInt(maxSize);
        }
        Integer[] data = new Integer[randSize];
        for (int i = 0 ; i < data.length ; i++){
            data[i] = random.nextInt(maxValue);
        }
        return data;
    }

    public static SegmentMethodEnum[] generateRandomMethod(int maxSize){
        SegmentMethodEnum[] methods = new SegmentMethodEnum[maxSize];
        Random random = new Random();
        SegmentMethodEnum[] values = SegmentMethodEnum.values();
        for (int i = 0 ; i < maxSize; i++){
            methods[i] = values[random.nextInt(values.length)];
        }
        return methods;
    }

    public static boolean isEqual(SegmentTree<Integer> segmentTree, CorrectSegmentTree<Integer> correctSegment, SegmentMethodEnum[] methodEnums, Integer[] data){
            Random random = new Random();
        try {
            for (SegmentMethodEnum cmd : methodEnums) {
                switch (cmd) {
                    case getSize:
                        int size1 = correctSegment.getSize();
                        int size2 = segmentTree.getSize();
                        if (size1 != size2) {
                            System.out.println(cmd + " result1= " + size1 + " result2= " + size2);
                            printErrorInfo(segmentTree, correctSegment, methodEnums, data);
                            return false;
                        }
                        break;
                    case get:
                        int nextInt = random.nextInt(correctSegment.getSize());
                        Integer get1 = correctSegment.get(nextInt);
                        Integer get2 = segmentTree.get(nextInt);
                        if (!get1.equals(get2)) {
                            System.out.println(cmd + " result1= " + get1 + " result2= " + get2);
                            printErrorInfo(segmentTree, correctSegment, methodEnums, data);
                            return false;
                        }
                        break;
                    case set:
                        nextInt = random.nextInt(correctSegment.getSize());
                        Integer nextValue = correctSegment.get(random.nextInt(correctSegment.getSize()));
                        correctSegment.set(nextInt, nextValue);
                        segmentTree.set(nextInt, nextValue);
                        break;
                    case batchUpdate:
                        int left1 = random.nextInt(correctSegment.getSize());
                        int right1 = random.nextInt(correctSegment.getSize());
                        Integer val = random.nextInt(10);
                        if(left1 < right1) {
                            correctSegment.batchUpdate(left1, right1,val);
                            segmentTree.batchUpdate(left1, right1,val);
                        }
                        else {
                            correctSegment.batchUpdate(right1, left1, val);
                            segmentTree.batchUpdate(right1, left1, val);
                        }
                        break;
                    case query:
                        int left = random.nextInt(correctSegment.getSize());
                        int right = random.nextInt(correctSegment.getSize());

                        Integer query1 = left < right ? correctSegment.query(left, right) : correctSegment.query(right, left);
                        Integer query2 = left < right ? segmentTree.query(left, right) : segmentTree.query(right, left);
                        if (!query1.equals(query2)) {
                            System.out.println(cmd + " result1= " + query1 + " result2= " + query2);
                            System.out.println("left="+left+" right="+right);
                            printErrorInfo(segmentTree, correctSegment, methodEnums, data);
                            return false;
                        }
                        break;
                }
            }

            Integer query1 = correctSegment.query(0, correctSegment.getSize() - 1);
            Integer query2 = segmentTree.query(0, correctSegment.getSize() - 1);
            if (!query1.equals(query2)) {
                System.out.println(" result1= " + query1 + " result2= " + query2);
                printErrorInfo(segmentTree, correctSegment, methodEnums, data);
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            printErrorInfo(segmentTree, correctSegment, methodEnums, data);
            return false;
        }
        return true;
    }

//    private static void printErrorInfo(SegmentTree2<Integer> segmentTree,CorrectSegmentTree<Integer> correctSegment, SegmentMethodEnum[] methodEnums,Integer[] data) {
    private static void printErrorInfo(SegmentTree<Integer> segmentTree, CorrectSegmentTree<Integer> correctSegment, SegmentMethodEnum[] methodEnums, Integer[] data) {
            StringBuilder sbr = new StringBuilder("-------------->comparator error!!!<-------------------\n");
        sbr.append("cmdList [");
        for (int i = 0 ; i < methodEnums.length ; i++){
            sbr.append(methodEnums[i].name());
            if(i != methodEnums.length-1){
                sbr.append(",");
            }
        }
        sbr.append("]\n");

        sbr.append("data [");
        for (int i = 0 ; i < data.length ; i++){
            sbr.append(data[i]);
            if(i != data.length-1){
                sbr.append(",");
            }
        }
        sbr.append("]\n");

        sbr.append("correct ").append(correctSegment.toString());
        sbr.append("\n");

        sbr.append("self :").append(segmentTree.toString());
        sbr.append("\n");
        sbr.append("-------------->comparator error!!!<-------------------\n");
        System.out.println(sbr.toString());
    }



}
