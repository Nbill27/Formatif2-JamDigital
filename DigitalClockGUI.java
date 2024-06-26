import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DigitalClockGUI extends JFrame {
    private DigitalClock clock;
    private JLabel timeLabel;
    private JLabel dateLabel;
    private JButton switchThemeButton;
    private JButton setAlarmButton;
    private JButton switchFormatButton;
    private boolean isDarkTheme;
    private boolean is12HourFormat;
    private Timer timer;
    private String alarmTime;

    public DigitalClockGUI(DigitalClock clock) {
        this.clock = clock;
        this.isDarkTheme = false;
        this.is12HourFormat = true;
        this.alarmTime = "";

        setTitle("Digital Clock");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        timeLabel = new JLabel(clock.getTime(), SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 40));
        add(timeLabel, BorderLayout.CENTER);

        dateLabel = new JLabel(getCurrentDate(), SwingConstants.CENTER);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        add(dateLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        switchThemeButton = new JButton("Switch Theme");
        switchThemeButton.addActionListener(e -> switchTheme());
        buttonPanel.add(switchThemeButton);

        setAlarmButton = new JButton("Set Alarm");
        setAlarmButton.addActionListener(e -> setAlarm());
        buttonPanel.add(setAlarmButton);

        switchFormatButton = new JButton("Switch Format");
        switchFormatButton.addActionListener(e -> switchFormat());
        buttonPanel.add(switchFormatButton);

        add(buttonPanel, BorderLayout.SOUTH);

        timer = new Timer(1000, e -> updateClock());
        timer.start();
    }

    private void updateClock() {
        clock.setSeconds(clock.getSeconds() + 1);
        if (clock.getSeconds() >= 60) {
            clock.setSeconds(0);
            clock.setMinutes(clock.getMinutes() + 1);
            if (clock.getMinutes() >= 60) {
                clock.setMinutes(0);
                clock.setHours(clock.getHours() + 1);
                if (clock.getHours() >= 24) {
                    clock.setHours(0);
                }
            }
        }

        if (is12HourFormat) {
            clock.setFormat("12-hour");
        } else {
            clock.setFormat("24-hour");
        }

        String currentTime = clock.getTime();
        timeLabel.setText(currentTime);

        // Check if alarm time matches current time
        if (!alarmTime.isEmpty() && alarmTime.equals(currentTime)) {
            JOptionPane.showMessageDialog(this, "Alarm! Time is " + alarmTime, "Alarm", JOptionPane.INFORMATION_MESSAGE);
            alarmTime = ""; // Reset alarm
        }
    }

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy");
        return dateFormat.format(Calendar.getInstance().getTime());
    }

    private void switchTheme() {
        if (isDarkTheme) {
            getContentPane().setBackground(Color.WHITE);
            timeLabel.setForeground(Color.BLACK);
            dateLabel.setForeground(Color.BLACK);
        } else {
            getContentPane().setBackground(Color.BLACK);
            timeLabel.setForeground(Color.WHITE);
            dateLabel.setForeground(Color.WHITE);
        }
        isDarkTheme = !isDarkTheme;
    }

    private void setAlarm() {
        String time = JOptionPane.showInputDialog(this, "Enter alarm time (HH:mm:ss)", "Set Alarm", JOptionPane.PLAIN_MESSAGE);
        if (time != null && !time.isEmpty()) {
            alarmTime = time;
            JOptionPane.showMessageDialog(this, "Alarm set for " + alarmTime, "Alarm Set", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void switchFormat() {
        is12HourFormat = !is12HourFormat;
        if (is12HourFormat) {
            clock.setFormat("12-hour");
        } else {
            clock.setFormat("24-hour");
        }
    }

    public static void main(String[] args) {
        DigitalClock clock = new DigitalClock(14, 30, 45);
        clock.setFormat("12-hour");

        SwingUtilities.invokeLater(() -> {
            new DigitalClockGUI(clock).setVisible(true);
        });
    }
}
