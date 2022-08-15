package com.anand.coding.design;

/**
 * BrowserHistory : visit, back, forward
 *
 */
public class _02_BrowserHistory_Array {

    private final String[] store = new String[5001];
    private int cursor = -1;
    private int end=-1;

    public _02_BrowserHistory_Array(String homepage) {
        store[++cursor] = homepage;
        end = cursor;
    }

    public void visit(String url) {
        store[++cursor] = url;
        end = cursor;
    }

    public String back(int steps) {
        cursor = Math.max(cursor-steps,0);
        return store[cursor];
    }

    public String forward(int steps) {
        cursor = Math.min(cursor+steps, end);
        return store[cursor];
    }

    public static void main(String []args){
        _02_BrowserHistory_Array browserHistory = new _02_BrowserHistory_Array("google.com");

        browserHistory.visit("abc.com");
        browserHistory.visit("def.com");
        browserHistory.visit("ghi.com");
        browserHistory.visit("jkl.com");

        System.out.println(browserHistory.back(2));
        browserHistory.visit("amazon.com");

        System.out.println(browserHistory.back(20));
        System.out.println(browserHistory.forward(2));

        browserHistory.visit("microsoft.com");

    }
}