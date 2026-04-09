package com.catalog.model;

public class Course {
    private String courseCode;
    private String name;
    private String level;
    private Double tuitionFee;
    private String description;
    private String instructor;
    private int duration;
    private boolean isFull;
    private int studentCount;

    public Course() {}

    public Course(String courseCode, String name, String level, Double tuitionFee,
                  String description, String instructor, int duration, boolean isFull, int studentCount) {
        this.courseCode = courseCode;
        this.name = name;
        this.level = level;
        this.tuitionFee = tuitionFee;
        this.description = description;
        this.instructor = instructor;
        this.duration = duration;
        this.isFull = isFull;
        this.studentCount = studentCount;
    }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public Double getTuitionFee() { return tuitionFee; }
    public void setTuitionFee(Double tuitionFee) { this.tuitionFee = tuitionFee; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public boolean isFull() { return isFull; }
    public void setFull(boolean isFull) { this.isFull = isFull; }

    public int getStudentCount() { return studentCount; }
    public void setStudentCount(int studentCount) { this.studentCount = studentCount; }
}