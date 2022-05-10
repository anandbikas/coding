package com.anand.coding.webbrowser;

/**
 * BrowserHistory : visit, back, forward
 *
 */
public class BrowserHistory {

    private DNode<String> cursor;

    public BrowserHistory(String homepage) {
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
        BrowserHistory browserHistory = new BrowserHistory("google.com");

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