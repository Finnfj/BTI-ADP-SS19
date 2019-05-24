package binarySearchTree;

public class BinarySearchTreeArrayEmbedding implements BinarySearchTreeI {
    private NodeArrayEmbedding[] nodes;
    private int size;

    public BinarySearchTreeArrayEmbedding(int grade) { // grade is also the min amount of elements the array will support
        size = 0;

        int gradeSize = 1; // amount of nodes that are only of the current grade (grade 1 has 1, grade 2 has 2, grade 3 has 4 and so on)
        int maxSize = 0; // amounf of nodes in general
        for (int i = 0; i < grade; i++) {
            maxSize += gradeSize;
            gradeSize *= 2; // grade size always increases by two
        }
        nodes = new NodeArrayEmbedding[maxSize+1]; // + 1 because the zeroth place is empty
    }

    @Override
    public NodeArrayEmbedding addData(Integer data) {
        if(size < nodes.length - 1) { // since the zeroth place is empty, length is actually one too large
            if (nodes[1] != null) { // check if we already have a root element
                NodeArrayEmbedding newNode = new NodeArrayEmbedding(data, nodes);
                size++;
                return nodes[nodes[1].insert(newNode)];
            } else {
                nodes[1] = new NodeArrayEmbedding(data, nodes, 1);
                size++;
                return nodes[1];
            }
        }
        return null;
    }

    @Override
    public void printTree(PrintVariant pv) {
        if(nodes[1] != null) {
            nodes[1].printData(pv);
            System.out.println();
        }
    }
}
