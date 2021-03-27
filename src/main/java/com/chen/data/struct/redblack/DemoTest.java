package com.chen.data.struct.redblack;

/**
 * @desc
 * @Author Chentian
 * @date 2021/3/25
 */
public class DemoTest {

    public static void main(String[] args) {

        RBTree<Integer> rbTree = new RBTree<>();
        int[] data = {42,37,12,18,6,11,5};

        for (int i = 0 ; i < data.length ; i++){
            rbTree.add(data[i]);
            System.out.println("add "+data[i]+" after:\n"+rbTree);
        }
        System.out.println("inOrder:"+rbTree.inOrder());

    }
}
