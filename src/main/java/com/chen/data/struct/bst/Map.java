package com.chen.data.struct.bst;

public interface Map<K,V> {
    /** 添加元素 */
    void add(K k, V v);
    /** 删除元素 */
    V remove(K k);
    /** 是否包含元素 */
    boolean contains(K k);
    /** 根据键获取元素 */
    V get(K k);
    /** 根据键设置元素 */
    void set(K k, V v);
    /** 获取映射键值对数量 */
    int getSize();
    /** 是否为空 */
    boolean isEmpty();
}
