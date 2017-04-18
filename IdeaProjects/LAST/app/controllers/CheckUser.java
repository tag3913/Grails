package controllers;

import com.evdb.javaapi.EVDBAPIException;
import com.evdb.javaapi.EVDBRuntimeException;
import models.DBManager;
import models.EventObject;
import models.Eventful;
import models.User;
import org.json.JSONException;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.io.IOException;
import java.sql.SQLException;


/**
 * Created by thom on 4/4/17.
 */
public class CheckUser extends Controller{

    public Result login() throws EVDBRuntimeException, EVDBAPIException, SQLException, IOException, JSONException {
        User user = Form.form(User.class).bindFromRequest().get();
        String email = user.getEmail();
        DBManager manager = new DBManager();
        session("connected", email);
        Eventful events = new Eventful();
        if(manager.findUser(user)){
            return ok(events.AllEvents(email));
        }
        else{
            return ok(login.render("this", "this"));
        }
    }

    public Result newUser(){
        return ok(newaccount.render());
    }

    public Result createUser() throws EVDBRuntimeException, EVDBAPIException, SQLException {
        User user = Form.form(User.class).bindFromRequest().get();
        String email = user.getEmail();
        String password = user.getPassword();
        DBManager manager = new DBManager();
        if(manager.addUser(user)) {
            Eventful eventful = new Eventful();
            eventful.updateEvents();
            return ok(homepage.render(email));
        }
        else{
            return ok(login.render("this", "this"));
        }
    }


}
