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

        System.out.println("preOrder:"+bst.preOrder());
        System.out.println("preOrderNR:"+bst.preOrderNR());

        System.out.println("inOrder:"+bst.inOrder());
        System.out.println("inOrderNR:"+bst.inOrderNR());

        System.out.println("postOrder:"+bst.postOrder());
        System.out.println("postOrderNR:"+bst.postOrderNR());

        System.out.println("levelOrder:"+bst.levelOrder());

        System.out.println(bst.minimum());
        System.out.println(bst.maximum());

        System.out.println(bst);
        bst.removeMin();
        System.out.println(bst);
        bst.removeMax();
        System.out.println(bst);
        bst.remove(10);
        System.out.println(bst);
        bst.remove(5);
        System.out.println(bst);

        checkCorrectBST();

        String a = "a";
        String b = null;
        System.out.println(a.equals(b));
    }

    public static void checkCorrectBST(){

        int[] arr = {5,2,6,1,4,7,3};
        CorrectBST<Integer> bst = new CorrectBST<>();
        for (int i = 0 ; i < arr.length ; i++){
            bst.add(arr[i]);
        }
        System.out.println(bst);

        System.out.println(bst.preOrder());
        System.out.println("--------");
        System.out.println(bst.preOrderNR());
        System.out.println("--------");
        System.out.println(bst.inOrder());
        System.out.println("--------");
        System.out.println(bst.postOrder());
        System.out.println("--------");
        System.out.println(bst.levelOrder());


        testByComparator(500000,5,50,5);

    }


    private static void testByComparator(int testTimes,int maxSize,int maxValue,int maxMethodSize){

        boolean result = false;
        for (int i = 0 ; i < testTimes; i++){

            BSTree<Integer> bst = new BSTree<>();
            CorrectBST<Integer> correctBST = BSTAlgorithmComparator.comparator();

            int[] data = BSTAlgorithmComparator.generateRandomData(maxSize,maxValue);
            BSTAlgorithmComparator.BSTMethodEnum[] methodEnums = BSTAlgorithmComparator.generateRandomMethod(maxMethodSize);

            result = BSTAlgorithmComparator.isEqual(bst, correctBST, methodEnums, data);
            if(!result){
                return;
            }
        }
        if(result){
            System.out.println("----------->comparator is ok!<---------------");
        }
    }

}
