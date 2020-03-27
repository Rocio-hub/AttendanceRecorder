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
        try ( Connection con = ds.getConnection()) {
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
    public List<Course> getAllCourses() {
        try ( Connection con = ds.getConnection()) {
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

    public void addNewAttendance(int studentId, int status, String date, String message) {

        try ( Connection con = ds.getConnection()) {
            String sql = "INSERT INTO Attendance (studentId, status, date, message) values (?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, studentId);
            pstmt.setInt(2, status);
            pstmt.setString(3, date);
            pstmt.setString(4, message);
            pstmt.executeUpdate();

        } catch (SQLServerException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Student> getAllAttendancesById(int studentId) {

        try ( Connection con = ds.getConnection()) {
            String sql = "SELECT status FROM Attendance WHERE studentId = ?";
            List<Student> studentLst = new ArrayList();

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, studentId);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int status = rs.getInt("status");

                Student student = new Student(studentId, status);
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
    public Student getReasonForAbsence(int studentId, String date) {
        try ( Connection con = ds.getConnection()) {
            String sql = "SELECT studentId, status, date, message FROM Attendance JOIN Students ON Attendance.studentId = Students.id WHERE studentId = ? AND date = ?";
            Student student = new Student();

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, studentId);
            pstmt.setString(2, date);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int status = rs.getInt("status");
                String message = rs.getString("message");

                student.setStatus(status);
                student.setMessage(message);
            }
            return student;
        } catch (SQLServerException sqlse) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, sqlse);
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean checkAlreadyExistingAttendance(int id, String date) {
        try (Connection con = ds.getConnection()) {
            String sql = "SELECT studentId, date FROM Attendance JOIN Students ON Students.id  = Attendance.studentId WHERE studentId = ? AND date = ?";

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, id);
            pstmt.setString(2, date);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public void deleteAttendanceByIdANDDate(int id, String date) {
        try (Connection con = ds.getConnection()) {
            String sql = "DELETE FROM Attendance WHERE studentId = ? AND date = ?";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, date);
            pstmt.executeUpdate();

        } catch (SQLServerException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
