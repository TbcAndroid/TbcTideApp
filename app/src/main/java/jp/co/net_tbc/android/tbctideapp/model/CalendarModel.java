package jp.co.net_tbc.android.tbctideapp.model;

/**
 * Created by Kenji Nagai on 2016/08/06.
 */
public class CalendarModel {
    private static final CalendarModel calendarModel = new CalendarModel();
    int year;
    int month;
    int day;
    String dayOfWeek;

    /**
     * constructor.
     */
    public CalendarModel() {
    }

    /* singleton pattern*/
    public static CalendarModel getInstance() {
        return calendarModel;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
