package jp.co.net_tbc.android.tbctideapp.model;

import java.io.Serializable;

/**
 * Created by Kenji Nagai on 2016/08/06.
 */
public class SpotModel implements Serializable {
    private static SpotModel spotModel = new SpotModel();
    private static final long serialVersionUID = -7464734216639422538L;
    private int portId = 0;
    private String prefectureName = "北海道";
    private String portName = "函館";
    // 緯度
    private double latitude = 0;
    // 経度
    private double longitude = 0;

    /**
     * Constructor
     */
    private SpotModel() { }

    public static SpotModel getInstance() { return spotModel; }

    public void setSpotModel(SpotModel spotModel){
        if(spotModel != null){
            setPortId(spotModel.getPortId());
            setPortName(spotModel.getPortName());
            setLatitude(spotModel.getLatitude());
            setLongitude(spotModel.getLongitude());
            setPrefectureName(spotModel.getPrefectureName());
        }
    }

    public int getPortId() { return portId; }

    public void setPortId(int portId) { this.portId = portId; }

    public String getPortName() { return portName; }

    public void setPortName(String portName) { this.portName = portName; }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPrefectureName() { return prefectureName; }

    public void setPrefectureName(String prefectureName) { this.prefectureName = prefectureName; }
}
