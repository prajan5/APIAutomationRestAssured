package pojo;

public class PojoStudent {

    private String name;
    private String location;
    private String 	phone;
    private String Courses[];

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String[] getCourses() {
        return Courses;
    }

    public void setCourses(String[] courses) {
        Courses = courses;
    }



}
