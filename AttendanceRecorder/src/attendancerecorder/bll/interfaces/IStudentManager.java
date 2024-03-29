
package attendancerecorder.bll.interfaces;

import attendancerecorder.be.Course;
import attendancerecorder.be.Student;
import java.util.List;

public interface IStudentManager {

    public List<Student> getAllStudents();

    public List<Course> getAllCourses();

    public void addNewAttendance(int studentId, int status, String date, String message, String dayOfWeek);

    public List<Student> getAllAttendancesById(int studentId);

    public Student getReasonForAbsence(int studentId, String date);

    public void deleteAttendanceByIdANDDate(int id, String date);

    public boolean checkAlreadyExistingAttendance(int id, String date);
    
    public void updateAbsencePercentageById(int id, float newAbsencePercentage);
    
    public void updateStudentPasswordById(int id, String newPassword);

}
