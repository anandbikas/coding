package com.anand.coding.problems.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * k closest points to the origin
 */
public class _06_KClosestPoints {

    private static class Point{
        int [] A;
        int dist;
        public Point(int []A) {
            this.A=A;
            this.dist = A[0]*A[0]+A[1]*A[1];
        }
    }


    public static int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Point> pq  = new PriorityQueue<>(Comparator.comparing(p->p.dist));

        for(int[]point : points){
            pq.add(new Point(point));
        }
        int [][] kClosestPoints = new int[k][];

        for(int i=0; i<k && !pq.isEmpty(); i++){
            kClosestPoints[i] = pq.remove().A;
        }

        return kClosestPoints;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args) {

        int [][] points = new int[][]{
                {1,3},
                {-2,2}
        };

        int [][]kClosestPoints = kClosest(points, 1);
        Arrays.stream(kClosestPoints).forEach( point -> System.out.println(Arrays.toString(point)));
    }
}
