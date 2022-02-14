/**
 * implementation of counter.
 */
public class Counter {
    // current count
    private int currentCount = 0;

    /**
     * add number to current count.
     * @param number the number that is being added.
     */
    void increase(int number) {
        currentCount = currentCount + number;
    }
    /**
     * subtract number from current count.
     * @param number the number that is being subtract.
     */
    void decrease(int number) {
        currentCount = currentCount - number;
    }
    /**
     * getter of current count.
     * @return the current count.
     */
    int getValue() {
        return currentCount;
    }
}