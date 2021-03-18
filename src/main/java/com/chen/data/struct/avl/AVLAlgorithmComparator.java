package com.chen.data.struct.avl;

import java.util.Random;

/**
 * @desc
 * @Author Chentian
 * @date 2021/3/4
 */
public class AVLAlgorithmComparator {
    public enum AVLMethodEnum {
        size,isEmpty,add,contains,minimum,maximum,remove,levelOrder
    }

    public static CorrectAVLTree<Integer,Object> comparator(){
        return new CorrectAVLTree<>();
    }

    public static int[] generateRandomData(int maxSize, int maxValue){
        Random random = new Random();
        int[] data = new int[maxSize];
        for (int i = 0 ; i < maxSize ; i++){
            data[i] = random.nextInt(maxValue);
        }
        return data;
    }

    public static AVLMethodEnum[] generateRandomMethod(int maxSize){
        Random random = new Random();
        AVLMethodEnum[] methodEnums = new AVLMethodEnum[maxSize];
        AVLMethodEnum[] values = AVLMethodEnum.values();
        for (int i = 0 ; i < maxSize ; i++){
            methodEnums[i] = values[random.nextInt(values.length)];
        }
        return methodEnums;
    }

    public static boolean isEqual(AVLTree<Integer> avlTree, CorrectAVLTree<Integer,Object> correctAVL, AVLMethodEnum[] methodEnums, int[] data){
        Random random = new Random();
        for (int i = 0 ; i < methodEnums.length ; i++){
            AVLMethodEnum cmd = methodEnums[i];
            switch (cmd){
                case size:
                    int size1 = avlTree.size();
                    int size2 = correctAVL.getSize();
                    if(size1 != size2){
                        System.out.println(cmd+" result1="+size1+" result2="+size2);
                        printErrorInfo(avlTree,correctAVL,methodEnums,data);
                        return false;
                    }
                    break;
                case isEmpty:
                    boolean empty1 = avlTree.isEmpty();
                    boolean empty2 = correctAVL.isEmpty();
                    if(empty1 != empty2){
                        System.out.println(cmd+" result1="+empty1+" result2="+empty2);
                        printErrorInfo(avlTree,correctAVL,methodEnums,data);
                        return false;
                    }
                    break;
                case add:
                    for (int j = 0 ; j < data.length; j++){
                        avlTree.add(data[j]);
                        correctAVL.add(data[j],null);
                    }
                    break;
                case contains:
                    if(!correctAVL.isEmpty()){
                        Integer minimum = correctAVL.minimum();
                        boolean contains1 = correctAVL.contains(minimum);
                        boolean contains2 = avlTree.contains(minimum);
                        if(contains1 != contains2){
                            System.out.println(cmd+" result1="+contains1+" result2="+contains2);
                            printErrorInfo(avlTree,correctAVL,methodEnums,data);
                            return false;
                        }
                    }
                    break;
                case minimum:
                    if(correctAVL.isEmpty() && !avlTree.isEmpty()){
                        System.out.println(cmd+" result1 is null result2 is not null");
                        printErrorInfo(avlTree,correctAVL,methodEnums,data);
                        return false;
                    }
                    if(!correctAVL.isEmpty()) {
                        Integer minimum1 = correctAVL.minimum();
                        Integer minimum2 = avlTree.minimum();
                        if (!minimum1.equals(minimum2)) {
                            System.out.println(cmd + " result1=" + minimum1 + " result2=" + minimum2);
                            printErrorInfo(avlTree, correctAVL, methodEnums, data);
                            return false;
                        }
                    }
                    break;
                case maximum:
                    if(correctAVL.isEmpty() && !avlTree.isEmpty()){
                        System.out.println(cmd+" result1 is null result2 is not null");
                        printErrorInfo(avlTree,correctAVL,methodEnums,data);
                        return false;
                    }
                    if(!correctAVL.isEmpty()) {
                        Integer maximum1 = correctAVL.maximum();
                        Integer maximum2 = avlTree.maximum();
                        if (!maximum1.equals(maximum2)) {
                            System.out.println(cmd + " result1=" + maximum1 + " result2=" + maximum2);
                            printErrorInfo(avlTree, correctAVL, methodEnums, data);
                            return false;
                        }
                    }
                    break;
                case remove:
                    int randNum = data[random.nextInt(data.length)];
                    avlTree.remove(randNum);
                    correctAVL.remove(randNum);
                    break;
                case levelOrder:
                    String s1 = correctAVL.levelOrder();
                    String s2 = avlTree.levelOrder();
                    if(!s1.equals(s2)){
                        System.out.println(cmd + " result1=" + s1 + " result2=" + s2);
                        printErrorInfo(avlTree,correctAVL,methodEnums,data);
                        return false;
                    }
                    break;
            }
        }

        if(correctAVL.isEmpty() && !avlTree.isEmpty()){
            System.out.println(" result1 is null result2 is not null");
            printErrorInfo(avlTree,correctAVL,methodEnums,data);
            return false;
        }

        if(!correctAVL.isEmpty()){
            String levelOrder1 = correctAVL.levelOrder();
            String levelOrder2 = avlTree.levelOrder();
            if (compareOrderResult(avlTree, correctAVL, methodEnums, data, null, levelOrder1, levelOrder2))
                return false;
        }

        return true;
    }

    private static boolean compareOrderResult(AVLTree<Integer> avlTree, CorrectAVLTree<Integer,Object> correctAVL, AVLMethodEnum[] methodEnums, int[] data, AVLMethodEnum cmd, String orderStr1, String orderStr2) {
        if(correctAVL.isEmpty() && !avlTree.isEmpty()){
            System.out.println(cmd+" result1 is null result2 is not null");
            printErrorInfo(avlTree,correctAVL,methodEnums,data);
            return false;
        }
        if (!correctAVL.isEmpty() && !orderStr1.equals(orderStr2)) {
            System.out.println(cmd + " result1=" + orderStr1 + " result2=" + orderStr2);
            printErrorInfo(avlTree, correctAVL, methodEnums, data);
            return true;
        }
        return false;
    }

    private static void printErrorInfo(AVLTree<Integer> avlTree, CorrectAVLTree<Integer,Object> correctAVL, AVLMethodEnum[] methodEnums, int[] data) {
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

        sbr.append("correctAVL ").append(correctAVL.levelOrder());
        sbr.append("\n");

        sbr.append("AVLTree :").append(avlTree.levelOrder());
        sbr.append("\n");
        sbr.append("-------------->comparator error!!!<-------------------\n");
        System.out.println(sbr.toString());
    }
}
