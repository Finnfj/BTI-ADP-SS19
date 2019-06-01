package binarySearchTree;

public interface BinarySearchTreeI {
    NodeI addData(Integer data);
    void printTree(PrintVariant pv);
    void updateSum();
    long getSum(int m, int M);
}
