package com.chen.data.struct.heap;

import com.chen.data.struct.queue.Queue;

/**
 * @desc 权重越大越先出队
 * @Author Chentian
 * @date 2021/3/7
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {
    private MaxHeap<E> maxHeap = new MaxHeap<>();

    @Override
    public void enqueue(E e) {
        maxHeap.heapPush(e);
    }

    @Override
    public E dequeue() {
        return maxHeap.heapPop();
    }

    @Override
    public E getFront() {
        return maxHeap.peek();
    }

    @Override
    public int getSize() {
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }
}
