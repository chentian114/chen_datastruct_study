package com.chen.data.struct.bst;

/**
 * @desc 基于二叉搜索树实现集合
 * @Author Chentian
 * @date 2021/3/6
 */
public class BSTSet<E extends Comparable<E>> implements Set<E>{

    private BSTree<E> bstTree = new BSTree<>();

    @Override
    public void add(E e) {
        bstTree.add(e);
    }

    @Override
    public void remove(E e) {
        bstTree.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return bstTree.contains(e);
    }

    @Override
    public int getSize() {
        return bstTree.size();
    }

    @Override
    public boolean isEmpty() {
        return bstTree.isEmpty();
    }
}
