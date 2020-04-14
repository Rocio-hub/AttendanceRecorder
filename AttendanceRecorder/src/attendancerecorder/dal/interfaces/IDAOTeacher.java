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

    public void updateTeacherPasswordById(int id, String newPassword);

    public void addNewStudent(String firstName, String lastName, String email, String password);

    public List<Student> getDaysOfAbsenceById(int id);

    public List<Student> getAllAttendancesByDate(String date);

    public void insertNewStatus(List<Student> studentLst);
    
    public List<Integer> getAllStudentsIds();
    
    public List<Integer> getAllAttendancesIdsByDate(String date);
    
    public void insertNewStatusII(List<Integer> idList);

}
