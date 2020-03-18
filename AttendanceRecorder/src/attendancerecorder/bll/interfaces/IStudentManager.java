/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendancerecorder.bll.interfaces;

import attendancerecorder.be.Course;
import attendancerecorder.be.Student;
import java.util.List;

public interface IStudentManager {

    public List<Student> getAllStudents();

    public List<Student> getStudentLoginData();
    
    public List<Course> getAllCourses();
    
    public void addNewAttendance(int studentId, int status, String date, String message);
    
    public void calculateOverallAttendance(int studentId, int status);

}
