package com.anand.coding.design;

/**
 * BrowserHistory : visit, back, forward
 *
 * leetcode.com/problems/design-browser-history
 */
public class _02_BrowserHistory {

    private DNode<String> cursor;

    public _02_BrowserHistory(String homepage) {
        cursor = new DNode<>(homepage);
    }

    public void visit(String url) {
        DNode<String> newNode = new DNode<>(url);
        cursor.next = newNode;
        newNode.prev = cursor;
        cursor = newNode;
    }

    public String back(int steps) {
        for(; cursor.prev!=null && steps-->0; cursor = cursor.prev);
        return cursor.data;
    }

    public String forward(int steps) {
        for(; cursor.next!=null && steps-->0; cursor = cursor.next);
        return cursor.data;
    }

    public static class DNode<T> {

        public T data;
        public DNode<T> prev, next;

        public DNode(T data) {
            this.data = data;
        }
    }

    public static void main(String []args){
        _02_BrowserHistory browserHistory = new _02_BrowserHistory("google.com");

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