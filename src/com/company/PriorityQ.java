package com.company;

/**
 * Created by Code on 7/30/14.
 */
public class PriorityQ {
    private Heap theHeap;
    private static final int DEF_SIZE=20;



    public PriorityQ() {
        theHeap = new Heap(DEF_SIZE);
    }
    public PriorityQ(int size) {
        theHeap = new Heap(size);
    }
    public void insert(int newNum) {
        theHeap.insertItem(newNum);
    }
    public int removeSmallest() {
        return theHeap.getSmallest();
    }
    public boolean isEmpty() {
        return theHeap.isEmpty();
    }

}
