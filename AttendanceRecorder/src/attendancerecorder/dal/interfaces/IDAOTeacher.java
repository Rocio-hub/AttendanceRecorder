/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendancerecorder.dal.interfaces;

import attendancerecorder.be.Teacher;
import java.util.List;


public interface IDAOTeacher {
    
    public List <Teacher>getAllTeachers();
}