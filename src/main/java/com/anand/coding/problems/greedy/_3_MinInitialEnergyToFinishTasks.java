package com.anand.coding.problems.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Minimum initial amount of energy required to finish all the tasks.
 * task {0,1} = (energy required to complete,  energy required to begin)
 *
 * Input: tasks = [[1,2],[2,4],[4,8]]
 * Output: 8
 * Starting with 8 energy, finish the tasks in the following order:
 *     - 3rd task. Now energy = 8 - 4 = 4.
 *     - 2nd task. Now energy = 4 - 2 = 2.
 *     - 1st task. Now energy = 2 - 1 = 1.
 *
 */
public class _3_MinInitialEnergyToFinishTasks {

    public static int minimumEffort(int[][] tasks) {
        Arrays.sort(tasks, Comparator.comparingInt((int[]task) -> task[1]-task[0]).reversed());

        int minimumEnergy=0;
        int currentEnergy=0;
        for(int []task: tasks){
            if(currentEnergy<task[1]){
                minimumEnergy += task[1]-currentEnergy;
                currentEnergy = task[1];
            }
            currentEnergy-=task[0];
        }

        return minimumEnergy;
    }


    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        int [][]A = {{1,3},{2,4},{10,11},{10,12},{8,9}};

        System.out.println(minimumEffort(A));

    }
}
