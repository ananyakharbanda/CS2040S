public class Guessing {

    // Your local variables here
    private int low = 0;
    private int high = 1000;
    private int mid = 500;

    /**
     * Implement how your algorithm should make a guess here
     */
    public int guess() {
        this.mid = (this.low + this.high) / 2; 
        return mid;
    }

    /**
     * Implement how your algorithm should update its guess here
     */
    public void update(int answer) {
        if (answer == -1) {
            this.low = this.mid + 1;
        } else if (answer == 1) {
            this.high = this.mid;
        }
    }
}
