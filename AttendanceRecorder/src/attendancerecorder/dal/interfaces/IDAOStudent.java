/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendancerecorder.dal.interfaces;

import attendancerecorder.be.Course;
import attendancerecorder.be.Student;
import java.util.List;

public interface IDAOStudent {

    public List<Student> getAllStudents();

    public List<Course> getAllCourses();

    public List<Student> getAllAttendancesById(int studentId);

    public void addNewAttendance(int studentId, int status, String date, String message);

    public Student getReasonForAbsence(int studentId, String date);

    public void deleteAttendanceByIdANDDate(int id, String date);

    public boolean checkAlreadyExistingAttendance(int id, String date);
}
