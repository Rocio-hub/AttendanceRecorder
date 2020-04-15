
package attendancerecorder.bll.managers;

import attendancerecorder.be.Course;
import attendancerecorder.be.Student;
import attendancerecorder.be.Teacher;
import attendancerecorder.bll.interfaces.IStudentManager;
import attendancerecorder.bll.interfaces.ITeacherManager;
import attendancerecorder.bll.interfaces.IbllFacade;
import java.util.List;

public class bllFacade implements IbllFacade {

    private ITeacherManager teacherMng = new TeacherManager();
    private IStudentManager studentMng = new StudentManager();
    @Override
    public List<Teacher> getAllTeachers() {
        return teacherMng.getAllTeachers();
    }

    @Override
    public List<Teacher> getTeacherLoginData() {
        return teacherMng.getTeacherLoginData();
    }

    @Override
    public List<Student> getStudentsOnCondition(String date, int status) {
        return teacherMng.getStudentsOnCondition(date, status);
    }

    @Override
    public List<Student> getAllStudentsForAbsenceOverview() {
        return teacherMng.getAllStudentsForAbsenceOverview();
    }

    @Override
    public float getAbsenceById(int id) {
        return teacherMng.getAbsenceById(id);
    }

    @Override
    public void updateTeacherPasswordById(int id, String newPassword) {
        teacherMng.updateTeacherPasswordById(id, newPassword);
    }

    @Override
    public void addNewStudent(String firstName, String lastName, String email, String password) {
       teacherMng.addNewStudent(firstName, lastName, email, password);
    }

    @Override
    public List<Student> getDaysOfAbsenceById(int id) {
        return teacherMng.getDaysOfAbsenceById(id);
    }

    @Override
    public List<Integer> getAllStudentsIds() {
        return teacherMng.getAllStudentsIds();
    }

    @Override
    public List<Integer> getAllAttendancesIdsByDate(String date) {    
        return teacherMng.getAllAttendancesIdsByDate(date);
    }

    @Override
    public void insertNewStatus(List<Integer> idList) {
        teacherMng.insertNewStatus(idList);
    }

    @Override
    public List<Student> getAllStudents() {
       return studentMng.getAllStudents();
    }

    @Override
    public List<Course> getAllCourses() {
       return studentMng.getAllCourses();
    }

    @Override
    public void addNewAttendance(int studentId, int status, String date, String message, String dayOfWeek) {
        studentMng.addNewAttendance(studentId, status, date, message, dayOfWeek);
    }

    @Override
    public List<Student> getAllAttendancesById(int studentId) {
        return studentMng.getAllAttendancesById(studentId);
    }

    @Override
    public Student getReasonForAbsence(int studentId, String date) {
       return studentMng.getReasonForAbsence(studentId, date);
    }

    @Override
    public void deleteAttendanceByIdANDDate(int id, String date) {
        studentMng.deleteAttendanceByIdANDDate(id, date);
    }

    @Override
    public boolean checkAlreadyExistingAttendance(int id, String date) {
        return studentMng.checkAlreadyExistingAttendance(id, date);
    }

    @Override
    public void updateAbsencePercentageById(int id, float newAbsencePercentage) {
        studentMng.updateAbsencePercentageById(id, newAbsencePercentage);
    }

    @Override
    public void updateStudentPasswordById(int id, String newPassword) {
       studentMng.updateStudentPasswordById(id, newPassword);
    }
    
}
