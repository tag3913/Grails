package models;


import com.avaje.ebean.Model;
import play.data.validation.Constraints;
import play.db.ebean.*;
import play.db.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Created by thom on 4/11/17.
 */

@Entity
@Table(name = "events")
public class EventObject extends Model{
    @Id
    @Constraints.Required
    protected int id;
    protected String title;
    protected String venue;
    protected String dateTime;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getVenue() {
        return venue;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public static Finder<Long, EventObject> find = new Finder<>(EventObject.class);
}
