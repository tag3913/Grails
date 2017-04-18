package models;
import com.avaje.ebean.Model;
import play.data.validation.Constraints;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by thom on 4/4/17.
 */
@Entity
@Table(name = "users")
public class User extends Model{

    @Id
    @Constraints.Required
    protected String email;

    protected String password;
    protected String username;

    public void setUsername(String name) {
        this.username = name;
    }

    public String getUsername() {
        return username;
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

    public static Finder<Long, User> find = new Finder<>(User.class);

}
