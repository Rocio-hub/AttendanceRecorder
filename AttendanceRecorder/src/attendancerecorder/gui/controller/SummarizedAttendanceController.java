/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendancerecorder.gui.controller;

import attendancerecorder.be.Student;
import attendancerecorder.bll.interfaces.IStudentManager;
import attendancerecorder.bll.interfaces.ITeacherManager;
import attendancerecorder.bll.managers.StudentManager;
import attendancerecorder.bll.managers.TeacherManager;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 */
public class SummarizedAttendanceController implements Initializable {

    ITeacherManager teacherMng = new TeacherManager();    
    //IStudentManager teacherMng = new TeacherManager();    
    private ObservableList<Student> studentLst;
    @FXML
    private TableView<Student> tv_summarizedAttendance;
    @FXML
    private TableColumn<Student, String> tc_name;
    @FXML
    private TableColumn<Student, String> tc_percentage;
    @FXML
    private JFXButton btn_contact;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        studentLst = FXCollections.observableArrayList();
        tc_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tc_percentage.setCellValueFactory(new PropertyValueFactory<>("percentage"));
        tv_summarizedAttendance.setItems(studentLst);
     }    

    @FXML
    private void click_contact(ActionEvent event) {
    }
    
//     private double calculateOverallAbsentAttendance() {
//        List<Student> studentLst = teacherMng.getAllStudentsForAbsenceOverview();
//        double counterPresent = 0;
//        double counterAbsent = 0;
//        double sum;
//
//        for (Student student : studentLst) {
//            if (student.getStatus() == 1) {
//                counterPresent++;
//            } else {
//                counterAbsent++;
//            }
//        }
//
//        sum = counterPresent + counterAbsent;
//        double absentPercentage = 100 - ((counterPresent * 100) / sum);
//        absentPercentage = Math.floor(absentPercentage * 100) / 100;
//        return absentPercentage;
//    }
     
     
   
            
}
