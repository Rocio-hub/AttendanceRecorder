/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoTest;

import attendancerecorder.be.Student;
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
    IdaoFacade facadeTest;

    @Test //tests the login checking if both email and password match with the ones on database for a teacher
    public void testLogin() {
        facadeTest = new daoFacade();
        String email = "teacher";
        String password = "teacher";
        boolean worked = false;

        List<Teacher> teacherLst = facadeTest.getTeacherLoginData();
        for (Teacher teacher : teacherLst) {
            if (teacher.getEmail().equals(email) && teacher.getPassword().equals(password)) {
                worked = true;
            }
        }
        assertEquals(true, worked);
    }

    @Test //tests if the absent table is receiving a list of students with matching both a specific date and status
    public void testAbsentTable() {
        facadeTest = new daoFacade();
        String date = "2020-04-16";
        int status = 0;
        boolean worked = false;
        
        List<Student> studentAbsentLst = facadeTest.getStudentsOnCondition(date, status);
        if (studentAbsentLst != null) {
            worked = true;
        }
        assertEquals(true, worked);        
    }
    
    @Test //tests if the present table is receiving a list of students with matching both a specific date and status
    public void testPresentTable() {
        facadeTest = new daoFacade();
        String date = "2020-04-16";
        int status = 1;
        boolean worked = false;
        
        List<Student> studentAbsentLst = facadeTest.getStudentsOnCondition(date, status);
        if (studentAbsentLst != null) {
            worked = true;
        }
        assertEquals(true, worked);        
    }
    
    
}
    

