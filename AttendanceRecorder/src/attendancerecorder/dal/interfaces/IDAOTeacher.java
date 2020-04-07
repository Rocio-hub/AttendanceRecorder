
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

}
