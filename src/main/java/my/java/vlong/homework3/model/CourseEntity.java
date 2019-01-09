package my.java.vlong.homework3.model;

import my.java.vlong.homework3.database.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseEntity {
    private int id;
    private String name;

    private PreparedStatement preparedStatement;

    public CourseEntity() {
    }

    public CourseEntity(String name) {
        this.name = name;
    }

    public CourseEntity(int id, String name) {
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
        return String.format("CourseEntity {id: %d, CourseEntity name: %s}", this.id, this.name);
    }
}
