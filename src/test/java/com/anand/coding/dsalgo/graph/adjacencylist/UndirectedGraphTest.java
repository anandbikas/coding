package com.anand.coding.dsalgo.graph.adjacencylist;

import com.anand.coding.dsalgo.graph.GraphType;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class UndirectedGraphTest {

    private Graph<String> undirectedGraph;

    @BeforeMethod
    private void setUp(){
        undirectedGraph = new Graph<>(GraphType.UNDIRECTED);

        for(int i=0; i<=5; i++){
            undirectedGraph.insert("node" + i);
        }

        undirectedGraph.addEdge(0,3);
        undirectedGraph.addEdge(0,5);
        undirectedGraph.addEdge(1,4);
        undirectedGraph.addEdge(2,5);
        undirectedGraph.addEdge(3,2);
        undirectedGraph.addEdge(5,4);
    }

    @Test
    private void testBasics(){

        undirectedGraph.display();
        undirectedGraph.bfsDisplay(5);
        undirectedGraph.dfsDisplayPreOrder(5);
        undirectedGraph.dfsDisplayPreOrderRec(5);

        undirectedGraph.removeEdge(4,5);
        undirectedGraph.bfsDisplay(5);

        Assert.assertEquals(undirectedGraph.outDegree(5),2);
    }
}
