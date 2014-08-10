package com.company;

/**
 * Created by Code on 8/5/14.
 */
public class Vertex {
    public char datum;
    public boolean touched;
    public boolean path;

    public Vertex(char letter) {
        datum=letter;
        path=touched=false;

    }
}
