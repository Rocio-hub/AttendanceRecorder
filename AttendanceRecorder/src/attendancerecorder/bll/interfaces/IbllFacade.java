
package attendancerecorder.bll.interfaces;

import attendancerecorder.be.Course;
import attendancerecorder.be.Student;
import attendancerecorder.be.Teacher;
import java.util.List;


public interface IbllFacade {
    
    public List<Student> getAllStudents();

    public List<Course> getAllCourses();

    public void addNewAttendance(int studentId, int status, String date, String message, String dayOfWeek);

    public List<Student> getAllAttendancesById(int studentId);

    public Student getReasonForAbsence(int studentId, String date);

    public void deleteAttendanceByIdANDDate(int id, String date);

    public boolean checkAlreadyExistingAttendance(int id, String date);
    
    public void updateAbsencePercentageById(int id, float newAbsencePercentage);
    
    public void updateStudentPasswordById(int id, String newPassword);
    
    public List<Teacher> getAllTeachers();

    public List<Teacher> getTeacherLoginData();

    public List<Student> getStudentsOnCondition(String date, int status);

    public List<Student> getAllStudentsForAbsenceOverview();

    public float getAbsenceById(int id);

    public void updateTeacherPasswordById(int id, String newPassword);
    
    public void addNewStudent(String firstName, String lastName, String email, String password);
    
    public List<Student> getDaysOfAbsenceById(int id);
    
    public List<Integer> getAllStudentsIds();
    
    public List<Integer> getAllAttendancesIdsByDate(String date);
    
    public void insertNewStatus(List<Integer> idList);
}
