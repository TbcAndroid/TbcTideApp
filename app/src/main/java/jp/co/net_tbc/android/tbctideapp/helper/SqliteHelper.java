package jp.co.net_tbc.android.tbctideapp.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

import jp.co.net_tbc.android.tbctideapp.model.CalendarModel;
import jp.co.net_tbc.android.tbctideapp.model.FishStarModel;
import jp.co.net_tbc.android.tbctideapp.model.SpotModel;
import jp.co.net_tbc.android.tbctideapp.model.WeatherModel;

/**
 * Created by Kenji Nagai on 2016/09/22.
 */
public class SqliteHelper extends SQLiteOpenHelper {

    /* Inner class that defines the table contents */
    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "Model";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String BINARY_TYPE = " BLOB";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    WeatherModel.class.getSimpleName() + BINARY_TYPE + COMMA_SEP +
                    CalendarModel.class.getSimpleName() + BINARY_TYPE + COMMA_SEP +
                    FishStarModel.class.getSimpleName() + BINARY_TYPE + COMMA_SEP +
                    SpotModel.class.getSimpleName() + BINARY_TYPE +
                    " )";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;


    public SqliteHelper(Context context, String dbName, int dbVer) {
        super(context, dbName, null, dbVer);
    }

    private List<?> modelList = Arrays.asList(WeatherModel.getInstance(), CalendarModel.getInstance(), FishStarModel.getInstance(), SpotModel.getInstance());


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void replaceModels(){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;

        for(Object model: modelList){
            try {
                baos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(baos);
                oos.writeObject(model);

                values.put(model.getClass().getSimpleName(), baos.toByteArray());
                baos.close();
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long num = db.replace(FeedEntry.TABLE_NAME, null, values);
    }

    public void restoreModels() {
        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                WeatherModel.class.getSimpleName(),
                CalendarModel.class.getSimpleName(),
                FishStarModel.class.getSimpleName(),
                SpotModel.class.getSimpleName()
        };

        Cursor c = db.query(
                FeedEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        int num = c.getColumnCount();
        int numCount = c.getCount();
        int index = c.getColumnIndex(projection[0]);
        c.moveToFirst();
        if(index != -1 && c.getCount() != 0 && !c.isNull(index)){
            try {
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                ObjectOutputStream oos = new ObjectOutputStream(baos);
//                oos.writeObject(c.getBlob(index));
                ByteArrayInputStream bais = new ByteArrayInputStream(c.getBlob(index));
                ObjectInputStream ois = new ObjectInputStream(bais);
                WeatherModel weatherModel = (WeatherModel) ois.readObject();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}
