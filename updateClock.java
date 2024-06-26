public class updateClock {
    public static void main(String[] args) {
        DigitalClock clock = new DigitalClock(14, 30, 45);
        clock.setFormat("12-hour");

        DigitalClockGUI gui = new DigitalClockGUI(clock);
        gui.setVisible(true);
    }
}
