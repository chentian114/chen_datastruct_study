package com.chen.data.struct.stack.retry;


import com.chen.data.struct.stack.Stack;
import com.chen.data.struct.stack.StackAlgorithmComparator;
import static com.chen.data.struct.stack.StackAlgorithmComparator.StackMethodEnum;

/**
 * @desc
 * @Author Chentian
 * @date 2020/10/22
 */
public class DemoTest {
    public static void main(String[] args) {

        Stack<Integer> stack = new ArrayStack<>();
        for (int i = 0 ; i < 5 ; i++){
            stack.push(i);
            System.out.println(stack);
        }

        for (int i = 0 ; i < 3 ; i++){
            stack.pop();
            System.out.println(stack);
        }

        System.out.println(stack.peek()+ " , "+stack.getSize());

        testByComparator1(500000,5,50,5);


    }

    private static void testByComparator1(int testTimes,int maxSize,int maxValue,int maxMethodSize){

        boolean result = false;
        for (int i = 0 ; i < testTimes; i++){

            Stack<Integer> stack = new ArrayStack<>();
            java.util.Stack<Integer> correctStack = StackAlgorithmComparator.comparator();

            int[] data = StackAlgorithmComparator.generateRandomData(maxSize,maxValue);
            StackMethodEnum[] methodEnums = StackAlgorithmComparator.generateRandomMethod(maxMethodSize);

            result = StackAlgorithmComparator.isEqual(stack, correctStack, methodEnums, data);
            if(!result){
                return;
            }
        }
        if(result){
            System.out.println("----------->comparator is ok!<---------------");
        }
    }
}
