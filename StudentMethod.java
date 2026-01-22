/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.studentmethod;

/**
 *
 * @author AEscudero2026
 */
public class StudentMethod {
    public static void main(String[] args) {
        // create the student profile
        Student student = new Student("Anderson Escudero", 
                                     "AP Comp Sci A", 
                                     "AP Calc AB", 
                                     "Capstone");
        
        // set the student test scores
        student.setTestScore(0, 95);
        student.setTestScore(1, 88);
        student.setTestScore(2, 92);
        
        // print out the final profile
        System.out.println(student);
    }
}
//create student class
class Student {
    private String studentName;
    private Course[] courses;
    
    // create the student constructor
    public Student() {
        this.studentName = "";
        this.courses = new Course[3];
        for (int i = 0; i < 3; i++) {
            courses[i] = new Course();
        }
    }
    
    // create the parameterized constructor
    public Student(String studentName, String course1, String course2, String course3) {
        this.studentName = studentName;
        this.courses = new Course[3];
        courses[0] = new Course(course1);
        courses[1] = new Course(course2);
        courses[2] = new Course(course3);
    }
    
    // set the test scores
    public void setTestScore(int courseIndex, int score) {
        courses[courseIndex].setTestScore(score);
    }
    
    // get the test scores
    public int getTestScore(int courseIndex) {
        return courses[courseIndex].getTestScore();
    }
    
    // average the 3 test scores from the courses
    public double getAverage() {
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += courses[i].getTestScore();
        }
        return sum / 3.0;
    }
    
    // create the toString method
    public String toString() {
        String result = "Student: " + studentName + "\n";
        for (int i = 0; i < 3; i++) {
            result += "  " + courses[i].toString() + "\n";
        }
        // get the average
        result += "Average: %" + getAverage();
        return result;
    }
}
//create the course class
class Course {
    private String courseName;
    private int testScore;
    
    // create the inital constructor
    public Course() {
        this.courseName = "";
        this.testScore = 0;
    }
    
    // create the next parameterized constructor
    public Course(String courseName) {
        this.courseName = courseName;
        this.testScore = 0;
    }
    
    // set the test score
    public void setTestScore(int score) {
        this.testScore = score;
    }
    
    // get the test score
    public int getTestScore() {
        return this.testScore;
    }
    
    // get the test score
    public String getCourseName() {
        return this.courseName;
    }
    
    // create final toString method
    public String toString() {
        return courseName + ": " + testScore;
    }
}


