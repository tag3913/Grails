package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author thom
 */
public class Weather {

    final String URL = "http://api.openweathermap.org/data/2.5/forecast/daily?id=5140405&cnt=7&APPID=b4cdb3d940b23503251ce4883be8c49f";

    public String[] forecast() throws IOException, JSONException{
        JSONObject forecast = returnJson();
        String returnWeather[] = new String[7];
        JSONArray list = ((JSONArray) forecast.get("list"));
        for(int i = 0; i < 7; i++){
            JSONObject day = list.getJSONObject(i);
            JSONArray weather = ((JSONArray) day.get("weather"));
            JSONObject find = weather.getJSONObject(0);
            returnWeather[i] = find.getString("main");
        }

        return returnWeather;
    }

    public JSONObject returnJson()throws IOException, JSONException{
        InputStream is = new URL(URL).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        }
        finally {
            is.close();
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }


}
