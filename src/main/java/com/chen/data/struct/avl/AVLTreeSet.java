package com.chen.data.struct.avl;

import com.chen.data.struct.bst.Set;

/**
 * @desc
 * @Author Chentian
 * @date 2021/3/18
 */
public class AVLTreeSet<E extends Comparable<E>> implements Set<E> {

    private AVLTreeMap<E,Object> map  = new AVLTreeMap<>();

    @Override
    public void add(E e) {
        map.add(e,null);
    }

    @Override
    public void remove(E e) {
        map.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return map.contains(e);
    }

    @Override
    public int getSize() {
        return map.getSize();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }
}
