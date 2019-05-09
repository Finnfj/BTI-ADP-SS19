package binarySearchTree;

public class BinarySerachTreeNodeLinking implements BinarySearchTreeI {
    Node root;

    @Override
    public Node addData(Object data) {
        Node newNode = new Node(data);
        if(root != null) {
            return root.insert(newNode);
        } else {
            root = newNode;
            return root;
        }
    }

    @Override
    public void printTree(PrintVariant pv) {
        switch (pv) {
            case INORDER:

                break;
            case POSTORDER:
                break;
            case PREORDER:
                break;
        }
    }
}
