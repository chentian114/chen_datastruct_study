package com.chen.data.struct.stack;

/**
 * @desc
 * @Author Chentian
 * @date 2020/10/22
 */
public class DemoTest {
    public static void main(String[] args) {

        Stack<Integer> stack = new ArrayStack<>();
        testStack(stack);

        Stack<Integer> stack2 = new QueueToStack<>();
        testStack(stack2);
    }

    private static void testStack(Stack<Integer> stack) {
        for (int i = 0 ; i < 5 ; i++){
            stack.push(i);
            System.out.println(stack);
        }

        for (int i = 0 ; i < 3 ; i++){
            stack.pop();
            System.out.println(stack);
        }

        System.out.println(stack.peek()+ " , "+stack.getSize());
    }
}
