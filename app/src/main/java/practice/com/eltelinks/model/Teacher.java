package practice.com.eltelinks.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "teachers")
public class Teacher {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String email;
    private String faculty;
    private String website;

    // State of the item
    @Ignore
    private boolean expanded;

    public Teacher(String name, String email, String faculty, String website) {
        this.name = name;
        this.email = email;
        this.faculty = faculty;
        this.website = website;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getWebsite() {
        return website;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isExpanded() {
        return expanded;
    }
}
