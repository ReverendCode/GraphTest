package com.company;

import java.util.*;

/**
 * Created by Code on 8/5/14.
 */
public class Graph {// matrix is indexed to list
    private static final int MAX_VERTEX = 20;
    private static final int BIG_NUM = 999999;
    private int nVertices;
    private Vertex[] vertexList; //list of vertexes
    private int[][] connMatrix; //Connection matrix
    private Queue theQueue;
    private Stack theStack;
    private PriorityQ thePriorityQ;

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
        theStack = new Stack();
        thePriorityQ = new PriorityQ();
    }
    public void addVertex(char label) {
        vertexList[nVertices++] = new Vertex(label);
    }

    public void addDirectedEdge(int start, int end) {
        connMatrix[start][end] = 1;
    }

    public void addEdge(int start, int end) {
       addDirectedEdge(start,end);
        addDirectedEdge(end,start);
    }


    public String breadthSpanningTree(char label) {//labels must be unique for this to work predictably
        String output="";
        int startLoc=findLabel(label);
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
        return output;
    }

    public String depthSpanningTree (char label) {
        String output = "";
        int startLoc = findLabel(label);

        vertexList[startLoc].touched=true;
        theStack.push(startLoc);
        output+=vertexList[startLoc].datum;

        while (!theStack.isEmpty()) {
            int temp = getNeighbor(theStack.peek());
            if (temp==-1) theStack.pop();
            else {
                vertexList[temp].touched=true;
                output+=vertexList[temp].datum;
                theStack.push(temp);
            }
        }
        for (int i=0;i<nVertices;i++) {
            vertexList[i].touched=false;
        }
        return output;
    }

    public int[][] weightedSpanningTree() {//should generate a grid of costs with -1 to indicate no path

        int[][] sPath = new int[nVertices][nVertices];
        for (int i = 0;i<nVertices;i++) {
            for (int j = 0;j<nVertices;j++) {
                if (i==j)sPath[i][j]=-1;
                else {
                    sPath[i][j] = findShortestPath(i, j);
                }


            }
        }
        return sPath;
    }

    public void addWeightedEdge(int start, int end, int weight) {
        addDirectedWeightedEdge(start,end,weight);
        addDirectedWeightedEdge(end,start,weight);
    }
    public void addDirectedWeightedEdge(int start, int end, int weight) {
        connMatrix[start][end]=weight;
    }
    private int findLabel(char label){
        for (int i = 0;i<nVertices;i++) {
            if (vertexList[i].datum==label) return i;
        }
        return -1;
    }

    private int getNeighbor(int current) {

        for (int i = 0;i < nVertices;i++) {
            if (connMatrix[current][i]==1 && !vertexList[i].touched) {
                return i;
            }
        }
        return -1;
    }
    private int findShortestPath(int start, int end) {

        vertexList[start].path=true;
        int[] next = findCheapestNeighbor(start);
        int curTotal=0;
        int minTotal=BIG_NUM;

        if (next[0]==-1) return -1;//isolated node
        //if (next[0]==end) return next[1];//best case scenario

        while (next[0]!=-1) {
            curTotal+=next[1];
            vertexList[next[0]].path=true;
            if (next[0]==end) {
                if (curTotal<minTotal) minTotal=curTotal;
                curTotal=0;
                vertexList[next[0]].touched=true;
                next[0]=start;
                unPath();
            }
            if (findCheapestNeighbor(next[0])[0]==end) {

                vertexList[next[0]].touched=true;
                curTotal+=findCheapestNeighbor(next[0])[1];

                if (curTotal<minTotal) {
                    minTotal = curTotal;
                }
                next[0]=start;
                curTotal = 0;

            }


            if (findCheapestNeighbor(next[0])[0]==-1) {//if you are at the bottom of the tree
                vertexList[next[0]].touched=true;//mark it touched, and return to the top
                next[0]=start;
                curTotal=0;
                unPath();
            }
            next=findCheapestNeighbor(next[0]);
        }
        unTouch();
        unPath();
        if (minTotal==BIG_NUM) return -1;
        return minTotal;
    }

    private int[] findCheapestNeighbor(int current) {
        int[] output = new int[2];
        int value=BIG_NUM;
        int loc=-1;
        for (int i = 0;i<nVertices;i++) {
            if (connMatrix[current][i]!=0 && value>connMatrix[current][i] && !vertexList[i].touched && !vertexList[i].path){
                value=connMatrix[current][i];
                loc=i;
            }
        }
        //if (loc!=-1) vertexList[loc].touched=true;
        output[0]=loc;//if this is -1, it means there are no more neighbors
        output[1]=value;
        return output;
    }


    private void unTouch() {
        for (int i = 0; i < nVertices;i++) {
            vertexList[i].touched=false;
        }
    }
    private void unPath() {
        for (int i =0;i<nVertices;i++) {
            vertexList[i].path=false;
        }
    }


}
