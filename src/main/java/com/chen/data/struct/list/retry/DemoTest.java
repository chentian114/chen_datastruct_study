package com.chen.data.struct.list.retry;

import com.chen.data.struct.list.List;

/**
 * @author: Chentian
 * @date: Created in 2021/2/23 7:00
 * @desc
 */
public class DemoTest {
    public static void main(String[] args) {

        List<Integer> list = new LinkedList<>();
        testList(list);

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
}
