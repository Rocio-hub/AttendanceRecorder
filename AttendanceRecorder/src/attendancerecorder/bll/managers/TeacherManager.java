/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendancerecorder.bll.managers;

import attendancerecorder.be.Teacher;
import attendancerecorder.bll.interfaces.ITeacherManager;
import attendancerecorder.dal.dao.DAOTeacher;
import attendancerecorder.dal.interfaces.IDAOTeacher;
import java.util.List;


public class TeacherManager implements ITeacherManager {
    IDAOTeacher daoTeacher = new DAOTeacher();
    @Override
    public List<Teacher> getAllTeachers() {
        return daoTeacher.getAllTeachers();
    }

    @Override
    public List<Teacher> getTeacherLoginData() {
      return daoTeacher.getTeacherLoginData();
    }
    
}