package com.company;

/**
 * Created by Code on 8/5/14.
 */
public class Stack {
    private int[] theStack;
    private int nextItem = 0;
    private static final int DEF_SIZE = 20;
    public Stack() {
        theStack= new int[DEF_SIZE];
    }
    public void push(int item) {
        theStack[nextItem++]=item;
    }
    public int peek() {
        return theStack[nextItem-1];
    }
    public int pop() {
        return theStack[--nextItem];
    }
    public boolean isEmpty() {
        return (nextItem==0);
    }
}
