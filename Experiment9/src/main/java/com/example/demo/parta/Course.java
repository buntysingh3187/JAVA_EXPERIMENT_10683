package com.example.demo.parta;

public class Course {
    private String name;

    public Course() {}

    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Course{name='" + name + "'}";
    }
}
