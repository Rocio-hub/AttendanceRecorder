
package attendancerecorder.dal.dao;

import attendancerecorder.be.Course;
import attendancerecorder.be.Student;
import attendancerecorder.be.Teacher;
import attendancerecorder.dal.interfaces.IDAOStudent;
import attendancerecorder.dal.interfaces.IDAOTeacher;
import attendancerecorder.dal.interfaces.IdaoFacade;
import java.util.List;


public class daoFacade implements IdaoFacade{
    
    private IDAOStudent daoStudent = new DAOStudent();
    private IDAOTeacher daoTeacher = new DAOTeacher();

    @Override
    public List<Student> getAllStudents() {
        return daoStudent.getAllStudents();
    }

    @Override
    public List<Course> getAllCourses() {
        return daoStudent.getAllCourses();
    }

    @Override
    public List<Student> getAllAttendancesById(int studentId) {
        return daoStudent.getAllAttendancesById(studentId);
    }

    @Override
    public void addNewAttendance(int studentId, int status, String date, String message, String dayOfWeek) {
       daoStudent.addNewAttendance(studentId, status, date, message, dayOfWeek);
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
        return daoStudent.checkAlreadyExistingAttendance(id, date);
    }

    @Override
    public void updateAbsencePercentageById(int id, float newAbsencePercentage) {
       daoStudent.updateAbsencePercentageById(id, newAbsencePercentage);
    }

    @Override
    public void updateStudentPasswordById(int id, String newPassword) {
       daoStudent.updateStudentPasswordById(id, newPassword);
    }

    
    
    
    
    
    
    
    
    @Override
    public List<Teacher> getAllTeachers() {
        return daoTeacher.getAllTeachers();
    }

    @Override
    public List<Teacher> getTeacherLoginData() {
        return daoTeacher.getTeacherLoginData();
    }

    @Override
    public List<Student> getStudentsOnCondition(String date, int present) {
       return daoTeacher.getStudentsOnCondition(date, present);
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
    public List<Student> getDaysOfAbsenceById(int id) {
       return daoTeacher.getDaysOfAbsenceById(id);
    }

    @Override
    public List<Integer> getAllStudentsIds() {
        return daoTeacher.getAllStudentsIds();
    }

    @Override
    public List<Integer> getAllAttendancesIdsByDate(String date) {
        return daoTeacher.getAllAttendancesIdsByDate(date);
    }

    @Override
    public void insertNewStatus(List<Integer> idList) {
       daoTeacher.insertNewStatus(idList);
    }
    
}
