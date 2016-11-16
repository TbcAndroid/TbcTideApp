package jp.co.net_tbc.android.tbctideapp.helper;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import jp.co.net_tbc.android.tbctideapp.R;
import jp.co.net_tbc.android.tbctideapp.activity.CalendarActivity;
import jp.co.net_tbc.android.tbctideapp.activity.SpotActivity;
import jp.co.net_tbc.android.tbctideapp.model.CalendarModel;
import jp.co.net_tbc.android.tbctideapp.model.FishStarModel;
import jp.co.net_tbc.android.tbctideapp.model.SpotModel;
import jp.co.net_tbc.android.tbctideapp.model.WeatherModel;

/**
 * Created by kenji on 2016/09/18.
 */
public class MainActivityViewHelper {
    private Activity activity;

    /* constructor. */
    public MainActivityViewHelper(Activity activity) {
        this.activity = activity;
    }

    public void setPortName() {
        // タイトルの港を入力する
        TextView portName = (TextView) activity.findViewById(R.id.status_text);
        portName.setText(SpotModel.getInstance().getPortName());
    }

    public void setStarFishData() {
        // テキストビューインスタンスを取得する
        TextView moonOld = (TextView) activity.findViewById(R.id.moon_old);
        TextView moonRise = (TextView) activity.findViewById(R.id.moonrise);
        TextView moonSet = (TextView) activity.findViewById(R.id.moonset);
        TextView sunRise = (TextView) activity.findViewById(R.id.sunrize);
        TextView sunSet = (TextView) activity.findViewById(R.id.sunset);

        // モデルのインスタンスを取得する
        FishStarModel fishStarModel = FishStarModel.getInstance();

        // テキストビューに値を入力する
        moonOld.setText(activity.getString(R.string.moon_old) + fishStarModel.getTideName());
        moonRise.setText(activity.getString(R.string.moonrise) + fishStarModel.getMoonriseTime());
        moonSet.setText(activity.getString(R.string.moonset) + fishStarModel.getMoonsetTime());
        sunRise.setText(activity.getString(R.string.sunrize) + fishStarModel.getSunriseTime());
        sunSet.setText(activity.getString(R.string.sunset) + fishStarModel.getSunsetTime());
    }

    public void setCalendarView() {
        // テキストビューインスタンスを取得する
        TextView textView = (TextView) activity.findViewById(R.id.calendar_view);

        // モデルのインスタンスを取得する
        CalendarModel calendarModel = CalendarModel.getInstance();
        FishStarModel fishStarModel = FishStarModel.getInstance();
        int index = calendarModel.getDayOfWeek() - 1;
        String htmlStr = calendarModel.getMonth() + "月" + calendarModel.getDay() + "日(" + CalendarActivity.week_name[index] + ") " + "<font color=" + activity.getText(R.string.font_color_tide_name) + ">" + fishStarModel.getTideName() + "</font>";
        textView.setText(Html.fromHtml(htmlStr));
    }

    public void setWeatherView() {
        // モデルのインスタンスを取得する
        WeatherModel weatherModel = WeatherModel.getInstance();

        // ビューインスタンスを取得する
        ImageView weatherImgView = (ImageView) activity.findViewById(R.id.weatherIcon);
        TextView weatherSummary = (TextView) activity.findViewById(R.id.weatherSummary);
        TextView maxView = (TextView) activity.findViewById(R.id.maxView);
        TextView minView = (TextView) activity.findViewById(R.id.minView);
        TextView deg = (TextView) activity.findViewById(R.id.deg);
        TextView speed = (TextView) activity.findViewById(R.id.speed);
        TextView hp = (TextView) activity.findViewById(R.id.hp);

        String uri = "drawable/w" + weatherModel.getIcon();
        int imageResource = activity.getResources().getIdentifier(uri, null, activity.getPackageName());
        Resources res = activity.getApplicationContext().getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res, imageResource);
        weatherImgView.setImageBitmap(bitmap);

