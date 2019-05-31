package binarySearchTree;

public class BinarySearchTreeNodeLinking implements BinarySearchTreeI {
    NodeNodeLinking root;

    @Override
    public NodeNodeLinking addData(Integer data) {
        NodeNodeLinking newNodeNodeLinking = new NodeNodeLinking(data);
        if(root != null) {
            return root.insert(newNodeNodeLinking);
        } else {
            root = newNodeNodeLinking;
            root.updatebSum();
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

	@Override
	public void updateSum() {
		root.updateSum();
	}

	@Override
	public int getSum(int m, int M) {
		NodeNodeLinking leftnode = root.findClosest(true, m);
		NodeNodeLinking rightnode = root.findClosest(false, M);
		int leftval = leftnode.getSum() - leftnode.getKey();
		int rightval = rightnode.getSum();
		return rightval - leftval;
	}
}
