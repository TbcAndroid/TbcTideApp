package jp.co.net_tbc.android.tbctideapp.model;

/**
 * Created by Kenji Nagai on 2016/08/06.
 */
public class SpotModel {
    private static final SpotModel spotModel = new SpotModel();
    private int portId;

    /**
     * Constructor
     */
    public SpotModel() {
    }


    public static SpotModel getInstance() {
        return spotModel;
    }

    public int getPortId() {
        return portId;
    }

    public void setPortId(int portId) {
        this.portId = portId;
    }
}
