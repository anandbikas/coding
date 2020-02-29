package com.anand.coding.dsalgo.graph.adjacencylist;

import com.anand.coding.dsalgo.graph.GraphType;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

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
        directedGraph.dfsDisplayPreOrder(5);
        directedGraph.dfsDisplayPreOrderRec(5);

        Assert.assertEquals(directedGraph.outDegree(5),3);
    }

    @Test
    private void testCyclic(){

        Assert.assertFalse(directedGraph.isCyclicDfsRec());

        directedGraph.addEdge(3,5);
        Assert.assertTrue(directedGraph.isCyclicDfsRec());

        directedGraph.dfsDisplayPreOrderRec(2);

        directedGraph.removeEdge(3,5);

        directedGraph.dfsDisplayPreOrderRec(2);
    }

    @Test
    private void testTopologicalSorting(){

        String[] topologicallySortedListBfs = {"node5", "node2", "node4", "node3", "node1", "node0"};
        Assert.assertEquals(directedGraph.topologicalSortingBfs().toArray(),topologicallySortedListBfs);

        String[] topologicallySortedListDfs = {"node5", "node4", "node2", "node3", "node1", "node0"};
        Assert.assertEquals(directedGraph.topologicalSortingDfsRec().toArray(),topologicallySortedListDfs);
    }

    @Test
    private void testPathDFSRec(){

        Assert.assertTrue(directedGraph.findAllPathsDFSRec(1,3).isEmpty());

        directedGraph.addEdge(5,3);
        String[] path5To3_1 =  {"node5", "node2", "node3"};
        String[] path5To3_2 =  {"node5", "node3"};

        List<List<String>> pathList5To3 = directedGraph.findAllPathsDFSRec(5, 3);

        Assert.assertEquals(pathList5To3.get(0).toArray(), path5To3_1);
        Assert.assertEquals(pathList5To3.get(1).toArray(), path5To3_2);
    }

    @Test
    private void testPathDFS(){

        Assert.assertTrue(directedGraph.findAllPathsDFS(1,3).isEmpty());

        directedGraph.addEdge(5,3);
        String[] path5To3_1 =  {"node5", "node2", "node3"};
        String[] path5To3_2 =  {"node5", "node3"};


        List<List<String>> pathList5To3 = directedGraph.findAllPathsDFS(5, 3);

        Assert.assertEquals(pathList5To3.get(0).toArray(), path5To3_1);
        Assert.assertEquals(pathList5To3.get(1).toArray(), path5To3_2);
    }
}

