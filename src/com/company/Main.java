package com.company;

public class Main {

    public static void main(String[] args) {
        Graph theGraph = new Graph();
        theGraph.addVertex('A');//this is a recreation of the given code
        theGraph.addVertex('B');//changed to output in a rational way
        theGraph.addVertex('C');
        theGraph.addVertex('D');
        theGraph.addVertex('E');
        theGraph.addEdge(0, 1);
        theGraph.addEdge(1, 2);
        theGraph.addEdge(0,3);
        theGraph.addEdge(3,4);
        System.out.println("**Testing initial breadth first Minimum spanning tree**");
        System.out.println(theGraph.breadthSpanningTree('A'));

        Graph biggerGraph = new Graph();
        biggerGraph.addVertex('A');//0
        biggerGraph.addVertex('B');//1
        biggerGraph.addVertex('C');//2
        biggerGraph.addVertex('D');//3
        biggerGraph.addVertex('E');//4
        biggerGraph.addVertex('F');//5
        biggerGraph.addVertex('G');//6
        biggerGraph.addVertex('H');//7
        biggerGraph.addVertex('I');//8

        biggerGraph.addEdge(0,1);
        biggerGraph.addEdge(0,8);
        biggerGraph.addEdge(8,7);
        biggerGraph.addEdge(7,6);
        biggerGraph.addEdge(6,5);
        biggerGraph.addEdge(5,4);
        biggerGraph.addEdge(4,3);
        biggerGraph.addEdge(3,2);
        biggerGraph.addEdge(0,2);
        biggerGraph.addEdge(0,3);
        biggerGraph.addEdge(1,7);
        biggerGraph.addEdge(1,5);
System.out.println("**Testing larger minimum spanning tree**");
        System.out.println(biggerGraph.breadthSpanningTree('C'));

    }
}
