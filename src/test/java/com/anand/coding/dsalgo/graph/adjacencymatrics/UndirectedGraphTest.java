package com.anand.coding.dsalgo.graph.adjacencymatrics;

import com.anand.coding.dsalgo.graph.GraphType;
import com.anand.coding.dsalgo.graph.adjacencymatrix.Graph;
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
        undirectedGraph.addEdge(1,4, 5);
        undirectedGraph.addEdge(2,5);
        undirectedGraph.addEdge(3,2);
        undirectedGraph.addEdge(5,4);
    }

    @Test
    private void testBasics(){

        undirectedGraph.display();
        undirectedGraph.bfsDisplay(5);
        undirectedGraph.dfsDisplay(5);
        undirectedGraph.dfsDisplayRec(5);

        Assert.assertEquals(undirectedGraph.inDegree(5),3);
        Assert.assertEquals(undirectedGraph.outDegree(5),3);

        undirectedGraph.removeEdge(4,5);
        undirectedGraph.bfsDisplay(5);
    }
}
