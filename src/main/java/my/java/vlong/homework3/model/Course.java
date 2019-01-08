package my.java.vlong.homework3.model;

import my.java.vlong.homework3.database.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Course {
    private int id;
    private String name;

    public Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public Course(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isValid() {
        return this.name != null && !this.name.equals("");
    }

    @Override
    public String toString() {
        return String.format("Course {id: %d, Course name: %s}", this.id, this.name);
    }
}
