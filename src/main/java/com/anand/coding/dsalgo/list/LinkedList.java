package com.anand.coding.dsalgo.list;

import com.anand.coding.dsalgo.stack.ArrayStack;
import com.anand.coding.dsalgo.stack.Stack;

import java.util.Iterator;

/**
 * Generic Linked List implementation
 * @param <T>
 */
public class LinkedList<T extends Comparable<T>> implements Iterable<T>{

    private Node<T> start;

    /**
     *
     * @param data
     * @return
     */
    public Node<T> insertStart(T data){

        final Node<T> newNode = new Node<>(data);
        newNode.setNext(start);
        start = newNode;

        return newNode;
    }

    /**
     *
     * @param data
     * @return
     */
    public Node<T> insertEnd(T data){

        final Node<T> newNode = new Node<>(data);
        if(start==null){
            start=newNode;
            return newNode;
        }

        Node<T> node;
        for(node=start; node.getNext()!=null; node=node.getNext());
        node.setNext(newNode);

        return newNode;
    }

    /**
     *
     * @param index
     * @param data
     * @return
     */
    public Node<T> insertAtIndex(int index, T data){

        final Node<T> newNode = new Node<>(data);
        if(index==1){
            newNode.setNext(start);
            start=newNode;
            return newNode;
        }

        int i;
        Node<T> node;
        for(node=start, i=2; node!=null; node=node.getNext(), i++){
            if(index==i){
                newNode.setNext(node.getNext());
                node.setNext(newNode);
                return newNode;
            }
        }

        return null;
    }

    /**
     * insert in sorted fashion
     *
     * @param data
     * @return
     */
    public Node<T> insertSorted(T data){

        final Node<T> newNode = new Node<>(data);
        if(start==null || start.getData().compareTo(data)>0){
            start=newNode;
            newNode.setNext(start);
            return newNode;
        }

        Node<T> node;
        for(node=start; node.getNext()!=null && node.getNext().getData().compareTo(data)<0; node=node.getNext());
        newNode.setNext(node.getNext());
        node.setNext(newNode);

        return newNode;
    }

    /**
     *
     * @return
     */
    public Node<T> deleteStart(){

        if(start==null){
            return null;
        }

        Node<T> deletedNode = start;
        start=deletedNode.getNext();

        deletedNode.setNext(null);
        return deletedNode;
    }

    /**
     *
     * @return
     */
    public Node<T> deleteEnd(){

        if(start==null || start.getNext()==null){
            Node<T> deletedNode = start;
            start = null;
            return deletedNode;
        }

        Node<T> node;
        for(node=start; node.getNext().getNext()!=null; node=node.getNext());

        Node<T> deletedNode = node.getNext();
        node.setNext(null);
        return deletedNode;
    }

    /**
     *
     * @param data
     * @return
     */
    public Node<T> delete(T data){

        if(start==null){
            return null;
        }

        if(start.getData()==data){
            Node<T> deletedNode = start;
            start=deletedNode.getNext();

            deletedNode.setNext(null);
            return deletedNode;
        }

        for(Node<T> node=start; node.getNext()!=null; node=node.getNext()){
            if(node.getNext().getData()==data){
                Node<T> deletedNode=node.getNext();
                node.setNext(deletedNode.getNext());

                deletedNode.setNext(null);
                return deletedNode;
            }
        }

        return null;
    }

    /**
     *
     * @param index
     * @return
     */
    public Node<T> deleteAtIndex(final int index){

        if(start==null){
            return null;
        }

        if(index==1){
            Node<T> deletedNode = start;
            start=deletedNode.getNext();

            deletedNode.setNext(null);
            return deletedNode;
        }

        int i;
        Node<T> node;
        for(node=start, i=2; node.getNext()!=null; node=node.getNext(), i++){
            if(index==i){
                Node<T> deletedNode=node.getNext();
                node.setNext(deletedNode.getNext());

                deletedNode.setNext(null);
                return deletedNode;
            }
        }

        return null;
    }

    /**
     *
     * @param data
     * @return
     */
    public Node<T> search(T data){

        Node<T> node;
        for(node=start; node!=null && !node.getData().equals(data); node=node.getNext());

        return node;
    }

    /**
     *
     * @param data
     * @return
     */
    public int findIndex(T data){

        Node<T> node;
        int i=1;
        for(node=start; node!=null && !node.getData().equals(data); node=node.getNext(), i++);

        if(node==null){
            return -1;
        }
        return i;
    }

