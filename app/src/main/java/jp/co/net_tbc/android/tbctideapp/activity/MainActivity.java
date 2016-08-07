package jp.co.net_tbc.android.tbctideapp.activity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

import jp.co.net_tbc.android.tbctideapp.R;
import jp.co.net_tbc.android.tbctideapp.model.CalendarModel;
import jp.co.net_tbc.android.tbctideapp.model.FishStarModel;
import jp.co.net_tbc.android.tbctideapp.model.WeatherModel;
import jp.co.net_tbc.android.tbctideapp.util.MoonUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initTestData();
        setStarFishData();
        setCalendarView();
        setWeatherView();
    }

    /* private method start */

    /* for test */
    private void initTestData() {
        /* Init CalendarModel */
        Calendar calendar = Calendar.getInstance();
        CalendarModel calendarModel = CalendarModel.getInstance();
        calendarModel.setYear(calendar.get(Calendar.YEAR));
        calendarModel.setMonth(calendar.get(Calendar.MONTH) + 1);
        calendarModel.setDay(calendar.get(Calendar.DAY_OF_MONTH));

        String[] week_name = {"日曜日", "月曜日", "火曜日", "水曜日",
                "木曜日", "金曜日", "土曜日"};

        calendarModel.setDayOfWeek(week_name[calendar.get(Calendar.DAY_OF_WEEK) - 1]);

        /* Init FishStarModel */
        FishStarModel fishStarModel = FishStarModel.getInstance();
        fishStarModel.setMoonriseTime("1:00");
        fishStarModel.setMoonsetTime("2:00");
        fishStarModel.setSunriseTime("3:00");
        fishStarModel.setSunsetTime("4:00");

        /* Init WeatherModel */
        // 月齢を計算する
        fishStarModel.setTideName("中潮");
        WeatherModel weatherModel = WeatherModel.getInstance();
        weatherModel.setMaxTemp(10000);
        weatherModel.setIcon("01n");
        weatherModel.setMinTemp(10);
    }


    private void setStarFishData() {
        // テキストビューインスタンスを取得する
        TextView moonOld = (TextView) this.findViewById(R.id.moon_old);
        TextView moonRise = (TextView) this.findViewById(R.id.moonrise);
        TextView moonSet = (TextView) this.findViewById(R.id.moonset);
        TextView sunRise = (TextView) this.findViewById(R.id.sunrize);
        TextView sunSet = (TextView) this.findViewById(R.id.sunset);

        // モデルのインスタンスを取得する
        CalendarModel calendarModel = CalendarModel.getInstance();
        FishStarModel fishStarModel = FishStarModel.getInstance();

        // 月齢を計算する
        int moon_old = MoonUtil.calculateAgeOfTheMoon(calendarModel.getYear(), calendarModel.getMonth(), calendarModel.getDay());

        // テキストビューに値を入力する
        moonOld.setText(getString(R.string.moon_old) + String.valueOf(moon_old));
        moonRise.setText(getString(R.string.moonrise) + String.valueOf(fishStarModel.getMoonriseTime()));
        moonSet.setText(getString(R.string.moonset) + String.valueOf(fishStarModel.getMoonsetTime()));
        sunRise.setText(getString(R.string.sunrize) + String.valueOf(fishStarModel.getSunriseTime()));
        sunSet.setText(getString(R.string.sunset) + String.valueOf(fishStarModel.getSunsetTime()));
    }

    private void setCalendarView() {
        // テキストビューインスタンスを取得する
        TextView textView = (TextView) this.findViewById(R.id.calendarView);

        // モデルのインスタンスを取得する
        CalendarModel calendarModel = CalendarModel.getInstance();
        FishStarModel fishStarModel = FishStarModel.getInstance();
        String htmlStr = calendarModel.getMonth() + "月" + calendarModel.getDay() + "日(" + calendarModel.getDayOfWeek() + ") " + "<font color=" + getText(R.string.font_color_tide_name) + ">" + fishStarModel.getTideName() + "</font>";
        textView.setText(Html.fromHtml(htmlStr));
    }

    private void setWeatherView() {
        // モデルのインスタンスを取得する
        WeatherModel weatherModel = WeatherModel.getInstance();

        // ビューインスタンスを取得する
        ImageView weatherImgView = (ImageView) findViewById(R.id.weatherIcon);
        TextView maxView = (TextView) findViewById(R.id.maxView);
        TextView minView = (TextView) findViewById(R.id.minView);


        String uri = "drawable/w" + weatherModel.getIcon();
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Resources res = this.getApplicationContext().getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res, imageResource);
        weatherImgView.setImageBitmap(bitmap);

        maxView.setText(getText(R.string.max) + String.valueOf(weatherModel.getMaxTemp()) + "℃");
        minView.setText(getText(R.string.min) + String.valueOf(weatherModel.getMinTemp()) +" ℃");

    }
    /* private method end */
}
