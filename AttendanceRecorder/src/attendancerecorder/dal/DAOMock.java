/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendancerecorder.dal;

import attendancerecorder.be.Student;
import java.util.List;

public class DAOMock implements IDAOMock {

    @Override
    public List<Student> getAllStudents() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /*
    Student std1 = new Student("Rocio");
    Student std2 = new Student("Nadia");
    Student std3 = new Student("Francesco");
    Student std4 = new Student("Mario");
    Student std5 = new Student("Dmitri");
    Student std6 = new Student("Nebojsa");
    Student std7 = new Student("Reza");

    @Override
    public List<Student> getAllStudents() {
    List<Student> allStudents= new ArrayList<>();
    allStudents.add(std1);
    allStudents.add(std2);
    allStudents.add(std3);
    allStudents.add(std4);
    allStudents.add(std5);
    allStudents.add(std6);
    allStudents.add(std7);
        return allStudents;
    }
   */    
}

