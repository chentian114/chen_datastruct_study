package com.chen.data.struct.array.retry;

import com.chen.data.struct.array.ArrayAlgorithmComparator;

import java.util.List;

/**
 * @desc
 * @Author Chentian
 * @date 2020/10/13
 */
public class DemoTest {

    public static void main(String[] args) {

        DynamicArray<Integer> array = new DynamicArray<>(10);

        for (int i = 0 ; i < 10 ; i += 2){
            array.addLast(i);
        }

        System.out.println(array);

        int index = array.find(2);
        System.out.println("element: 2 index:"+index);

        for (int i = 1 ; i < 10 ; i += 2){
            array.add(i,i);
        }
        System.out.println(array);

        array.addFirst(-1);
        System.out.println(array);

        array.set(10,10);
        System.out.println(array);

        for (int i = 0 ; i < 5 ; i += 1){
            array.remove(i);
        }
        System.out.println(array);


        int testTimes = 500000;
        testComparator(5,10,6,testTimes);
    }

    /**
     * 使用对数器验证自定义实现的动态数组
     * @param maxDataSize   随机样本数组最大容量
     * @param maxValue      随机样本值域参照值
     * @param maxMethodSize 随机操作方法数组最大容量
     * @param testTimes     测试次数
     */
    public static void testComparator(int maxDataSize, int maxValue, int maxMethodSize, int testTimes){

        boolean result = false;
        for (int i = 0 ; i < testTimes ; i ++) {
            int[] data = ArrayAlgorithmComparator.generateRandomData(maxDataSize, maxValue);
            List<ArrayAlgorithmComparator.ArrayMethodEnum> cmdList = ArrayAlgorithmComparator.generateRandomMethod(maxMethodSize);

            java.util.List<Integer> correctList = ArrayAlgorithmComparator.comparator();
            DynamicArray array = new DynamicArray<>(10);

            result = ArrayAlgorithmComparator.isEqual(array,correctList, cmdList, data);
            if(!result){
                return;
            }
        }
        if(result) {
            System.out.println("-------------->comparator is ok!<-------------------");
        }
    }
}
