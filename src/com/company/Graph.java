package com.company;

/**
 * Created by Code on 8/5/14.
 */
public class Graph {// matrix is indexed to list
    private static final int MAX_VERTEX = 20;
    private int nVertices;
    private Vertex[] vertexList; //list of vertexes
    private int[][] connMatrix; //Connection matrix
    private Queue theQueue;

    public Graph () {
        vertexList = new Vertex[MAX_VERTEX];
        connMatrix = new int[MAX_VERTEX][MAX_VERTEX];
        nVertices=0;
        for (int i =0;i<MAX_VERTEX;i++) {
            for (int j = 0;j<MAX_VERTEX;j++) {
                connMatrix[i][j] = 0; //initialize the grid
            }
        }
        theQueue = new Queue();
    }
    public void addVertex(char label) {
        vertexList[nVertices++] = new Vertex(label);
    }

    public void addEdge(int start, int end) {
        connMatrix[start][end] = 1;
        connMatrix[end][start] = 1;
    }


    public String breadthSpanningTree(char label) {//labels must be unique for this to work predictably
        String output="";
        int startLoc=0;
        for (int i = 0;i < nVertices;i++) {
            if (vertexList[i].datum == label) {
                 startLoc = i;
            }
        }
        vertexList[startLoc].touched=true;
        theQueue.insert(startLoc);
        int neighbor;

        while (!theQueue.isEmpty()) {
            int currentNum = theQueue.remove();
            while ( (neighbor=getNeighbor(currentNum)) != -1) {
                vertexList[neighbor].touched=true;
                output += vertexList[currentNum].datum;
                output+= vertexList[neighbor].datum + " ";
                theQueue.insert(neighbor);
            }
        }
        for (int i = 0; i < nVertices;i++) {//reset touch flags
            vertexList[i].touched=false;
        }
//this will return a strange looking output unless it is formatted somehow.

        return output;
    }

    public String depthSpanningTree (char label) {
        String output = "";



        return output;
    }
    private int getNeighbor(int current) {

        for (int i = 0;i < nVertices;i++) {
            if (connMatrix[current][i]==1 && !vertexList[i].touched) {
                return i;
            }
        }
        return -1;
    }

}
