package com.chen.data.struct.heap;

import java.util.Scanner;

/**
 * @desc CD80-随时找到数据流的中位数
 * @Author Chentian
 * @date 2021/3/7
 * https://www.nowcoder.com/ta/programmer-code-interview-guide
 */
public class MedianFinder {

    private MaxHeap<Integer> maxHeap;
    private MinHeap<Integer> minHeap;
    private int size;

    public MedianFinder() {
        maxHeap = new MaxHeap<>();
        minHeap = new MinHeap<>();
        size = 0;
    }

    public void addNum(int num) {
        size++;
        if(maxHeap.isEmpty() && minHeap.isEmpty()){
            maxHeap.heapPush(num);
            return;
        }

        Integer maxPeek = maxHeap.peek();
        if(num < maxPeek){
            maxHeap.heapPush(num);
        }else {
            minHeap.heapPush(num);
        }

        int size1 = maxHeap.size();
        int size2 = minHeap.size();
        if(Math.abs(size1-size2) <= 1){
            return;
        }
        if(size1 > size2){
            minHeap.heapPush(maxHeap.heapPop());
        }else {
            maxHeap.heapPush(minHeap.heapPop());
        }
    }

    public double findMedian() {
        if(size == 0){
            return -1;
        }
        if(size%2 == 0){
            return (maxHeap.peek() + minHeap.peek())/2.0;
        }
        if(maxHeap.size() > minHeap.size()){
            return maxHeap.peek();
        }else {
            return minHeap.peek();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MedianFinder medianFinder = new MedianFinder();
        int num = scanner.nextInt();
        for (int i = 0 ; i < num ; i++){
            int cmd = scanner.nextInt();
            if(cmd == 1){
                medianFinder.addNum(scanner.nextInt());
            }else {
                double median = medianFinder.findMedian();
                if(median == -1.0){
                    System.out.println(-1);
                }else {
                    System.out.printf("%.1f\n",median);
                }
            }
        }
    }

}
