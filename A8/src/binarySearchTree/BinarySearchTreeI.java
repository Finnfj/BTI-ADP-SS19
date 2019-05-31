package binarySearchTree;

public interface BinarySearchTreeI {
    NodeI addData(Integer data);
    void printTree(PrintVariant pv);
    void updateSum();
    int getSum(int m, int M);
}
