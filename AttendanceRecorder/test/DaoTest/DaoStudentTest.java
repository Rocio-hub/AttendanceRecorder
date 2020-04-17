
package DaoTest;

import attendancerecorder.be.Student;
import attendancerecorder.dal.dao.daoFacade;
import attendancerecorder.dal.interfaces.IdaoFacade;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DaoStudentTest {

    public DaoStudentTest() {
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

    IdaoFacade facadeTest;

    @Test //tests the login checking if both email and password match with the ones on database for a student
    public void testLogin() {
        facadeTest = new daoFacade();
        String email = "student";
        String password = "student";
        boolean worked = false;

        List<Student> studentLst = facadeTest.getAllStudents();
        for (Student student : studentLst) {
            if (student.getEmail().equals(email) && student.getPassword().equals(password)) {
                worked = true;
            }
        }
        assertEquals(true, worked);
    }

    int numberOfStudents = 0;

    @Test //checks if the application is able to add a new student 
    public void addNewStudent() {
        facadeTest = new daoFacade();
        boolean worked = false;
        int studentId = 31;
        String firstName = "a";
        String lastName = "b";
        String email = "c";
        String password = "d";

        List<Student> list1 = facadeTest.getAllStudents();
        facadeTest.addNewStudent(firstName, lastName, email, password);
        List<Student> list2 = facadeTest.getAllStudents();
        
        if (list1.size() == (list2.size())-1) {
            worked = true;
        }
        assertEquals(true, worked);
    }
}
