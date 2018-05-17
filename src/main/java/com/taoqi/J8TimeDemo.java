package com.taoqi;

import java.sql.Date;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.TimeZone;

import static java.time.temporal.TemporalAdjusters.*;

/**
 * Created by TQ-G153 on 2018/2/11.
 */
public class J8TimeDemo {

    public static void main(String[] args) throws Exception {
        //getToday();
        //customDate();
        // getSpecifiedDate();
        // birthday();
        // localTime();
        // clock();
        // compareDate();
        // zonedDateTime();
        // yearMonth();
        // leapYear();
        // period();
        // instant();
        // dateTimeFormatter1();
        // dateTimeFormatter2();
        // dateTimeFormatter3();
        // duration();
        temporalAdjuster();
    }

    public static void temporalAdjuster() {
        LocalDate localDate = LocalDate.now();
        LocalDate date0 = localDate.with(dayOfWeekInMonth(1, DayOfWeek.FRIDAY));// 转换成本月的第1个周五
        LocalDate date = localDate.with(dayOfWeekInMonth(2, DayOfWeek.FRIDAY));// 转换成本月的第1个周五
        LocalDate date1 = localDate.with(firstDayOfMonth());// 转换成本月的第一天
        System.out.println(localDate.with(firstDayOfNextMonth()));
        System.out.println(localDate.with(firstDayOfNextYear()));
        System.out.println(localDate.with(firstDayOfYear()));
        System.out.println(localDate.with(firstInMonth(DayOfWeek.FRIDAY)));
        System.out.println(localDate.with(firstInMonth(DayOfWeek.FRIDAY)));
        System.out.println(localDate.with(lastInMonth(DayOfWeek.FRIDAY)));
        System.out.println(localDate.with(next(DayOfWeek.FRIDAY)));
        System.out.println(localDate.with(previous(DayOfWeek.FRIDAY)));
        System.out.println(localDate);
        System.out.println(localDate.with(nextOrSame(DayOfWeek.FRIDAY)));
        System.out.println(localDate.with(previousOrSame(DayOfWeek.FRIDAY)));
    }

    public static Period period() {
        Instant instant = Instant.now();
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        Period between = Period.between(localDate, LocalDate.of(2018, 6, 18));
        System.out.println(LocalDate.of(2018, 6, 18).toEpochDay() - localDate.toEpochDay());
        return between;
    }

    public static Duration duration() {
        Instant instant = Instant.now();
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        Duration duration = Duration.between(instant, instant);
        System.out.println(duration.getNano());
        // Duration.between(localDate, localDate); // lead to exception
        System.out.println(Duration.between(localTime, localTime).getNano());
        System.out.println(Duration.between(localDateTime, localDateTime).getNano());
        System.out.println(Duration.between(localTime, localDateTime).getNano());
        // Duration.between(localDateTime, instant);// lead to exception
        return duration;
    }

    // get time from str
    public static void dateTimeFormatter3() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateTimeStr = "2018-02-11";
        System.out.println(LocalDate.parse(dateTimeStr, formatter));
    }

    // parse time to str by custom formatter
    public static void dateTimeFormatter2() {
        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(now));
    }

    // parse to,e tp str by default BASIC_ISO_DATE formatter
    public static void dateTimeFormatter1() {
        String dayAfterTomorrow = "20140116";
        LocalDate formatted = LocalDate.parse(dayAfterTomorrow,
                DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(formatted);
    }

    public static void instant() {
        Instant instant = Instant.now();
        System.out.println(instant);
        System.out.println(Date.from(instant));
    }

    public static void leapYear() {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.isLeapYear());
    }

    public static void yearMonth() {
        YearMonth currentYearMonth = YearMonth.now();
        System.out.printf("Days in month year %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth());
        YearMonth creditCardExpiry = YearMonth.of(2018, Month.FEBRUARY);
        System.out.printf("Your credit card expires on %s %n", creditCardExpiry);
    }

    public static void zonedDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        System.out.println(ZonedDateTime.of(localDateTime, TimeZone.getTimeZone("CST").toZoneId()));
        System.out.println(ZonedDateTime.of(localDateTime, TimeZone.getTimeZone("CTT").toZoneId()));
    }

    public static void compareDate() {
        LocalDate earlier = LocalDate.now().minusDays(1);
        LocalDate later = LocalDate.now();
        System.out.println(earlier.isBefore(later));
        System.out.println(earlier.isAfter(later));
    }

    public static void clock() {
        Clock clock = Clock.systemUTC();
        Clock clock1 = Clock.systemDefaultZone();
        System.out.println(clock);
        System.out.println(clock1);
    }

    public static LocalTime localTime() {
        LocalTime now = LocalTime.now();
        System.out.println(now);
        now.plusHours(1);
        System.out.println(now.plusHours(1));
        return now;
    }

    public static MonthDay birthday() {
        LocalDate dateOfBirth = LocalDate.of(1997, 2, 11);
        MonthDay birthday = MonthDay.of(dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());
        MonthDay monthDay = MonthDay.now();
        System.out.println(birthday.equals(monthDay));
        return monthDay;
    }

    public static LocalDate getSpecifiedDate() {
        LocalDate specifiedDay = LocalDate.of(2018, 2, 11);
        System.out.println(specifiedDay);
        System.out.println(specifiedDay.plus(1, ChronoUnit.WEEKS));
        return specifiedDay;
    }

    public static LocalDate getToday() {
        LocalDate today = LocalDate.now();
        System.out.println(today);
        System.out.println(today.getYear());
        System.out.println(today.getMonth());
        System.out.println(today.getMonthValue());
        System.out.println(today.getDayOfMonth());
        return today;
    }

    public static LocalDate customDate() {
        LocalDate localDate = LocalDate.of(2014, 3, 18);
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonth());
        System.out.println(localDate.getMonthValue());
        System.out.println(localDate.getDayOfMonth());
        System.out.println(localDate.getDayOfWeek());
        System.out.println(localDate.getDayOfYear());
        System.out.println(localDate.lengthOfMonth());
        System.out.println(localDate.lengthOfYear());
        System.out.println(localDate.isLeapYear());
        return localDate;
    }
}
