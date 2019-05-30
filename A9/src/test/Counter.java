package test;

public class Counter {
    private long count;

    public Counter() {
        count = 0;
    }

    public void increaseCount() {
        count++;
    }

    public void resetCount() {
        count = 0;
    }

    public long getCount() {
        return count;
    }
}
