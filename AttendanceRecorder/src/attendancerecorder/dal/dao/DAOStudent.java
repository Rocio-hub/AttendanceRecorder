/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendancerecorder.dal.dao;

import attendancerecorder.be.Student;
import attendancerecorder.dal.interfaces.IDAOStudent;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOStudent implements IDAOStudent {
    
    private final SQLServerDataSource ds;

    // set up connection to the Database
    public DAOStudent() {
        ds = new SQLServerDataSource();
        ds.setDatabaseName("AttendanceRecorder");
        ds.setUser("CSe19B_2");
        ds.setPassword("CSe19B_2");
        ds.setServerName("10.176.111.31");
        ds.setPortNumber(1433);
    }

    @Override
    public List<Student> getAllStudents() {
        try (Connection con = ds.getConnection()) {
            String sql = "SELECT id, firstName, lastName, email, password FROM Student";
            List<Student> studentLst = new ArrayList();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String password = rs.getString("password");                

                Student student = new Student(id, firstName, lastName, email, password);
                studentLst.add(student);
            }
            return studentLst;
        } catch (SQLServerException sqlse) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, sqlse);
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Student> getStudentLoginData() {
        try (Connection con = ds.getConnection()) {
            String sql = "SELECT id, email, password FROM Student";
            List<Student> studentLst = new ArrayList();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String password = rs.getString("password");                

                Student student = new Student(id, email, password);
                studentLst.add(student);
            }
            return studentLst;
        } catch (SQLServerException sqlse) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, sqlse);
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
