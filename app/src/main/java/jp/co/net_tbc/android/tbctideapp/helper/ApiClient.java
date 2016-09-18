package jp.co.net_tbc.android.tbctideapp.helper;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by kenji on 2016/09/18.
 */
public class ApiClient {
    public String connet(String url) {
        String str = null;
        Log.d("URL", url);
        try {
            URL weatherUrl = new URL(url);
            HttpURLConnection con = (HttpURLConnection) weatherUrl.openConnection();
            str = InputStreamToString(con.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("Response data", str);
        return str;
    }

    private String InputStreamToString(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}
