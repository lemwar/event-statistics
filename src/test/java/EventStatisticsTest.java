import eventstatistics.EventStatistics;
import eventstatistics.IEventStatistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.TimeConstants;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class EventStatisticsTest {
    private IEventStatistics eventStatistics;
    private long currentTime;

    @BeforeEach
    void createTestObject() {
        eventStatistics = new EventStatistics();
        currentTime = System.currentTimeMillis();
    }

    @Test
    void isEmptyWhenCreatedTest() {
        assertEquals(0, eventStatistics.getEventCountInLastDay());
    }

    @Test
    void addEventWithTheSameTimeTest() {
        eventStatistics.addEvent(currentTime);
        eventStatistics.addEvent(currentTime);
        assertEquals(2, eventStatistics.getEventCountInLastDay());
    }

    @Test
    void addEventInMinuteTest() {
        eventStatistics.addEvent(currentTime);
        eventStatistics.addEvent(currentTime - (TimeConstants.MILLISECONDS_IN_MINUTE / 2L));
        assertEquals(2, eventStatistics.getEventCountInLastMinute());
    }

    @Test
    void addEventMoreThanMinuteTest() {
        eventStatistics.addEvent(currentTime -
                (TimeConstants.MILLISECONDS_IN_MINUTE + TimeConstants.MILLISECONDS_IN_SECOND));
        assertEquals(0, eventStatistics.getEventCountInLastMinute());
    }

    @Test
    void addEventInHourTest() {
        eventStatistics.addEvent(System.currentTimeMillis() - (TimeConstants.MILLISECONDS_IN_HOUR / 2L));
        assertEquals(1L, eventStatistics.getEventCountInLastHour());
    }

    @Test
    void addEventMoreThanHourTest() {
        eventStatistics.addEvent(System.currentTimeMillis() -
                (TimeConstants.MILLISECONDS_IN_HOUR + TimeConstants.MILLISECONDS_IN_SECOND));
        assertEquals(0L, eventStatistics.getEventCountInLastHour());
    }

    @Test
    void addEventInDayTest() {
        eventStatistics.addEvent(System.currentTimeMillis() - (TimeConstants.MILLISECONDS_IN_DAY / 2L));
        assertEquals(1L, eventStatistics.getEventCountInLastDay());
    }

    @Test
    void addEventMoreThanDayTest() {
        eventStatistics.addEvent(System.currentTimeMillis() -
                (TimeConstants.MILLISECONDS_IN_DAY + TimeConstants.MILLISECONDS_IN_SECOND));
        assertEquals(0L, eventStatistics.getEventCountInLastDay());
    }

    @Test
    void addEventMultiTreadAsync() throws InterruptedException {
        int eventNum = 10_000_000;
        int threadNum = 10;

        ExecutorService threadPool = Executors.newFixedThreadPool(threadNum);

        for (int i = 0; i < eventNum; i++) {
            threadPool.submit(() -> eventStatistics.addEvent(System.currentTimeMillis()));
        }

        threadPool.shutdown();
        boolean done = threadPool.awaitTermination(1, TimeUnit.MINUTES);
        assertTrue(done);
        assertEquals(eventNum, eventStatistics.getEventCountInLastMinute());
    }
}
