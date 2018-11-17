package eventstatistics;


/**
 * The library for collecting statistics on different events
 */
public interface IEventStatistics {

    /**
     * Register a new event
     *
     * @param eventTime event time
     */
    void addEvent(long eventTime);

    /**
     * Get the number of events for the last minute
     *
     * @return the number of events
     */
    long getEventCountInLastMinute();

    /**
     * Get the number of events for the last hour
     *
     * @return the number of events
     */
    long getEventCountInLastHour();

    /**
     * Get the number of events for the last day (24 hours)
     *
     * @return the number of events
     */
    long getEventCountInLastDay();


}
