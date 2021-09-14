package com.anand.coding.problems.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Find minimum number of refueling required to reach destination.
 * Given target_distance, start_fuel_of_vehicle, [][]stations containing location and fuel.
 * The vehicle has unlimited fuel tank capacity and refuels all the fuel from a station.
 *
 * Example:
 * Input: target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
 * Output: 2 (Refuels at stations 10 and 60.)
 *
 */
public class _11_MinimumRefuelStops {


    public static int minRefuelStops(int target, int startFuel, int[][] stations) {

        PriorityQueue<Integer> fuels = new PriorityQueue<>(Comparator.comparing(Integer::intValue).reversed());

        int dist=startFuel;
        int fuelStationIndex=0;
        int refuels=0;

        while(dist<target){
            for(; fuelStationIndex<stations.length && stations[fuelStationIndex][0]<=dist; fuelStationIndex++){
                fuels.add(stations[fuelStationIndex][1]);
            }
            if(fuels.isEmpty()){
                break;
            }
            dist += fuels.remove();
            refuels++;
        }

        return dist>=target ? refuels : -1;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args) {

        int [][] fuelStations = {{10,60},{20,30},{30,30},{60,40}};
        System.out.println(minRefuelStops(100, 10, fuelStations));
    }
}
