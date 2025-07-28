package edu.rit.swen253.page.tiger.GPA;

/*
 * Represents a course object. Each course has a course name
 * number of credits, grade received, and past grade if applicable
 */
public class Course {
    private String name;
    private int credits;
    private String grade;
    private String pastGrade;

    /*
     * Includes all parameters
     */
    public Course(String name, int credits, String grade, String pastGrade) {
        this.name = name;
        this.credits = credits;
        this.grade = grade;
        this.pastGrade = pastGrade;
    }

    /*
     * No past grade for the course.
     */
    public Course(String name, int credits, String grade) {
        this.name = name;
        this.credits = credits;
        this.grade = grade;
        this.pastGrade = "";
    }

    /*
     * Returns course name.
     */
    public String getName() {
        return this.name;
    }

    /*
     * Returns how many credits the course is worth
     */
    public int getCredits() {
        return this.credits;
    }

    /*
     * Returns the grade received for the course
     */
    public String getGrade() {
        return this.grade;
    }

    /*
     * Returns the past grade received in the course
     */
    public String getPastGrade() {
        return this.pastGrade;
    }
}