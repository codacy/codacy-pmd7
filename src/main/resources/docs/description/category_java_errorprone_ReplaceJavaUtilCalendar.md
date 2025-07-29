Since: PMD 7.16.0

The legacy java.util.Calendar API is error-prone, mutable, and not thread-safe. It has confusing month indexing 
(January = 0), inconsistent field semantics, and verbose usage patterns. The modern java.time API (introduced in Java 8) 
provides immutable, thread-safe alternatives with clear, intuitive methods.

Use LocalDate (for date-only operations), LocalDateTime (for date and time), or ZonedDateTime (when timezone is important) from java.time package instead.

Example(s):
```
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class Foo {
    void problematic() {
        // Problematic - using legacy Calendar API
        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.JANUARY, 15); // Month indexing is confusing (0-based)
        cal.add(Calendar.DAY_OF_MONTH, 7);
        
        Calendar specific = new GregorianCalendar(2024, 0, 15); // Also problematic
    }
    
    void preferred() {
        // Preferred - using modern java.time API
        LocalDate date = LocalDate.of(2024, 1, 15); // Month indexing is intuitive (1-based)
        LocalDate weekLater = date.plusDays(7);
        
        LocalDateTime dateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
    }
}
```
