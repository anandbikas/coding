package com.anand.coding.dsalgo.list;

/**
 * Generic Doubly Linked List implementation
 * @param <T>
 */
public class DoublyLinkedList<T extends Comparable<T>> {

    private DNode<T> start;
    private DNode<T> end;

    int length=0;

    /**
     *
     * @param data
     * @return
     */
    public DNode<T> insertStart(T data){

        final DNode<T> newNode = new DNode<>(data);
        newNode.next = start;
        start = newNode;

        if(end==null){
            end = newNode;
        } else{
            newNode.next.prev= newNode;
        }
        length++;
        return newNode;
    }

    /**
     *
     * @param data
     * @return
     */
    public DNode<T> insertEnd(T data){

        final DNode<T> newNode = new DNode<>(data);
        newNode.prev = end;
        end = newNode;

        if(start==null){
            start=newNode;
        } else{
            newNode.prev.next = newNode;
        }

        length++;
        return newNode;
    }

    /**
     *
     * @param newNode
     */
    private void fixNextAndPrevInsertion(final DNode<T> newNode){

        if(newNode.next==null){
            end = newNode;
        } else {
            newNode.next.prev = newNode;
        }

        if(newNode.prev == null) {
            start = newNode;
        } else {
            newNode.prev.next = newNode;
        }
    }

    /**
     *
     * @param index
     * @param data
     * @return
     */
    public DNode<T> insertAtIndex(int index, T data){

        final DNode<T> newNode = new DNode<>(data);
        int i;
        DNode<T> node;
        for(node=start, i=1; node!=null && i<index; node=node.next, i++);

        if(index==i){
            newNode.next = node;
            newNode.prev = node==null? end : node.prev;

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
    public DNode<T> insertSorted(T data){

        final DNode<T> newNode = new DNode<>(data);

        DNode<T> node;
        for(node=start; node!=null && node.data.compareTo(data)<0; node=node.next);

        newNode.next = node;
        newNode.prev = node==null? end : node.prev;

        fixNextAndPrevInsertion(newNode);
        length++;

        return newNode;
    }

    /**
     *
     * @return
     */
    public DNode<T> deleteStart(){

        if(start==null){
            return null;
        }

        DNode<T> deletedNode = start;
        start=deletedNode.next;
        start.prev = null;

        if(start==null){
            end = null;
        }
        length--;

        deletedNode.next = null;
        return deletedNode;
    }

    /**
     *
     * @return
     */
    public DNode<T> deleteEnd(){

        if(end==null){
            return null;
        }

        DNode<T> deletedNode = end;
        end=deletedNode.prev;
        end.next = null;

        if(end==null){
            start = null;
        }

        deletedNode.prev = null;

        length--;
        return deletedNode;
    }

    /**
     *
     * @param deletedNode
     */
    public void fixNextAndPrevDeletion(final DNode<T> deletedNode){

        if(deletedNode.next==null){
            end = deletedNode.prev;
            end.next = null;
        } else {
            deletedNode.next.prev = deletedNode.prev;
        }

        if(deletedNode.prev == null) {
            start = deletedNode.next;
            start.prev = null;
        } else {
            deletedNode.prev.next = deletedNode.next;
        }

        deletedNode.prev = null;
        deletedNode.next = null;
    }

    /**
     *
     * @param data
     * @return
     */
    public DNode<T> delete(T data){

        for(DNode<T> node = start; node!=null; node=node.next){
            if(node.data==data){
                DNode<T> deletedNode=node;
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
    public DNode<T> deleteAtIndex(final int index){

        int i;
        DNode<T> node;
        for(node=start, i=1; node!=null && i<index; node=node.next, i++);

        if(node!=null){
            DNode<T> deletedNode=node;
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
    public DNode<T> search(T data){

        DNode<T> node;
        for(node=start; node!=null && !node.data.equals(data); node=node.next);

        return node;
    }

    /**
     *
     * @param data
     * @return
     */
    public int findIndexFromStart(T data){

        DNode<T> node;
        int i=1;
        for(node=start; node!=null && !node.data.equals(data); node=node.next, i++);

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

        DNode<T> node;
        int i=1;
        for(node=end; node!=null && !node.data.equals(data); node=node.prev, i++);

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
    public DNode<T> getElementAtIndexFromStart(final int index){

        DNode<T> node;
        int i=1;
        for(node=start; node!=null && i<index; node=node.next, i++);

        return node;
    }

    /**
     *
     * @param index
     * @return
     */
    public DNode<T> getElementAtIndexFromEnd(final int index){

        DNode<T> node;
        int i=1;
        for(node=end; node!=null && i<index; node=node.prev, i++);

        return node;
    }


    /**
     *
     */
    public void display(){
        System.out.println("Linked list: ");
        for(DNode node = start; node!=null; node=node.next){
            System.out.print(node.data + ", ");
        }
        System.out.println();
    }

    /**
     *
     */
    public void displayReverse(){
        System.out.println("Linked list (reversed): ");
        for(DNode node = end; node!=null; node=node.prev){
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
     * Swap k and k+1 th nodes if present
     * @param k
     */
    public void swapAdjacentNodes(final int k){

        if(start==null || start.next==null){
            return;
        }

        DNode<T> node=start;
        for(int i=1; node.next!=null; i++, node=node.next){
            if(k==i){
                DNode<T> temp = node.next;

                node.next = temp.next;

                if(node.next==null){
                    end=node;
                } else {
                    node.next.prev = node;
                }

                temp.next = node;
                temp.prev = node.prev;

                node.prev = temp;
                if(temp.prev==null){
                    start = temp;
                } else {
                    temp.prev.next = temp;
                }
                break;
            }
        }
    }

    /**
     * Sort in bubble fashion.
     *
     */
    public void bubbleSort() {

        for (int i=0; i < length; i++) {
            DNode<T> a = start;

            for(int j=0; j < length-1-i; j++){
                DNode<T> b = a.next;

                if(a.compareTo(b)>0){
                    a.next = b.next;

                    if(a.next==null){
                        end=a;
                    } else {
                        a.next.prev = a;
                    }

                    b.next = a;
                    b.prev = a.prev;

                    a.prev = b;
                    if(b.prev==null){
                        start = b;
                    } else {
                        b.prev.next = b;
                    }
                } else {
                    a=b;
                }
            }
        }
    }

    /**
     *
     */
    public void reverse(){

        DNode<T> node = start;

        while(node!=null ){
            DNode<T> temp = node.next;
            node.next = node.prev;
            node.prev = temp;

            node=temp;
        }

        DNode<T> temp = start;
        start = end;
        end = temp;
    }

    /**
     *
     */
    public void resetList(){
        start=null;
        end=null;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        final DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

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