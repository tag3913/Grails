package models;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by thom on 4/4/17.
 */
@Entity
public class User extends Model{

    @Id
    protected String email;
    protected String password;
    protected String username;

    public void setUsername(String email) {
        this.email = email;
    }

    public String getUsername() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

}
