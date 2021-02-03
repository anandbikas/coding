package com.anand.coding.dsalgo.graph.adjacencylist.usingHashMap;

import com.anand.coding.dsalgo.graph.GraphType;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class UndirectedGraphTest {

    private Graph<String> undirectedGraph;

    private Graph<String> undirectedWeightedGraph;


    @BeforeMethod
    private void setUp(){
        undirectedGraph = new Graph<>(GraphType.UNDIRECTED);

        /**
         *
         *        (0)               (1)
         *         |   .                .
         *         |     .               .
         *         |       (5) - - - - - (4)
         *         |          .
         *         |            .
         *        (3) - - - - - - (2)
         *
         */
        for(int i=0; i<=5; i++){
            undirectedGraph.insert("node" + i);
        }

        undirectedGraph.addEdge("node0","node3");
        undirectedGraph.addEdge("node0","node5");
        undirectedGraph.addEdge("node1","node4");
        undirectedGraph.addEdge("node2","node5");
        undirectedGraph.addEdge("node3","node2");
        undirectedGraph.addEdge("node5","node4");


        undirectedWeightedGraph = new Graph<>(GraphType.UNDIRECTED);

        /**
         *                  8            7
         *           (1) - - - - - (2) - - - - - (3)
         *      4  .  |             | \           |  .
         *       .    |            2|   \         |     .9
         *     .      |             |     \4      |        .
         *  (0)     11|            (8)      \     |14       (4)
         *     .      |          .  |         \   |        .
         *      8.    |      . 7    |6         \  |     .10
         *         .  |   .    .    |            \|  .
         *           (7) - - - - - (6) - - - - - (5)
         *                   1             2
         */
        for(int i=0; i<=8; i++){
            undirectedWeightedGraph.insert("node" + i);
        }

        undirectedWeightedGraph.addEdge("node0","node1",4);
        undirectedWeightedGraph.addEdge("node0","node7", 8);

        undirectedWeightedGraph.addEdge("node1","node2", 8);
        undirectedWeightedGraph.addEdge("node1","node7", 11);

        undirectedWeightedGraph.addEdge("node2","node3", 7);
        undirectedWeightedGraph.addEdge("node2","node8", 2);
        undirectedWeightedGraph.addEdge("node2","node5", 4);

        undirectedWeightedGraph.addEdge("node3","node5", 14);
        undirectedWeightedGraph.addEdge("node3","node4", 9);

        undirectedWeightedGraph.addEdge("node4","node5", 10);

        undirectedWeightedGraph.addEdge("node6","node5", 2);
        undirectedWeightedGraph.addEdge("node6","node7", 1);
        undirectedWeightedGraph.addEdge("node6","node8", 6);

        undirectedWeightedGraph.addEdge("node7","node8", 7);

    }

    @Test
    private void testBasics(){

        undirectedGraph.display();
        undirectedGraph.bfsDisplay("node5");
        undirectedGraph.dfsDisplayPreOrder("node5");
        undirectedGraph.dfsDisplayPreOrderRec("node5");

        undirectedGraph.removeEdge("node4","node5");
        undirectedGraph.bfsDisplay("node5");

        Assert.assertEquals(undirectedGraph.outDegree("node5"),2);
    }

    @Test
    private void testCyclic(){

        Assert.assertTrue(undirectedGraph.isCyclicUnionFind());

        undirectedGraph.removeEdge("node3","node2");

        undirectedGraph.dfsDisplayPreOrderRec("node2");

        Assert.assertFalse(undirectedGraph.isCyclicUnionFind());

    }

    @Test
    private void testKruskalMST(){
        undirectedGraph.display();
        System.out.println();
        undirectedGraph.kruskalsMinimumSpanningTree().display();
    }

    @Test
    private void testKruskalMSTWeighted(){

        undirectedWeightedGraph.displayWeighted();
        System.out.println();
        undirectedWeightedGraph.kruskalsMinimumSpanningTree().displayWeighted();
    }

    @Test
    private void testPrimsMSTWeighted(){

        undirectedWeightedGraph.displayWeighted();
        System.out.println();
        undirectedWeightedGraph.primsMinimumSpanningTree().displayWeighted();
    }

    @Test
    private void testDijkstraSPTWeighted(){

        undirectedWeightedGraph.displayWeighted();
        System.out.println();
        undirectedWeightedGraph.dijkstraShortestPathTree("node0").displayWeighted();
        undirectedWeightedGraph.dijkstraShortestPathTreeHeap("node0").displayWeighted();
    }

}
