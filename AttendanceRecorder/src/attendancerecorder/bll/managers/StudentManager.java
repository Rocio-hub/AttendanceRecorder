
package attendancerecorder.bll.managers;

import attendancerecorder.be.Course;
import attendancerecorder.be.Student;
import attendancerecorder.bll.interfaces.IStudentManager;
import attendancerecorder.dal.dao.DAOStudent;
import attendancerecorder.dal.interfaces.IDAOStudent;
import java.util.List;

public class StudentManager implements IStudentManager {

    //Instance for the Data Access Object
    IDAOStudent daoStudent = new DAOStudent();

    @Override
    public List<Student> getAllStudents() {
        return daoStudent.getAllStudents();
    }

    @Override
    public List<Course> getAllCourses() {
        return daoStudent.getAllCourses();
    }

    @Override
    public void addNewAttendance(int studentId, int status, String date, String message, String dayOfWeek) {
        daoStudent.addNewAttendance(studentId, status, date, message, dayOfWeek);
    }

    @Override
    public List<Student> getAllAttendancesById(int studentId) {
        return daoStudent.getAllAttendancesById(studentId);
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

}
