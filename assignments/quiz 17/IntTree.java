
// Simple binary tree class that includes methods to construct a
// tree of ints, to print the structure, and to print the data
// using a preorder, inorder or postorder traversal. The trees
// built have nodes numbered starting with 1 and numbered
// sequentially level by level with no gaps in the tree. The
// documentation refers to these as "sequential trees."
//
// from buildingjavaprograms.com

import java.util.*;
/*
 *
 * Name: Thomas Kent
 * Date: 10 March 2017
 * Course: CS 211
 *
 * IntTree class
 * Chapter 17 quiz
 * slightStutter() - semi-stutters the tree by copying each root node into its left child, OR right child, OR neither, in that order of availability
 * slightStutter(IntTreeNode root) - recursive helper for above
 *
 */
public class IntTree {
    private IntTreeNode overallRoot;

    // pre : max > 0
    // post: constructs a sequential tree with given number of
    // nodes
    public IntTree(int max) {
        if (max <= 0) {
            throw new IllegalArgumentException("max: " + max);
        }
        overallRoot = buildTree(1, max);
    }

    public IntTree() {
        overallRoot = null;
    }

    // constructor added so we can build page 1029 reference trees
    public IntTree(IntTreeNode given) {
        overallRoot = given;
    }

    // QUIZ CODE STARTS

    // sort of stutters the tree
    public void slightStutter() {
        slightStutter(this.overallRoot);
    }

    // recursive helper
    private void slightStutter(IntTreeNode root) {
        if (root.left == null && root.right == null)
            // if this node has no children, copy it into the left child and stop
            root.left = new IntTreeNode(root.data);
        else if (root.left != null && root.right == null) {
            // if this node has a right child but no left child, copy it into the left child and recurse into the right child
            root.right = new IntTreeNode(root.data);
            slightStutter(root.left);
        } else if (root.left == null && root.right != null) {
            // if this node has a left child but no right child, copy it into the right child and recurse into the left child
            root.left = new IntTreeNode(root.data);
            slightStutter(root.right);
        } else {
            // if this node has both children, copy nothing and recurse into both of them
            slightStutter(root.left);
            slightStutter(root.right);
        }
    }

    // QUIZ CODE ENDS

    // Exercise #7, Chapter 17
    public boolean isFull() {
        return (overallRoot == null || isFull(overallRoot));
    }

    private boolean isFull(IntTreeNode root) {
        if (root.left == null && root.right == null) {
            return true;
        } else {
            return (root.left != null && root.right != null && isFull(root.left) && isFull(root.right));
        }
    }

    // post: returns a sequential tree with n as its root unless
    // n is greater than max, in which case it returns an
    // empty tree
    private IntTreeNode buildTree(int n, int max) {
        if (n > max) {
            return null;
        } else {
            return new IntTreeNode(n, buildTree(2 * n, max), buildTree(2 * n + 1, max));
        }
    }

    // post: prints the tree contents using a preorder traversal
    public void printPreorder() {
        System.out.print("preorder:");
        printPreorder(overallRoot);
        System.out.println();
    }

    // post: prints the tree contents using a preorder traversal
    // post: prints in preorder the tree with given root
    private void printPreorder(IntTreeNode root) {
        if (root != null) {
            System.out.print(" " + root.data);
            printPreorder(root.left);
            printPreorder(root.right);
        }
    }

    // post: prints the tree contents using a inorder traversal
    public void printInorder() {
        System.out.print("inorder:");
        printInorder(overallRoot);
        System.out.println();
    }

    // post: prints in inorder the tree with given root
    private void printInorder(IntTreeNode root) {
        if (root != null) {
            printInorder(root.left);
            System.out.print(" " + root.data);
            printInorder(root.right);
        }
    }

    // post: prints the tree contents using a postorder traversal
    public void printPostorder() {
        System.out.print("postorder:");
        printPostorder(overallRoot);
        System.out.println();
    }

    // post: prints in postorder the tree with given root
    private void printPostorder(IntTreeNode root) {
        if (root != null) {
            printPostorder(root.left);
            printPostorder(root.right);
            System.out.print(" " + root.data);
        }
    }

    // post: prints the tree contents, one per line, following an
    // inorder traversal and using indentation to indicate
    // node depth; prints right to left so that it looks
    // correct when the output is rotated.
    public void printSideways() {
        printSideways(overallRoot, 0);
    }

    // post: prints in reversed preorder the tree with given
    // root, indenting each line to the given level
    private void printSideways(IntTreeNode root, int level) {
        if (root != null) {
            printSideways(root.right, level + 1);
            for (int i = 0; i < level; i++) {
                System.out.print("    ");
            }
            System.out.println(root.data);
            printSideways(root.left, level + 1);
        }
    }
}