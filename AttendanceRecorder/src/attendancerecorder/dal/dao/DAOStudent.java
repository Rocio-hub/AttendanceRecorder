/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendancerecorder.dal.dao;

import attendancerecorder.be.Course;
import attendancerecorder.be.Student;
import attendancerecorder.dal.interfaces.IDAOStudent;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
            String sql = "SELECT id, firstName, lastName, email, password FROM Students";
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
            String sql = "SELECT  id, email, password FROM Students";
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

    @Override
    public List<Course> getAllCourses() {
        try (Connection con = ds.getConnection()) {
            String sql = "SELECT id, name FROM Courses";
            List<Course> courseLst = new ArrayList();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                
                Course course = new Course(id, name);
                courseLst.add(course);
            }
            return courseLst;
        } catch (SQLServerException sqlse) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, sqlse);
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;        
    }
    
        public void addMovie(String name, float personalrating, float imdbrating, int lastview, String filelink, String imdbbrowser) {
        
        try (Connection con = ds.getConnection()) {
            String sql = "INSERT INTO Movies (name, personalrating, imdbrating, lastview, filelink,imdbbrowser) values (?,?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, name);
            pstmt.setFloat(2, personalrating);
            pstmt.setFloat(3, imdbrating);
            pstmt.setInt(4, lastview);
            pstmt.setString(5, filelink);
            pstmt.setString(6, imdbbrowser);
            pstmt.executeUpdate();

        } catch (SQLServerException ex) {
            Logger.getLogger(DAOMovie.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOMovie.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
