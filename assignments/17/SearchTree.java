/*
 *
 * Name: Thomas Kent
 * Date: 09 March 2017
 * Course: CS 211
 *
 * SearchTree<E> class
 * Implements 3 solutions from the book and 1 from the ppt
 * 
 * A. Exercise #7 isFull(); - checks if every node has either 0 or 2 children
 * B. Exercise #9 equals(t2); - checks two trees for equality
 * C. Exercise #12 removeLeaves(); - removes all leaf nodes
 * D. see PowerPoint remove(data); - removes a single node
 * 
 */

// Class SearchTree stores and prints a binary search tree of
// objects of type E. E must implement the Comparable<E>
// interface. from Reges and Stepp, BJP 3ed.
// modified by W.P. Iverson, to not allow duplicates added
// Bellevue College, November 2015

public class SearchTree<E extends Comparable<E>> {
    private SearchTreeNode<E> overallRoot; // root of overall tree

    // post: constructs an empty search tree
    public SearchTree() {
        overallRoot = null;
    }

    // WRITE ADDITIONAL METHODS HERE:

    // Exercise 7 - isFull() - returns true if the binary tree is considered full (every node has exactly 0 or 2 children)
    public boolean isFull() {
        return isFull(this.overallRoot);
    }

    // recursive helper for isFull
    private boolean isFull(SearchTreeNode<E> root) {
        if (root == null)
            // if this node doesn't exist, it is autmoatically full
            return true;
        if (root.left != null ^ root.right != null)
            // only has left or right but not both, so is not full
            return false;

        // either both of these will be null (0 children, full) or both will exist (recursive check)
        return isFull(root.left) && isFull(root.right);
    }

    // Exercise 9 - equals(t2) - compares two trees for equality
    public boolean equals(SearchTree<E> t2) {
        return equals(this.overallRoot, t2.overallRoot);
    }

    // recursive helper for equals
    private boolean equals(SearchTreeNode<E> root, SearchTreeNode<E> other) {
        if (root == null && other == null)
            // if both nodes are null, they are equal
            return true;
        if (root.left != null ^ other.left != null)
            // if one node has a left child and the other doesn't, can't be equal
            return false;
        if (root.right != null ^ other.right != null)
            // if one node has a right child and the other doesn't, can't be equal
            return false;
        if (root.data.compareTo(other.data) != 0)
            // data on these nodes is not equal, so checking the children is not needed
            return false;

        // by this point, both nodes exist, have the same data, and have matching (but not necessarily equal) child nodes
        return equals(root.left, other.left) && equals(root.right, other.right);
    }

    // Exercise 12 - removeLeaves() - removes all leaf nodes from the tree
    public void removeLeaves() {
        removeLeaves(this.overallRoot);
    }

    // recursive helper for removeLeaves
    private void removeLeaves(SearchTreeNode<E> root) {
        if (root == null)
            // if this node doesn't exist, just stop
            return;

        if (root.left != null)
            // if the left child exists, check its children
            if (root.left.left == null && root.left.right == null)
                // if the left side has no children, remove it
                root.left = null;
            else
                // if at least one child exists, recursively check that nodes leaves
                removeLeaves(root.left);

        if (root.right != null)
            // if the right child exists, check its children
            if (root.right.left == null && root.right.right == null)
                // if the right side has no children, remove it
                root.right = null;
            else
                // if at least one child exists, recursively check that nodes leaves
                removeLeaves(root.right);
    }

    // ppt - remove(E data) - removes a given element from the tree
    public void remove(E data) {
        if (contains(data))
            // if the tree contains the node, look up its location and remove it
            this.overallRoot = remove(this.overallRoot, data);
    }

    // recursive helper for remove
    private SearchTreeNode<E> remove(SearchTreeNode<E> root, E data) {
        if (root == null)
            // if this node is null, stop recursing
            return root;

        if (root.data.compareTo(data) > 0)
            // if this node is 'greater' than the value to be removed, recurse down the left side
            root.left = remove(root.left, data);
        else if (root.data.compareTo(data) < 0)
            // if this node is 'less' than the value to be removed, recurse down the right side
            root.right = remove(root.right, data);
        else {
            if (root.left == null)
                // no left, promote right (possibly null)
                return root.right;
            else if (root.right == null)
                // no right, promote left (definitely null)
                return root.left;
            // if the above results in null, this node is a leaf.
            // because the recursive call sets the value to this return, returning the null will remove the leaf node from the tree

            // by this point, the node to be removed has two children.
            // So find the in-order successor (min value to the right) to use to replace this
            SearchTreeNode<E> successor = root.right;
            while (successor.left != null) {
                successor = successor.left;
            }

            root.data = successor.data;

            // delete the successor
            root.right = remove(root.right, root.data);
        }

        return root;
    }

    // ASSIGNMENT CODE ENDS

    // post: value added to tree so as to preserve binary search tree
    public void add(E value) {
        overallRoot = add(overallRoot, value);
    }

    // post: value added to tree so as to preserve binary search tree
    private SearchTreeNode<E> add(SearchTreeNode<E> root, E value) {
        if (root == null) {
            root = new SearchTreeNode<E>(value);
        } else if (root.data.compareTo(value) > 0) {
            root.left = add(root.left, value);
        } else if (root.data.compareTo(value) < 0) {
            root.right = add(root.right, value);
        }
        return root;
    }

    // post: returns true if tree contains value, returns false otherwise
    public boolean contains(E value) {
        return contains(overallRoot, value);
    }

    // post: returns true if given tree contains value, returns false otherwise
    private boolean contains(SearchTreeNode<E> root, E value) {
        if (root == null) {
            return false;
        } else {
            int compare = value.compareTo(root.data);
            if (compare == 0) {
                return true;
            } else if (compare < 0) {
                return contains(root.left, value);
            } else { // compare > 0
                return contains(root.right, value);
            }
        }
    }

    // post: prints the data of the tree, one per line
    public void print() {
        printInorder(overallRoot);
        System.out.println();
    }

    // post: prints the data of the tree using an inorder traversal
    private void printInorder(SearchTreeNode<E> root) {
        if (root != null) {
            printInorder(root.left);
            System.out.print(root.data + ", ");
            printInorder(root.right);
        }
    }

    private static class SearchTreeNode<E> {
        public E data; // data stored in this node
        public SearchTreeNode<E> left; // left subtree
        public SearchTreeNode<E> right; // right subtree

        // post: constructs a leaf node with given data
        public SearchTreeNode(E data) {
            this(data, null, null);
        }

        // post: constructs a node with the given data and links
        public SearchTreeNode(E data, SearchTreeNode<E> left, SearchTreeNode<E> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
}
