package com.chen.data.struct.heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @desc 最大堆对数器
 * @Author Chentian
 * @date 2021/3/7
 */
public class MaxHeapAlgorithmComparator {

    public enum HeapMethodEnum{
        size,isEmpty,heapPush,heapPop,peek
    }

    public static java.util.PriorityQueue<Integer> comparatorMax(){
        //返回最大堆
        return new java.util.PriorityQueue<>((Integer a, Integer b) -> b-a);
    }

    public static java.util.PriorityQueue<Integer> comparatorMin(){
        //返回最小堆
        return new java.util.PriorityQueue<>();
    }

    public static int[] generateRandomData(int maxSize, int maxValue){
        int[] data = new int[maxSize];
        Random random = new Random();
        for (int i = 0 ; i < maxSize; i++){
            data[i] = random.nextInt(maxValue);
        }
        return data;
    }

    public static HeapMethodEnum[] generateRandomMethods(int maxSize){
        HeapMethodEnum[] cmds = new HeapMethodEnum[maxSize];
        Random random = new Random();
        HeapMethodEnum[] values = HeapMethodEnum.values();
        for (int i = 0 ; i < maxSize ; i++){
            cmds[i] = values[random.nextInt(values.length)];
        }
        return cmds;
    }

    public static boolean isEqual(Heap<Integer> maxHeap, java.util.PriorityQueue<Integer> correctHeap,int[] data, HeapMethodEnum[] methods){
        for (HeapMethodEnum cmd : methods){
            switch (cmd){
                case size:
                    int size1 = correctHeap.size();
                    int size2 = maxHeap.size();
                    if(size1 != size2){
                        System.out.println(cmd+" result1="+size1+" result2="+size2);
                        printErrorInfo(maxHeap,correctHeap,data,methods);
                        return false;
                    }
                    break;
                case isEmpty:
                    boolean empty1 = correctHeap.isEmpty();
                    boolean empty2 = maxHeap.isEmpty();
                    if(empty1 != empty2){
                        System.out.println(cmd+" result1="+empty1+" result2="+empty2);
                        printErrorInfo(maxHeap,correctHeap,data,methods);
                        return false;
                    }
                    break;
                case heapPush:
                    for (int i = 0 ; i < data.length; i++){
                        correctHeap.add(data[i]);
                        maxHeap.heapPush(data[i]);
                    }
                    break;
                case heapPop:
                    if (checkIsEmpty(maxHeap, correctHeap, data, methods, cmd)){
                        return false;
                    }
                    if(correctHeap.isEmpty()){
                        continue;
                    }
                    for (int i = 0 ; i < correctHeap.size()*0.8; i++) {
                        Integer poll1 = correctHeap.poll();
                        Integer poll2 = maxHeap.heapPop();
                        if (!poll1.equals(poll2)) {
                            System.out.println(cmd + " result1=" + poll1 + " result2=" + poll2);
                            printErrorInfo(maxHeap, correctHeap, data, methods);
                            return false;
                        }
                    }
                    break;
                case peek:
                    if (checkIsEmpty(maxHeap, correctHeap, data, methods, cmd)){
                        return false;
                    }
                    if(correctHeap.isEmpty()){
                        continue;
                    }
                    Integer peek1 = correctHeap.peek();
                    Integer peek2 = maxHeap.peek();
                    if(!peek1.equals(peek2)){
                        System.out.println(cmd+" result1="+peek1+" result2="+peek2);
                        printErrorInfo(maxHeap,correctHeap,data,methods);
                        return false;
                    }
                    break;
            }
        }
        return true;
    }

    private static boolean checkIsEmpty(Heap<Integer> maxHeap, PriorityQueue<Integer> correctHeap, int[] data, HeapMethodEnum[] methods, HeapMethodEnum cmd) {
        if(correctHeap.isEmpty() && !maxHeap.isEmpty()){
            System.out.println(cmd+" result1= is empty result2 is not empty!");
            printErrorInfo(maxHeap,correctHeap,data,methods);
            return true;
        }
        return false;
    }


    private static void printErrorInfo(Heap<Integer> maxHeap, java.util.PriorityQueue<Integer> correctHeap,int[] data, HeapMethodEnum[] methodEnums) {
        StringBuilder sbr = new StringBuilder("-------------->comparatorMax error!!!<-------------------\n");
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

        sbr.append("correct ").append(Arrays.toString(correctHeap.toArray()));
        sbr.append("\n");

        sbr.append("self :").append(maxHeap.toString());
        sbr.append("\n");
        sbr.append("-------------->comparatorMax error!!!<-------------------\n");
        System.out.println(sbr.toString());
    }


}
