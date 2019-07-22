package com.anand.coding.dsalgo.graph.adjacencymatrics;

import com.anand.coding.dsalgo.graph.GraphType;
import com.anand.coding.dsalgo.graph.adjacencymatrix.Graph;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

@Test
public class DirectedGraphTest {

    private Graph<String> directedGraph;

    @BeforeMethod
    private void setUp(){
        directedGraph = new Graph<>(6, GraphType.DIRECTED);

        for(int i=0; i<=5; i++){
            directedGraph.insert("node" + i);
        }

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
        directedGraph.addEdge(5,0);
        directedGraph.addEdge(5,2);
        directedGraph.addEdge(5,4);
        directedGraph.addEdge(4,1);
        directedGraph.addEdge(3,0);
        directedGraph.addEdge(2,3);
    }

    @Test
    private void testBasics(){

        directedGraph.display();
        directedGraph.bfsDisplay(5);
        directedGraph.dfsDisplay(5);
        directedGraph.dfsDisplayRec(5);

        Assert.assertEquals(directedGraph.inDegree(5),0);
        Assert.assertEquals(directedGraph.outDegree(5),3);
    }


    @Test
    private void testPathMatrix(){
        directedGraph.addEdge(0,3);

        display(directedGraph.pathMatrix(1));

        display(directedGraph.pathMatrix(2));

        display(directedGraph.allPathMatrix(6));

        display(directedGraph.allPathMatrixWarshall());


    }

    /**
     *
     */
    public void display(int [][] A){

        System.out.println("Matrix");

        for(int i=0; i<A.length; i++){
            for(int j=0; j<A.length; j++){
                System.out.print(String.format("%4d", A[i][j]));
            }
            System.out.println();
        }
    }

}

