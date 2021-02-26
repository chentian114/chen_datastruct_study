package com.chen.data.struct.queue.retry;


import com.chen.data.struct.queue.Queue;
import com.chen.data.struct.queue.QueueAlgorithmComparator;
import static com.chen.data.struct.queue.QueueAlgorithmComparator.QueueMethodEnum;

/**
 * @desc
 * @Author Chentian
 * @date 2020/10/22
 */
public class DemoTest {

    public static void main(String[] args) {

        Queue<Integer> queue = new ArrayQueue<>();
        checkQueue(queue);

        checkResize(queue);

        System.out.println("---------------");
        queue = new LoopQueue<>();
        checkQueue(queue);
        try {
            checkResize(queue);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        testByComparator1(500000,5,50,5);

    }

    private static void checkResize(Queue<Integer> queue) {
        for (int i = 0 ; i < 20 ; i++){
            queue.enqueue(i);
        }
        System.out.println(queue);
        for (int i = 0 ; i < 15; i++){
            queue.dequeue();
        }
        System.out.println(queue);
    }

    private static void checkQueue(Queue<Integer> queue) {
        for (int i = 0 ; i < 5 ; i++){
            queue.enqueue(i);
            System.out.println(queue);
        }

        for (int i = 0 ; i < 3 ; i++){
            Integer e = queue.dequeue();
            System.out.println("dequeue e="+e+" "+queue);
        }

        System.out.println(queue.getFront()+" "+queue.getSize());
    }

    private static void testByComparator1(int testTimes,int maxSize,int maxValue,int maxMethodSize){

        boolean result = false;
        for (int i = 0 ; i < testTimes; i++){

            Queue<Integer> queue = new LoopQueue<>();
            java.util.LinkedList<Integer> correctQueue = QueueAlgorithmComparator.comparator();

            int[] data = QueueAlgorithmComparator.generateRandomData(maxSize,maxValue);
            QueueMethodEnum[] methodEnums = QueueAlgorithmComparator.generateRandomMethod(maxMethodSize);

            result = QueueAlgorithmComparator.isEqual(queue, correctQueue, methodEnums, data);
            if(!result){
                return;
            }
        }
        if(result){
            System.out.println("----------->comparator is ok!<---------------");
        }
    }
}
