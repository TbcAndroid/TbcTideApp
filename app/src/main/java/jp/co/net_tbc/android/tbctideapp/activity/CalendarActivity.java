package jp.co.net_tbc.android.tbctideapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;

import java.util.Calendar;

import jp.co.net_tbc.android.tbctideapp.R;
import jp.co.net_tbc.android.tbctideapp.model.CalendarModel;


public class CalendarActivity extends Activity implements OnDateChangeListener,OnClickListener{
	// 選択年月日保持用
	private int year = 0;
	private int month = 0;
	private int day = 0;
    private int dayOfWeek = 0;

    CalendarModel calendarModel;
    public static final String[] week_name = {"日曜日", "月曜日", "火曜日", "水曜日",
            "木曜日", "金曜日", "土曜日"};

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar);

        // カレンダーモデルのインスタンスを入れる
        calendarModel = CalendarModel.getInstance();

		// カレンダー日付選択リスナー作成
		CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
		calendarView.setOnDateChangeListener(this);

        // カレンダーの初期値を設定する
        initCalendarValue(calendarView);

		// OKボタンクックリスナー作成
		Button button = (Button) findViewById(R.id.ok_button_calendar);
		button.setOnClickListener(this);
	}
	// カレンダー選択
	@Override
	public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
        // 保持用年月日にセット
        this.year = year;
        this.month = month + 1;
        this.day = dayOfMonth;

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        this.dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
	}

	// OKボタンクック
	@Override
	public void onClick(View view) {
        saveData(year, month, day, dayOfWeek);

		// MainActivity へ遷移
		Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
		startActivity(intent);
	}

    private void initCalendarValue(CalendarView calendarView){
        // 初期値設定
        year = calendarModel.getYear();
        month = calendarModel.getMonth();
        day = calendarModel.getDay();
        dayOfWeek = calendarModel.getDayOfWeek();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        calendarView.setDate(calendar.getTimeInMillis());
    }

    private void saveData(int year, int month, int day, int dayOfWeek){
        // CalendarModel へセット
        calendarModel.setYear(year);
        calendarModel.setMonth(month);
        calendarModel.setDay(day);
        calendarModel.setDayOfWeek(dayOfWeek);
    }
}
