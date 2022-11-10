package com.anand.coding.dsalgo.list;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Generic Linked List implementation
 *  To reduce start==null check complexity, use a header node with header.next=start;
 * @param <T>
 */
public class LinkedList<T extends Comparable<T>> implements Iterable<T>{

    private final Node<T> header = new Node<>(null);
    private int size = 0;

    /**
     *
     * @param data
     * @return
     */
    public Node<T> addStart(T data){
        final Node<T> newNode = new Node<>(data);
        newNode.next = header.next;
        header.next = newNode;
        size++;
        return newNode;
    }

    /**
     *
     * @param data
     * @return
     */
    public Node<T> add(T data){
        final Node<T> newNode = new Node<>(data);

        Node<T> node;
        for(node=header; node.next!=null; node=node.next);
        node.next = newNode;
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

        Node<T> node=header;
        for(int i=1; node!=null && i<index; node=node.next, i++);

        if(node!=null){
            newNode.next = node.next;
            node.next = newNode;
            size++;
            return newNode;
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

        Node<T> node;
        for(node=header; node.next!=null && node.next.data.compareTo(newNode.data)<0; node=node.next);
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
        if(header.next==null){
            return null;
        }

        Node<T> deletedNode = header.next;
        header.next=deletedNode.next;
        size--;

        deletedNode.next = null;
        return deletedNode;
    }

    /**
     *
     * @return
     */
    public Node<T> removeLast(){
        if(header.next==null){
            return null;
        }

        Node<T> node;
        for(node=header; node.next.next!=null; node=node.next);
        Node<T> deletedNode = node.next;
        node.next = null;

        size--;

        deletedNode.next = null;
        return deletedNode;
    }

    /**
     *
     * @param data
     * @return
     */
    public Node<T> remove(T data){

        for(Node<T> node=header; node.next!=null; node=node.next){
            if(node.next.data.equals(data)){
                Node<T> deletedNode=node.next;
                node.next = deletedNode.next;
                deletedNode.next = null;
                size--;

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
    public void removeAll(T data){

        for(Node<T> node=header; node.next!=null;){
            if(node.next.data.equals(data)){
                node.next = node.next.next;
                size--;
            } else {
                node=node.next;
            }
        }
    }

    /**
     *
     * @param index
     * @return
     */
    public Node<T> removeIndex(int index){
        if(index<1) {
            return null;
        }

        Node<T> node=header;
        for(int i=1; node.next!=null && i<index; node=node.next, i++);

        if(node.next!=null){
            Node<T> deletedNode=node.next;
            node.next = deletedNode.next;
            deletedNode.next = null;
            size--;

            return deletedNode;
        }

        return null;
    }

    /**
     * Remove a node without knowing its previous.
     * Shift data a step left upto end.
     * @param someNode
     */
    public void remove(Node<T> someNode) {

        Node<T> node=new Node<>(null);
        node.next = someNode;
        while(node.next.next!=null){
            node=node.next;
            node.data = node.next.data;
        }
        node.next = null;
    }

    /**
     *
     * @param data
     * @return
     */
    public Node<T> search(T data){

        Node<T> node;
        for(node=header.next; node!=null && !node.data.equals(data); node=node.next);

        return node;
    }

    /**
     *
     * @param data
     * @return
     */
    public int indexOf(T data){

        Node<T> node=header.next;
        int i=1;
        for(; node!=null && !node.data.equals(data); node=node.next, i++);

        return (node==null) ? -1 : i;
    }

    /**
     *
     * @param index
     * @return
     */
    public Node<T> node(int index){
        if(index<1) {
            return null;
        }

        Node<T> node=header.next;
        for(int i=1; node!=null && i<index; node=node.next, i++);

        return node;
    }

    /**
     *
     */
    public void display(){
        System.out.println("Linked list: ");
        for(Node<T> node=header.next; node!=null; node=node.next){
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

        Node<T> maxNode=header.next;
        for(Node<T> node=header.next; node!=null; node=node.next) {
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

        Node<T> minNode=header.next;
        for(Node<T> node=header.next; node!=null; node=node.next){
            if(minNode.compareTo(node)>0){
                minNode = node;
            }
        }
        return minNode;
    }

    /**
     *
     */
    public void partitionLessThanX(T x) {

        Node<T> leftHeader = new Node<>(null);
        Node<T> endLeft = leftHeader;

        Node<T> endRight = header;

        while(endRight.next!=null) {
            if(endRight.next.data.compareTo(x)<0){
                Node<T> node = endRight.next;
                 endRight.next = node.next;
                endLeft = endLeft.next = node;
            } else {
                endRight = endRight.next;
            }
        }
        endLeft.next = header.next;
        header.next = leftHeader.next;
    }

    /**
     * Swap k and k+1 th nodes if present
     * @param k
     */
    public void swapAdjacent(final int k){
        Node<T> node=header;
        for(int i=1; node.next!=null && i<k; i++, node=node.next);

        if(node.next!=null && node.next.next!=null){
            Node<T> a = node.next;
            Node<T> b = a.next;
            a.next = b.next;
            b.next = a;
            node.next = b;
        }
    }

    /**
     *
     */
    public void insertionSort() {

        Node<T> start = header.next;
        header.next = null;

        while (start!=null) {
            Node<T> newNode = start;
            start = start.next;

            Node<T> node;
            for (node=header; node.next != null && node.next.data.compareTo(newNode.data) < 0; node = node.next);
            newNode.next = node.next;
            node.next = newNode;
        }
    }

    /**
     * Sort in bubble fashion.
     *
     */
    public void bubbleSort() {

        for (int i=0; i < size; i++) {
            Node<T> node = header;
            Node<T> a = header.next;

            for(int j=0; j < size-1-i; j++){
                Node<T> b = a.next;

                if(a.compareTo(b)>0){
                    a.next = b.next;
                    b.next = a;
                    node.next = b;
                    node=b;
                } else {
                    node=a;
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
    private Node<T> mergeAsSorted(Node<T> start1, Node<T> start2){

        final Node<T> header = new Node<>(null);

        Node<T> end = header;
        while(start1 != null && start2 != null){
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
        header.next = mergeSort(header.next, size());
    }

    /**
     *
     */
    private Node<T> mergeSort(Node<T> start, int length){

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

        list1 = mergeSort(list1, middle);
        list2 = mergeSort(list2, length-middle);
        return mergeAsSorted(list1, list2);
    }

    /**
     *
     */
    public void reverse(){

        Node<T> node = header.next;
        Node<T> start=null;

        while(node!=null){
            Node<T> temp = node.next;
            node.next = start;
            start = node;
            node = temp;
        }
        header.next = start;
    }

    /**
     *
     */
    public void reverseRec() {
        if(header.next==null){
            return;
        }
        reverseRec(header.next).next = null;
    }

    /**
     *
     * @param node
     */
    private Node<T> reverseRec(Node<T> node){

        if(node.next==null){
            return header.next = node;
        }
        return reverseRec(node.next).next = node;
    }

    /**
     *
     */
    public void reverse(int left, int right){

        int i=1;
        for(Node<T> node1=header; node1.next!=null; node1=node1.next, i++){
            if(i==left) {
                int j=i;
                for(Node<T> node2=node1.next; node2!=null; node2=node2.next, j++) {
                    if(j==right){
                        Node<T> node3 = node2.next;
                        node2.next = null;

                        // Reverse the sub list from node1.next to node2
                        Node<T> node = node1.next;
                        Node<T> s = node3;
                        while(node!=null) {
                            Node<T> temp = node.next;
                            node.next = s;
                            s=node;
                            node=temp;
                        }
                        node1.next = s;
                        break;
                    }
                }
                break;
            }
        }
    }

    /**
     *
     * @return
     */
    public Node<T> getMiddleNode(){

        Node<T> slowPtr, fastPtr;

        for(slowPtr=fastPtr=header.next; fastPtr!=null && fastPtr.next!=null; ){
            slowPtr=slowPtr.next;
            fastPtr=fastPtr.next.next;
        }

        return slowPtr;
    }

    /**
     *
     * @return
     */
    public Node<T> removeMiddleNode() {
        if(header.next==null) {
            return null;
        }

        Node<T> prevOfslowPtr = header;
        Node<T> slowPtr, fastPtr;

        for(slowPtr=fastPtr=header.next; fastPtr!=null && fastPtr.next!=null; ) {
            prevOfslowPtr = slowPtr;
            slowPtr=slowPtr.next;
            fastPtr=fastPtr.next.next;
        }

        prevOfslowPtr.next = slowPtr.next;

        slowPtr.next = null;
        return slowPtr;
    }

    /**
     *
     * @return
     */
    public boolean isPalindrome(){
        final Node<T> start = header.next;
        if(start==null || start.next==null){
            return true;
        }

        boolean isPalindrome = true;
        Node<T> prevOfslowPtr = header;
        Node<T> slowPtr, fastPtr;

        for(slowPtr=fastPtr=start; fastPtr!=null && fastPtr.next!=null; ){
            prevOfslowPtr = slowPtr;
            slowPtr=slowPtr.next;
            fastPtr=fastPtr.next.next;
        }

        prevOfslowPtr.next = null;

        LinkedList<T> list1 = new LinkedList<>();
        list1.header.next = slowPtr;
        list1.reverse();

        Node<T> node1, node2;
        for(node1=start, node2=list1.header.next; node1!=null; node1=node1.next, node2=node2.next){
            if(!node1.equals(node2)) {
                isPalindrome =  false;
                break;
            }
        }
        list1.reverse();
        prevOfslowPtr.next = slowPtr;
        return isPalindrome;
    }

    /**
     *
     * @return
     */
    public boolean isPalindromeUsingStack(){

        Stack<Node<T>> stack = new Stack<>();

        for(Node<T> node=header.next; node!=null; node=node.next){
            stack.push(node);
        }

        for(Node<T> node=header.next; node!=null; node=node.next){
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
        final NodeHolder leftNodeHolder = new NodeHolder(header.next);
        return isPalindromeRec(leftNodeHolder, header.next);
    }

    /**
     *
     * @return
     */
    private boolean isPalindromeRec(final NodeHolder leftNode, Node<T> rightNode){
        if(rightNode==null){
            return true;
        }

        boolean isPalindrome = isPalindromeRec(leftNode, rightNode.next)
                                    && leftNode.node.equals(rightNode);
        leftNode.node = leftNode.node.next;
        return isPalindrome;
    }

    /**
     * Floyd Algorithm
     *
     * @return
     */
    public boolean hasLoop(){

        Node<T> slowPtr,fastPtr;

        for(slowPtr=fastPtr=header.next; fastPtr!=null && fastPtr.next!=null; ){
            slowPtr=slowPtr.next;
            fastPtr=fastPtr.next.next;

            if(slowPtr==fastPtr){
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

        Node<T> slowPtr, fastPtr;

        for(slowPtr=fastPtr=header.next; fastPtr!=null && fastPtr.next!=null; ){
            slowPtr=slowPtr.next;
            fastPtr=fastPtr.next.next;

            if(slowPtr==fastPtr){
                for(slowPtr=header.next; slowPtr!=fastPtr; slowPtr=slowPtr.next,fastPtr=fastPtr.next);
                return slowPtr;
            }
        }
        return null;
    }

    /**
     *
     * @return
     */
    public int getLoopLength(){

        Node<T> slowPtr, fastPtr;

        int loopLength=0;
        for(slowPtr=fastPtr=header.next; fastPtr!=null && fastPtr.next!=null; loopLength++){
            slowPtr=slowPtr.next;
            fastPtr=fastPtr.next.next;

            if(slowPtr==fastPtr){
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

        Node<T> node = header.next;
        for(int i=1; node!=null && i<k; node=node.next, i++);

        if(node!=null){
            Node<T> kthNodeFromEnd = header.next;
            for(; node.next!=null; node=node.next, kthNodeFromEnd=kthNodeFromEnd.next);

            return kthNodeFromEnd;
        }

        return null;
    }

    /**
     *
     * @param k
     */
    public Node<T> removeKthNodeFromEnd(final int k){

        Node<T> node = header.next;
        for(int i=1; node!=null && i<k; node=node.next, i++);

        if(node!=null){
            Node<T> prevOfKth = header;
            for(; node.next!=null; node=node.next, prevOfKth=prevOfKth.next);

            Node<T> kthNodeFromEnd = prevOfKth.next;
            prevOfKth.next = kthNodeFromEnd.next;
            kthNodeFromEnd.next = null;
            return kthNodeFromEnd;
        }

        return null;
    }

    /**
     *
     * @param k
     */
    public void rotate( int k){
        if(header.next==null){
            return;
        }

        k%=size;
        if(k<1) return;

        Node<T> node = header.next;
        for(int i=1; node!=null && i<k; node=node.next, i++);

        if(node!=null){
            Node<T> prevOfKth = header;
            for(; node.next!=null; node=node.next, prevOfKth=prevOfKth.next);

            Node<T> kthNodeFromEnd = prevOfKth.next;
            prevOfKth.next = null;
            node.next = header.next;
            header.next=kthNodeFromEnd;
        }
    }

    /**
     *
     */
    public void resetList(){
        header.next=null;
    }

    /**
     *
     */
    public void reversePairs() {
        if(header.next==null) {
            return;
        }
        Node<T> _2ndNode = header.next.next;

        for(Node<T> node = header; node.next!=null && node.next.next!=null;){
            Node<T> a = node.next;
            Node<T> b = a.next;
            a.next = b.next;
            b.next = a;
            node.next=b;
            node = a;
        }

        if(_2ndNode!=null){
            header.next = _2ndNode;
        }
    }

    /**
     *
     */
    public void reverseKGroup(int k) {

        Node<T> end = header;

        Node<T> s=header.next;
        while(s!=null) {
            Node<T> kThNode = s;
            for (int i=1; i < k && kThNode != null; i++, kThNode=kThNode.next);
            if(kThNode==null) {
                end.next = s;
                break;
            }

            Node<T> nextS = kThNode.next;
            kThNode.next = null;

            end.next = kThNode;
            end = s;

            Node<T> node = s;
            s=null;
            while(node!=null){
                Node<T> temp = node.next;
                node.next = s;
                s=node;
                node=temp;
            }

            s = nextS;
        }
    }

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
        for(Node<Integer> node = (Node<Integer>)header.next; node!=null; node=node.next){
            if(!node.data.equals(9)){
                none9Node = node;
            }
        }

        if(none9Node==null) {
            none9Node = new Node<>(0);
            none9Node.next = (Node<Integer>) header.next;
            header.next = (Node<T>) none9Node;
            size++;
        }

        none9Node.data += 1;
        for(Node<Integer> node=none9Node.next; node!=null; node=node.next){
            node.data = 0;
        }
    }

    /**
     *
     * @param l1
     * @param l2
     * @return
     */
    public Node<Integer> addTwoNumbers(Node<Integer> l1, Node<Integer> l2) {

        Node<Integer> resultHeader = new Node<>(null);
        Node<Integer> l3 = resultHeader;

        int rem=0,sum;

        for(; !(l1==null || l2==null); l1=l1.next,l2=l2.next){
            sum = l1.data+l2.data + rem;
            l3 = l3.next = new Node<>(sum%10);
            rem = sum/10;
        }

        if(l1==null) l1=l2;
        for(; l1!=null; l1=l1.next){
            sum = l1.data + rem;
            l3 = l3.next = new Node<>(sum%10);
            rem = sum/10;
        }

        if(rem>0){
            l3.next = new Node<>(rem);
        }

        return resultHeader.next;
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

        Node<T> node = header.next;
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
        return(header.next==null);
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
            if(list.header.next!=null){
                heap.add(list.header.next);
            }
            list.header.next=null;
        }

        Node<T> end = header;
        while (!heap.isEmpty()){
            end.next = heap.remove();
            end = end.next;

            if(end.next!=null){
                heap.add(end.next);
            }
        }
        end.next = null;
    }

    /**
     *
     */
    public void deleteDuplicates(){
        Set<T> set = new HashSet<>();

        Node<T> end = header;

        for(Node<T> node=header.next; node!=null; node=node.next){
            if(!set.contains(node.data)){
                set.add(node.data);
                end = end.next = node;
            }
        }
        end.next = null;
    }

    /**
     *
     */
    public void deleteDuplicatesSorted(){

        Node<T> end = header;

        for(Node<T> node=header.next; node!=null; node=node.next){
            if(!node.data.equals(end.data)){
                end=end.next=node;
            }
        }
        end.next=null;
    }

    /**
     *
     */
    public void deleteAllDuplicatesSorted(){

        Node<T> end = header;

        Node<T> node=header.next;
        for(; node!=null && node.next!=null; node=node.next){
            if(node.data.equals(node.next.data)){
                for(;node.next!=null && node.data.equals(node.next.data); node=node.next);
            } else {
                end=end.next=node;
            }
        }
        end.next=node;
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

        linkedList.reversePairs();
        linkedList.display();
        System.out.println();

        linkedList.reverseKGroup(4);
        linkedList.display();
        System.out.println();

        //Test addOne to the number list, 939
        LinkedList<Integer> numberList = new LinkedList<>();
        numberList.addStart(9);
        numberList.addStart(3);
        numberList.addStart(9);
        numberList.display();
        numberList.addOne();
        numberList.display();

        numberList.removeKthNodeFromEnd(2);
        numberList.display();

        LinkedList<Integer> l4 = new LinkedList<>();
        Arrays.stream(new int[]{4,2,1,3}).forEach(l4::add);
        l4.display();
        l4.mergeSort();
        l4.display();

        LinkedList<Integer> l5 = new LinkedList<>();
        Arrays.stream(new int[]{4,2,1,3}).forEach(l5::add);
        l5.display();
        l5.insertionSort();
        l5.display();

        LinkedList<Integer> l6 = new LinkedList<>();
        Arrays.stream(new int[]{1,4,2,2,3,4}).forEach(l6::add);
        l6.display();
        l6.deleteDuplicates();
        l6.display();

        LinkedList<Integer> l7 = new LinkedList<>();
        Arrays.stream(new int[]{1,2,2,3,3,4,5,5,6}).forEach(l7::add);
        l7.display();
        l7.deleteDuplicatesSorted();
        l7.display();

        l7 = new LinkedList<>();
        Arrays.stream(new int[]{1,2,2,3,3,4,5,5,6}).forEach(l7::add);
        l7.display();
        l7.deleteAllDuplicatesSorted();
        l7.display();

        l7 = new LinkedList<>();
        Arrays.stream(new int[]{1,2,2,3,3,4,5,5,6}).forEach(l7::add);
        l7.display();
        l7.reverse(3,6);
        l7.display();

        l7 = new LinkedList<>();
        Arrays.stream(new int[]{1,2,3}).forEach(l7::add);
        l7.display();
        l7.removeMiddleNode();
        l7.display();

    }

    public static class Node<T extends Comparable<T>> implements Comparable<Node<T>> {

        public T data;
        public Node<T> next;

        public Node(T data) {
            this.data = data;
        }

        @Override
        public boolean equals(Object o) {
            return o!=null
                    && this.getClass() == o.getClass()
                    && data.equals(((Node<?>) o).data);
        }

        @Override public int compareTo(Node<T> that) { return this.data.compareTo(that.data);}
        @Override public String toString() { return data.toString(); }
        @Override public int hashCode() { return Objects.hash(data, next); }
    }

    private class NodeHolder {
        Node<T> node;
        public NodeHolder(Node<T> node) {
            this.node = node;
        }
    }

}
