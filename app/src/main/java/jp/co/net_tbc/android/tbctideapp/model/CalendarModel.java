package jp.co.net_tbc.android.tbctideapp.model;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by Kenji Nagai on 2016/08/06.
 */
public class CalendarModel implements Serializable {
    private static CalendarModel calendarModel = new CalendarModel();
    private static final long serialVersionUID = -6207986572276046824L;
    int year;
    int month;
    int day;
    int dayOfWeek = 0;

    /**
     * constructor.
     */
    private CalendarModel() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    /* singleton pattern*/
    public static CalendarModel getInstance() {
        return calendarModel;
    }

    public void setCalendarModel(CalendarModel calendarModel){
        if(calendarModel != null){
            setYear(calendarModel.getYear());
            setMonth(calendarModel.getMonth());
            setDay(calendarModel.getDay());
            setDayOfWeek(calendarModel.getDayOfWeek());
        }
    };

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

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
