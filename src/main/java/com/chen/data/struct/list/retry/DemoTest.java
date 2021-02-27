package com.chen.data.struct.list.retry;

import com.chen.data.struct.list.List;
import com.chen.data.struct.queue.Queue;
import com.chen.data.struct.stack.Stack;
import com.chen.data.struct.list.ListAlgorithmComparator;
import static com.chen.data.struct.list.ListAlgorithmComparator.ListMethodEnum;

/**
 * @author: Chentian
 * @date: Created in 2021/2/23 7:00
 * @desc
 */
public class DemoTest {
    public static void main(String[] args) {

        List<Integer> list = new LinkedList<>();
        testList(list);
        testByComparator1(500000,5,50,5);


        List<Integer> list2 = new DummyHeadList<>();
        testList(list2);
        testByComparator2(500000,5,50,5);

        Stack<Integer> stack = new LinkedListStack<>();
        testStack(stack);

        Queue<Integer> queue = new LinkedListQueue<>();
        checkQueue(queue);

    }
    private static void testList(List<Integer> list) {
        for (int i = 0 ; i < 10 ; i++){
            list.addLast(i);
            System.out.println(list);
        }

        list.addFirst(-1);
        System.out.println(list);

        list.add(1,-1);
        System.out.println(list);

        list.set(3,2);
        System.out.println(list);

        list.removeFirst();
        System.out.println(list);

        list.remove(1);
        System.out.println(list);

        list.removeLast();
        System.out.println(list);

        list.removeElement(-1);
        System.out.println(list);

        list.set(0,1);
        System.out.println(list);

        System.out.println(list.contains(8));
        System.out.println(list.contains(10));
        System.out.println(list.get(0));

        System.out.println("====================================");
    }

    private static void testStack(Stack<Integer> stack) {
        for (int i = 0 ; i < 5 ; i++){
            stack.push(i);
            System.out.println(stack);
        }

        for (int i = 0 ; i < 3 ; i++){
            stack.pop();
            System.out.println(stack);
        }

        System.out.println(stack.peek()+ " , "+stack.getSize());
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

            List<Integer> list = new LinkedList<>();
            java.util.LinkedList<Integer> correctList = ListAlgorithmComparator.comparator();

            int[] data = ListAlgorithmComparator.generateRandomData(maxSize,maxValue);
            ListMethodEnum[] methodEnums = ListAlgorithmComparator.generateRandomMethod(maxMethodSize);

            result = ListAlgorithmComparator.isEqual(list, correctList, methodEnums, data);
            if(!result){
                return;
            }
        }
        if(result){
            System.out.println("----------->comparator is ok!<---------------");
        }
    }


    private static void testByComparator2(int testTimes,int maxSize,int maxValue,int maxMethodSize){

        boolean result = false;
        for (int i = 0 ; i < testTimes; i++){

            List<Integer> list = new DummyHeadList<>();
            java.util.LinkedList<Integer> correctList = ListAlgorithmComparator.comparator();

            int[] data = ListAlgorithmComparator.generateRandomData(maxSize,maxValue);
            ListMethodEnum[] methodEnums = ListAlgorithmComparator.generateRandomMethod(maxMethodSize);

            result = ListAlgorithmComparator.isEqual(list, correctList, methodEnums, data);
            if(!result){
                return;
            }
        }
        if(result){
            System.out.println("----------->comparator is ok!<---------------");
        }
    }

}
