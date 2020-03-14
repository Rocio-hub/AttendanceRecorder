/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendancerecorder.dal.dao;

import attendancerecorder.be.Student;
import attendancerecorder.dal.interfaces.IDAOTeacher;
import attendancerecorder.be.Teacher;
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


public class DAOTeacher implements IDAOTeacher{

    private SQLServerDataSource ds;
    
    public DAOTeacher(){
     ds = new SQLServerDataSource();
        ds.setDatabaseName("AttendanceRecorder");
        ds.setUser("CSe19B_2");
        ds.setPassword("CSe19B_2");
        ds.setServerName("10.176.111.31");
        ds.setPortNumber(1433);
    }
    @Override
    public List<Teacher> getAllTeachers() {
       try (Connection con = ds.getConnection()) {
            String sql = "SELECT id, name, email, password FROM Teachers";
            List<Teacher> teacherLst = new ArrayList();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                Teacher teacher = new Teacher(id, name, email, password);
                teacherLst.add(teacher);
            }
            return teacherLst;
        } catch (SQLServerException sqlse) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, sqlse);
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Teacher> getTeacherLoginData() {
        try (Connection con = ds.getConnection()) {
            String sql = "SELECT email, password FROM Teachers";
            List<Teacher> teacherLst = new ArrayList();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String email = rs.getString("email");
                String password = rs.getString("password");
                Teacher teacher = new Teacher(email, password);
                teacherLst.add(teacher);
            }
            return teacherLst;
        } catch (SQLServerException sqlse) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, sqlse);
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Student> getStudentsOnCondition(String date, int present) {
         try (Connection con = ds.getConnection()) {
             List<Student> studentLst = new ArrayList();
            // String sql = "SELECT firstName From Students join Attendance on id=studentId where date=? and present=? ";
           
             Student student = new Student("caracol");
             studentLst.add(student);
            return studentLst;
        } catch (SQLServerException sqlse) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, sqlse);
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

   
    }
    

