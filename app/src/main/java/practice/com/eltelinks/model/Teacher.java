package practice.com.eltelinks.model;

public class Teacher {

    private int id;
    private String name;
    private String email;
    private String faculty;
    private String website;

    // State of the item
    //set as NotColumn later in db
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

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isExpanded() {
        return expanded;
    }
}
