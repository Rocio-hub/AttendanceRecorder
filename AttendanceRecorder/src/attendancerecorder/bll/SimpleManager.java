/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendancerecorder.bll;

import attendancerecorder.dal.DAOMock;
import attendancerecorder.be.Student;
import java.util.List;

/**
 *
 * @author mac
 */
public class SimpleManager {
    DAOMock daoMock = new DAOMock();

public List<Student> getAllStudents(){
    List<Student> allStudents = daoMock.getAllStudents();
    return allStudents;

}





}
