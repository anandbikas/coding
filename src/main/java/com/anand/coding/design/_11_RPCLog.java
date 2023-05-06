package com.anand.coding.design;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import static com.anand.coding.design._11_RPCLog.Type.*;

/**
 * You have a stream of rpc request logs coming in. Each log is of the form {id, timestamp, type(start/end)}.
 * Given a timeout T, figure out at the earliest possible time if a request has timed out.
 *
 * Eg :
 * id - time - type
 * 0 - 0 - Start
 * 1 - 1 - Start
 * 0 - 2 - End
 * 2 - 6 - Start
 * 1 - 7 - End
 * Timeout = 3
 * Ans : {1, 6} ( figured out id 1 had timed out at time 6 )
 *
 */
public class _11_RPCLog {

    private final LinkedHashMap<Integer, Log> map = new LinkedHashMap<>();
    private final int timeout;

    public _11_RPCLog(int timeout) {
        this.timeout = timeout;
    }

    /**
     *
     * @param logs
     */
    public void process(List<Log> logs){
        for(Log currentLog : logs){
            process(currentLog);
        }
    }

    /**
     *
     * @param currentLog
     */
    public void process(Log currentLog) {

        // Process currentLog
        if(currentLog.type==START) {
            map.put(currentLog.id, currentLog);

        } else { //type==END
            //Has not timed-out earlier and ended gracefully
            if(map.containsKey(currentLog.id) && currentLog.time <= map.get(currentLog.id).time+timeout) {
                map.remove(currentLog.id);
            }
        }

        // Find timeouts for running RPCs
        List<Integer> timeoutRpcIdList = new ArrayList<>();
        for(Log log : map.values()) {
            if(currentLog.time <= log.time+timeout){
                break;
            }
            timeoutRpcIdList.add(log.id);
            System.out.printf("(%d %d %s),  ", log.id, log.time, currentLog.time);
        }

        timeoutRpcIdList.forEach(map::remove);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        _11_RPCLog rpcLog = new _11_RPCLog(3);

        List<Log> logs = new ArrayList<Log>(){{
            add(new Log(0,0, START));
            add(new Log(1,1, START));
            add(new Log(0,2, END));
            add(new Log(2,6, START));
            add(new Log(1,7, END));
        }};

        rpcLog.process(logs); //Expires Log(1,1) at time 6
        rpcLog.process(new Log(3, 10, START)); //Expires Log(2,6) at time 10
        rpcLog.process(new Log(4, 20, START)); //Expires Log(2,6) at time 10

    }


    enum Type {START,END}
    public static class Log {
        int id; int time; Type type;
        public Log(int id, int time, Type type) {
            this.id = id; this.time = time; this.type = type;
        }
    }

}