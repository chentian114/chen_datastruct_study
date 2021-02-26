package com.chen.data.struct.list;

import java.util.Random;

/**
 * @desc
 * @Author Chentian
 * @date 2021/2/26
 */
public class ListAlgorithmComparator {


    public enum ListMethodEnum{
        add,addFirst,addLast,set,get,getFirst,getLast,contains,remove,removeFirst,removeLast,removeElement,size,isEmpty
    }

    public static java.util.LinkedList<Integer> comparator(){
        return new java.util.LinkedList<>();
    }

    public static int[] generateRandomData(int maxSize, int maxValue){
        Random random = new Random();
        int[] data = new int[maxSize];
        for (int i = 0 ; i < maxSize ; i++){
            data[i] = random.nextInt(maxValue);
        }
        return data;
    }

    public static ListMethodEnum[] generateRandomMethod(int maxSize){
        Random random = new Random();
        ListMethodEnum[] methodEnums = new ListMethodEnum[maxSize];
        ListMethodEnum[] values = ListMethodEnum.values();
        for (int i = 0 ; i < maxSize ; i++){
            methodEnums[i] = values[random.nextInt(values.length)];
        }
        return methodEnums;
    }


    public static boolean isEqual(List<Integer> list, java.util.LinkedList<Integer> correctList, ListMethodEnum[] methodEnums, int[] data){
        for (int i = 0 ; i < methodEnums.length ; i++){
            ListMethodEnum cmd = methodEnums[i];
            switch (cmd){
                case add:
                    break;
                case addFirst:
                    break;
                case addLast:
                    break;
                case set:
                    break;
                case get:
                    break;
                case getFirst:
                    break;
                case getLast:
                    break;
                case contains:
                    break;
                case remove:
                    break;
                case removeFirst:
                    break;
                case removeLast:
                    break;
                case removeElement:
                    break;
                case size:
                    break;
                case isEmpty:
                    break;
            }
        }

        return true;
    }

    private static void printErrorInfo(List<Integer> list, java.util.LinkedList<Integer> correctList, ListMethodEnum[] methodEnums, int[] data) {
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

        sbr.append("correctList ");
        for (int i = 0 ; i <correctList.size() ; i++){
            sbr.append(correctList.get(i));
            if(i != correctList.size()-1){
                sbr.append(",");
            }
        }
        sbr.append("\n");

        sbr.append("list :").append(list.toString());
        sbr.append("\n");
        sbr.append("-------------->comparator error!!!<-------------------\n");
        System.out.println(sbr.toString());
    }

}
