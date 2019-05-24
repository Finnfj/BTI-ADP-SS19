package binarySearchTree;

public class BinarySerachTreeNodeLinking implements BinarySearchTreeI {
    NodeNodeLinking root;

    @Override
    public NodeNodeLinking addData(Object data) {
        NodeNodeLinking newNodeNodeLinking = new NodeNodeLinking(data);
        if(root != null) {
            return root.insert(newNodeNodeLinking);
        } else {
            root = newNodeNodeLinking;
            return root;
        }
    }

    @Override
    public void printTree(PrintVariant pv) {
        if(root != null) {
            root.printData(pv);
            System.out.println();
        }
    }
}
