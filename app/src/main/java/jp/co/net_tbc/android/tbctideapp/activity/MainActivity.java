package jp.co.net_tbc.android.tbctideapp.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;

import jp.co.net_tbc.android.tbctideapp.R;
import jp.co.net_tbc.android.tbctideapp.chart.TideChartSetter;
import jp.co.net_tbc.android.tbctideapp.db.ModelData;
import jp.co.net_tbc.android.tbctideapp.helper.MainActivityViewHelper;
import jp.co.net_tbc.android.tbctideapp.model.FishStarModel;
import jp.co.net_tbc.android.tbctideapp.model.WeatherModel;
import jp.co.net_tbc.android.tbctideapp.thread.FishStarThread;
import jp.co.net_tbc.android.tbctideapp.thread.GetDayWeatherInfoThread;

public class MainActivity extends AppCompatActivity {
    MainActivityViewHelper mainActivityViewHelper;
    ModelData modelData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // MainActivityからViewの操作を切り分けたクラスをニューする
        mainActivityViewHelper = new MainActivityViewHelper(this);

        // ClickListenerを初期化する
        mainActivityViewHelper.initClickListener();

        // データベースにモデルの情報がある場合、モデルを復元する
        modelData = new ModelData(getApplicationContext());
        if (WeatherModel.getInstance().needInit()) {
            modelData.restoreModels();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // ネットワークが使用可能か確認する
        boolean netEnable = isNetEnable();

        // 天気情報を取得できる日付可確認する
        boolean weatherGetEnable = WeatherModel.canGetWeatherInfo();

        // インターネットに利用できない場合、日付の隣にエラーメッセージを表示する
        mainActivityViewHelper.initErrorView(netEnable);

        // 天気情報が利用できない場合、天気viewにエラーメッセージを表示する
        mainActivityViewHelper.initWeatherView(weatherGetEnable);

        // Internetを利用可能かつ天気情報を取得できる日付の場合、天気情報を取得する
        // FishStarから潮汐情報を取得する
        if (netEnable && weatherGetEnable) {
            GetDayWeatherInfoThread getDayWeatherInfoThread = new GetDayWeatherInfoThread();
            Thread weatherThread = new Thread(getDayWeatherInfoThread);
            FishStarThread fs = new FishStarThread();
            Thread fishStarThread = new Thread(fs);
            try {
                // FishStarのREST API呼び出す
                fishStarThread.start();
                fishStarThread.join(5000);

                // FishStarから受け取った位置情報を使うため、FishStar呼出し後、天気情報は取得できるREST APIを呼び出す
                weatherThread.start();
                weatherThread.join(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                mainActivityViewHelper.initWeatherView(false);
            }
        }

        // 各Viewに値を設定する
        mainActivityViewHelper.setPortName();
        mainActivityViewHelper.setStarFishData();
        mainActivityViewHelper.setCalendarView();
        mainActivityViewHelper.setWeatherView();

        TideChartSetter tideChartSetter = new TideChartSetter((LineChart) findViewById(R.id.chart), getApplicationContext(), FishStarModel.getInstance());
        tideChartSetter.setChart();
    }

    @Override
    protected void onStart() {
        super.onStart();
        modelData.restoreModels();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // モデルを保存する
        // TODO 保存するタイミングがここでいいかは不明
        modelData.replaceModels();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    // TODO 別クラスに分けるべき?
    private boolean isNetEnable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        if (netInfo != null) {
            return netInfo.isConnected();
        } else {
            return false;
        }
    }
}