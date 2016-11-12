package jp.co.net_tbc.android.tbctideapp.model;

import java.io.Serializable;

/**
 * Created by Kenji Nagai on 2016/08/06.
 */
public class TidedetailModel implements Serializable {
    private static final long serialVersionUID = 4090083565605781655L;
    private String tideTime;
    private String tideLevel;

    public TidedetailModel() { }

    public TidedetailModel(String tideTime, String tideLevel) {
        this.tideTime = tideTime;
        this.tideLevel = tideLevel;
    }

    public String getTideTime() { return tideTime; }

    public void setTideTime(String tideTime) {
        this.tideTime = tideTime;
    }

    public String getTideLevel() { return tideLevel; }

    public void setTideLevel(String tideLevel) {
        this.tideLevel = tideLevel;
    }
}
