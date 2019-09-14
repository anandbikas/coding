package com.anand.coding.dsalgo.graph.adjacencylist.usingHashMap;

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

        directedGraph.addEdge("node5","node0");
        directedGraph.addEdge("node5","node2");
        directedGraph.addEdge("node5","node4");
        directedGraph.addEdge("node4","node1");
        directedGraph.addEdge("node3","node0");
        directedGraph.addEdge("node2","node3");
    }

    @Test
    private void testBasics() {

        directedGraph.display();
        directedGraph.bfsDisplay("node5");
        directedGraph.dfsDisplayPreOrder("node5");
        directedGraph.dfsDisplayPreOrderRec("node5");

        Assert.assertEquals(directedGraph.outDegree("node5"),3);

        directedGraph.displayWeighted();
        System.out.println(directedGraph);

    }

    @Test
    private void testCyclic(){

        Assert.assertFalse(directedGraph.isCyclicDfsRec());

        directedGraph.addEdge("node3","node5");
        Assert.assertTrue(directedGraph.isCyclicDfsRec());

        directedGraph.dfsDisplayPreOrderRec("node2");

        directedGraph.removeEdge("node3","node5");

        directedGraph.dfsDisplayPreOrderRec("node2");
    }

    @Test
    private void testTopologicalSorting(){

        String[] topologicallySortedListBfs = {"node5", "node2", "node4", "node3", "node1", "node0"};
        Assert.assertEquals(directedGraph.topologicalSortingBfs().toArray(),topologicallySortedListBfs);

        //String[] topologicallySortedListDfs = {"node5", "node4", "node2", "node3", "node1", "node0"};
        String[] topologicallySortedListDfs = {"node5", "node2", "node3", "node0", "node4", "node1"};

        Assert.assertEquals(directedGraph.topologicalSortingDfsRec().toArray(),topologicallySortedListDfs);
    }

    @Test
    private void testPathDFSRec(){

        Assert.assertTrue(directedGraph.findPathDFSRec("node1","node3").isEmpty());

        directedGraph.addEdge("node5","node3");
        String[] path5To3_1 =  {"node5", "node2", "node3"};
        String[] path5To3_2 =  {"node5", "node3"};

        List<String[]> pathList5To3 = directedGraph.findPathDFSRec("node5", "node3");

        Assert.assertEquals(pathList5To3.get(0), path5To3_1);
        Assert.assertEquals(pathList5To3.get(1), path5To3_2);
    }

    @Test
    private void testPathDFS(){

        Assert.assertTrue(directedGraph.findPathDFS("node1","node3").isEmpty());

        directedGraph.addEdge("node5","node3");
        String[] path5To3_1 =  {"node5", "node2", "node3"};
        String[] path5To3_2 =  {"node5", "node3"};


        List<String[]> pathList5To3 = directedGraph.findPathDFS("node5", "node3");

        Assert.assertEquals(pathList5To3.get(0), path5To3_1);
        Assert.assertEquals(pathList5To3.get(1), path5To3_2);
    }
}