    /**
     *
     * @param index
     * @return
     */
    public Node<T> getElementAtIndex(final int index){

        Node<T> node;
        int i=1;
        for(node=start; node!=null && i<index; node=node.getNext(), i++);

        return node;
    }

    /**
     *
     */
    public void display(){
        System.out.println("Linked list: ");
        for(Node node=start; node!=null; node=node.getNext()){
            System.out.print(node.getData() + ", ");
        }
        System.out.println();
    }

    /**
     *
     * @return
     */
    public int length(){
        int count=0;
        for(Node node=start; node!=null; node=node.getNext()){
            count++;
        }
        return count;
    }

    /**
     *
     * @return
     */
    public Node<T> maxValueNode(){

        if(start==null){
            return null;
        }

        Node<T> maxValueNode=start;
        for(Node<T> node=start.getNext(); node!=null; node=node.getNext()) {
            if (maxValueNode.compareTo(node) < 0) {
                maxValueNode = node;
            }
        }
        return maxValueNode;
    }

    /**
     *
     * @return
     */
    public Node<T> minValueNode(){

        if(start==null){
            return null;
        }
        Node<T> minValueNode=start;
        for(Node<T> node=start.getNext(); node!=null; node=node.getNext()){
            if(minValueNode.compareTo(node)>0){
                minValueNode = node;

            }
        }
        return minValueNode;
    }

    /**
     * Swap k and k+1 th nodes if present
     * @param k
     */
    public void swapAdjacentNodes(final int k){

        if(start==null || start.getNext()==null){
            return;
        }

        Node<T> nodeSave=null;
        Node<T> node=start;
        for(int i=1; node.getNext()!=null; i++, nodeSave=node, node=node.getNext()){
            if(k==i){
                Node<T> temp = node.getNext();
                node.setNext(temp.getNext());
                temp.setNext(node);
                if(nodeSave==null){
                    start = temp;
                } else {
                    nodeSave.setNext(temp);
                }
            }
        }
    }

    /**
     * Sort in bubble fashion.
     *
     */
    public void bubbleSort() {

        final int length = length();
        for (int i=0; i < length; i++) {
            Node<T> nodeSave = null;
            Node<T> node = start;
            for(int j=0; j < length-1-i; j++){
                if(node.compareTo(node.getNext())>0){

                    Node<T> temp = node.getNext();
                    node.setNext(temp.getNext());
                    temp.setNext(node);
                    if(nodeSave==null){
                        start = temp;
                    } else {
                        nodeSave.setNext(temp);
                    }
                    nodeSave=temp;
                } else {
                    nodeSave=node;
                    node=node.getNext();
                }
            }
        }
    }

    /**
     *
     */
    public void reverse(){

        Node<T> node = start;
        start=null;

        while(node!=null ){
            Node<T> temp = node.getNext();
            node.setNext(start);
            start=node;
            node=temp;
        }
    }

    /**
     *
     */
    public void reverseRec() {
        Node<T> lastNode = reverseRec(start);

        if(lastNode!=null){
            lastNode.setNext(null);
        }
    }

    /**
     *
     * @param node
     */
    public Node<T> reverseRec(Node<T> node){

        if(node == null || node.getNext()==null){
            start = node;
            return node;
        }
        reverseRec(node.getNext()).setNext(node);

        return node;
    }

    /**
     *
     * @return
     */
    public Node<T> getMiddleNode(){

        Node slowPointer;
        Node fastPointer;

        for(slowPointer=fastPointer=start; fastPointer!=null && fastPointer.getNext()!=null; ){
            slowPointer=slowPointer.getNext();
            fastPointer=fastPointer.getNext().getNext();
        }

        return slowPointer;
    }

    /**
     *
     * @return
     */
    public boolean isPalindrome(){
        if(start==null || start.getNext()==null){
            return true;
        }

        boolean isPalindrome = true;
        Node<T> prevOfSlowPointer =null;
        Node<T> slowPointer;
        Node<T> fastPointer;

        for(slowPointer=fastPointer=start; fastPointer!=null && fastPointer.getNext()!=null; ){
            prevOfSlowPointer = slowPointer;
            slowPointer=slowPointer.getNext();
            fastPointer=fastPointer.getNext().getNext();
        }

        Node<T> middleNode=slowPointer;
        Node<T> prevOfMiddleNode=prevOfSlowPointer;

        prevOfMiddleNode.setNext(null);

        LinkedList<T> list1 = new LinkedList<>();
        list1.start = middleNode;
        list1.reverse();

        Node<T> node1;
        Node<T> node2;
        for(node1=this.start, node2=list1.start; node1!=null; node1=node1.getNext(), node2=node2.getNext()){
            if(!node1.equals(node2)) {
                isPalindrome =  false;
                break;
            }
        }
        list1.reverse();
        prevOfMiddleNode.setNext(middleNode);
        return isPalindrome;
    }

