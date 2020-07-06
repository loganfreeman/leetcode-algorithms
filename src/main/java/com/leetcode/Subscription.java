package com.leetcode;

import java.math.*;
import java.text.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Subscription {
    public Subscription() {}
    public Subscription(int id, int customerId, int monthlyPriceInDollars) {
        this.id = id;
        this.customerId = customerId;
        this.monthlyPriceInDollars = monthlyPriceInDollars;
    }

    public int id;
    public int customerId;
    public int monthlyPriceInDollars;
}

class User {
    public User() {}
    public User(int id, String name, LocalDate activatedOn, LocalDate deactivatedOn, int customerId) {
        this.id = id;
        this.name = name;
        this.activatedOn = activatedOn;
        this.deactivatedOn = deactivatedOn;
        this.customerId = customerId;
    }

    public int id;
    public String name;
    public LocalDate activatedOn;
    public LocalDate deactivatedOn;
    public int customerId;
}

class Challenge {
    public static double billFor(String month, Subscription activeSubscription, User[] users) {
        // your code here!
        int days = daysInAMonth(month);
        int mon = getMonth(month);
        int year = getYear(month);
        double rate = dailyRate(month, activeSubscription);
        double total = 0;
        for(User user : users) {
            for(int i = 1; i <= days; i++) {
                int activeMonth = user.activatedOn.getMonthValue();
                int activeDay = user.activatedOn.getDayOfMonth();

                int inactiveMonth = 12;
                int inactiveDay = 31;
                if(user.deactivatedOn != null) {
                    inactiveMonth = user.deactivatedOn.getMonthValue();
                    inactiveDay = user.deactivatedOn.getDayOfMonth();
                }
                LocalDate date = LocalDate.of(year, mon, i);

                if(date.isAfter(user.activatedOn)) {
                    if(user.deactivatedOn != null) {
                        if(user.deactivatedOn.isAfter(date)) {
                            total += rate;
                        }
                    } else {
                        total += rate;
                    }

                }

            }
        }
        return total;
    }

    /*******************
     * Helper functions *
     *******************/

    /**
     Takes a LocalDate object and returns a LocalDate which is the first day
     of that month. For example:

     firstDayOfMonth(LocalDate.of(2019, 2, 7)) // => LocalDate.of(2019, 2, 1)
     **/
    private static LocalDate firstDayOfMonth(LocalDate date) {
        return date.withDayOfMonth(1);
    }

    /**
     Takes a LocalDate object and returns a LocalDate which is the last day
     of that month. For example:

     lastDayOfMonth(LocalDate.of(2019, 2, 7)) // => LocalDate.of(2019, 2, 28)
     **/
    private static LocalDate lastDayOfMonth(LocalDate date) {
        return date.withDayOfMonth(date.lengthOfMonth());
    }

    /**
     Takes a LocalDate object and returns a LocalDate which is the next day.
     For example:

     nextDay(LocalDate.of(2019, 2, 7))  // => LocalDate.of(2019, 2, 8)
     nextDay(LocalDate.of(2019, 2, 28)) // => LocalDate.of(2019, 3, 1)
     **/
    private static LocalDate nextDay(LocalDate date) {
        return date.plusDays(1);
    }
    public static int getMonth(String month) {
        Pattern pattern = Pattern.compile("(\\d{4})-(\\d{2})");
        Matcher matcher = pattern.matcher(month);
        matcher.matches();
        return Integer.parseInt(matcher.group(2));
    }
    public static int getYear(String month) {
        Pattern pattern = Pattern.compile("(\\d{4})-(\\d{2})");
        Matcher matcher = pattern.matcher(month);
        matcher.matches();
        return Integer.parseInt(matcher.group(1));
    }
    public static int daysInAMonth(String month) {
        Pattern pattern = Pattern.compile("(\\d{4})-(\\d{2})");
        Matcher matcher = pattern.matcher(month);
        matcher.matches();
        YearMonth yearMonthObject = YearMonth.of(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        return yearMonthObject.lengthOfMonth(); //28
    }
    public static double dailyRate(String month, Subscription subscription) {
        int days = daysInAMonth(month);
        return (double)subscription.monthlyPriceInDollars / days;
    }

    public static void main(String[] args) {
        String month = "2019-01";
        System.out.println(daysInAMonth(month));
        Subscription newPlan = new Subscription(1, 1, 4);
        System.out.println(newPlan.monthlyPriceInDollars);
        System.out.println(dailyRate(month, newPlan));
        System.out.println((double)4/31);
    }
}

