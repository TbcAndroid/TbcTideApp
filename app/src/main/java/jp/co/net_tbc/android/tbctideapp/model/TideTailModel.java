package jp.co.net_tbc.android.tbctideapp.model;

/**
 * Created by Kenji Nagai on 2016/08/06.
 */
public class TideTailModel {
    private String tideTime;
    private int tideLevel;

    public String getTideTime() {
        return tideTime;
    }

    public void setTideTime(String tideTime) {
        this.tideTime = tideTime;
    }

    public int getTideLevel() {
        return tideLevel;
    }

    public void setTideLevel(int tideLevel) {
        this.tideLevel = tideLevel;
    }
}
