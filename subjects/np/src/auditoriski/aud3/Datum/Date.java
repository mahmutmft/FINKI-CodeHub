package auditoriski.aud3.Datum;

import java.util.Objects;

public class Date implements Comparable<Date> {
    private static final int FIRST_YEAR = 1800;
    private static final int LAST_YEAR = 2500;
    private static final int DAYS_IN_YEAR = 365;

    private static final int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int[] daysTillFirstOfMonth;
    private static final int[] daysTillFirstOfYear;

    static {
        daysTillFirstOfMonth = new int[12];

        for (int i = 1; i < 12; i++) {
            daysTillFirstOfMonth[i] = daysTillFirstOfMonth[i - 1] + daysOfMonth[i - 1];
        }
        int totalYears = LAST_YEAR - FIRST_YEAR + 1;
        daysTillFirstOfYear = new int[totalYears];
        int currentYear = FIRST_YEAR;

        for (int i = 1; i < totalYears; i++) {
            daysTillFirstOfYear[i] = daysTillFirstOfYear[i - 1] + DAYS_IN_YEAR;
            if (isLeapYear(currentYear))
                daysTillFirstOfYear[i]++;
            currentYear++;
        }
    }

    private int days;

    public Date(int days) {
        this.days = days;
    }

    public Date(int day, int month, int year) {
        int days = 0;
        if (!isDateInvalid(year))
            throw new RuntimeException();
        days += daysTillFirstOfYear[year - FIRST_YEAR];
        days += daysTillFirstOfMonth[month - 1];
        if (isLeapYear(year) && month >= 2) {
            days++;
        }
        days += day;
        this.days = days;

    }

    private static boolean isLeapYear(int year) {
        return (year % 400 == 0 || year % 4 == 0 && year % 100 != 0);
    }

    private boolean isDateInvalid(int year) {
        return !(year < FIRST_YEAR || year > LAST_YEAR);
    }

    public java.util.Date increment(int days) {
        return new java.util.Date(this.days + days);
    }

    public int subtract(Date date) {
        return Math.abs(this.days - date.days);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Date data = (Date) o;
        return days == data.days;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(days);
    }

    @Override
    public int compareTo(Date o) {
        return Integer.compare(this.days, o.days);
    }

}
