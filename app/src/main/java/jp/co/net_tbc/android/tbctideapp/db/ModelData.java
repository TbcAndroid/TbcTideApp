package jp.co.net_tbc.android.tbctideapp.db;

import android.content.Context;

import jp.co.net_tbc.android.tbctideapp.helper.SqliteHelper;

/**
 * Created by Kenji Nagai on 2016/09/22.
 */
public class ModelData {
    private SqliteHelper sqliteHelper;
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "Model.db";

    public ModelData(Context context){
        sqliteHelper = new SqliteHelper(context, DATABASE_NAME,  DATABASE_VERSION);
    }

    public void restoreModels(){
        sqliteHelper.restoreModels();
    }

    public void replaceModels(){
        sqliteHelper.replaceModels();
    }
}
