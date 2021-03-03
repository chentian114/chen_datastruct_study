package com.chen.data.struct.bst;

/**
 * @author: Chentian
 * @date: Created in 2021/3/4 6:58
 * @desc
 */
public class DemoTest {
    public static void main(String[] args) {

        int[] arr = {5,2,6,1,4,7,3};

        BSTree<Integer> bst = new BSTree<>();
        for (int i = 0 ; i < arr.length ; i++){
            bst.add(arr[i]);
        }
        System.out.println(bst);

        System.out.println(bst.contains(5));
        System.out.println(bst.contains(10));

        System.out.println(bst.preOrder());
        System.out.println(bst.preOrderNR());

        System.out.println(bst.inOrder());
        System.out.println(bst.inOrderNR());

        System.out.println(bst.postOrder());
        System.out.println(bst.postOrderNR());
    }

}
