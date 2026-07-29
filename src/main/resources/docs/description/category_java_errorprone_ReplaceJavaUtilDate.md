Since: PMD 7.16.0

The legacy `java.util.Date` class is mutable, not thread-safe, and has a confusing API. Many of its methods
are deprecated, it doesn't handle timezones properly, and it represents both date and time even when only
one is needed. The constructor parameters are particularly error-prone: year is &quot;years since 1900&quot; and
month is 0-based (January = 0). The modern java.time API (introduced in Java 8) provides better type safety,
immutability, and clearer semantics.

Use `LocalDate` (date only), `LocalTime` (time only), `LocalDateTime` (date and time), `Instant` (timestamp),
or `ZonedDateTime` (date-time with timezone) from `java.time` package instead.

Note: This includes subtypes such as `java.sql.Date`, `java.sql.Time` and `java.sql.Timestamp`.
Since JPA 3.2 (Jakarta Persistence) usage of `java.util.Date` and `java.util.Calendar` and others is
deprecated there as well in favour of `java.time` API.

Example(s):
```
import java.util.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class Foo {
    void problematic() {
        // Problematic - using legacy Date API
        Date now = new Date();
        Date epoch = new Date(0L);
        Date custom = new Date(124, 0, 15); // Deprecated constructor: year=1900+124=2024, month=0=January
        
        // Mutable operations are error-prone
        now.setTime(System.currentTimeMillis());
    }
    
    void preferred() {
        // Preferred - using modern java.time API
        Instant now = Instant.now(); // For timestamps
        LocalDate today = LocalDate.now(); // For date only
        LocalDateTime dateTime = LocalDateTime.now(); // For date and time
        ZonedDateTime zonedDateTime = ZonedDateTime.now(); // With timezone
        
        // Immutable operations are safer
        LocalDate tomorrow = today.plusDays(1);
        LocalDateTime nextHour = dateTime.plusHours(1);
    }
}
```
