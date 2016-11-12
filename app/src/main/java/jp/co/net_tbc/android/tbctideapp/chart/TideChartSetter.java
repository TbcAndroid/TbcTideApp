package jp.co.net_tbc.android.tbctideapp.chart;

import android.content.Context;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import jp.co.net_tbc.android.tbctideapp.R;
import jp.co.net_tbc.android.tbctideapp.model.FishStarModel;
import jp.co.net_tbc.android.tbctideapp.model.TidedetailModel;

/**
 * Created by Kenji Nagai on 2016/08/07.
 */
public class TideChartSetter {
    private LineChart mChart;
    private Context context;
    private FishStarModel fishStarModel;

    /**
     * Constructor.
     */
    public TideChartSetter(LineChart mChart, Context context, FishStarModel fishStarModel) {
        this.mChart = mChart;
        this.context = context;
        this.fishStarModel = fishStarModel;
    }

    public void setChart() {
        initChart(mChart);

        // 縦軸の設定
        LineDataSet lineDataSet = new LineDataSet(getEntry(), "潮位");
        lineDataSet.setDrawCubic(true);
        lineDataSet.setColor(context.getResources().getColor(R.color.colorPrimary));
        lineDataSet.setCircleColor(context.getResources().getColor(R.color.colorPrimary));
        lineDataSet.setLineWidth(2);
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
        List<TidedetailModel> tidedetailModelList = fishStarModel.getTidedetails();
        int aveTideLevel = getAryAve(tidedetailModelList);

        // fist y:Average
        entries.add(new Entry(Float.valueOf(aveTideLevel), 0));
        int firstCount = 1;

        int firstIterator = 0;
        int i = 0;
        for (; i < tidedetailModelList.size(); i++) {
            entries.add(new Entry(Float.valueOf(tidedetailModelList.get(i).getTideLevel()), i + firstCount));
        }

        // last y:Average Tide Level
        entries.add(new Entry(Float.valueOf(aveTideLevel), i + 1));

        return entries;
    }

    private ArrayList<String> getLabel() {
        ArrayList<String> labels = new ArrayList<>();
        List<TidedetailModel> tidedetailModelList = fishStarModel.getTidedetails();

        //start 0:00
        labels.add("");

        for (TidedetailModel tidedetailModel : tidedetailModelList) {
            labels.add(tidedetailModel.getTideTime());
        }

        //end 24:00
        labels.add("");

        return labels;
    }

    private int getAryAve(List<TidedetailModel> tidedetailModelList) {
        int sum = 0;
        for (TidedetailModel tidedetailModel : tidedetailModelList) {
            sum += Integer.valueOf(tidedetailModel.getTideLevel());
        }
        // return ave
        return sum / tidedetailModelList.size();
    }
}