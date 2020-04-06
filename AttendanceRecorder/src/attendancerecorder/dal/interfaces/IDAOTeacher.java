/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendancerecorder.dal.interfaces;

import attendancerecorder.be.Student;
import attendancerecorder.be.Teacher;
import java.util.List;

public interface IDAOTeacher {

    public List<Teacher> getAllTeachers();

    public List<Teacher> getTeacherLoginData();

    public List<Student> getStudentsOnCondition(String date, int present);

    public List<Student> getAllStudentsForAbsenceOverview();

    public float getAbsenceById(int id);
    
    public void updatePasswordById(int id, String newPassword);
    
    }
