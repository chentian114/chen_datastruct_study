package com.chen.data.struct.array;

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
    }
}
