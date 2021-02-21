package com.chen.data.struct.stack;

/**
 * @author: Chentian
 * @date: Created in 2021/2/20 21:11
 * @desc
 */
public class Leetcode20ValidParentheses {

    public boolean isValid(String s) {
        if(s.length() < 1 || s.length()%2 != 0){
            return false;
        }

        Stack<Character> stack = new ArrayStack<>();
        for (int i = 0 ; i < s.length() ; i++){
            char c = s.charAt(i);
            if('[' == c || '{'==c || '('==c){
                stack.push(c);
            }else {
                if(stack.isEmpty()){
                    return false;
                }
                char c2 = stack.pop();
                if(']'==c && '['!=c2) {
                    return false;
                }
                if(')'==c && '('!=c2){
                    return false;
                }
                if('}'==c && '{'!=c2){
                    return false;
                }
            }
        }
        if(!stack.isEmpty()){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String str="()";
        System.out.println(new Leetcode20ValidParentheses().isValid(str));
    }
}
