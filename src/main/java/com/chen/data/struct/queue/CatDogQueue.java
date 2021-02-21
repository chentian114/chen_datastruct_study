package com.chen.data.struct.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author: Chentian
 * @date: Created in 2020/12/3 3:39
 * @desc 牛客网-在线编程-程序员代码面试指南-CD100-猫狗队列
 */
public class CatDogQueue {

    private static final String CAT = "cat";
    private static final String DOG = "dog";

    private class Pet{
        private String type;
        private Integer id;
        private Integer count;

        public Pet(String type, Integer id, Integer count) {
            this.type = type;
            this.id = id;
            this.count = count;
        }
    }

    private Queue<Pet> catQueue = new LinkedList<>();
    private Queue<Pet> dogQueue = new LinkedList<>();
    private Integer count = 0;

    public void add(String type,Integer id){
        if(CAT.equals(type)){
            catQueue.add(new Pet(type,id,count++));
        }
        else if(DOG.equals(type)){
            dogQueue.add(new Pet(type,id,count++));
        }
    }

    public Pet pollDog(){
        if(dogQueue.isEmpty()){
            throw new IllegalArgumentException("dog is empty!");
        }
        return dogQueue.poll();
    }

    public Pet pollCat(){
        if(catQueue.isEmpty()){
            throw new IllegalArgumentException("cat is empty!");
        }
        return catQueue.poll();
    }

    public Pet pollAll(){
        if(isEmpty()){
            throw new IllegalArgumentException("cat and dog is empty!");
        }
        if(catQueue.isEmpty()){
            return dogQueue.poll();
        }
        if(dogQueue.isEmpty()){
            return catQueue.poll();
        }

        return catQueue.peek().count < dogQueue.peek().count ? catQueue.poll() : dogQueue.poll();
    }

    public boolean isEmpty(){
        return dogQueue.isEmpty() && catQueue.isEmpty();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CatDogQueue catDogQueue = new CatDogQueue();
        StringBuilder result = new StringBuilder();
        int num = sc.nextInt();
        for (int i = 0 ; i < num ; i++){
            String cmd = sc.next();
            switch (cmd){
                case "add":
                    String type = sc.next();
                    Integer id = sc.nextInt();
                    catDogQueue.add(type,id);
                    break;
                case "pollAll":
                    while (!catDogQueue.isEmpty()) {
                        print(result,catDogQueue.pollAll());
                    }
                    break;
                case "pollDog":
                    while (!catDogQueue.dogQueue.isEmpty()) {
                        print(result, catDogQueue.pollDog());
                    }
                    break;
                case "pollCat":
                    while (!catDogQueue.catQueue.isEmpty()) {
                        print(result, catDogQueue.pollCat());
                    }
                    break;
                case  "isEmpty":
                    print(result, catDogQueue.isEmpty());
                    break;
                case "isDogEmpty":
                    print(result, catDogQueue.dogQueue.isEmpty());
                    break;
                case "isCatEmpty":
                    print(result,catDogQueue.catQueue.isEmpty());
                    break;
            }
        }
        System.out.print(result);
    }

    public static void print(StringBuilder result, Pet pet){
        result.append(pet.type+" "+pet.id+"\n");
    }

    public static void print(StringBuilder result, boolean isEmpty){
        result.append(isEmpty ? "yes\n" : "no\n");
    }
}