        // 天気情報の時間を表示する
        Calendar weatherCalendar = Calendar.getInstance();
        weatherCalendar.setTimeInMillis(weatherModel.getWeatherDt());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M/d H時");
        String weatherTime = simpleDateFormat.format(weatherCalendar.getTime());

        String summaryText = weatherModel.getWeather();

        // 天気情報の時間および天気概要を表示する
        weatherSummary.setText(summaryText + " " + weatherTime);

        maxView.setText(String.format("%1$.1f", weatherModel.getMaxTemp()) + " ℃");
        minView.setText(String.format("%1$.1f", weatherModel.getMinTemp()) + " ℃");

        deg.setText(weatherModel.getDegDirection());
        speed.setText(String.format("%.1f", weatherModel.getSpeed()) + "m/s");
        hp.setText(weatherModel.getPressure() + "hPa");
    }

    public void initErrorView(boolean netEna) {
        TextView errText = (TextView) activity.findViewById(R.id.error_text);

        if (netEna) {
            errText.setVisibility(View.INVISIBLE);
        } else {
            errText.setVisibility(View.VISIBLE);
        }
    }

    public void initWeatherView(boolean weatherEna) {
        TextView errText = (TextView) activity.findViewById(R.id.errorWeather);
        ImageView weatherIcon = (ImageView) activity.findViewById(R.id.weatherIcon);
        TextView weatherSummary = (TextView) activity.findViewById(R.id.weatherSummary);
        TextView maxTemp = (TextView) activity.findViewById(R.id.maxView);
        TextView minTemp = (TextView) activity.findViewById(R.id.minView);
        TextView deg = (TextView) activity.findViewById(R.id.deg);
        TextView speed = (TextView) activity.findViewById(R.id.speed);
        TextView hp = (TextView) activity.findViewById(R.id.hp);
        LinearLayout ll = (LinearLayout) activity.findViewById((R.id.weatherNormalLayout));
        LinearLayout ll2 = (LinearLayout) activity.findViewById((R.id.weatherNormalLayout2));

        if (weatherEna) {
            weatherIcon.setVisibility(View.VISIBLE);
            weatherSummary.setVisibility(View.VISIBLE);
            maxTemp.setVisibility(View.VISIBLE);
            minTemp.setVisibility(View.VISIBLE);
            deg.setVisibility(View.VISIBLE);
            speed.setVisibility(View.VISIBLE);
            hp.setVisibility(View.VISIBLE);
            errText.setVisibility(View.GONE);
            ll.setVisibility(View.VISIBLE);
            ll2.setVisibility(View.VISIBLE);
        } else {
            weatherIcon.setVisibility(View.GONE);
            weatherSummary.setVisibility(View.GONE);
            maxTemp.setVisibility(View.GONE);
            minTemp.setVisibility(View.GONE);
            deg.setVisibility(View.GONE);
            speed.setVisibility(View.GONE);
            hp.setVisibility(View.GONE);
            errText.setVisibility(View.VISIBLE);
            ll.setVisibility(View.GONE);
            ll2.setVisibility(View.GONE);
        }
    }

    public void initClickListener() {
        // SpotActivityを起動するClickリスナーを作成する
        TextView statusView = (TextView) activity.findViewById(R.id.status_text);
        statusView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // インテントを生成する
                Intent intent = new Intent(activity.getApplication(), SpotActivity.class);
                // SpotActivityを起動する
                activity.startActivity(intent);
            }
        });

        // CalendarActivityを起動するClickリスナーを作成する
        TextView calendarView = (TextView) activity.findViewById(R.id.calendar_view);
        calendarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // インテントを生成する
                Intent intent = new Intent(activity.getApplication(), CalendarActivity.class);
                // SpotActivityを起動する
                activity.startActivity(intent);
            }
        });
    }
}