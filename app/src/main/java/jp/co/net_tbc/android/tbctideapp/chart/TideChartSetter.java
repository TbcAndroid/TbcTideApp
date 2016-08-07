package jp.co.net_tbc.android.tbctideapp.chart;

import android.content.Context;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

import jp.co.net_tbc.android.tbctideapp.model.FishStarModel;
import jp.co.net_tbc.android.tbctideapp.model.TideTailModel;

/**
 * Created by Kenji Nagai on 2016/08/07.
 */
public class TideChartSetter {
    private LineChart mChart;
    private FishStarModel fishStarModel;

    /**
     * Constructor.
     */
    public TideChartSetter(LineChart mChart, FishStarModel fishStarModel) {
        this.mChart = mChart;
        this.fishStarModel = fishStarModel;
    }

    public void setChart() {
        initChart(mChart);

        // 縦軸の設定
        LineDataSet lineDataSet = new LineDataSet(getEntry(), "潮位");
        lineDataSet.setDrawCubic(true);
        LineData data = new LineData(getLabel(), lineDataSet);
        mChart.setData(data);
    }

    private void initChart(LineChart mChart) {
        mChart.setDescription("時間");
        mChart.setTouchEnabled(false);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);

        YAxis yAxis = mChart.getAxisLeft();
        yAxis.setDrawGridLines(false);
        yAxis.setAxisMaxValue(200);
        yAxis.setAxisMinValue(0);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        mChart.invalidate();
    }

    private ArrayList<Entry> getEntry() {
        ArrayList<Entry> entries = new ArrayList<>();

        for (int i = 0; i < fishStarModel.getTideTails().size(); i++) {
            entries.add(new Entry(Float.valueOf(fishStarModel.getTideTails().get(i).getTideLevel()), i));
        }
        return entries;
    }

    private ArrayList<String> getLabel() {
        ArrayList<String> labels = new ArrayList<>();

        for(TideTailModel tideTailModel : fishStarModel.getTideTails()){
            labels.add(tideTailModel.getTideTime());
        }

        return labels;
    }
}