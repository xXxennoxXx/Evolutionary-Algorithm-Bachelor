package application;

import java.util.GregorianCalendar;

public class Timer {
    final long start;

    public Timer() {
        GregorianCalendar cal = new GregorianCalendar();
        start = cal.getTimeInMillis();
    }

    public long getTime() {
        GregorianCalendar cal = new GregorianCalendar();
        return cal.getTimeInMillis() - start;
    }
}
