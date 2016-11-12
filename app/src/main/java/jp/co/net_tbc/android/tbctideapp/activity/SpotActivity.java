package jp.co.net_tbc.android.tbctideapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import jp.co.net_tbc.android.tbctideapp.R;
import jp.co.net_tbc.android.tbctideapp.model.SpotModel;
import jp.co.net_tbc.android.tbctideapp.util.PortManager;

public class SpotActivity extends AppCompatActivity {
    List<String> prefectureList = Arrays.asList("兵庫県", "北海道", "大阪");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot);

        // Spinnerインスタンス
        final Spinner prefectureSpinner = (Spinner) findViewById(R.id.spinner_prefecture);
        Spinner portSpinner = (Spinner) findViewById(R.id.spinner_spot);

        // Spinnerに値を設定するためのAdapterを作成する
        final ArrayAdapter<String> prefectureAdpter = createAdapter(PortManager.getPrefs());
        prefectureSpinner.setAdapter(prefectureAdpter);

        // Spinnerのアイテムが選択された時に呼び出されるコールバックリスナーを登録する
        setPrefectureSpinnerListener(prefectureSpinner, portSpinner);
    }

    private ArrayAdapter<String> createAdapter(List<String> items) {
        ArrayAdapter<String> adpter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        adpter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        return adpter;
    }

    private void setPrefectureSpinnerListener(final Spinner prefectureSpinner, final Spinner portSpinner) {
        prefectureSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String prefectureName = (String) prefectureSpinner.getSelectedItem();
                int prefectureIndex = prefectureSpinner.getSelectedItemPosition();
                String toastText = "item: " + prefectureName + "\nitemIndex: " + prefectureIndex;
                Toast.makeText(SpotActivity.this, toastText, Toast.LENGTH_LONG).show();

                // PortSpinnerのアダプターを作成し、設定する
                List<String> portNameList = PortManager.getPorts(prefectureName);
                ArrayAdapter<String> adapter = createAdapter(portNameList);
                portSpinner.setAdapter(adapter);
                // Spinnerのアイテムが選択された時に呼び出されるコールバックリスナーを登録する
                setPortSpinnerListener(portSpinner);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setPortSpinnerListener(final Spinner portSpinner){
        portSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String portName = (String)portSpinner.getSelectedItem();
                int portNumber = PortManager.getPortId(portName);
                SpotModel spotModel = SpotModel.getInstance();
                spotModel.setPortName(portName);
                spotModel.setPortId(portNumber);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
