package controllers;

import models.DBManager;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import static play.mvc.Results.ok;


/**
 * Created by thom on 4/4/17.
 */
public class CheckUser extends Controller{

    public Result login() {
        User user = Form.form(User.class).bindFromRequest().get();
        String email = user.getEmail();
        String password = user.getPassword();
        DBManager manager = new DBManager();
        if(manager.findUser(password, email)){
            return ok(homepage.render(email));
        }
        else{
            return ok(login.render("this", "this"));
        }
    }

    public Result newUser(){
        return ok(newaccount.render());
    }

    public Result createUser() {
        User user = Form.form(User.class).bindFromRequest().get();
        String email = user.getEmail();
        String password = user.getPassword();
        DBManager manager = new DBManager();
        if(manager.addUser(user.getUsername(), password, email)) {
            return ok(homepage.render(email));
        }
        else{
            return ok(login.render("this", "this"));
        }
    }


}
