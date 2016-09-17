package jp.co.net_tbc.android.tbctideapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;

import java.util.ArrayList;
import java.util.Calendar;

import jp.co.net_tbc.android.tbctideapp.R;
import jp.co.net_tbc.android.tbctideapp.chart.TideChartSetter;
import jp.co.net_tbc.android.tbctideapp.model.CalendarModel;
import jp.co.net_tbc.android.tbctideapp.model.FishStarModel;
import jp.co.net_tbc.android.tbctideapp.model.TideTailModel;
import jp.co.net_tbc.android.tbctideapp.model.WeatherModel;
import jp.co.net_tbc.android.tbctideapp.thread.GetDayWeatherInfoThread;
import jp.co.net_tbc.android.tbctideapp.util.MoonUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ClickListener初期化
        initClickListener();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // ネットワークが使用可能か確認する
        boolean netEnable = checkNetEnable();

        // Error時にviewを切り替える
        initView(netEnable);

        // 天気情報が利用できない場合、Viewにエラーメッセージを表示する
        initWeatherView(WeatherModel.enaGetWeatherInfo());

        if(WeatherModel.enaGetWeatherInfo()){
            GetDayWeatherInfoThread getDayWeatherInfoThread = new GetDayWeatherInfoThread();
            Thread weatherThread = new Thread(getDayWeatherInfoThread);
            weatherThread.start();
            try {
                weatherThread.join(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                initWeatherView(false);
            }
        }

        setStarFishData();
        setCalendarView();
        setWeatherView();

        TideChartSetter tideChartSetter = new TideChartSetter((LineChart) findViewById(R.id.chart), getApplicationContext(), FishStarModel.getInstance());
        tideChartSetter.setChart();
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
        TextView textView = (TextView) this.findViewById(R.id.calendar_view);

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

        maxView.setText(getText(R.string.max) + String.format("%1$.1f", weatherModel.getMaxTemp()) + "℃");
        minView.setText(getText(R.string.min) + String.format("%1$.1f", weatherModel.getMinTemp()) + " ℃");
    }

    private void initClickListener() {
        // SpotActivityを起動するClickリスナーを作成する
        TextView statusView = (TextView) findViewById(R.id.status_text);
        statusView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // インテントを生成する
                Intent intent = new Intent(getApplication(), SpotActivity.class);
                // SpotActivityを起動する
                startActivity(intent);
            }
        });

        // CalendarActivityを起動するClickリスナーを作成する
        TextView calendarView = (TextView) findViewById(R.id.calendar_view);
        calendarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // インテントを生成する
                Intent intent = new Intent(getApplication(), CalendarActivity.class);
                // SpotActivityを起動する
                startActivity(intent);
            }

        });
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

    private void initView(boolean netEna) {
        LineChart lineChart = (LineChart) findViewById(R.id.chart);
        TextView errText = (TextView) findViewById(R.id.error_text);

        if (netEna) {
            initTestData();
            lineChart.setVisibility(View.VISIBLE);
            errText.setVisibility(View.INVISIBLE);
        } else {
            initErrMsg();
            lineChart.setVisibility(View.INVISIBLE);
            errText.setVisibility(View.VISIBLE);
        }
    }

    private void initWeatherView(boolean weatherEna){
        TextView errText = (TextView) findViewById(R.id.errorWeather);
        ImageView weatherIcon = (ImageView) findViewById(R.id.weatherIcon);
        TextView maxTemp = (TextView) findViewById(R.id.maxView);
        TextView minTemp = (TextView) findViewById(R.id.minView);

        if(weatherEna){
            weatherIcon.setVisibility(View.VISIBLE);
            maxTemp.setVisibility(View.VISIBLE);
            minTemp.setVisibility(View.VISIBLE);
            errText.setVisibility(View.GONE);
        } else{
            weatherIcon.setVisibility(View.GONE);
            maxTemp.setVisibility(View.GONE);
            minTemp.setVisibility(View.GONE);
            errText.setVisibility(View.VISIBLE);
        }
    }
}
