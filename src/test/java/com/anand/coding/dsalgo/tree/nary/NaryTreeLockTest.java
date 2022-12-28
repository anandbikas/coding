package com.anand.coding.dsalgo.tree.nary;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Nary Tree concurrent lock/unlock test.
 */
@Test
public class NaryTreeLockTest {

    private NaryTree<Integer> naryTree;
    private List<Node<Integer>> nodeList;
    private List<Node<Integer>> nodeListWithDuplicateNodes;

    @BeforeMethod
    private void setUp(){
        /*
         *                     1
         *                 2      3
         *               4  5    6  7
         *                     8
         */
        nodeList = new ArrayList<>();
        naryTree = new NaryTree<>(1, 2);

        naryTree.insertAsChild(2, 1, 0);
        naryTree.insertAsChild(3, 1, 1);

        naryTree.insertAsChild(4, 2, 0);
        naryTree.insertAsChild(5, 2, 1);

        naryTree.insertAsChild(6, 3, 0);
        naryTree.insertAsChild(7, 3, 1);

        naryTree.insertAsChild(8, 6, 0);

        nodeList = naryTree.preOrderTraversalRecNodeList();
        nodeListWithDuplicateNodes = new ArrayList<>(nodeList);
        nodeListWithDuplicateNodes.addAll(nodeList);
    }

    @Test
    public void naryTreeTest(){
        naryTree.displayPaths();
    }

    @Test(invocationCount = 3)
    public void lockTest(){

        List<Node<Integer>> lockedNodes = new ArrayList<>();

        // Concurrent lock with duplicate nodes
        nodeListWithDuplicateNodes.parallelStream().forEach(node -> {
            if(node.lock()) {
                lockedNodes.add(node);
            }
        });

        // Validate locked node's descendants and ancestors should not be locked
        lockedNodes.forEach(node -> {
            Assert.assertEquals(node.lockedDescendants, 0);
            for(Node<Integer> parent = node.parent; parent!=null; parent=parent.parent){
                Assert.assertFalse(parent.isLocked);
            }
        });

        // Again, serially lock all the nodes. No node should be lockable now.
        nodeListWithDuplicateNodes.forEach(node -> Assert.assertFalse(node.lock()));
    }

    @Test(invocationCount = 3)
    public void lockTestLockableNodesOnly(){

        //Lock Node 4,5,6,8
        nodeList.stream().filter(node -> node.data >3 && node.data<8).collect(Collectors.toList())
            .parallelStream().forEach(node -> Assert.assertTrue(node.lock()));
    }

    @Test(invocationCount = 3)
    public void lockUnlockTest(){

        Random random = new Random();

        // Concurrent lock/unlock with duplicate nodes
        nodeListWithDuplicateNodes.parallelStream().forEach(node -> {
            if(random.nextBoolean()) {
                node.lock();
            } else {
                node.unLock();
            }
        });

        // Validate locked node's descendants and ancestors should not be locked
        nodeList.parallelStream().forEach(node -> {
            if(node.isLocked){
                Assert.assertEquals(node.lockedDescendants, 0);
                for(Node<Integer> parent = node.parent; parent!=null; parent=parent.parent){
                    Assert.assertFalse(parent.isLocked);
                }
            }
        });

        // Validate lockedDescendants
        nodeList.parallelStream().forEach(node -> {
            if(node.lockedDescendants>0){
                Assert.assertEquals(
                        naryTree.preOrderTraversalRecNodeList(node).stream().filter(n -> n.isLocked).count(),
                            node.lockedDescendants);
            }
        });
    }
}
