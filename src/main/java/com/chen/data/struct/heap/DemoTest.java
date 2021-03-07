package com.chen.data.struct.heap;


import java.util.Arrays;
import java.util.Random;

/**
 * @desc
 * @Author Chentian
 * @date 2021/3/7
 */
public class DemoTest {

    public static void main(String[] args) {
        testMaxHeap();
        System.out.println("--------------");
        testMaxHeapByComparator(100000,30,20,10);
        System.out.println("--------------");

        testMinHeap();
        System.out.println("--------------");
        testMinHeapByComparator(100000,30,20,10);
        System.out.println("--------------");

        testPriorityQueue();
    }

    private static void testPriorityQueue() {
        int[] data = new int[20];
        Random random = new Random();
        for (int i = 0 ; i < data.length ; i++){
            data[i] = random.nextInt(10000);
        }


        System.out.println("data enqueue is:"+ Arrays.toString(data));
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0 ; i < data.length ; i++){
            queue.enqueue(data[i]);
        }

        System.out.print("data dequeue is:[");
        for (int i = 0 ; i < data.length ; i++){
            System.out.print(queue.dequeue());
            if(i != data.length-1){
                System.out.print(", ");
            }
        }
        System.out.println("]");

    }

    private static void testMinHeap() {
        MinHeap<Integer> heap = new MinHeap<>();
        int[] data = {10,14,25,33,81,82,99};

        for (int i = 0 ; i < data.length ; i++){
            heap.heapPush(data[i]);
        }
        System.out.println(heap);

        System.out.println(heap.peek());
        heap.heapPop();
        System.out.println(heap);

        System.out.println(heap.replace(3));
        System.out.println(heap);
    }



    private static void testMinHeapByComparator(int testTimes, int maxSize, int maxValue, int maxMethodSize){

        boolean result = false;
        for (int i = 0 ; i < testTimes; i++){

            MinHeap<Integer> minHeap = new MinHeap<>();
            java.util.PriorityQueue<Integer> correctHeap = MaxHeapAlgorithmComparator.comparatorMin();

            int[] data = MaxHeapAlgorithmComparator.generateRandomData(maxSize,maxValue);
            MaxHeapAlgorithmComparator.HeapMethodEnum[] methodEnums = MaxHeapAlgorithmComparator.generateRandomMethods(maxMethodSize);

            result = MaxHeapAlgorithmComparator.isEqual(minHeap, correctHeap, data, methodEnums);
            if(!result){
                return;
            }
        }
        if(result){
            System.out.println("----------->comparatorMin is ok!<---------------");
        }
    }

    private static void testMaxHeap() {
        MaxHeap<Integer> heap = new MaxHeap<>();
        int[] data = {10,7,2,5,1};

        for (int i = 0 ; i < data.length ; i++){
            heap.heapPush(data[i]);
        }
        System.out.println(heap);

        System.out.println(heap.peek());
        heap.heapPop();
        System.out.println(heap);

        System.out.println(heap.replace(3));
        System.out.println(heap);

        Integer[] data2 = {1,2,5,7,10};
        MaxHeap<Integer> heap2 = new MaxHeap<>(data2);
        System.out.println(heap2);

    }

    private static void testMaxHeapByComparator(int testTimes, int maxSize, int maxValue, int maxMethodSize){

        boolean result = false;
        for (int i = 0 ; i < testTimes; i++){

            MaxHeap<Integer> maxHeap = new MaxHeap<>();
            java.util.PriorityQueue<Integer> correctHeap = MaxHeapAlgorithmComparator.comparatorMax();

            int[] data = MaxHeapAlgorithmComparator.generateRandomData(maxSize,maxValue);
            MaxHeapAlgorithmComparator.HeapMethodEnum[] methodEnums = MaxHeapAlgorithmComparator.generateRandomMethods(maxMethodSize);

            result = MaxHeapAlgorithmComparator.isEqual(maxHeap, correctHeap, data, methodEnums);
            if(!result){
                return;
            }
        }
        if(result){
            System.out.println("----------->comparatorMax is ok!<---------------");
        }
    }

}
