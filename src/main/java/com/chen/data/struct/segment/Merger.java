package com.chen.data.struct.segment;

/**
 * @author: Chentian
 * @date: Created in 2021/3/9 20:50
 * @desc 线段树合并接口
 */
public interface Merger<E> {
    E merge(E a, E b);
}
