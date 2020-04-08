
package attendancerecorder.bll.interfaces;

import attendancerecorder.be.Student;
import attendancerecorder.be.Teacher;
import java.util.List;

public interface ITeacherManager {

    public List<Teacher> getAllTeachers();

    public List<Teacher> getTeacherLoginData();

    public List<Student> getStudentsOnCondition(String date, int status);

    public List<Student> getAllStudentsForAbsenceOverview();

    public float getAbsenceById(int id);

    public void updateTeacherPasswordById(int id, String newPassword);
    
    public void addNewStudent(String firstName, String lastName, String email, String password);
    
    public List<Student> getDaysOfAbsenceById(int id);

}
