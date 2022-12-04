package com.anand.coding.design;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * Design a stack-like data structure to push elements to the stack and pop the most frequent element from the stack.
 * For tie, return the element closest to top.
 *
 * leetcode.com/problems/maximum-frequency-stack
 *
 */
public class _10_MaxFreqStack {

    private final HashMap<Integer, Integer> freqMap = new HashMap<>();
    private final HashMap<Integer, Stack<Integer>> stackMap = new HashMap<>();

    private int maxFreq = 0;

    public void push(int x) {
        int f = freqMap.getOrDefault(x, 0) + 1;
        freqMap.put(x, f);
        if(maxFreq<f) maxFreq = f;
        if (!stackMap.containsKey(f)) stackMap.put(f, new Stack<>());

        stackMap.get(f).add(x);
    }

    public int pop() {
        int x = stackMap.get(maxFreq).pop();
        freqMap.put(x, maxFreq-1);
        if (stackMap.get(maxFreq).size() == 0) maxFreq--;

        return x;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        _10_MaxFreqStack freqStack = new _10_MaxFreqStack();

        Arrays.asList(5,7,5,7,4,5).forEach(freqStack::push);

        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
    }
}