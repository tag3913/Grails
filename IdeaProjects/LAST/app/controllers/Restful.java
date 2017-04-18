package controllers;
import com.fasterxml.jackson.databind.JsonNode;
import models.DBManager;
import models.EventObject;
import models.Eventful;
import models.User;
import org.json.JSONException;
import play.mvc.Result;

import play.mvc.Controller;

/**
 * Created by thom on 4/17/17.
 */
public class Restful extends Controller {
    //PUT
    public Result newEvent() {
        System.out.println("::PUT::");
        //System.out.println(request().body().asJson().toString());
        JsonNode json = request().body().asJson();
        String title = json.findPath("title").textValue();
        String venue = json.findPath("venue").textValue();
        String date = json.findPath("date/time").textValue();
        EventObject newEvent = new EventObject();
        newEvent.setTitle(title);
        newEvent.setVenue(venue);
        newEvent.setDateTime(date);
        newEvent.save();
        return ok("EVENTS UPDATED");
    }

    //GET
    public Result getEvents(String username) throws JSONException{
        System.out.println("::GET::");
        Eventful eventful = new Eventful();
        if(User.find.where().eq("email", username).findUnique() != null){
            return ok(eventful.allEventsJson().toString());
        }
        else
            return ok("User not registered");

    }
}
