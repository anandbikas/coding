package com.anand.coding.design;

/**
 * Text Editor with a cursor with following operations: add, delete, cursorLeft, cursorRight
 *
 * void addText(string text):    Appends text to where the cursor is.
 * int deleteText(int k):        Deletes k characters to the left of the cursor. Returns the number of characters actually deleted.
 * string cursorLeft(int k):     Moves the cursor to the left k times. Return max 10 characters to the left of the cursor.
 * string cursorRight(int k):    Moves the cursor to the right k times. Return max 10 characters to the left of the cursor.
 *
 * leetcode.com/problems/design-a-text-editor
 */
public class _02_TextEditor {

    private final Node header, tailer;
    private Node cursor;

    public _02_TextEditor() {
        this.header = new Node('\0');
        this.tailer = new Node('\0');
        header.next = tailer;
        tailer.prev = header;
        this.cursor = header;
    }

    public void addText(String text) {
        for(char c: text.toCharArray()) {
            Node node = new Node(c);
            node.next=cursor.next;
            node.prev = cursor;
            cursor.next.prev = node;
            cursor = cursor.next = node;
        }
    }

    public int deleteText(int k) {
        int i;
        Node right = cursor.next;
        for(i=0; i<k && cursor!=header; i++, cursor = cursor.prev);
        cursor.next = right;
        right.prev = cursor;
        return i;
    }

    public String cursorLeft(int k) {
        for(int i=0; i<k && cursor!=header; i++, cursor=cursor.prev);
        return getLeftTenSubstring();
    }

    public String cursorRight(int k) {
        for(int i=0; i<k && cursor.next!=tailer; i++, cursor=cursor.next);
        return getLeftTenSubstring();
    }

    private String getLeftTenSubstring(){
        StringBuilder sb = new StringBuilder();
        int count=10;
        for(Node node=cursor; count>0 && node!=header; count--, node=node.prev){
            sb.append(node.c);
        }
        return sb.reverse().toString();
    }

    public static class Node {
        public char c;
        public Node prev, next;

        public Node(char c) {
            this.c = c;
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String []args) {
        _02_TextEditor te = new _02_TextEditor();

        te.addText("mycoding");
        System.out.println(te.deleteText(4));
        te.addText("practice");
        System.out.println(te.cursorRight(3));
        System.out.println(te.cursorLeft(8));
        System.out.println(te.deleteText(10));
        System.out.println(te.cursorLeft(2));
        System.out.println(te.cursorRight(6));
    }
}