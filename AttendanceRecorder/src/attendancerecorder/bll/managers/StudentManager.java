/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendancerecorder.bll.managers;

import attendancerecorder.be.Course;
import attendancerecorder.be.Student;
import attendancerecorder.bll.interfaces.IStudentManager;
import attendancerecorder.dal.dao.DAOStudent;
import attendancerecorder.dal.interfaces.IDAOStudent;
import java.util.ArrayList;
import java.util.List;

public class StudentManager implements IStudentManager {

    //Instance for the Data Access Object
    IDAOStudent daoStudent = new DAOStudent();

    //Lists that we will use to refer to the ones recieved from the methods of the Data Access Layer
    private List<Student> studentLst = new ArrayList();
    private List<Student> studentLoginLst = new ArrayList();
    private List<Course> courseLst = new ArrayList();

    @Override
    public List<Student> getAllStudents() {
        studentLst = daoStudent.getAllStudents();
        return studentLst;
    }

    @Override
    public List<Course> getAllCourses() {
        courseLst = daoStudent.getAllCourses();
        return courseLst;
    }

    @Override
    public void addNewAttendance(int studentId, int status, String date, String message) {
        daoStudent.addNewAttendance(studentId, status, date, message);
    }

    @Override
    public List<Student> getAllAttendancesById(int studentId) {
        studentLst = daoStudent.getAllAttendancesById(studentId);
        return studentLst;
    }

    @Override
    public Student getReasonForAbsence(int studentId, String date) {
        return daoStudent.getReasonForAbsence(studentId, date);
    }

    @Override
    public void deleteAttendanceByIdANDDate(int id, String date) {
        daoStudent.deleteAttendanceByIdANDDate(id, date);
    }

    @Override
    public boolean checkAlreadyExistingAttendance(int id, String date) {
        boolean aaa = daoStudent.checkAlreadyExistingAttendance(id, date);
        return aaa;
    }
    
    @Override
    public void updateAbsencePercentageById(int id, float newAbsencePercentage) {
        daoStudent.updateAbsencePercentageById(id, newAbsencePercentage);
    }

    @Override
    public void updateStudentPasswordById(int id, String newPassword) {
       daoStudent.updateStudentPasswordById(id, newPassword);
    }

}
