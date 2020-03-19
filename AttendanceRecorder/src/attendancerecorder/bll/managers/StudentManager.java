/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendancerecorder.bll.managers;

import attendancerecorder.be.Attendance;
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
    private List<Attendance> attLst = new ArrayList();
    

    @Override
    public List<Student> getAllStudents() {
        studentLst = daoStudent.getAllStudents();
        return studentLst;
    }

    @Override
    public List<Student> getStudentLoginData() {
        studentLoginLst = daoStudent.getStudentLoginData();
        return studentLoginLst;
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
    public List<Attendance> getAllAttendancesById() {
        attLst = daoStudent.getAllAttendancesById();
        return attLst;
    }


    

}
