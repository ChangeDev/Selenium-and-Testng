package com.taoqi;

import java.time.*;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.TemporalAdjusters.*;

/**
 * Created by TQ-G153 on 2018/2/11.
 */
public class J8TimeDemo {

    public static void main(String[] args) throws Exception {
        dateTimeFormatter();
    }

    public static void zonedDateTime() {
        // LocalDateTime LocalDate Instant都可以与ZoneId组合 转换成ZonedDateTime
        ZonedDateTime zonedDateTime = LocalDate.now().atStartOfDay(ZoneId.systemDefault());
        ZonedDateTime zonedDateTime1 = LocalDateTime.now().atZone(ZoneId.systemDefault());
        ZonedDateTime zonedDateTime2 = Instant.now().atZone(ZoneId.systemDefault());
    }

    public static void dateTimeFormatter() {
        // DateTimeFormatter是线程安全的
        // 创建DateTimeFormatter
        LocalDate localDate = LocalDate.now();
        // 2014-03-18
        DateTimeFormatter isoLocalDate = DateTimeFormatter.ISO_LOCAL_DATE;
        // 20140318
        DateTimeFormatter basicIsoDate = DateTimeFormatter.BASIC_ISO_DATE;
        // DateTimeFormatter.BASIC_ISO_DATE
        DateTimeFormatter.ofPattern("yyyyMMdd");
        // 也可以通过String和DateTimeFormatter实例直接创建日期、时间对象
        LocalDate date = LocalDate.parse("20140318", basicIsoDate);
    }

    public static void temporalAdjuster() {
        LocalDate localDate = LocalDate.now();
        // 将日期转换成本月的第1个周五
        LocalDate date0 = localDate.with(dayOfWeekInMonth(1, DayOfWeek.FRIDAY));
        // 将日期转换成本月的第1个周五
        LocalDate date = localDate.with(dayOfWeekInMonth(2, DayOfWeek.FRIDAY));
        // 将日期转换成本月的第一天
        LocalDate date1 = localDate.with(firstDayOfMonth());
        // 将日期转换成下月的第一天
        System.out.println(localDate.with(firstDayOfNextMonth()));
        // 将日期转换成明年的第一天
        System.out.println(localDate.with(firstDayOfNextYear()));
        // 将日期转换成今年的第一天
        System.out.println(localDate.with(firstDayOfYear()));
        // 将日期转换成本月的第一个周五
        System.out.println(localDate.with(firstInMonth(DayOfWeek.FRIDAY)));
        // 将日期转换成本月的最后一个周五
        System.out.println(localDate.with(lastInMonth(DayOfWeek.FRIDAY)));
        // 将日期转换成下一个周五
        System.out.println(localDate.with(next(DayOfWeek.FRIDAY)));
        // 将日期转换成上一个周五
        System.out.println(localDate.with(previous(DayOfWeek.FRIDAY)));
        // 将日期转换成下一个周五 如果当前日期就是周五 则不用转换
        System.out.println(localDate.with(nextOrSame(DayOfWeek.FRIDAY)));
        // 将日期转换成上一个周五  如果当前日期就是周五 则不用转换
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

    public static LocalDate getLocalDate() {
        LocalDate today = LocalDate.now();
        System.out.println(today);
        System.out.println(today.getYear());
        System.out.println(today.getMonth());
        System.out.println(today.getMonthValue());
        System.out.println(today.getDayOfMonth());
        return today;
    }

    public static LocalDate customDate() {
        // 获取2014年3月18号的日期
        LocalDate localDate = LocalDate.of(2014, 3, 18);
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonth());
        System.out.println(localDate.getMonthValue());
        System.out.println(localDate.getDayOfMonth());
        System.out.println(localDate.getDayOfWeek());
        System.out.println(localDate.getDayOfYear());
        System.out.println(localDate.lengthOfMonth());
        System.out.println(localDate.lengthOfYear());
        // 是否为闰年
        System.out.println(localDate.isLeapYear());
        return localDate;
    }
}
