package com.anand.coding.problems.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * N jobs with associated profit and deadline, each job performed in unit time.
 * Print sequence of jobs to be processed to get maximum profit.
 */
public class JobSequencing {

    private class Job implements Comparable<Job> {
        private String id;
        private Integer deadLine;
        private Integer profit;

        public Job(String id, Integer deadLine, Integer profit){
            this.id = id;
            this.deadLine = deadLine;
            this.profit = profit;
        }

        @Override
        public int compareTo(Job job) {
            return this.profit.compareTo(job.profit);
        }
    }

    private List<Job> jobList = new ArrayList<>();

    public void insert(String id, Integer deadLine, Integer profit){
        jobList.add(new Job(id, deadLine, profit));
    }

    /**
     *
     */
    public void printJobSequencing(){

        Collections.sort(jobList, Collections.reverseOrder());

        int n = jobList.size();
        Job [] timeSlots = new Job[n];

        jobList.forEach(job -> {

            for(int j= Math.min(n,job.deadLine)-1; j>=0; j--){
                if(timeSlots[j]==null){
                    timeSlots[j] = job;
                    break;
                }
            }
        });

        int totalProfit= 0;
        for(int i=0; i<n; i++){
            if(timeSlots[i]!=null){
                System.out.print(timeSlots[i].id + " -> ");
                totalProfit += timeSlots[i].profit;
            }
        }

        System.out.println("\nTotal Profit: " + totalProfit);

    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        JobSequencing jobSequencing = new JobSequencing();
        jobSequencing.insert("A", 5, 30);
        jobSequencing.insert("B", 1, 50);
        jobSequencing.insert("C", 3, 20);
        jobSequencing.insert("D", 2, 70);
        jobSequencing.insert("E", 1, 60);

        jobSequencing.printJobSequencing();


        JobSequencing jobSequencing1 = new JobSequencing();
        jobSequencing1.insert("A", 2, 100);
        jobSequencing1.insert("B", 1, 19);
        jobSequencing1.insert("C", 2, 27);
        jobSequencing1.insert("D", 1, 25);
        jobSequencing1.insert("E", 3, 15);

        jobSequencing1.printJobSequencing();

    }

}
