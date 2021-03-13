package com.chen.data.struct.segment;

/**
 * @author: Chentian
 * @date: Created in 2021/3/12 7:28
 * @desc
 */
public interface SegmentTree<E> {

    /**
     * 查询区间[queryL,queryR] 的值
     */
    E query(int queryL, int queryR);

    /**
     * 修改指定位置的值
     */
    void set(int index, E e);

    /**
     * 指量对指定区间的值做 merge 指定值
     */
    void batchUpdate(int updateL, int updateR, E num);

    int getSize();

    E get(int index);
}
