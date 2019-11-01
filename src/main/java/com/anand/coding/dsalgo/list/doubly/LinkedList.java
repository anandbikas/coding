package com.anand.coding.dsalgo.list.doubly;

/**
 * Generic Doubly Linked List implementation
 * @param <T>
 */
public class LinkedList<T extends Comparable<T>> {

    private Node<T> start;
    private Node<T> end;

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

        if(end==null){
            end = newNode;
        } else{
            newNode.getNext().setPrev(newNode);
        }
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
        newNode.setPrev(end);
        end = newNode;

        if(start==null){
            start=newNode;
        } else{
            newNode.getPrev().setNext(newNode);
        }

        length++;
        return newNode;
    }

    /**
     *
     * @param newNode
     */
    private void fixNextAndPrevInsertion(final Node<T> newNode){

        if(newNode.getNext()==null){
            end = newNode;
        } else {
            newNode.getNext().setPrev(newNode);
        }

        if(newNode.getPrev() == null) {
            start = newNode;
        } else {
            newNode.getPrev().setNext(newNode);
        }
    }

    /**
     *
     * @param index
     * @param data
     * @return
     */
    public Node<T> insertAtIndex(int index, T data){

        final Node<T> newNode = new Node<>(data);
        int i;
        Node<T> node;
        for(node=start, i=1; node!=null && i<index; node=node.getNext(), i++);

        if(index==i){
            newNode.setNext(node);
            newNode.setPrev(node==null? end : node.getPrev());

            fixNextAndPrevInsertion(newNode);

            length++;
            return newNode;
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

        Node<T> node;
        for(node=start; node!=null && node.getData().compareTo(data)<0; node=node.getNext());

        newNode.setNext(node);
        newNode.setPrev(node==null? end : node.getPrev());

        fixNextAndPrevInsertion(newNode);
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
        start.setPrev(null);

        if(start==null){
            end = null;
        }
        length--;

        deletedNode.setNext(null);
        return deletedNode;
    }

    /**
     *
     * @return
     */
    public Node<T> deleteEnd(){

        if(end==null){
            return null;
        }

        Node<T> deletedNode = end;
        end=deletedNode.getPrev();
        end.setNext(null);

        if(end==null){
            start = null;
        }

        deletedNode.setPrev(null);

        length--;
        return deletedNode;
    }

    /**
     *
     * @param deletedNode
     */
    public void fixNextAndPrevDeletion(final Node<T> deletedNode){

        if(deletedNode.getNext()==null){
            end = deletedNode.getPrev();
            end.setNext(null);
        } else {
            deletedNode.getNext().setPrev(deletedNode.getPrev());
        }

        if(deletedNode.getPrev() == null) {
            start = deletedNode.getNext();
            start.setPrev(null);
        } else {
            deletedNode.getPrev().setNext(deletedNode.getNext());
        }

        deletedNode.setPrev(null);
        deletedNode.setNext(null);
    }

    /**
     *
     * @param data
     * @return
     */
    public Node<T> delete(T data){

        for(Node<T> node=start; node!=null; node=node.getNext()){
            if(node.getData()==data){
                Node<T> deletedNode=node;
                fixNextAndPrevDeletion(deletedNode);

                length--;
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

        int i;
        Node<T> node;
        for(node=start, i=1; node!=null && i<index; node=node.getNext(), i++);

        if(node!=null){
            Node<T> deletedNode=node;
            fixNextAndPrevDeletion(deletedNode);

            length--;
            return deletedNode;
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
    public int findIndexFromStart(T data){

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
     * @param data
     * @return
     */
    public int findIndexFromEnd(T data){

        Node<T> node;
        int i=1;
        for(node=end; node!=null && !node.getData().equals(data); node=node.getPrev(), i++);

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
    public Node<T> getElementAtIndexFromStart(final int index){

        Node<T> node;
        int i=1;
        for(node=start; node!=null && i<index; node=node.getNext(), i++);

        return node;
    }

    /**
     *
     * @param index
     * @return
     */
    public Node<T> getElementAtIndexFromEnd(final int index){

        Node<T> node;
        int i=1;
        for(node=end; node!=null && i<index; node=node.getPrev(), i++);

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
     */
    public void displayReverse(){
        System.out.println("Linked list (reversed): ");
        for(Node node=end; node!=null; node=node.getPrev()){
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
     * Swap k and k+1 th nodes if present
     * @param k
     */
    public void swapAdjacentNodes(final int k){

        if(start==null || start.getNext()==null){
            return;
        }

        Node<T> node=start;
        for(int i=1; node.getNext()!=null; i++, node=node.getNext()){
            if(k==i){
                Node<T> temp = node.getNext();

                node.setNext(temp.getNext());

                if(node.getNext()==null){
                    end=node;
                } else {
                    node.getNext().setPrev(node);
                }

                temp.setNext(node);
                temp.setPrev(node.getPrev());

                node.setPrev(temp);
                if(temp.getPrev()==null){
                    start = temp;
                } else {
                    temp.getPrev().setNext(temp);
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
            Node<T> node = start;
            for(int j=0; j < length-1-i; j++){
                if(node.compareTo(node.getNext())>0){
                    Node<T> temp = node.getNext();

                    node.setNext(temp.getNext());

                    if(node.getNext()==null){
                        end=node;
                    } else {
                        node.getNext().setPrev(node);
                    }

                    temp.setNext(node);
                    temp.setPrev(node.getPrev());

                    node.setPrev(temp);
                    if(temp.getPrev()==null){
                        start = temp;
                    } else {
                        temp.getPrev().setNext(temp);
                    }
                } else {
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

        while(node!=null ){
            Node<T> temp = node.getNext();
            node.setNext(node.getPrev());
            node.setPrev(temp);

            node = node.getPrev();
        }

        Node<T> temp = start;
        start = end;
        end = temp;
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

        list.insertAtIndex(6, 0);
        list.deleteAtIndex(11);
        System.out.println(list.search(0));

        list.display();
        list.bubbleSort();
        list.display();

        list.reverse();
        list.display();

        list.bubbleSort();

        list.swapAdjacentNodes(1);
        list.display();

        list.deleteEnd();
        list.deleteStart();
        list.delete(5);
        list.display();

        list.displayReverse();
    }
}