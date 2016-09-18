package jp.co.net_tbc.android.tbctideapp.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;

import java.util.ArrayList;
import java.util.Calendar;

import jp.co.net_tbc.android.tbctideapp.R;
import jp.co.net_tbc.android.tbctideapp.chart.TideChartSetter;
import jp.co.net_tbc.android.tbctideapp.helper.MainActivityViewHelper;
import jp.co.net_tbc.android.tbctideapp.model.CalendarModel;
import jp.co.net_tbc.android.tbctideapp.model.FishStarModel;
import jp.co.net_tbc.android.tbctideapp.model.SpotModel;
import jp.co.net_tbc.android.tbctideapp.model.TideTailModel;
import jp.co.net_tbc.android.tbctideapp.model.WeatherModel;
import jp.co.net_tbc.android.tbctideapp.thread.GetDayWeatherInfoThread;

public class MainActivity extends AppCompatActivity {
    MainActivityViewHelper mainActivityViewHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityViewHelper = new MainActivityViewHelper(this);
        // ClickListener初期化
        mainActivityViewHelper.initClickListener();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // ネットワークが使用可能か確認する
        boolean netEnable = checkNetEnable();

        // Error時にviewを切り替える
        mainActivityViewHelper.initView(netEnable);

        if (netEnable) {
            initTestData();
        } else {
            initErrMsg();
        }

        // 天気情報が利用できない場合、Viewにエラーメッセージを表示する
        mainActivityViewHelper.initWeatherView(WeatherModel.enaGetWeatherInfo());

        if (netEnable && WeatherModel.enaGetWeatherInfo()) {
            GetDayWeatherInfoThread getDayWeatherInfoThread = new GetDayWeatherInfoThread();
            Thread weatherThread = new Thread(getDayWeatherInfoThread);
            weatherThread.start();
            try {
                weatherThread.join(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                mainActivityViewHelper.initWeatherView(false);
            }
        }

        mainActivityViewHelper.setPortName();
        mainActivityViewHelper.setStarFishData();
        mainActivityViewHelper.setCalendarView();
        mainActivityViewHelper.setWeatherView();

        if (netEnable) {
            TideChartSetter tideChartSetter = new TideChartSetter((LineChart) findViewById(R.id.chart), getApplicationContext(), FishStarModel.getInstance());
            tideChartSetter.setChart();
        }
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

        int[] tideLevelAry = {37, 34, 43, 19};
        String[] tideTimeAry = {"01:28", "05:10", "11:08", "18:53"};
        ArrayList<TideTailModel> tideTails = new ArrayList<>();

        for (int i = 0; i < tideLevelAry.length; i++) {
            TideTailModel tideTailModel = new TideTailModel();
            tideTailModel.setTideLevel(tideLevelAry[i]);
            tideTailModel.setTideTime(tideTimeAry[i]);
            tideTails.add(i, tideTailModel);
        }
        fishStarModel.setTideTails(tideTails);

        /* Init WeatherModel */
        // 月齢を計算する
        fishStarModel.setTideName("中潮");
        WeatherModel weatherModel = WeatherModel.getInstance();
        weatherModel.setMaxTemp(10000);
        weatherModel.setIcon("01n");
        weatherModel.setMinTemp(10);

        //SpotModel
        SpotModel spotModel = SpotModel.getInstance();
        spotModel.setLatitude(34.702485);
        spotModel.setLongitude(135.495951);
        spotModel.setPortName("大阪");
    }

    private void initErrMsg() {
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
        fishStarModel.setMoonriseTime("");
        fishStarModel.setMoonsetTime("");
        fishStarModel.setSunriseTime("");
        fishStarModel.setSunsetTime("");

        /* Init WeatherModel */
        // 月齢を計算する
        fishStarModel.setTideName("");
        WeatherModel weatherModel = WeatherModel.getInstance();
        weatherModel.setMaxTemp(0);
        weatherModel.setIcon("01d");
        weatherModel.setMinTemp(0);
        weatherModel.setWeather("");
    }

    private boolean checkNetEnable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        if (netInfo != null) {
            return netInfo.isConnected();
        } else {
            return false;
        }
    }
}