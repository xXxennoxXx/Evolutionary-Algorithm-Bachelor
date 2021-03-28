package utils;

public class Timer {

    private Long startTime;

    public Timer() {
        startTime = System.currentTimeMillis();
    }

    public Long getTime() {
        return System.currentTimeMillis() - startTime;
    }
}
