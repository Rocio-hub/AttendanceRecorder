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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOTeacher implements IDAOTeacher {

    private SQLServerDataSource ds;

    public DAOTeacher() {
        ds = new SQLServerDataSource();
        ds.setDatabaseName("AttendanceRecorder");
        ds.setUser("CSe19B_2");
        ds.setPassword("CSe19B_2");
        ds.setServerName("10.176.111.31");
        ds.setPortNumber(1433);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        try ( Connection con = ds.getConnection()) {
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
        try ( Connection con = ds.getConnection()) {
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
    public List<Student> getStudentsOnCondition(String date, int status) {
        try ( Connection con = ds.getConnection()) {

            List<Student> studentLst = new ArrayList();
            String sql = "SELECT Students.id, Students.firstName, Students.lastName, Attendance.status, Attendance.message FROM Students JOIN Attendance ON id=studentId WHERE date = ? AND status = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, date);
            pstmt.setInt(2, status);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String message = rs.getString("message");
                String fullName = firstName + " " + lastName;
                Student student = new Student(id, fullName, message);
                studentLst.add(student);
            }

            return studentLst;
        } catch (SQLServerException sqlse) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, sqlse);
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Student> getAllStudentsForAbsenceOverview() {
        try ( Connection con = ds.getConnection()) {
            String sql = "SELECT id, firstName, lastName, absencePercentage, email FROM Students ORDER BY absencePercentage DESC";
            List<Student> studentLst = new ArrayList();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String fullName = firstName + " " + lastName;
                float absencePercentage = rs.getFloat("absencePercentage");
                String email = rs.getString("email");
                Student student = new Student(id, fullName, absencePercentage, email);
                studentLst.add(student);
            }

            return studentLst;
        } catch (SQLServerException sqlse) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, sqlse);
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public float getAbsenceById(int id) {
        try ( Connection con = ds.getConnection()) {
            float absencePercentage = 0;
            String sql = "SELECT absencePercentage FROM Students WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                absencePercentage = rs.getFloat("absencePercentage");
            }
            return absencePercentage;

        } catch (SQLServerException sqlse) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, sqlse);
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void updatePasswordById(int id, String newPassword) {
        try ( Connection con = ds.getConnection()) {
            String sql = "UPDATE Students SET password = ? WHERE id = ? ";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, newPassword);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();

        } catch (SQLServerException sqlse) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, sqlse);
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateTeacherPasswordById(int id, String newPassword) {
        try ( Connection con = ds.getConnection()) {
            String sql = "UPDATE Teachers SET password = ? WHERE id = ? ";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, newPassword);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();

        } catch (SQLServerException sqlse) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, sqlse);
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addNewStudent(String firstName, String lastName, String email, String password) {
        try ( Connection con = ds.getConnection()) {
            String sql = "INSERT INTO Students (firstName, lastName, email, password) values (?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setString(4, password);
            pstmt.executeUpdate();

        } catch (SQLServerException sqlse) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, sqlse);
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Student> getDaysOfAbsenceById(int id) {
        try ( Connection con = ds.getConnection()) {
            List<Student> studentLst = new ArrayList();
            String sql = "SELECT Students.id ,Attendance.dayOfWeek FROM Students JOIN Attendance ON id=studentId WHERE Students.id = ? AND status = 0";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int studentId = rs.getInt("id");
                String dayOfWeek = rs.getString("dayOfWeek");
                Student student = new Student(studentId, dayOfWeek);
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
    public List<Student> getAllAttendancesByDate(String date) {
        try ( Connection con = ds.getConnection()) {
            List<Student> studentLst = new ArrayList();
            String sql = "SELECT studentId, status, date FROM Attendance WHERE date = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, date);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int studentId = rs.getInt("studentId");
                int status = rs.getInt("status");
                String newDate = rs.getString("date");
                Student student = new Student(studentId, status, newDate);
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
    public void insertNewStatus(List<Student> studentLst) {
        try ( Connection con = ds.getConnection()) {
            String sql = "INSERT INTO Attendance (studentId, status, date, dayOfWeek) values (?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);     
                for (Student student : studentLst) {
                    pstmt.setInt(1, student.getId());
                    pstmt.setInt(2, 0);
                    pstmt.setString(3, getCurrentDate().toString());
                    pstmt.setString(4, getCurrentDate().getDayOfWeek().toString());
                    pstmt.executeUpdate();
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public LocalDate getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate now = LocalDate.now();
        return now;
    }

}
