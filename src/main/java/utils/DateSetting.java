package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DateSetting {
    public static List<String> getDates(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> list  = new ArrayList<>();
        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        list = IntStream.iterate(0, i -> i + 1).limit(numOfDaysBetween).mapToObj(i -> startDate.plusDays(i))
                .collect(Collectors.toList());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        List<String> dates = new ArrayList<>();
        for (int i = 0; i<list.size();i++) {
            dates.add( formatter.format(list.get(i)));
        }
        return dates;
    }
}
