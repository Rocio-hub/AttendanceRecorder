package attendancerecorder.bll.managers;

import attendancerecorder.be.Student;
import attendancerecorder.be.Teacher;
import attendancerecorder.bll.interfaces.ITeacherManager;
import attendancerecorder.dal.dao.DAOTeacher;
import attendancerecorder.dal.interfaces.IDAOTeacher;
import java.util.List;

public class TeacherManager implements ITeacherManager {

    //Instance for the Data Access Object
    IDAOTeacher daoTeacher = new DAOTeacher();

    @Override
    public List<Teacher> getAllTeachers() {
        return daoTeacher.getAllTeachers();
    }

    @Override
    public List<Teacher> getTeacherLoginData() {
        return daoTeacher.getTeacherLoginData();
    }

    @Override
    public List<Student> getStudentsOnCondition(String date, int status) {
        return daoTeacher.getStudentsOnCondition(date, status);
    }

    @Override
    public List<Student> getAllStudentsForAbsenceOverview() {
        return daoTeacher.getAllStudentsForAbsenceOverview();
    }

    @Override
    public float getAbsenceById(int id) {
        return daoTeacher.getAbsenceById(id);
    }

    @Override
    public void updateTeacherPasswordById(int id, String newPassword) {
        daoTeacher.updateTeacherPasswordById(id, newPassword);
    }

    @Override
    public void addNewStudent(String firstName, String lastName, String email, String password) {
        daoTeacher.addNewStudent(firstName, lastName, email, password);
    }
    @Override
     public List<Student> getDaysOfAbsenceById(int id){
         return daoTeacher.getDaysOfAbsenceById(id);
     }

    @Override
    public List<Student> getAllAttendancesByDate(String date) {
        return daoTeacher.getAllAttendancesByDate(date);
    }

    @Override
    public void insertNewStatus(List<Student> studentLst) {
        daoTeacher.insertNewStatus(studentLst);
    }
    
    
    
}
