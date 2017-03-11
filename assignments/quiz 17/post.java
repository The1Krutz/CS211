
public class post {
    public static void main(String[] args) {
        // Code to post with Quiz17
        // IntTree tree = new IntTree(new IntTreeNode(9, new IntTreeNode(8), new IntTreeNode(7)));
        IntTree tree = new IntTree(
            new IntTreeNode(
                9, 
                new IntTreeNode(8,
                    null,
                    new IntTreeNode(3)), 
                new IntTreeNode(7,
                    new IntTreeNode(5),
                    null)
                ));






        tree.printSideways();
        tree.slightStutter();
        System.out.println("---------------------------------");
        tree.printSideways();

    }
}