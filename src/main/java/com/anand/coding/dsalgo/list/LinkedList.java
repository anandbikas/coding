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
    private int length=0;

    /**
     *
     * @param data
     * @return
     */
    public Node<T> insertStart(T data){

        final Node<T> newNode = new Node<>(data);
        newNode.next = start;
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
        for(node=start; node.next!=null; node=node.next);
        node.next=newNode;

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
            newNode.next = start;
            start=newNode;
            length++;
            return newNode;
        }

        int i;
        Node<T> node;
        for(node=start, i=2; node!=null; node=node.next, i++){
            if(index==i){
                newNode.next = node.next;
                node.next = newNode;
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
        if(start==null || start.data.compareTo(data)>0){
            newNode.next = start;
            start=newNode;
            length++;
            return newNode;
        }

        Node<T> node;
        for(node=start; node.next!=null && node.next.data.compareTo(data)<0; node=node.next);
        newNode.next = node.next;
        node.next = newNode;
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
        start=deletedNode.next;
        length--;

        deletedNode.next = null;
        return deletedNode;
    }

    /**
     *
     * @return
     */
    public Node<T> deleteEnd(){

        if(start==null || start.next==null){
            Node<T> deletedNode = start;
            start = null;
            length--;
            return deletedNode;
        }

        Node<T> node;
        for(node=start; node.next.next!=null; node=node.next);

        Node<T> deletedNode = node.next;
        node.next = null;

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

        if(start.data.equals(data)){
            Node<T> deletedNode = start;
            start=deletedNode.next;
            length--;

            deletedNode.next = null;
            return deletedNode;
        }

        for(Node<T> node=start; node.next!=null; node=node.next){
            if(node.next.data.equals(data)){
                Node<T> deletedNode=node.next;
                node.next = deletedNode.next;
                length--;

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
    public Node<T> deleteAtIndex(final int index){

        if(start==null){
            return null;
        }

        if(index==1){
            Node<T> deletedNode = start;
            start=deletedNode.next;
            length--;

            deletedNode.next = null;
            return deletedNode;
        }

        int i;
        Node<T> node;
        for(node=start, i=2; node.next!=null; node=node.next, i++){
            if(index==i){
                Node<T> deletedNode=node.next;
                node.next = deletedNode.next;
                length--;

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
    public int findIndex(T data){

        Node<T> node;
        int i=1;
        for(node=start; node!=null && !node.data.equals(data); node=node.next, i++);

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
        for(node=start; node!=null && i<index; node=node.next, i++);

        return node;
    }

    /**
     *
     */
    public void display(){
        System.out.println("Linked list: ");
        for(Node node=start; node!=null; node=node.next){
            System.out.print(node.data + ", ");
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
        for(Node<T> node=start.next; node!=null; node=node.next) {
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
        for(Node<T> node=start.next; node!=null; node=node.next){
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

        if(start==null || start.next==null){
            return;
        }

        Node<T> prevOfA=null;
        Node<T> a=start;
        for(int i=1; a.next!=null; i++, prevOfA=a, a=a.next){
            if(k==i){
                Node<T> b = a.next;
                a.next = b.next;
                b.next = a;
                if(prevOfA==null){
                    start = b;
                } else {
                    prevOfA.next = b;
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
                Node<T> b = a.next;

                if(a.compareTo(b)>0){
                    a.next = b.next;
                    b.next = a;
                    if(prevOfA==null){
                        start = b;
                    } else {
                        prevOfA.next = b;
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
                end3.next = start1;
                end3 = start1;
                start1= start1.next;

            } else {
                end3.next = start2;
                end3 = start2;
                start2= start2.next;
            }
        }

        while(start1!=null){
            end3.next = start1;
            end3 = start1;
            start1= start1.next;
        }

        while(start2!=null){
            end3.next = start2;
            end3 = start2;
            start2= start2.next;
        }

        end3.next = null;

        return start3.next;
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
        if(start == null || start.next==null){
            return start;
        }

        Node list1 = start;
        Node end1=start;

        int middle = length/2;
        for(int i=0; i<middle-1; i++){
            end1=end1.next;
        }

        Node list2 = end1.next;
        end1.next = null;

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
        Node<T> lastNode = reverseRec(start);

        if(lastNode!=null){
            lastNode.next = null;
        }
    }

    /**
     *
     * @param node
     */
    public Node<T> reverseRec(Node<T> node){

        if(node == null || node.next==null){
            start = node;
            return node;
        }
        reverseRec(node.next).next = node;

        return node;
    }

    /**
     *
     * @return
     */
    public Node<T> getMiddleNode(){

        Node slowPointer;
        Node fastPointer;

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
        Node<T> prevOfSlowPointer =null;
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

        boolean isPalindrome = isPalindromeRec(listWithLeftNode, rightNode.next)
                                    && listWithLeftNode.start.equals(rightNode);
        listWithLeftNode.start = listWithLeftNode.start.next;
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
            T data = (T) node.data;
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
        if(start.next!=null) {
            binaryMinHeap.insert(start.next);
        }

        Node<T> temp = start;
        while (!binaryMinHeap.isEmpty()){
            temp.next = binaryMinHeap.extractMin();
            temp = temp.next;

            if(temp.next!=null){
                binaryMinHeap.insert(temp.next);
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
