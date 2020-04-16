package attendancerecorder.bll.managers;

import attendancerecorder.be.Course;
import attendancerecorder.be.Student;
import attendancerecorder.bll.interfaces.IStudentManager;
import attendancerecorder.dal.dao.DAOStudent;
import attendancerecorder.dal.dao.daoFacade;
import attendancerecorder.dal.interfaces.IDAOStudent;
import attendancerecorder.dal.interfaces.IdaoFacade;
import java.util.List;

public class StudentManager implements IStudentManager {

    //Instance for the Data Access Layer Facade
    private IdaoFacade daoFacade = new daoFacade();

    @Override
    public List<Student> getAllStudents() {
        return daoFacade.getAllStudents();
    }

    @Override
    public List<Course> getAllCourses() {
        return daoFacade.getAllCourses();
    }

    @Override
    public void addNewAttendance(int studentId, int status, String date, String message, String dayOfWeek) {
        daoFacade.addNewAttendance(studentId, status, date, message, dayOfWeek);
    }

    @Override
    public List<Student> getAllAttendancesById(int studentId) {
        return daoFacade.getAllAttendancesById(studentId);
    }

    @Override
    public Student getReasonForAbsence(int studentId, String date) {
        return daoFacade.getReasonForAbsence(studentId, date);
    }

    @Override
    public void deleteAttendanceByIdANDDate(int id, String date) {
        daoFacade.deleteAttendanceByIdANDDate(id, date);
    }

    @Override
    public boolean checkAlreadyExistingAttendance(int id, String date) {
        return daoFacade.checkAlreadyExistingAttendance(id, date);
    }

    @Override
    public void updateAbsencePercentageById(int id, float newAbsencePercentage) {
        daoFacade.updateAbsencePercentageById(id, newAbsencePercentage);
    }

    @Override
    public void updateStudentPasswordById(int id, String newPassword) {
        daoFacade.updateStudentPasswordById(id, newPassword);
    }

}
