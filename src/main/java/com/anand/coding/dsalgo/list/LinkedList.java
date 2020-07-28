package com.anand.coding.dsalgo.list;

import java.util.Stack;
import com.anand.coding.dsalgo.tree.arraybased.BinaryMinHeap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Generic Linked List implementation
 * @param <T>
 */
public class LinkedList<T extends Comparable<T>> implements Iterable<T>{

    private Node<T> start;
    int length=0;

    /**
     *
     * @param data
     * @return
     */
    public Node<T> insertStart(T data){

        final Node<T> newNode = new Node<>(data);
        newNode.setNext(start);
        start = newNode;

        length++;
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
            length++;
            return newNode;
        }

        Node<T> node;
        for(node=start; node.getNext()!=null; node=node.getNext());
        node.setNext(newNode);

        length++;
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
            length++;
            return newNode;
        }

        int i;
        Node<T> node;
        for(node=start, i=2; node!=null; node=node.getNext(), i++){
            if(index==i){
                newNode.setNext(node.getNext());
                node.setNext(newNode);
                length++;
                return newNode;
            }
        }

        throw new IndexOutOfBoundsException();
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
            newNode.setNext(start);
            start=newNode;
            length++;
            return newNode;
        }

        Node<T> node;
        for(node=start; node.getNext()!=null && node.getNext().getData().compareTo(data)<0; node=node.getNext());
        newNode.setNext(node.getNext());
        node.setNext(newNode);
        length++;

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
        length--;

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
            length--;
            return deletedNode;
        }

        Node<T> node;
        for(node=start; node.getNext().getNext()!=null; node=node.getNext());

        Node<T> deletedNode = node.getNext();
        node.setNext(null);

        length--;
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

        if(start.getData().equals(data)){
            Node<T> deletedNode = start;
            start=deletedNode.getNext();
            length--;

            deletedNode.setNext(null);
            return deletedNode;
        }

        for(Node<T> node=start; node.getNext()!=null; node=node.getNext()){
            if(node.getNext().getData().equals(data)){
                Node<T> deletedNode=node.getNext();
                node.setNext(deletedNode.getNext());
                length--;

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
            length--;

            deletedNode.setNext(null);
            return deletedNode;
        }

        int i;
        Node<T> node;
        for(node=start, i=2; node.getNext()!=null; node=node.getNext(), i++){
            if(index==i){
                Node<T> deletedNode=node.getNext();
                node.setNext(deletedNode.getNext());
                length--;

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
       return length;
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

        Node<T> prevOfA=null;
        Node<T> a=start;
        for(int i=1; a.getNext()!=null; i++, prevOfA=a, a=a.getNext()){
            if(k==i){
                Node<T> b = a.getNext();
                a.setNext(b.getNext());
                b.setNext(a);
                if(prevOfA==null){
                    start = b;
                } else {
                    prevOfA.setNext(b);
                }
            }
        }
    }

    /**
     * Sort in bubble fashion.
     *
     */
    public void bubbleSort() {

        for (int i=0; i < length; i++) {
            Node<T> prevOfA = null;
            Node<T> a = start;

            for(int j=0; j < length-1-i; j++){
                Node<T> b = a.getNext();

                if(a.compareTo(b)>0){
                    a.setNext(b.getNext());
                    b.setNext(a);
                    if(prevOfA==null){
                        start = b;
                    } else {
                        prevOfA.setNext(b);
                    }
                    prevOfA=b;
                } else {
                    prevOfA=a;
                    a=b;
                }
            }
        }
    }

    /**
     *
     * @param start1
     * @param start2
     * @return start of sorted list.
     */
    public Node mergeAsSorted(Node start1, Node start2){

        //Assign a dummy Object so that start ==null check is not required.
        Node start3 = new Node<>(null);
        Node end3 = start3;

        while(start1 != null && start2 !=null){
            if(start1.compareTo(start2) <=0){
                end3.setNext(start1);
                end3 = start1;
                start1= start1.getNext();

            } else {
                end3.setNext(start2);
                end3 = start2;
                start2= start2.getNext();
            }
        }

        while(start1!=null){
            end3.setNext(start1);
            end3 = start1;
            start1= start1.getNext();
        }

        while(start2!=null){
            end3.setNext(start2);
            end3 = start2;
            start2= start2.getNext();
        }

        end3.setNext(null);

        return start3.getNext();
    }

    /**
     *
     * @return
     */
    public void mergeSort() {
        this.start = mergeSort(start, length());
    }

    /**
     *
     */
    public Node mergeSort(Node start, int length){

        // List is empty or has only one element
        if(start == null || start.getNext()==null){
            return start;
        }

        Node list1 = start;
        Node end1=start;

        int middle = length/2;
        for(int i=0; i<middle-1; i++){
            end1=end1.getNext();
        }

        Node list2 = end1.getNext();
        end1.setNext(null);

        mergeSort(list1, middle);
        mergeSort(list2, length-middle);
        return mergeAsSorted(list1, list2);
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

        Stack<Node<T>> stack = new Stack<>();

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

    /**
     *
     * @return
     */
    public boolean isEmpty(){
        return(start==null);
    }


    /**
     * Merges k sorted lists into a new list.
     *
     * @param listOfLists
     * @return
     */
    public void mergeKSortedLists(List<LinkedList<T>> listOfLists){

        BinaryMinHeap<Node<T>> binaryMinHeap = new BinaryMinHeap<>(listOfLists.size());

        for(LinkedList<T> list: listOfLists){
            if(list.start !=null){
                binaryMinHeap.insert(list.start);
            }
            list.start=null;
        }

        if(binaryMinHeap.isEmpty()){
            return;
        }

        start = binaryMinHeap.extractMin();
        if(start.getNext()!=null) {
            binaryMinHeap.insert(start.getNext());
        }

        Node<T> temp = start;
        while (!binaryMinHeap.isEmpty()){
            temp.setNext(binaryMinHeap.extractMin());
            temp = temp.getNext();

            if(temp.getNext()!=null){
                binaryMinHeap.insert(temp.getNext());
            }
        }
        temp.setNext(null);
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        List<LinkedList<Integer>> list = new ArrayList<>();

        LinkedList<Integer> l1 = new LinkedList<>();
        LinkedList<Integer> l2 = new LinkedList<>();
        LinkedList<Integer> l3 = new LinkedList<>();

        Arrays.stream(new int[]{4,10,15,24}).forEach(l1::insertEnd);
        Arrays.stream(new int[]{0,9,12,20}).forEach(l2::insertEnd);;
        Arrays.stream(new int[]{5,18,22,30}).forEach(l3::insertEnd);;

        list.add(l1);
        list.add(l2);
        list.add(l3);

        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.mergeKSortedLists(list);
        linkedList.display();

    }
}