    /**
     *
     * @return
     */
    public boolean isPalindromeUsingStack(){

        Stack<Node<T>> stack = new ArrayStack<>();

        for(Node<T> node=start; node!=null; node=node.getNext()){
            stack.push(node);
        }

        for(Node<T> node=start; node!=null; node=node.getNext()){
            if(!stack.pop().equals(node)) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @return
     */
    public boolean isPalindromeRec() {
        LinkedList<T> listWithLeftNode = new LinkedList<>();
        listWithLeftNode.start = start;
        return isPalindromeRec(listWithLeftNode, start);
    }

    /**
     *
     * @return
     */
    private boolean isPalindromeRec(LinkedList<T> listWithLeftNode, Node<T> rightNode){
        if(rightNode==null){
            return true;
        }

        boolean isPalindrome = isPalindromeRec(listWithLeftNode, rightNode.getNext())
                                    && listWithLeftNode.start.equals(rightNode);
        listWithLeftNode.start = listWithLeftNode.start.getNext();
        return isPalindrome;
    }

    /**
     * Floyd Algorithm
     *
     * @return
     */
    public boolean hasLoop(){

        Node slowPointer;
        Node fastPointer;

        for(slowPointer=fastPointer=start; fastPointer!=null && fastPointer.getNext()!=null; ){
            slowPointer=slowPointer.getNext();
            fastPointer=fastPointer.getNext().getNext();

            if(slowPointer==fastPointer){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return
     */
    public Node<T> getStartNodeOfLoop(){

        Node<T> slowPointer;
        Node<T> fastPointer;

        for(slowPointer=fastPointer=start; fastPointer!=null && fastPointer.getNext()!=null; ){
            slowPointer=slowPointer.getNext();
            fastPointer=fastPointer.getNext().getNext();

            if(slowPointer==fastPointer){
                for(slowPointer=start; slowPointer!=fastPointer;
                        slowPointer=slowPointer.getNext(),fastPointer=fastPointer.getNext());
                return slowPointer;
            }
        }
        return null;
    }

    /**
     *
     * @return
     */
    public int getLoopLength(){

        Node<T> slowPointer;
        Node<T> fastPointer;

        int loopLength=0;
        for(slowPointer=fastPointer=start; fastPointer!=null && fastPointer.getNext()!=null; loopLength++){
            slowPointer=slowPointer.getNext();
            fastPointer=fastPointer.getNext().getNext();

            if(slowPointer==fastPointer){
               return loopLength+1;
            }
        }
        return 0;
    }

    /**
     *
     */
    public void fixLoop(){

        Node<T> startOfLoop = getStartNodeOfLoop();

        if(startOfLoop==null){
            return;
        }
        Node<T> node;
        for(node=startOfLoop; node.getNext()!=startOfLoop; node=node.getNext());
        node.setNext(null);
    }

    /**
     *
     * @param k
     */
    public Node<T> kthNodeFromEnd(final int k){

        Node<T> node = start;
        for(int i=1; node!=null && i<k; node=node.getNext(), i++);

        if(node!=null){
            Node<T> kthNodeFromEnd = start;
            for(; node.getNext()!=null; node=node.getNext(), kthNodeFromEnd=kthNodeFromEnd.getNext());

            return kthNodeFromEnd;
        }

        return null;
    }

    /**
     *
     */
    public void resetList(){
        start=null;
    }

    /* TODO:
        *
        * reversePair
        * reverseK
        * oddEven
        * isPalindrome
        * intersectingNode
     */

    /**
     *
     * @return
     */
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    /**
     *
     */
    @SuppressWarnings("unchecked")
    class LinkedListIterator implements Iterator<T> {

        Node node = start;
        public boolean hasNext() {
            return node!=null;
        }

        public T next() {
            T data = (T) node.getData();
            node = node.getNext();
            return data;
        }

        public void remove() {
            throw new UnsupportedOperationException("not supported");
        }
    }
}
