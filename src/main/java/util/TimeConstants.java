package util;

import java.util.concurrent.TimeUnit;


public class TimeConstants {

    public static final long MILLISECONDS_IN_SECOND = TimeUnit.SECONDS.toMillis(1L);
    public static final long MILLISECONDS_IN_MINUTE = TimeUnit.MINUTES.toMillis(1L);
    public static final long MILLISECONDS_IN_HOUR = TimeUnit.HOURS.toMillis(1L);
    public static final long MILLISECONDS_IN_DAY = TimeUnit.DAYS.toMillis(1L);

    private TimeConstants() {}
}
