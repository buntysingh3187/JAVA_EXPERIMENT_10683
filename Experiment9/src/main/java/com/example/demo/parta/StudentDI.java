package com.example.demo.parta;

public class StudentDI {
    private String name;
    private Course course;

    public StudentDI() {}

    public StudentDI(String name, Course course) {
        this.name = name;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void study() {
        System.out.println(name + " is studying " + course.getName());
    }

    @Override
    public String toString() {
        return "StudentDI{name='" + name + "', course=" + course + "}";
    }
}
