package pojo;

import java.util.List;

public class PojoStudentWithList {


    private int id;
    private String name;
    private String location;
    private String 	phone;
    private List<String> Courses;
    private List<PojoStudentWithListObject> Books;



    public PojoStudentWithList() {}

    public List<PojoStudentWithListObject> getBooks() {
        return Books;
    }

    public void setBooks(List<PojoStudentWithListObject> books) {
        Books = books;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getCourses() {
        return Courses;
    }

    public void setCourses(List<String> courses) {
        Courses = courses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
