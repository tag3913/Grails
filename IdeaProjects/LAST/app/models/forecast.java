package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;
import play.db.ebean.*;
import play.db.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by thom on 4/17/17.
 */

@Entity
@Table(name = "weather")
public class forecast extends Model{
    @Id
    @Constraints.Required
    protected int id;
    protected String sky;
    protected String date;

    public int getId() {
        return id;
    }

    public String getSky() {
        return sky;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSky(String sky) {
        this.sky = sky;
    }

    public static Finder<Long, forecast> find = new Finder<>(forecast.class);
}
