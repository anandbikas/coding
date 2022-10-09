package com.anand.coding.design;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * Design a stack-like data structure to push elements to the stack and pop the most frequent element from the stack.
 * For tie, return the element closest to top.
 *
 */
public class _10_FreqStack {

    private final HashMap<Integer, Integer> freqMap = new HashMap<>();
    private final HashMap<Integer, Stack<Integer>> stackMap = new HashMap<>();

    private int maxfreq = 0;

    /**
     *
     * @param x
     */
    public void push(int x) {
        int f = freqMap.getOrDefault(x, 0) + 1;
        freqMap.put(x, f);
        if(maxfreq<f) maxfreq = f;
        if (!stackMap.containsKey(f)) stackMap.put(f, new Stack<>());

        stackMap.get(f).add(x);
    }

    /**
     *
     * @return
     */
    public int pop() {
        int x = stackMap.get(maxfreq).pop();
        freqMap.put(x, maxfreq-1);
        if (stackMap.get(maxfreq).size() == 0) maxfreq--;

        return x;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        _10_FreqStack freqStack = new _10_FreqStack();

        Arrays.asList(5,7,5,7,4,5).forEach(freqStack::push);

        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
    }
}