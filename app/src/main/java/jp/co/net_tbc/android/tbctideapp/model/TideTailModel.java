package jp.co.net_tbc.android.tbctideapp.model;

import java.io.Serializable;

/**
 * Created by Kenji Nagai on 2016/08/06.
 */
public class TideTailModel implements Serializable {
    private static final long serialVersionUID = 4090083565605781655L;
    private String tideTime;
    private int tideLevel;

    public TideTailModel() { }

    public TideTailModel(String tideTime, int tideLevel) {
        this.tideTime = tideTime;
        this.tideLevel = tideLevel;
    }

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
