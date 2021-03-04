package com.chen.data.struct.bst;

import java.util.Random;

/**
 * @desc
 * @Author Chentian
 * @date 2021/3/4
 */
public class BSTAlgorithmComparator {
    public enum BSTMethodEnum{
        size,isEmpty,add,contains,preOrder,preOrderNR,inOrder,inOrderNR,postOrder,postOrderNR,levelOrder,minimum,maximum,removeMin,removeMax,remove
    }

    public static CorrectBST<Integer> comparator(){
        return new CorrectBST<>();
    }

    public static int[] generateRandomData(int maxSize, int maxValue){
        Random random = new Random();
        int[] data = new int[maxSize];
        for (int i = 0 ; i < maxSize ; i++){
            data[i] = random.nextInt(maxValue);
        }
        return data;
    }

    public static BSTMethodEnum[] generateRandomMethod(int maxSize){
        Random random = new Random();
        BSTMethodEnum[] methodEnums = new BSTMethodEnum[maxSize];
        BSTMethodEnum[] values = BSTMethodEnum.values();
        for (int i = 0 ; i < maxSize ; i++){
            methodEnums[i] = values[random.nextInt(values.length)];
        }
        return methodEnums;
    }

    public static boolean isEqual(BSTree<Integer> bst, CorrectBST<Integer> correctBST, BSTMethodEnum[] methodEnums, int[] data){
        Random random = new Random();
        for (int i = 0 ; i < methodEnums.length ; i++){
            BSTMethodEnum cmd = methodEnums[i];
            switch (cmd){
                case size:
                    int size1 = bst.size();
                    int size2 = correctBST.size();
                    if(size1 != size2){
                        System.out.println(cmd+" result1="+size1+" result2="+size2);
                        printErrorInfo(bst,correctBST,methodEnums,data);
                        return false;
                    }
                    break;
                case isEmpty:
                    boolean empty1 = bst.isEmpty();
                    boolean empty2 = correctBST.isEmpty();
                    if(empty1 != empty2){
                        System.out.println(cmd+" result1="+empty1+" result2="+empty2);
                        printErrorInfo(bst,correctBST,methodEnums,data);
                        return false;
                    }
                    break;
                case add:
                    for (int j = 0 ; j < data.length; j++){
                        bst.add(data[j]);
                        correctBST.add(data[j]);
                    }
                    break;
                case contains:
                    if(!correctBST.isEmpty()){
                        Integer minimum = correctBST.minimum();
                        boolean contains1 = correctBST.contains(minimum);
                        boolean contains2 = bst.contains(minimum);
                        if(contains1 != contains2){
                            System.out.println(cmd+" result1="+contains1+" result2="+contains2);
                            printErrorInfo(bst,correctBST,methodEnums,data);
                            return false;
                        }
                    }
                    break;
                case preOrder:
                    String preOrder1 = correctBST.preOrder();
                    String preOrder2 = bst.preOrder();
                    if (compareOrderResult(bst, correctBST, methodEnums, data, cmd, preOrder1, preOrder2)) return false;
                    break;
                case preOrderNR:
                    String preOrderNR1 = correctBST.preOrderNR();
                    String preOrderNR2 = bst.preOrderNR();
                    if (compareOrderResult(bst, correctBST, methodEnums, data, cmd, preOrderNR1, preOrderNR2))
                        return false;
                    break;
                case inOrder:
                    String inOrder1 = correctBST.inOrder();
                    String inOrder2 = bst.inOrder();
                    if (compareOrderResult(bst, correctBST, methodEnums, data, cmd, inOrder1, inOrder2))
                        return false;
                    break;
                case inOrderNR:
                    String inOrderNR1 = correctBST.inOrder();
                    String inOrderNR2 = bst.inOrderNR();
                    if (compareOrderResult(bst, correctBST, methodEnums, data, cmd, inOrderNR1, inOrderNR2))
                        return false;
                    break;
                case postOrder:
                    String postOrder1 = correctBST.postOrder();
                    String postOrder2 = bst.postOrder();
                    if (compareOrderResult(bst, correctBST, methodEnums, data, cmd, postOrder1, postOrder2))
                        return false;
                    break;
                case postOrderNR:
                    String postOrderNR1 = correctBST.postOrder();
                    String postOrderNR2 = bst.postOrderNR();
                    if (compareOrderResult(bst, correctBST, methodEnums, data, cmd, postOrderNR1, postOrderNR2))
                        return false;
                    break;
                case levelOrder:
                    String levelOrder1 = correctBST.levelOrder();
                    String levelOrder2 = bst.levelOrder();
                    if (compareOrderResult(bst, correctBST, methodEnums, data, cmd, levelOrder1, levelOrder2))
                        return false;
                    break;
                case minimum:
                    if(correctBST.isEmpty() && !bst.isEmpty()){
                        System.out.println(cmd+" result1 is null result2 is not null");
                        printErrorInfo(bst,correctBST,methodEnums,data);
                        return false;
                    }
                    if(!correctBST.isEmpty()) {
                        Integer minimum1 = correctBST.minimum();
                        Integer minimum2 = bst.minimum();
                        if (!minimum1.equals(minimum2)) {
                            System.out.println(cmd + " result1=" + minimum1 + " result2=" + minimum2);
                            printErrorInfo(bst, correctBST, methodEnums, data);
                            return false;
                        }
                    }
                    break;
                case maximum:
                    if(correctBST.isEmpty() && !bst.isEmpty()){
                        System.out.println(cmd+" result1 is null result2 is not null");
                        printErrorInfo(bst,correctBST,methodEnums,data);
                        return false;
                    }
                    if(!correctBST.isEmpty()) {
                        Integer maximum1 = correctBST.maximum();
                        Integer maximum2 = bst.maximum();
                        if (!maximum1.equals(maximum2)) {
                            System.out.println(cmd + " result1=" + maximum1 + " result2=" + maximum2);
                            printErrorInfo(bst, correctBST, methodEnums, data);
                            return false;
                        }
                    }
                    break;
                case removeMin:
                    if(!correctBST.isEmpty()){
                        bst.removeMin();
                        correctBST.removeMin();
                    }
                    break;
                case removeMax:
                    if(!correctBST.isEmpty()){
                        bst.removeMax();
                        correctBST.removeMax();
                    }
                    break;
                case remove:
                    int randNum = data[random.nextInt(data.length)];
                    bst.remove(randNum);
                    correctBST.remove(randNum);
                    break;
            }
        }

        return true;
    }

    private static boolean compareOrderResult(BSTree<Integer> bst, CorrectBST<Integer> correctBST, BSTMethodEnum[] methodEnums, int[] data, BSTMethodEnum cmd, String orderStr1, String orderStr2) {
        if(correctBST.isEmpty() && !bst.isEmpty()){
            System.out.println(cmd+" result1 is null result2 is not null");
            printErrorInfo(bst,correctBST,methodEnums,data);
            return false;
        }
        if (!correctBST.isEmpty() && !orderStr1.equals(orderStr2)) {
            System.out.println(cmd + " result1=" + orderStr1 + " result2=" + orderStr2);
            printErrorInfo(bst, correctBST, methodEnums, data);
            return true;
        }
        return false;
    }

    private static void printErrorInfo(BSTree<Integer> bst, CorrectBST<Integer> correctBST, BSTMethodEnum[] methodEnums, int[] data) {
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

        sbr.append("correctBST ").append(correctBST);
        sbr.append("\n");

        sbr.append("BSTree :").append(bst);
        sbr.append("\n");
        sbr.append("-------------->comparator error!!!<-------------------\n");
        System.out.println(sbr.toString());
    }
}
