package com.anand.coding.dsalgo.graph.adjacencylist;

import com.anand.coding.dsalgo.graph.GraphType;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class DirectedGraphTest {

    private Graph<String> directedGraph;

    @BeforeMethod
    private void setUp(){
        directedGraph = new Graph<>(GraphType.DIRECTED);

        /**
         *
         *        (0)               (1)
         *         ^  ^.               ^.
         *         |     .               .
         *         |       (5) - - - - > (4)
         *         |          .
         *         |            .>
         *        (3) < - - - - - (2)
         *
         */
        for(int i=0; i<=5; i++){
            directedGraph.insert("node" + i);
        }

        directedGraph.addEdge(5,0);
        directedGraph.addEdge(5,2);
        directedGraph.addEdge(5,4);
        directedGraph.addEdge(4,1);
        directedGraph.addEdge(3,0);
        directedGraph.addEdge(2,3);
    }

    @Test
    private void testBasics() {

        directedGraph.display();
        directedGraph.bfsDisplay(5);
        directedGraph.dfsDisplay(5);
        directedGraph.dfsDisplayRec(5);

        Assert.assertEquals(directedGraph.outDegree(5),3);
    }

    @Test
    private void testCyclic(){

        Assert.assertFalse(directedGraph.isCyclicDfsRec());

        directedGraph.addEdge(3,5);
        Assert.assertTrue(directedGraph.isCyclicDfsRec());

        directedGraph.dfsDisplayRec(2);

        directedGraph.removeEdge(3,5);

        directedGraph.dfsDisplayRec(2);
    }

    @Test
    private void testTopologicalSorting(){

        String[] topologicallySortedListBfs = {"node5", "node4", "node2", "node1", "node3", "node0"};
        Assert.assertEquals(directedGraph.topologicalSortingBfs(),topologicallySortedListBfs);

        String[] topologicallySortedListDfs = {"node5", "node4", "node2", "node3", "node1", "node0"};
        Assert.assertEquals(directedGraph.topologicalSortingDfsRec(),topologicallySortedListDfs);
    }
}

