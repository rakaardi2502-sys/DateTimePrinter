import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.ZonedDateTime;
import java.util.Scanner;
import java.util.HashMap;

public class DateTimePrinter {

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime timeToday = LocalDateTime.now();
        ZonedDateTime zonedTimeToday = ZonedDateTime.now();
        Scanner scanner = new Scanner(System.in);

        // Telur
        // tepung
        // gula
        // susu

        System.out.println(today);
        System.out.println(time);
        System.out.println(timeToday);
        System.out.println(zonedTimeToday);

        DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("EEEE, MMM dd, yyyy\n" + "HH:MM:SS");
        DateTimeFormatter newFormat2 = DateTimeFormatter.ofPattern("EEEE, MMM dd, yyyy\n" + "HH:mm:ss\n" + "zzz:" +  " Z");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

        String formatedDate = timeToday.format(newFormat);
        System.out.println(formatedDate);
        String formatedDate2 = zonedTimeToday.format(newFormat2);
        System.out.println(formatedDate2);
        String date = timeToday.format(dateFormat);
        String alarm = timeToday.format(timeFormat);

        int telur = timeToday.getMonthValue();
        System.out.println(telur);
        System.out.print("Set the day (dd/MM/yyyy): ");
        String setDay = scanner.nextLine();

        if (!setDay.matches("\\d{2}\\/\\d{2}\\/\\d{4}")) {
            System.out.println("Invalid input");
        }
        else {
            String[] date1 = date.split("/");
            int day1 = Integer.parseInt(date1[0]);
            int month1 = Integer.parseInt(date1[1]);
            int year1 = Integer.parseInt(date1[2]);

            String[] date2 = setDay.split("/");
            int day2 = Integer.parseInt(date2[0]);
            int month2 = Integer.parseInt(date2[1]);
            int year2 = Integer.parseInt(date2[2]);
            
            int day = day2 - day1;
            int month = month2 - month1;
            int year = year2 - year1;
            if (day < 0) {
                day += 30;
                month--;
            }
            if (month < 0) {
                month += 12;
                year--;
            }
           
            if (year >= 0 && month >= 0 && day >= 0) {
                System.out.print("Set the alarm (HH:MM:SS): ");
                String setAlarm = scanner.nextLine();
                if (!setAlarm.matches("\\[0-23]d{2}\\:\\[0-59]d{2}\\:\\[0-59]d{2}")) {
                    System.out.println("Invalid input");
                }
                else {
                    String[] timeSplit1 = alarm.split(":");
                    int hour1 = Integer.parseInt(timeSplit1[0]); 
                    int minute1 = Integer.parseInt(timeSplit1[1]);
                    int seconds1 = Integer.parseInt(timeSplit1[2]);

                    String[] timeSplit2 = setAlarm.split(":");
                    int hour2 = Integer.parseInt(timeSplit2[0]); 
                    int minute2 = Integer.parseInt(timeSplit2[1]);
                    int seconds2 = Integer.parseInt(timeSplit2[2]);

                    int second = seconds2 - seconds1;
                    int minute = minute2 - minute1;
                    int hour = hour2 - hour1;
                    if (second < 0) {
                        second += 60;
                        minute--;
                    }
                    if (minute < 0) {
                        minute += 60;
                        hour--;
                    }
                    if (hour < 0) {
                        hour += 24;
                        day --;
                    }
                    if (day < 0) {
                        System.out.println("Time has passed");
                        return;
                    }
                    else {
                        // time calculator
                        System.out.printf("Day remain: %d:%d:%d\n", day, month, year);
                        System.out.printf("Time remain: %d:%d:%d", hour, minute, second);
                    }
                }
            }
            else {
                System.out.println("Time has passed");
            }
        }
    }
    private static void dayPerMonthCalculator(int untilDay, int untilMonth, int untilYear) {
        HashMap<Integer, Integer> monts = new HashMap<>();
        monts.put(1, 31);
        monts.put(2, 28);
        monts.put(3, 31);
        monts.put(4, 30);
        monts.put(5, 31);
        monts.put(6, 30);
        monts.put(7, 31);
        monts.put(8, 30);
        monts.put(9, 31);
        monts.put(10, 30);
        monts.put(11, 31);
        monts.put(12, 30);
        
        int totalDays = 0;
        LocalDateTime timeDate = LocalDateTime.now();
        int fromDay = timeDate.getDayOfMonth();
        int fromMonth = timeDate.getMonthValue();
        int fromYear = timeDate.getYear();
        int dateSubstraction = untilDay - fromDay;
        int monthSubstraction = untilMonth - fromMonth;
        int yearSubstraction = untilYear - fromYear;

        
        if (dateSubstraction < 0) {
            monthSubstraction--;
        }
        if (monthSubstraction < 0) {
            yearSubstraction--;
        }
        if (yearSubstraction < 0) {
            return;
        }
        
        for (int i = 0;i <= yearSubstraction; i++) {
            if (((fromMonth + i) % 4) == 0) {
                monts.replace(2, 29);
            }
            else {
                monts.replace(2, 28);
            }
            for (int j = (fromMonth - 1);j < (untilMonth - 1);j++) {
            totalDays += monts.get(j + 1);
            }
        }
        

        
        System.out.println("Day remain: " + totalDays);
    }
}