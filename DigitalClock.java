public class DigitalClock extends Time {
    private String format;

    public DigitalClock(int hours, int minutes, int seconds) {
        super(hours, minutes, seconds);
        this.format = "24-hour"; // Default format is 24-hour
    }

    public String getTime() {
        if (format.equals("12-hour")) {
            return String.format("%02d:%02d:%02d %s",
                    (getHours() == 0 || getHours() == 12) ? 12 : getHours() % 12,
                    getMinutes(), getSeconds(),
                    getHours() < 12 ? "AM" : "PM");
        } else {
            return String.format("%02d:%02d:%02d", getHours(), getMinutes(), getSeconds());
        }
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
