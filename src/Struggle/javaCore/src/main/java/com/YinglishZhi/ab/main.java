package com.YinglishZhi.ab;

import java.time.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author LDZ
 * @date 2019-11-21 18:20
 */
public class main {

    public static void main(String[] args) {

//        Page page = Page.builder()
//                .pageNum(2)
//                .pageSize(2)
//                .totalCount(19)
//                .build();
//
//        System.out.println(page.toString());
//        long time = 1541001600000L;
//        LocalDateTime data = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
//        System.out.println(data);
        long begin = 1569859200000L;
        long end = 1541001600000L;
        Map<Long, Long> longLongMap = splitToMonth(end, begin);
        System.out.println(longLongMap);


    }

    private static Map<Long, Long> splitToMonth(long begin, long end) {
        Map<Long, Long> result = new HashMap<>();

        LocalDateTime beginTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(begin), ZoneId.systemDefault());
        LocalDateTime endTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(end), ZoneId.systemDefault());

        Function<LocalDateTime, Long> timeToUnix = localDateTime -> localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();

        for (LocalDateTime i = endTime; !i.isBefore(beginTime); i = i.minusMonths(1)) {
            LocalDateTime timeFloor = i.plusMonths(1).minusDays(1).with(LocalTime.MAX);
            result.put(timeToUnix.apply(i.withDayOfMonth(1).with(LocalTime.MIN)),
                    timeToUnix.apply(timeFloor.isAfter(endTime) ? endTime : timeFloor));
        }
        return result;

    }

}
