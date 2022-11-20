package com.anand.coding.dsalgo.graph.adjacencylist;

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

        undirectedGraph.addEdge(0,3);
        undirectedGraph.addEdge(0,5);
        undirectedGraph.addEdge(1,4);
        undirectedGraph.addEdge(2,5);
        undirectedGraph.addEdge(3,2);
        undirectedGraph.addEdge(5,4);


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

        undirectedWeightedGraph.addEdge(0,1,4);
        undirectedWeightedGraph.addEdge(0,7, 8);

        undirectedWeightedGraph.addEdge(1,2, 8);
        undirectedWeightedGraph.addEdge(1,7, 11);

        undirectedWeightedGraph.addEdge(2,3, 7);
        undirectedWeightedGraph.addEdge(2,8, 2);
        undirectedWeightedGraph.addEdge(2,5, 4);

        undirectedWeightedGraph.addEdge(3,5, 14);
        undirectedWeightedGraph.addEdge(3,4, 9);

        undirectedWeightedGraph.addEdge(4,5, 10);

        undirectedWeightedGraph.addEdge(6,5, 2);
        undirectedWeightedGraph.addEdge(6,7, 1);
        undirectedWeightedGraph.addEdge(6,8, 6);

        undirectedWeightedGraph.addEdge(7,8, 7);

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

    @Test
    private void testCyclic(){

        Assert.assertTrue(undirectedGraph.isCyclicUnionFind());

        undirectedGraph.removeEdge(3,2);

        undirectedGraph.dfsDisplayPreOrderRec(2);

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

        undirectedWeightedGraph.display();
        System.out.println();
        undirectedWeightedGraph.kruskalsMinimumSpanningTree().display();
    }

    @Test
    private void testPrimsMSTWeighted(){

        undirectedWeightedGraph.display();
        System.out.println();
        undirectedWeightedGraph.primsMinimumSpanningTree().display();
    }

    @Test
    private void testDijkstraSPTWeighted(){

        undirectedWeightedGraph.display();
        System.out.println();
        undirectedWeightedGraph.dijkstraShortestPathTree(0).display();
        undirectedWeightedGraph.dijkstraShortestPathTreeHeap(0).display();
    }

    @Test
    private void testBellmanSPTWeighted(){

        undirectedWeightedGraph.display();
        System.out.println();
        undirectedWeightedGraph.bellmanShortestPathTree(0).display();
    }

}
