package com.anand.coding.design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class _06_OrderedStream {

    private String [] A;

    private int i=1;
    private int h;

    private List<String> arrayList;
    private List<String> emptyList = new ArrayList<>();

    public _06_OrderedStream(int n) {
        A = new String[n+1];
        arrayList = Arrays.asList(A);
    }

    public List<String> insert(int key, String value) {
        A[key] = value;

        if(i==key){
            for(h=i; i<A.length && A[i]!=null; i++);
            return arrayList.subList(h, i);
        }

        return emptyList;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        _06_OrderedStream orderedStream = new _06_OrderedStream(5);

        IntStream.range(1,6).parallel().forEach(x -> {
            System.out.println(orderedStream.insert(x, "value-"+x));
        });
    }
}
