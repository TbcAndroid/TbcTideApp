package jp.co.net_tbc.android.tbctideapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    Spinner prefectureSpinner = null;
    Spinner portSpinner = null;
    Button okButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot);

        // Widgetインスタンス
        prefectureSpinner = (Spinner) findViewById(R.id.spinner_prefecture);
        portSpinner = (Spinner) findViewById(R.id.spinner_spot);
        okButton = (Button) findViewById(R.id.ok_button);

        // Spinnerに値を設定するためのAdapterを作成する
        final ArrayAdapter<String> prefectureAdpter = createAdapter(PortManager.getPrefs());
        prefectureSpinner.setAdapter(prefectureAdpter);

        // 初期値設定
        prefectureSpinner.setSelection(prefectureAdpter.getPosition(SpotModel.getInstance().getPrefectureName()));

        // Spinnerのアイテムが選択された時に呼び出されるコールバックリスナーを登録する
        setPrefectureSpinnerListener(prefectureSpinner, portSpinner);

        // 決定ボタンのリスナーを登録する
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MainActivityへ画面遷移する
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop(){
        super.onStop();
        savePortData(portSpinner, prefectureSpinner);
    }

    private ArrayAdapter<String> createAdapter(List<String> items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.sppiner_text, items);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        return adapter;
    }

    private void setPrefectureSpinnerListener(final Spinner prefectureSpinner, final Spinner portSpinner) {
        prefectureSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String prefectureName = (String) prefectureSpinner.getSelectedItem();
                initPortSpinnerAdapter(prefectureName, portSpinner, prefectureSpinner);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

//    private void setPortSpinnerListener(final Spinner portSpinner, final Spinner prefectureSpinner){
//        portSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                savePortData(portSpinner, prefectureSpinner);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) { }
//        });
//    }

    private void savePortData(final Spinner portSpinner, final Spinner prefectureSpinner){
        String portName = (String)portSpinner.getSelectedItem();
        int portNumber = PortManager.getPortId(portName);
        String prefectureName = (String)prefectureSpinner.getSelectedItem();

        SpotModel spotModel = SpotModel.getInstance();
        spotModel.setPortName(portName);
        spotModel.setPortId(portNumber);
        spotModel.setPrefectureName(prefectureName);
    }

    private void initPortSpinnerAdapter(String prefectureName, Spinner portSpinner, Spinner prefectureSpinner){
        // PortSpinnerのアダプターを作成し、設定する
        List<String> portNameList = PortManager.getPorts(prefectureName);
        ArrayAdapter<String> adapter = createAdapter(portNameList);
        portSpinner.setAdapter(adapter);
        // Spinnerのアイテムが選択された時に呼び出されるコールバックリスナーを登録する
//        setPortSpinnerListener(portSpinner, prefectureSpinner);
        // 初期値設定
        portSpinner.setSelection(adapter.getPosition(SpotModel.getInstance().getPrefectureName()));
    }
}
