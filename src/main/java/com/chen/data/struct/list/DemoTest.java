package com.chen.data.struct.list;

import static com.chen.data.struct.list.ListAlgorithmComparator.ListMethodEnum;
/**
 * @desc
 * @Author Chentian
 * @date 2020/11/2
 */
public class DemoTest {

    public static void main(String[] args) {

        List<Integer> list = new LinkedList<>();
        testList(list);
        testByComparator1(500000,5,50,5);

        list = new DummyHeadList<>();
        testList(list);
        testByComparator2(500000,5,50,5);
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
