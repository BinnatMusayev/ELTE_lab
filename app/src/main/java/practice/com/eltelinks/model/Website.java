package practice.com.eltelinks.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

// add this to database anootation also
@Entity(tableName = "websites")
public class Website {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String url;
    private String logo;

    public Website(String title, String url) {
        this.title = title;
        this.url = url;

        if (this.url.endsWith("/")){
            this.logo = this.url + "favicon.ico";
        }else{
            this.logo = this.url+"/favicon.ico";
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
