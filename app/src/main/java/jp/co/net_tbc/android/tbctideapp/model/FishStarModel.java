package jp.co.net_tbc.android.tbctideapp.model;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Kenji Nagai on 2016/08/06.
 */
public class FishStarModel implements Serializable {
    @XStreamOmitField
    private static FishStarModel fishStarModel = new FishStarModel();

    @XStreamOmitField
    private static final long serialVersionUID = 561472575341079847L;

    private String sunriseTime = "0:00";
    private String sunsetTime = "0:00";
    private String moonriseTime = "0:00";
    private String moonsetTime = "0:00";
    private String tideName = "";
    private List<TidedetailModel> tidedetails = Arrays.asList(new TidedetailModel("1:00", "1"), new TidedetailModel("2:00", "2"), new TidedetailModel("3:00", "3"), new TidedetailModel("4:00", "4"));
    private double latitude = 0.0;
    private double longitude = 0.0;

    private FishStarModel() {
    }

    public static FishStarModel getInstance() {
        return fishStarModel;
    }

    public void setFishStarModel(FishStarModel fishStarModel){
        if(fishStarModel != null){
            setSunriseTime(fishStarModel.getSunriseTime());
            setSunsetTime(fishStarModel.getSunsetTime());
            setMoonriseTime(fishStarModel.getMoonriseTime());
            setMoonsetTime(fishStarModel.getMoonsetTime());
            setTideName(fishStarModel.getTideName());
            setTidedetails(fishStarModel.getTidedetails());
            setLongitude(fishStarModel.getLongitude());
            setLatitude(fishStarModel.getLatitude());
        }
    }

    public String getSunriseTime() {
        return sunriseTime;
    }

    public void setSunriseTime(String sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    public String getSunsetTime() {
        return sunsetTime;
    }

    public void setSunsetTime(String sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    public String getMoonriseTime() {
        return moonriseTime;
    }

    public void setMoonriseTime(String moonriseTime) {
        this.moonriseTime = moonriseTime;
    }

    public String getMoonsetTime() {
        return moonsetTime;
    }

    public void setMoonsetTime(String moonsetTime) {
        this.moonsetTime = moonsetTime;
    }

    public String getTideName() {
        return tideName;
    }

    public void setTideName(String tideName) {
        this.tideName = tideName;
    }

    public List<TidedetailModel> getTidedetails() {
        return tidedetails;
    }

    public void setTidedetails(List<TidedetailModel> tidedetails) {
        List<TidedetailModel> addList = new ArrayList<TidedetailModel>();
        for(TidedetailModel addModel: tidedetails){
            if(!addModel.getTideLevel().isEmpty() && !addModel.getTideTime().isEmpty()){
                addList.add(addModel);
            }
        }
        this.tidedetails = addList;
    }

    public double getLatitude() { return latitude; }

    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }

    public void setLongitude(double longitude) { this.longitude = longitude; }
}