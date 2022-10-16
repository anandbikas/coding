package com.anand.coding.dsalgo.list;

import java.util.HashSet;
import java.util.Stack;
import java.util.PriorityQueue;
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
    private int size =0;

    /**
     *
     * @param data
     */
    public void addStart(T data){

        final Node<T> newNode = new Node<>(data);
        newNode.next = start;
        start = newNode;
        size++;
    }

    /**
     *
     * @param data
     * @return
     */
    public Node<T> add(T data){

        final Node<T> newNode = new Node<>(data);

        if(start==null){
            start=newNode;
            size++;
            return newNode;
        }

        Node<T> node;
        for(node=start; node.next!=null; node=node.next);
        node.next=newNode;
        size++;
        return newNode;
    }

    /**
     *
     * @param index
     * @param data
     * @return
     */
    public Node<T> add(int index, T data){
        final Node<T> newNode = new Node<>(data);
        if(index==1){
            newNode.next = start;
            start=newNode;
            size++;
            return newNode;
        }

        int i;
        Node<T> node;
        for(node=start, i=2; node!=null; node=node.next, i++){
            if(index==i){
                newNode.next = node.next;
                node.next = newNode;
                size++;
                return newNode;
            }
        }

        throw new IndexOutOfBoundsException();
    }

    /**
     *
     * @param data
     * @return
     */
    public Node<T> addSorted(T data){

        final Node<T> newNode = new Node<>(data);
        if(start==null || start.data.compareTo(data)>0){
            newNode.next = start;
            start=newNode;
            size++;
            return newNode;
        }

        Node<T> node;
        for(node=start; node.next!=null && node.next.data.compareTo(data)<0; node=node.next);
        newNode.next = node.next;
        node.next = newNode;
        size++;
        return newNode;
    }

    /**
     *
     * @return
     */
    public Node<T> removeFirst(){

        if(start==null){
            return null;
        }

        Node<T> deletedNode = start;
        start=deletedNode.next;
        size--;

        deletedNode.next = null;
        return deletedNode;
    }

    /**
     *
     * @return
     */
    public Node<T> removeLast(){

        if(start==null || start.next==null){
            Node<T> deletedNode = start;
            start = null;
            size--;
            return deletedNode;
        }

        Node<T> node;
        for(node=start; node.next.next!=null; node=node.next);

        Node<T> deletedNode = node.next;
        node.next = null;

        size--;
        return deletedNode;
    }

    /**
     *
     * @param data
     * @return
     */
    public Node<T> remove(T data){

        Node<T> header = new Node<>(null);
        header.next = start;

        for(Node<T> node=header; node.next!=null; node=node.next){
            if(node.next.data.equals(data)){
                Node<T> deletedNode=node.next;
                node.next = deletedNode.next;
                start = header.next;
                size--;

                deletedNode.next = null;
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
    public Node<T> removeIndex(int index){

        Node<T> header = new Node<>(null);
        header.next = start;

        int i=1;
        for(Node<T> node=header; node.next!=null; node=node.next, i++){
            if(index==i){
                Node<T> deletedNode=node.next;
                node.next = deletedNode.next;
                start = header.next;
                size--;

                deletedNode.next = null;
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
        for(node=start; node!=null && !node.data.equals(data); node=node.next);

        return node;
    }

    /**
     *
     * @param data
     * @return
     */
    public int indexOf(T data){

        Node<T> node;
        int i=1;
        for(node=start; node!=null && !node.data.equals(data); node=node.next, i++);

        return (node==null) ? -1 : i;
    }

    /**
     *
     * @param index
     * @return
     */
    public Node<T> node(final int index){

        Node<T> node;
        int i=1;
        for(node=start; node!=null && i<index; node=node.next, i++);

        return node;
    }

    /**
     *
     */
    public void display(){
        System.out.println("Linked list: ");
        for(Node<T> node=start; node!=null; node=node.next){
            System.out.print(node.data + ", ");
        }
        System.out.println();
    }

    /**
     *
     * @return
     */
    public int size(){
       return size;
    }

    /**
     *
     * @return
     */
    public Node<T> max(){

        if(start==null){
            return null;
        }

        Node<T> maxNode=start;
        for(Node<T> node=start.next; node!=null; node=node.next) {
            if (maxNode.compareTo(node) < 0) {
                maxNode = node;
            }
        }
        return maxNode;
    }

    /**
     *
     * @return
     */
    public Node<T> min(){

        if(start==null){
            return null;
        }
        Node<T> minNode=start;
        for(Node<T> node=start.next; node!=null; node=node.next){
            if(minNode.compareTo(node)>0){
                minNode = node;

            }
        }
        return minNode;
    }

    /**
     * Swap k and k+1 th nodes if present
     * @param k
     */
    public void swapAdjacent(final int k){
        //Assign a dummy Object so that start ==null check is not required.
        Node<T> header = new Node<>(null);
        header.next = start;

        if(start==null || start.next==null){
            return;
        }

        Node<T> prevOfA=header;
        Node<T> a=start;
        for(int i=1; a.next!=null; i++, prevOfA=a, a=a.next){
            if(k==i){
                Node<T> b = a.next;
                a.next = b.next;
                b.next = a;
                prevOfA.next = b;
                break;
            }
        }
        start = header.next;
    }

    /**
     * Sort in bubble fashion.
     *
     */
    public void bubbleSort() {
        //Assign a dummy Object so that start ==null check is not required.
        Node<T> header = new Node<>(null);
        header.next = start;

        for (int i=0; i < size; i++) {
            Node<T> prevOfA = header;
            Node<T> a = start;

            for(int j=0; j < size-1-i; j++){
                Node<T> b = a.next;

                if(a.compareTo(b)>0){
                    a.next = b.next;
                    b.next = a;
                    prevOfA.next = b;
                    prevOfA=b;
                } else {
                    prevOfA=a;
                    a=b;
                }
            }
        }
        start = header.next;
    }

    /**
     *
     * @param start1
     * @param start2
     * @return start of sorted list.
     */
    private Node<T> mergeAsSorted(Node<T> start1, Node<T> start2){

        //Assign a dummy Object so that start ==null check is not required.
        Node<T> header = new Node<>(null);

        Node<T> end = header;
        while(start1 != null && start2 !=null){
            if(start1.compareTo(start2) <=0){
                end = end.next = start1;
                start1= start1.next;
            } else {
                end = end.next = start2;
                start2= start2.next;
            }
        }

        for(;start1!=null;start1=start1.next){
            end = end.next = start1;
        }

        for(;start2!=null; start2=start2.next){
            end = end.next = start2;
        }

        end.next = null;

        return header.next;
    }

    /**
     *
     * @return
     */
    public void mergeSort() {
        this.start = mergeSort(start, size());
    }

    /**
     *
     */
    public Node<T> mergeSort(Node<T> start, int length){

        // List is empty or has only one element
        if(start == null || start.next==null){
            return start;
        }

        Node<T> list1 = start;

        int middle = length/2;

        Node<T> mid=start;
        for(int i=0; i<middle-1; mid=mid.next, i++);

        Node<T> list2 = mid.next;
        mid.next = null;

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
            Node<T> temp = node.next;
            node.next = start;
            start=node;
            node=temp;
        }
    }

    /**
     *
     */
    public void reverseRec() {
        if(start==null){
            return;
        }
        reverseRec(start).next = null;
    }

    /**
     *
     * @param node
     */
    private Node<T> reverseRec(Node<T> node){

        if(node.next==null){
            return start = node;
        }
        return reverseRec(node.next).next = node;
    }

    /**
     *
     * @return
     */
    public Node<T> getMiddleNode(){

        Node<T> slowPointer;
        Node<T> fastPointer;

        for(slowPointer=fastPointer=start; fastPointer!=null && fastPointer.next!=null; ){
            slowPointer=slowPointer.next;
            fastPointer=fastPointer.next.next;
        }

        return slowPointer;
    }

    /**
     *
     * @return
     */
    public boolean isPalindrome(){
        if(start==null || start.next==null){
            return true;
        }

        boolean isPalindrome = true;
        Node<T> prevOfSlowPointer = null;
        Node<T> slowPointer;
        Node<T> fastPointer;

        for(slowPointer=fastPointer=start; fastPointer!=null && fastPointer.next!=null; ){
            prevOfSlowPointer = slowPointer;
            slowPointer=slowPointer.next;
            fastPointer=fastPointer.next.next;
        }

        Node<T> middleNode=slowPointer;
        Node<T> prevOfMiddleNode=prevOfSlowPointer;

        prevOfMiddleNode.next = null;

        LinkedList<T> list1 = new LinkedList<>();
        list1.start = middleNode;
        list1.reverse();

        Node<T> node1;
        Node<T> node2;
        for(node1=this.start, node2=list1.start; node1!=null; node1=node1.next, node2=node2.next){
            if(!node1.equals(node2)) {
                isPalindrome =  false;
                break;
            }
        }
        list1.reverse();
        prevOfMiddleNode.next = middleNode;
        return isPalindrome;
    }

    /**
     *
     * @return
     */
    public boolean isPalindromeUsingStack(){

        Stack<Node<T>> stack = new Stack<>();

        for(Node<T> node=start; node!=null; node=node.next){
            stack.push(node);
        }

        for(Node<T> node=start; node!=null; node=node.next){
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
        LinkedList<T> list = new LinkedList<>();
        list.start = start;
        return isPalindromeRec(list, start);
    }

    /**
     *
     * @return
     */
    private boolean isPalindromeRec(LinkedList<T> list, Node<T> rightNode){
        if(rightNode==null){
            return true;
        }

        boolean isPalindrome = isPalindromeRec(list, rightNode.next)
                                    && list.start.equals(rightNode);
        list.start = list.start.next;
        return isPalindrome;
    }

    /**
     * Floyd Algorithm
     *
     * @return
     */
    public boolean hasLoop(){

        Node<T> slowPointer;
        Node<T> fastPointer;

        for(slowPointer=fastPointer=start; fastPointer!=null && fastPointer.next!=null; ){
            slowPointer=slowPointer.next;
            fastPointer=fastPointer.next.next;

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

        for(slowPointer=fastPointer=start; fastPointer!=null && fastPointer.next!=null; ){
            slowPointer=slowPointer.next;
            fastPointer=fastPointer.next.next;

            if(slowPointer==fastPointer){
                for(slowPointer=start; slowPointer!=fastPointer;
                        slowPointer=slowPointer.next,fastPointer=fastPointer.next);
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
        for(slowPointer=fastPointer=start; fastPointer!=null && fastPointer.next!=null; loopLength++){
            slowPointer=slowPointer.next;
            fastPointer=fastPointer.next.next;

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
        for(node=startOfLoop; node.next!=startOfLoop; node=node.next);
        node.next = null;
    }

    /**
     *
     * @param k
     */
    public Node<T> kthNodeFromEnd(final int k){

        Node<T> node = start;
        for(int i=1; node!=null && i<k; node=node.next, i++);

        if(node!=null){
            Node<T> kthNodeFromEnd = start;
            for(; node.next!=null; node=node.next, kthNodeFromEnd=kthNodeFromEnd.next);

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
     */

    /**
     *
     * @param s1
     * @param s2
     * @return
     */
    public Node<T> intersectingNode(Node<T> s1, Node<T> s2) {
        HashSet<Node<T>> visited = new HashSet<>();

        while(s1!=null){
            visited.add(s1);
            s1 = s1.next;
        }

        for(; s2!=null; s2=s2.next){
            if(visited.contains(s2)){
                return s2;
            }
        }

        return null;
    }

    /**
     * Add 1 to the number represented by the list.
     *
     * Approach...
     *
     * 1. Reverse list and add 1 and then reverse again
     * 2. Recursive approach
     * 3. Trick: Find rightmost digit not 9
     *      a. Not found, add new node with value 1 and make all others 0.
     *      b. Else, add 1 to it and make all to its right 0 if any.
     */
    public void addOne(){

        Node<Integer> none9Node=null;
        for(Node<Integer> node = (Node<Integer>)start; node!=null; node=node.next){
            if(!node.data.equals(9)){
                none9Node = node;
            }
        }

        if(none9Node==null) {
            none9Node = new Node<>(0);
            none9Node.next = (Node<Integer>) start;
            start = (Node<T>) none9Node;
            size++;
        }

        none9Node.data += 1;
        for(Node<Integer> node=none9Node.next; node!=null; node=node.next){
            node.data = 0;
        }
    }

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
    private class LinkedListIterator implements Iterator<T> {

        Node<T> node = start;
        public boolean hasNext() {
            return node!=null;
        }

        public T next() {
            T data = node.data;
            node = node.next;
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

        PriorityQueue<Node<T>> heap = new PriorityQueue<>(listOfLists.size());

        for(LinkedList<T> list: listOfLists){
            if(list.start!=null){
                heap.add(list.start);
            }
            list.start=null;
        }

        if(heap.isEmpty()){
            return;
        }

        start = heap.remove();
        if(start.next!=null) {
            heap.add(start.next);
        }

        Node<T> temp = start;
        while (!heap.isEmpty()){
            temp.next = heap.remove();
            temp = temp.next;

            if(temp.next!=null){
                heap.add(temp.next);
            }
        }
        temp.next = null;
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

        Arrays.stream(new int[]{4,10,15,24}).forEach(l1::add);
        Arrays.stream(new int[]{0,9,12,20}).forEach(l2::add);;
        Arrays.stream(new int[]{5,18,22,30}).forEach(l3::add);;

        list.add(l1);
        list.add(l2);
        list.add(l3);

        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.mergeKSortedLists(list);
        linkedList.display();

        //Test addOne to the number list, 939
        LinkedList<Integer> numberList = new LinkedList<>();
        numberList.addStart(9);
        numberList.addStart(3);
        numberList.addStart(9);
        numberList.display();
        numberList.addOne();
        numberList.display();
    }
}
