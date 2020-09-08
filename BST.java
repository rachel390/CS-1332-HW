import java.util.Collection;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Your implementation of a BST.
 * @author Rachel
 * @version 1.0
 * @userid rmills30
 * @GTID 903394578
 *
 * Collaborators: Did not worth with anyone else
 *
 * Resources: Did not use any other materials
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize the BST with the data in the
     * Collection. The elements should be added to the BST in the order in 
     * which they appear in the Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular for-loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public BST(Collection<T> data) {
        if (data != null) {
            for (T datum : data) {
                if (datum != null) {
                    add(datum);
                } else {
                    throw new java.lang.IllegalArgumentException("Null elements may not be added to the BST.");
                }
            }
        } else {
            throw new java.lang.IllegalArgumentException("Null elements may not be added to the BST.");
        }
    }

    /**
     * Adds the data to the tree.
     *
     * The data becomes a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Data must not be null");
        } else {
            root = addHelper(root, new BSTNode<>(data));
            size++;
        }
    }

    /**
     * helps to recursively traverse the list to find the proper
     * location to efficiently add data to the BST
     * @param node where to begin traversing
     * @param newNode newNode being added to the BST
     * @return node where new data will be added
     */
    private BSTNode<T> addHelper(BSTNode<T> node, BSTNode<T> newNode) {
        if (node == null) {
            return newNode;
        } else if (node.getData() == newNode.getData()) {
            size--;
            return node;
        } else if (newNode.getData().compareTo(node.getData()) > 0) {
            node.setRight(addHelper(node.getRight(), newNode));
        } else if (newNode.getData().compareTo(node.getData()) < 0) {
            node.setLeft(addHelper(node.getLeft(), newNode));
        }
        return node;
    }
    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the predecessor to
     * replace the data. You MUST use recursion to find and remove the
     * predecessor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T remove(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Data must not be null.");
        }
        BSTNode<T> dummy = new BSTNode<>(null);
        root = removeHelper(root, data, dummy);
        if (dummy.getData() == null) {
            throw new java.util.NoSuchElementException("Data was not found in the BST");
        }
        return dummy.getData();
    }

    /**
     *Recursive helper method to remove a node from the BST
     * @param curr node to begin search for node to remove
     * @param data data to be removed
     * @param dummy placeholder for data which was removed
     * @return data that was removed
     */
    private BSTNode<T> removeHelper(BSTNode<T> curr, T data, BSTNode<T> dummy) {
        if (curr == null) {
            return curr;
        } else if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(removeHelper(curr.getLeft(), data, dummy));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(removeHelper(curr.getRight(), data, dummy));
        } else if (data.compareTo(curr.getData()) == 0) {
            dummy.setData(curr.getData());
            size--;
            //cases for how many children the node being removed has
            if (curr.getRight() == null && curr.getLeft() == null) {
                return null;
            } else if (curr.getRight() == null) {
                return curr.getLeft();
            } else if (curr.getLeft() == null) {
                return curr.getRight();
            } else {
                BSTNode<T> dummy2 = new BSTNode<>(null);
                curr.setLeft((removePredecessor(curr.getLeft(), dummy2)));
                dummy2.setRight(curr.getRight());
                dummy2.setLeft(curr.getLeft());
                return dummy2;
            }
        }
        return curr;
    }

    /**
     *Recursive helper method to return the predecessor of the node being
     * removed in the case that the node has 2 children
     * @param curr node from which to retrieve the predecessor of the node
     * @param dummy dummy node to store data that was removed
     * @return returns predecessor to replace node being removed
     */
    private BSTNode<T> removePredecessor(BSTNode<T> curr, BSTNode<T> dummy) {
        if (curr.getRight() == null) {
            dummy.setData(curr.getData());
            return curr.getLeft();
        } else {
            curr.setRight(removePredecessor(curr.getRight(), dummy));
            return curr;
        }
    }


    /**
     * Returns the data from the tree matching the given parameter.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Data to be retrieved must not be null.");
        }
        if (root == null) {
            throw new java.util.NoSuchElementException("Data is not in the tree because the tree is empty.");
        }
        if (data.compareTo(root.getData()) == 0) {
            return root.getData();
        } else {
            return getHelper(root, data);
        }
    }

    /**
     * recursive helper method for returning the data from
     * the tree matching the given parameter.
     * @param node node where the search begins
     * @param data data being searched for
     * @return the data of the node being searched for when it is found
     */
    private T getHelper(BSTNode<T> node, T data) {
        if (node == null) {
            throw new java.util.NoSuchElementException("The data was not found.");
        } else if (data.compareTo(node.getData()) < 0) {
            return getHelper(node.getLeft(), data);
        } else if (data.compareTo(node.getData()) > 0) {
            return getHelper(node.getRight(), data);
        } else {
            return node.getData();
        }
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Data must not be null in order to be searched in the BST.");
        }
        return containsHelper(data, root);
    }

    /**
     * recursive helper method to contains method, returns whether or not data matching the given parameter is contained
     * within the tree.
     * @param data data being searched for in the tree
     * @param node where to begin to search traversal
     * @return whether or not the data was found in the tree
     */
    private boolean containsHelper(T data, BSTNode<T> node) {
        if (node == null) {
            return false;
        } else if (data.compareTo(node.getData()) < 0) {
            return containsHelper(data, node.getLeft());
        } else if (data.compareTo(node.getData()) > 0) {
            return containsHelper(data, node.getRight());
        } else {
            return true;
        }
    }

    /**
     * Generate a pre-order traversal of the tree.
     *
     * Must be O(n).
     *
     * @return the preorder traversal of the tree
     */
    public List<T> preorder() {
        List<T> list = new ArrayList<>();
        preorderHelper(root, list);
        return list;
    }

    /**
     * recursive helper method to generate a pre-order traversal of the tree
     * @param node node where the traversal begins
     * @param list list containing preorder traversal of the tree
     */
    private void preorderHelper(BSTNode<T> node, List<T> list) {
        if (node != null) {
            list.add(node.getData());
            preorderHelper(node.getLeft(), list);
            preorderHelper(node.getRight(), list);
        }
    }

    /**
     * Generate an in-order traversal of the tree.
     *
     * Must be O(n).
     *
     * @return the inorder traversal of the tree
     */
    public List<T> inorder() {
        List<T> list = new ArrayList<>();
        inorderHelper(root, list);
        return list;
    }

    /**
     * recursive helper method to traverse the BST in order
     * @param node current node being examined
     * @param list list to which each node is added in order
     */
    private void inorderHelper(BSTNode<T> node, List<T> list) {
        if (node != null) {
            inorderHelper(node.getLeft(), list);
            list.add(node.getData());
            inorderHelper(node.getRight(), list);
        }
    }

    /**
     * Generate a post-order traversal of the tree.
     *
     * Must be O(n).
     *
     * @return the postorder traversal of the tree
     */
    public List<T> postorder() {
        List<T> list = new ArrayList<>();
        postorderHelper(root, list);
        return list;
    }

    /**
     * recursive helper method to generate post-order traversal of the tree
     * @param node node where to begin the traversal
     * @param list list containing postorder traversal of the tree
     */
    private void postorderHelper(BSTNode<T> node, List<T> list) {
        if (node != null) {
            postorderHelper(node.getLeft(), list);
            postorderHelper(node.getRight(), list);
            list.add(node.getData());
        }
    }

    /**
     * Generate a level-order traversal of the tree.
     *
     * This does not need to be done recursively.
     *
     * Hint: You will need to use a queue of nodes. Think about what initial
     * node you should add to the queue and what loop / loop conditions you
     * should use. You may import java.util.Queue as well as an implmenting
     * class.
     *
     * Must be O(n).
     *
     * @return the level order traversal of the tree
     */
    public List<T> levelorder() {
        Queue<BSTNode<T>> q = new ArrayDeque<>();
        LinkedList<T> list = new LinkedList<>();
        if (root != null) {
            list.add(root.getData());
            q.add(root);
        }
        while (!q.isEmpty()) {
            BSTNode<T> curr = q.poll();
            if (curr != null) {
                if (curr.getLeft() != null) {
                    q.add(curr.getLeft());
                    list.add(curr.getLeft().getData());
                }
                if (curr.getRight() != null) {
                    q.add(curr.getRight());
                    list.add(curr.getRight().getData());
                }
            }
        }
        return list;
    }

    /**
     * Returns the height of the root of the tree.
     *
     * A node's height is defined as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child has a height of -1.
     *
     * Must be O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return heightHelper(root);
    }
    /**
     *recursive helper method to help calculate the height of the tree
     * @param node current node being counted
     * @return the count
     */
    private int heightHelper(BSTNode<T> node) {
        int height = -1;
        if (node == null) {
            return height;
        } else {
            height = 1 + Math.max(heightHelper(node.getLeft()), heightHelper(node.getRight()));
        }
        return height;
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Finds the path between two elements in the tree, specifically the path
     * from data1 to data2, inclusive of both.
     *
     * To do this, you must first find the deepest common ancestor of both data
     * and add it to the list. Then traverse to data1 while adding its ancestors
     * to the front of the list. Finally, traverse to data2 while adding its
     * ancestors to the back of the list. Please note that there is no
     * relationship between the data parameters in that they may not belong
     * to the same branch. You will most likely have to split off and
     * traverse the tree for each piece of data.
     * *
     * You may only use 1 list instance to complete this method. Think about
     * what type of list to use since you will have to add to the front and
     * back of the list.
     *
     * This method only needs to traverse to the deepest common ancestor once.
     * From that node, go to each data in one traversal each. Failure to do
     * so will result in a penalty.
     *
     * If both data1 and data2 are equal and in the tree, the list should be
     * of size 1 and contain the element from the tree equal to data1 and data2.
     *
     * Hint: How can we use the order property of the BST to locate the deepest
     * common ancestor?
     *
     * Ex:
     * Given the following BST composed of Integers
     *              50
     *          /        \
     *        25         75
     *      /    \
     *     12    37
     *    /  \    \
     *   11   15   40
     *  /
     * 10
     * findPathBetween(10, 40) should return the list [10, 11, 12, 25, 37, 40]
     * findPathBetween(50, 37) should return the list [50, 25, 37]
     * findPathBetween(75, 75) should return the list [75]
     *
     * Must be O(log n) for a balanced tree and O(n) for worst case.
     *
     * @param data1 the data to start the path from
     * @param data2 the data to end the path on
     * @return the unique path between the two elements
     * @throws java.lang.IllegalArgumentException if either data1 or data2 is
     *                                            null
     * @throws java.util.NoSuchElementException   if data1 or data2 is not in
     *                                            the tree
     */
    public List<T> findPathBetween(T data1, T data2) {
        LinkedList<T> list = new LinkedList<>();
        if (data1 == null || data2 == null) {
            throw new java.lang.IllegalArgumentException("Data entered must not be null.");
        } else if (root == null) {
            throw new java.util.NoSuchElementException("Data is not in the tree because the tree is empty.");
        }
        pathHelper(list, root, data1, data2);
        return list;
    }

    /**
     *recurisve helper method to find the path between two nodes,
     * specifically finds the deepest common ancestor between the two
     * @param list list to which the path of nodes will be added
     * @param node current node being examined
     * @param data1 the first data to be traced
     * @param data2 the second data to be traced
     * @return the list of the path between two nodes
     */
    private List<T> pathHelper(LinkedList<T> list, BSTNode<T> node, T data1, T data2) {
        if (node != null) {
            int data1Comp = data1.compareTo(node.getData());
            int data2Comp = data2.compareTo(node.getData());
            if (data1Comp == 0 && data2Comp == 0) {
                list.add(node.getData());
                return list;
            } else if (data1Comp < 0 && data2Comp < 0) {
                pathHelper(list, node.getLeft(), data1, data2);
            } else if (data1Comp > 0 && data2Comp > 0) {
                pathHelper(list, node.getRight(), data1, data2);
            } else if (data1Comp != 0 && data2Comp == 0) {
                traversalHelper1(list, data1, node);
                list.addLast(data2);
            } else if (data1Comp == 0 && data2Comp != 0) {
                list.addFirst(data1);
                traversalHelper2(list, data2, node);
            } else if (data1Comp != 0 && data2Comp != 0) {
                list.add(node.getData());
                //traverse to data1
                traversalHelper1(list, data1, node);
                //traverse to data2
                traversalHelper2(list, data2, node);
            }
            return list;
        } else {
            throw new java.util.NoSuchElementException("One or both of the data were missing from the BST.");
        }
    }

    /**
     *recursive helper method to add the path of the first data to the deepest common ancestor to the list
     * @param list list to which the path of nodes will be added
     * @param data the data being traced
     * @param node current node being examined
     */
    private void traversalHelper1(LinkedList<T> list, T data, BSTNode<T> node) {
        if (node == null) {
            throw new java.util.NoSuchElementException("One or both of the data were missing from the BST.");
        }
        if (data.compareTo(node.getData()) < 0) {
            if (node.getLeft() != null) {
                list.addFirst(node.getLeft().getData());
            }
            traversalHelper1(list, data, node.getLeft());
        } else if (data.compareTo(node.getData()) > 0) {
            if (node.getRight() != null) {
                list.addFirst(node.getRight().getData());
            }
            traversalHelper1(list, data, node.getRight());
        }
    }

    /**
     *recursive helper method to add the path of the second data to the deepest common ancestor to the list
     * @param list list to which the path of nodes will be added
     * @param data data being traced
     * @param node current node being examined
     */
    private void traversalHelper2(LinkedList<T> list, T data, BSTNode<T> node) {
        if (node == null) {
            throw new java.util.NoSuchElementException("One or both of the data was missing from the BST.");
        }
        if (data.compareTo(node.getData()) < 0) {
            if (node.getLeft() != null) {
                list.addLast(node.getLeft().getData());
            }
            traversalHelper2(list, data, node.getLeft());
        } else if (data.compareTo(node.getData()) > 0) {
            if (node.getRight() != null) {
                list.addLast(node.getRight().getData());
            }
            traversalHelper2(list, data, node.getRight());
        }
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
