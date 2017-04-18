package models;
import java.awt.*;
import java.util.List;


/**
 * Created by thom on 4/4/17.
 */
public class DBManager {

    public DBManager() {

        try {
            // The newInstance() call is a work around for some
            // broken Java implementations
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error: unable to load driver class!" + ex);
                System.exit(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Add a users name, password, and email
    //EMAIL IS THE PRIMARY KEY, this is because it's different for each user
    public boolean addUser (User newUser) {
        newUser.save();
        return true;
    }

    //We will use this primarily at login.
    //We search the users by the email and if the password matches, return true (get the okay to log in)
    public boolean findUser(User returnUser) {
        String email = returnUser.getEmail();
        String password = returnUser.getPassword();
        List<EventObject> events = EventObject.find.all();
        List<User> all = User.find.all();
        if(User.find.where().eq("email", email).findUnique() != null)
            if(User.find.where().eq("email", email).findUnique().getPassword().equals(password))
                return true;
            else
                return false;
        else
            return false;
    }

    public void newEvent(EventObject event){
        event.save();
    }

}


