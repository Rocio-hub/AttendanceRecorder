package attendancerecorder.bll.managers;

import attendancerecorder.be.Student;
import attendancerecorder.be.Teacher;
import attendancerecorder.bll.interfaces.ITeacherManager;
import attendancerecorder.dal.dao.DAOTeacher;
import attendancerecorder.dal.dao.daoFacade;
import attendancerecorder.dal.interfaces.IDAOTeacher;
import attendancerecorder.dal.interfaces.IdaoFacade;
import java.util.List;

public class TeacherManager implements ITeacherManager {

    //Instance for the Data Access Layer Facade
    IdaoFacade daoFacade = new daoFacade();

    @Override
    public List<Teacher> getAllTeachers() {
        return daoFacade.getAllTeachers();
    }

    @Override
    public List<Teacher> getTeacherLoginData() {
        return daoFacade.getTeacherLoginData();
    }

    @Override
    public List<Student> getStudentsOnCondition(String date, int status) {
        return daoFacade.getStudentsOnCondition(date, status);
    }

    @Override
    public List<Student> getAllStudentsForAbsenceOverview() {
        return daoFacade.getAllStudentsForAbsenceOverview();
    }

    @Override
    public float getAbsenceById(int id) {
        return daoFacade.getAbsenceById(id);
    }

    @Override
    public void updateTeacherPasswordById(int id, String newPassword) {
        daoFacade.updateTeacherPasswordById(id, newPassword);
    }

    @Override
    public void addNewStudent(String firstName, String lastName, String email, String password) {
        daoFacade.addNewStudent(firstName, lastName, email, password);
    }
    @Override
     public List<Student> getDaysOfAbsenceById(int id){
         return daoFacade.getDaysOfAbsenceById(id);
     }

    @Override
    public List<Integer> getAllStudentsIds() {
      return daoFacade.getAllStudentsIds();
    }

    @Override
    public List<Integer> getAllAttendancesIdsByDate(String date) {
        return daoFacade.getAllAttendancesIdsByDate(date);
    }

    @Override
    public void insertNewStatus(List<Integer> idList) {
        daoFacade.insertNewStatus(idList);
    }
    
    
    
    
    
}
