/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoTest;

import attendancerecorder.be.Teacher;
import attendancerecorder.dal.dao.daoFacade;
import attendancerecorder.dal.interfaces.IdaoFacade;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class DaoTeacherTest {
    
    public DaoTeacherTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testLogin(){
    IdaoFacade facadeTest = new daoFacade();
    String email = "t";
    String password = "t";
    boolean worked = false;
    
     List<Teacher> teacherLst = facadeTest.getTeacherLoginData();
        for (Teacher teacher : teacherLst) {
           if(teacher.getEmail().equals(email)&& teacher.getPassword().equals(password)) worked = true;
        }
         assertEquals(true, worked);
    }
}
