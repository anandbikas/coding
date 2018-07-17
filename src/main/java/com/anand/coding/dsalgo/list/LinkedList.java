package com.anand.coding.dsalgo.list;


/**
 * Generic Linked List implementation
 * @param <T>
 */
public class LinkedList<T extends Comparable<T>> {

    private Node<T> start;

    /**
     *
     * @param data
     * @return
     */
    public Node insertStart(T data){

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
    public Node insertEnd(T data){

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
    public Node insertAtIndex(int index, T data){

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
     *
     * @return
     */
    public Node deleteStart(){

        if(start==null){
            return null;
        }

        Node<T> deletedNode = start;
        start=deletedNode.getNext();
        return deletedNode;
    }

    /**
     *
     * @return
     */
    public Node<T> deleteEnd(){

        if(start==null){
            return null;
        }

        if(start.getNext()==null){
            Node<T> deletedNode = start;
            start=deletedNode.getNext();
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
            return deletedNode;
        }

        for(Node<T> node=start; node.getNext()!=null; node=node.getNext()){
            if(node.getNext().getData()==data){
                Node<T> deletedNode=node.getNext();
                node.setNext(deletedNode.getNext());
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
            return deletedNode;
        }

        int i;
        Node<T> node;
        for(node=start, i=2; node.getNext()!=null; node=node.getNext(), i++){
            if(index==i){
                Node<T> deletedNode=node.getNext();
                node.setNext(deletedNode.getNext());
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
               return loopLength;
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
     */
    public void resetList(){
        start=null;
    }


    /**
     *
     * @param args
     */
    public static void main(String [] args){

        final LinkedList<Integer> list = new LinkedList<>();

        for(int i=6; i<=10; i++){
            list.insertEnd(i);
        }
        for(int i=5; i>0; i--){
            list.insertStart(i);
        }
        System.out.println("length: " + list.minValueNode());

        System.out.println("list.minValueNode(): " + list.minValueNode());
        System.out.println("list.maxValueNode(): " + list.maxValueNode());

        list.insertAtIndex(6, 0);
        list.deleteAtIndex(11);
        System.out.println(list.search(0));
        list.bubbleSort();
        list.display();

        list.reverse();
        list.display();

        list.bubbleSort();

        list.swapAdjacentNodes(1);
        list.display();

        list.search(9).setNext(list.search(3));
        System.out.println("list.hasLoop(): " + list.hasLoop());
        System.out.println("list.getStartNodeOfLoop(): " + list.getStartNodeOfLoop());
        System.out.println("list.getLoopLength(): " + list.getLoopLength());

        list.fixLoop();

        list.deleteEnd();
        list.deleteStart();
        list.delete(5);
        list.display();


    }
}
