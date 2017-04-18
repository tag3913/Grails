package models;

import com.evdb.javaapi.APIConfiguration;
import com.evdb.javaapi.EVDBAPIException;
import com.evdb.javaapi.EVDBRuntimeException;
import com.evdb.javaapi.data.Event;
import com.evdb.javaapi.data.Location;
import com.evdb.javaapi.data.request.EventSearchRequest;
import com.evdb.javaapi.operations.EventOperations;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by paulk4ever on 2/19/17.
 */
public class Eventful {

    final private String API_KEY = "qX2dNH9TZGLvBT8B";
    final private String USER_NAME = "events420";
    final private String PASSWORD = "pass55word";

    public EventOperations eventOperations;
    public EventSearchRequest eventSearchRequest;
    public Location location;


    public Eventful() {
        APIConfiguration apiConfiguration = new APIConfiguration();
        apiConfiguration.setApiKey(API_KEY);
        apiConfiguration.setEvdbUser(USER_NAME);
        apiConfiguration.setEvdbPassword(PASSWORD);

        eventOperations = new EventOperations();
        eventSearchRequest = new EventSearchRequest();
        eventSearchRequest.setPageSize(10);
        eventSearchRequest.setDateRange("Next week");
        location = new Location();
        location.setCountry("United States");
        location.setCity("Syracuse");
        //eventSearchRequest.setCategory("soccer");
    }

    public String[] weather() throws IOException, JSONException{
        Weather forecast = new Weather();
        return forecast.forecast();
    }

    public List<Event> search(String keyWord) throws EVDBRuntimeException, EVDBAPIException {
        eventSearchRequest.setKeywords(keyWord);
        eventSearchRequest.setLocation(location.getCity());
        eventSearchRequest.setDateRange("Next week");
        return eventOperations.search(eventSearchRequest).getEvents();
    }

    public void updateEvents () throws EVDBRuntimeException, EVDBAPIException, SQLException{
        System.out.print("HERE");
        for (Event event : search("")) {
            System.out.print("HERE2");
            EventObject newEvent = new EventObject();
            newEvent.setTitle(event.getTitle());
            newEvent.setVenue(event.getVenueAddress());
            newEvent.setDateTime(event.getStartTime().toString());
            newEvent.save();
        }
    }
    public void deleteEvents () throws EVDBRuntimeException, EVDBAPIException, SQLException{
        EventObject deleter = new EventObject();
    }
    public void updateWeather () throws EVDBRuntimeException, EVDBAPIException, SQLException, IOException, JSONException{
        String[] weather = weather();
        Calendar c;
        c = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        for(int dateNum = 0; dateNum < 7; dateNum++){
            forecast newForecast = new forecast();
            newForecast.setDate(dateFormat.format(date).toString());
            newForecast.setSky(weather[dateNum]);
            newForecast.save();
            c.setTime(date);
            c.add(Calendar.DATE, 1);
            date = c.getTime();
        }

    }

    public String AllEvents(String name) throws IOException, JSONException {
        String result = "Welcome " + name + "\nForecast: \n";
        Calendar c;
        c = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        Weather forecast = new Weather();
        for (String day: forecast.forecast()){
            result +=dateFormat.format(date) + " : " + day + "\n";
            c.setTime(date);
            c.add(Calendar.DATE, 1);
            date = c.getTime();
        }
        result += "\nEVENTS :";
        List<EventObject> events = EventObject.find.all();
        for(int i = 0; i < EventObject.find.findRowCount(); i++){
            result += "\n" + events.get(i).getTitle() + "\n";
            result += events.get(i).getVenue() + "\n";
            result += events.get(i).getDateTime() + "\n";
        }
        return result;
    }

    public JSONObject allEventsJson() throws JSONException{
        JSONObject feedback = new JSONObject();
        List<EventObject> events = EventObject.find.all();
        int eventCounter = 1;

        for(int i = 0; i < EventObject.find.findRowCount(); i++){
            JSONObject someEvent = new JSONObject();
            JSONArray container = new JSONArray();
            someEvent.put("Name", events.get(i).getTitle());
            someEvent.put("Venue", events.get(i).getVenue());
            someEvent.put("Time", events.get(i).getDateTime());
            container.put(someEvent);
            feedback.put("Event" + eventCounter, container);
            eventCounter++;
        }
        return feedback;

    }
}
