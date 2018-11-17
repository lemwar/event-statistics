package eventstatistics;

import util.TimeConstants;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


public class EventStatistics implements IEventStatistics {

    private Map<Long, Long> events;

    public EventStatistics() {
        events = new ConcurrentHashMap<>();
    }

    public void addEvent(long eventTime) {
        events.compute(eventTime, (time, count) -> count == null ? 1 : ++count);
    }

    public long getEventCountInLastMinute() {
        long currentTime = System.currentTimeMillis();
        long startTime = currentTime - TimeConstants.MILLISECONDS_IN_MINUTE;
        return getEventCountInTimePeriod(startTime, currentTime);
    }

    public long getEventCountInLastHour() {
        long currentTime = System.currentTimeMillis();
        long startTime = currentTime - TimeConstants.MILLISECONDS_IN_HOUR;
        return getEventCountInTimePeriod(startTime, currentTime);
    }

    public long getEventCountInLastDay() {
        long currentTime = System.currentTimeMillis();
        long startTime = currentTime - TimeConstants.MILLISECONDS_IN_DAY;
        return getEventCountInTimePeriod(startTime, currentTime);
    }

    /**
     * Get the number of events for the specified period of time
     *
     * @param startTime period start time
     * @param endTime period end time
     * @return the number of events
     */
    private long getEventCountInTimePeriod(long startTime, long endTime) {
        AtomicLong countForTimePeriod = new AtomicLong();
        events.forEach((time, count) -> {
            if (time >= startTime && time <= endTime) {
                countForTimePeriod.addAndGet(count);
            }
        });
        return countForTimePeriod.get();
    }
}
