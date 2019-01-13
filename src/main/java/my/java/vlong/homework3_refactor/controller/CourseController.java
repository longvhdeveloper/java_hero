package my.java.vlong.homework3_refactor.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import my.java.vlong.homework3_refactor.dto.CourseDTO;
import my.java.vlong.homework3_refactor.dto.StudentDTO;
import my.java.vlong.homework3_refactor.exception.AddedException;
import my.java.vlong.homework3_refactor.exception.DataNotFoundException;
import my.java.vlong.homework3_refactor.exception.DeletedException;
import my.java.vlong.homework3_refactor.exception.ResultListEmptyException;
import my.java.vlong.homework3_refactor.exception.UpdatedException;
import my.java.vlong.homework3_refactor.service.CourseService;

public class CourseController {

    private CourseService courseService;
    private Map<String, Object> response;

    public CourseController() {
        courseService = new CourseService();
        response = new HashMap<>();
    }

    public Map<String, Object> add(CourseDTO courseDTO) {
        try {
            CourseDTO course = courseService.add(courseDTO);
            response.put("success", "1");
            response.put("message", "Add course success.");
            response.put("course", course);
        } catch (AddedException ex) {
            response.put("error", ex.getMessage());
        }
        return response;
    }

    public Map<String, Object> update(int id, CourseDTO courseDTO) {
        try {
            CourseDTO course = courseService.update(id, courseDTO);
            response.put("success", "1");
            response.put("message", "Updated course success.");
            response.put("course", course);
        } catch (UpdatedException | DataNotFoundException ex) {
            response.put("error", ex.getMessage());
        }
        return response;
    }

    public Map<String, Object> delete(int id) {
        try {
            courseService.delete(id);
            response.put("success", "1");
            response.put("message", "Deleted course success.");
        } catch (DeletedException ex) {
            response.put("error", ex.getMessage());
        }
        return response;
    }

    public Map<String, Object> findAll() {
        List<CourseDTO> courses = new ArrayList<>();
        try {
            courses = courseService.findAll();
        } catch (ResultListEmptyException ex) {
            response.put("error", ex.getMessage());
        }
        response.put("courses", courses);
        return response;
    }

    public Map<String, Object> search(String keyWord) {
        List<CourseDTO> courses = new ArrayList<>();
        try {
            courses = courseService.search(keyWord);
        } catch (ResultListEmptyException ex) {
            response.put("error", ex.getMessage());
        }
        response.put("courses", courses);
        return response;
    }

    public Map<String, Object> viewStudentInCourse(int id) {
        List<StudentDTO> students = new ArrayList<>();
        try {
            students = courseService.getStudentOfCourse(id);
        } catch (DataNotFoundException | ResultListEmptyException ex) {
            response.put("error", ex.getMessage());
        }
        response.put("students", students);
        return response;
    }
}
