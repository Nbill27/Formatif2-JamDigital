public class ClockArray {
    private Time[] clocks;

    public ClockArray(int size) {
        clocks = new Time[size];
    }

    public void setTime(int index, int hours, int minutes, int seconds) {
        if (index >= 0 && index < clocks.length) {
            clocks[index] = new Time(hours, minutes, seconds);
        }
    }

    public Time getTime(int index) {
        if (index >= 0 && index < clocks.length) {
            return clocks[index];
        }
        return null;
    }

    public int getSize() {
        return clocks.length;
    }
}
