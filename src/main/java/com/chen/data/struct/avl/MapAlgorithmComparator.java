package com.chen.data.struct.avl;

import com.chen.data.struct.bst.Map;

import java.util.HashMap;
import java.util.Random;

/**
 * @desc 映射对数器
 * @Author Chentian
 * @date 2021/3/18
 */
public class MapAlgorithmComparator {

    public enum MapMethodEnum{
        add,remove,contains,get,set,getSize,isEmpty
    }


    public static java.util.Map<Integer,Integer> comparator(){
        return new HashMap<>();
    }

    public static int[] generateRandomData(int maxSize, int maxValue){
        Random random = new Random();
        int[] data = new int[maxSize];
        for (int i = 0 ; i < maxSize ; i++){
            data[i] = random.nextInt(maxValue);
        }
        return data;
    }

    public static MapMethodEnum[] generateRandomMethod(int maxSize){
        Random random = new Random();
        MapMethodEnum[] methodEnums = new MapMethodEnum[maxSize];
        MapMethodEnum[] values = MapMethodEnum.values();
        for (int i = 0 ; i < maxSize ; i++){
            methodEnums[i] = values[random.nextInt(values.length)];
        }
        return methodEnums;
    }

    public static boolean isEqual(Map<Integer,Integer> map, java.util.Map<Integer,Integer> correctMap, MapMethodEnum[] methodEnums, int[] data) {
        Random random = new Random();
         for (MapMethodEnum cmd : methodEnums){
             switch (cmd){
                 case add:
                     for (int i = 0 ; i < data.length; i++){
                         int randIndex = random.nextInt(data.length);
                         correctMap.put(data[i],data[randIndex]);
                         map.add(data[i],data[randIndex]);
                     }
                     break;
                 case remove:
                     if(correctMap.isEmpty() && !map.isEmpty()){
                         System.out.println(cmd+" error! result1 is empty and result2 is not empty!");
                         printErrorInfo(map,correctMap,methodEnums,data);
                         return false;
                     }
                     if(!correctMap.isEmpty()) {
                         int randIndex = random.nextInt(data.length);
                         Integer remove1 = correctMap.remove(data[randIndex]);
                         Integer remove2 = map.remove(data[randIndex]);
                         if(remove1 == null && remove2 != null){
                             System.out.println(cmd+" error! result1 is empty and result2 is not empty!");
                             printErrorInfo(map,correctMap,methodEnums,data);
                             return false;
                         }
                         if(remove1!=null && !remove1.equals(remove2)){
                             System.out.println(cmd+" error! result1 is "+remove1+" and result2 is "+remove2);
                             printErrorInfo(map,correctMap,methodEnums,data);
                             return false;
                         }
                     }
                     break;
                 case contains:
                     int randIndex = random.nextInt(data.length);
                     boolean contains1 = correctMap.containsKey(data[randIndex]);
                     boolean contains2 = map.contains(data[randIndex]);
                     if(contains1 != contains2){
                         System.out.println(cmd+" error! result1 is "+contains1+" and result2 is "+contains2);
                         printErrorInfo(map,correctMap,methodEnums,data);
                         return false;
                     }
                     break;
                 case get:
                     randIndex = random.nextInt(data.length);
                     Integer get1 = correctMap.get(data[randIndex]);
                     Integer get2 = map.get(data[randIndex]);
                     if(get1 == null && get2 != null){
                         System.out.println(cmd+" error! result1 is empty and result2 is not empty!");
                         printErrorInfo(map,correctMap,methodEnums,data);
                         return false;
                     }
                     if(get1!=null &&!get1.equals(get2)){
                         System.out.println(cmd+" error! result1 is "+get1+" and result2 is "+get2);
                         printErrorInfo(map,correctMap,methodEnums,data);
                         return false;
                     }
                     break;
                 case set:
                     if(correctMap.isEmpty() && !map.isEmpty()){
                         System.out.println(cmd+" error! result1 is empty and result2 is not empty!");
                         printErrorInfo(map,correctMap,methodEnums,data);
                         return false;
                     }
                     randIndex = random.nextInt(data.length);
                     if(!correctMap.isEmpty() && correctMap.get(data[randIndex]) != null) {
                         correctMap.put(data[randIndex], data[randIndex]);
                         map.set(data[randIndex], data[randIndex]);
                     }
                     break;
                 case getSize:
                     int size1 = correctMap.size();
                     int size2 = map.getSize();
                     if(size1 != size2){
                         System.out.println(cmd+" error! result1 is "+size1+" and result2 is "+size2);
                         printErrorInfo(map,correctMap,methodEnums,data);
                         return false;
                     }
                     break;
                 case isEmpty:
                     boolean empty1 = correctMap.isEmpty();
                     boolean empty2 = map.isEmpty();
                     if(empty1 != empty2){
                         System.out.println(cmd+" error! result1 is "+empty1+" and result2 is "+empty2);
                         printErrorInfo(map,correctMap,methodEnums,data);
                         return false;
                     }
                     break;
             }
         }


        return true;
    }

   private static void printErrorInfo(Map<Integer,Integer> map, java.util.Map<Integer,Integer> correctMap, MapMethodEnum[] methodEnums, int[] data) {
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

        sbr.append("correctMap ").append(correctMap);
        sbr.append("\n");

        sbr.append("map :").append(map);
        sbr.append("\n");
        sbr.append("-------------->comparator error!!!<-------------------\n");
        System.out.println(sbr.toString());
    }
}
