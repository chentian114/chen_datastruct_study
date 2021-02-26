package com.chen.data.struct.queue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

/**
 * @desc
 * @Author Chentian
 * @date 2021/2/26
 */
public class QueueAlgorithmComparator {

    public enum QueueMethodEnum{
        enqueue,dequeue,getFront,getSize,isEmpty
    }

    public static java.util.LinkedList<Integer> comparator(){
        return new java.util.LinkedList<>();
    }

    public static int[] generateRandomData(int maxSize, int maxValue){
        Random random = new Random();
        int[] data = new int[maxSize];
        for (int i = 0 ; i < maxSize ; i++){
            data[i] = random.nextInt(maxValue);
        }
        return data;
    }

    public static QueueMethodEnum[] generateRandomMethod(int maxSize){
        Random random = new Random();
        QueueMethodEnum[] methodEnums = new QueueMethodEnum[maxSize];
        QueueMethodEnum[] values = QueueMethodEnum.values();
        for (int i = 0 ; i < maxSize ; i++){
            methodEnums[i] = values[random.nextInt(values.length)];
        }
        return methodEnums;
    }

    public static boolean isEqual(Queue<Integer> queue, java.util.LinkedList<Integer> correctQueue, QueueMethodEnum[] methodEnums, int[] data){
        for (int i = 0 ; i < methodEnums.length ; i++){
            QueueMethodEnum cmd = methodEnums[i];
            switch (cmd){
                case enqueue:
                    for (int j = 0 ; j < data.length ; j++) {
                        queue.enqueue(data[i]);
                        correctQueue.offer(data[i]);
                    }
                    break;
                case dequeue:
                    if(correctQueue.isEmpty()){
                        continue;
                    }
                    Integer dequeue1 = queue.dequeue();
                    Integer dequeue2 = correctQueue.remove();
                    if(!dequeue1.equals(dequeue2)){
                        System.out.println(cmd+" result1="+dequeue1+" result2="+dequeue2);
                        printErrorInfo(queue,correctQueue,methodEnums,data);
                        return false;
                    }
                    break;
                case getFront:
                    if(correctQueue.isEmpty()){
                        continue;
                    }
                    Integer front1 = queue.getFront();
                    Integer first2 = correctQueue.getFirst();
                    if(!front1.equals(first2)){
                        System.out.println(cmd+" result1="+front1+" result2="+first2);
                        printErrorInfo(queue,correctQueue,methodEnums,data);
                        return false;
                    }
                    break;
                case getSize:
                    int size1 = queue.getSize();
                    int size2 = correctQueue.size();
                    if(size1 != size2){
                        System.out.println(cmd+" result1="+size1+" result2="+size2);
                        printErrorInfo(queue,correctQueue,methodEnums,data);
                        return false;
                    }
                    break;
                case isEmpty:
                    boolean empty1 = queue.isEmpty();
                    boolean empty2 = correctQueue.isEmpty();
                    if(empty1 != empty2){
                        System.out.println(cmd+" result1="+empty1+" result2="+empty2);
                        printErrorInfo(queue,correctQueue,methodEnums,data);
                        return false;
                    }
                    break;
            }
        }

        for (int i = 0 ; i < correctQueue.size() ; i++){
            Integer dequeue1 = queue.dequeue();
            Integer dequeue2 = correctQueue.remove();
            if(!dequeue1.equals(dequeue2)){
                System.out.println("result1="+dequeue1+" result2="+dequeue2);
                printErrorInfo(queue,correctQueue,methodEnums,data);
                return false;
            }
        }

        return true;
    }

    private static void printErrorInfo(Queue<Integer> queue, LinkedList<Integer> correctQueue, QueueMethodEnum[] methodEnums, int[] data) {
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

        sbr.append("correctQueue ").append(Arrays.toString(correctQueue.toArray()));
        sbr.append("\n");

        sbr.append("queue :").append(queue.toString());
        sbr.append("\n");
        sbr.append("-------------->comparator error!!!<-------------------\n");
        System.out.println(sbr.toString());
    }

}
