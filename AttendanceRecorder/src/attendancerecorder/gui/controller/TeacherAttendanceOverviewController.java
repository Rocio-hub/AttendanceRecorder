/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendancerecorder.gui.controller;

import attendancerecorder.be.Student;
import attendancerecorder.bll.interfaces.ITeacherManager;
import attendancerecorder.bll.managers.TeacherManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TeacherAttendanceOverviewController implements Initializable {
    
    ITeacherManager TeacherMng = new TeacherManager();
    private ObservableList<Student> presentStudents;
    private ObservableList<Student> absentStudents;
    String teacherName;
    
    @FXML
    private Label percentageOfAbsence;
    @FXML
    private AnchorPane pane;
    @FXML
    private Label className;
    @FXML
    private TableView<Student> students;
    @FXML
    private Label text2;
    @FXML
    private Label lbl_popup;
    @FXML
    private JFXDatePicker datePicker;
    @FXML
    private JFXButton btn_close;
    @FXML
    private TableColumn<Student, String> tableview_present;
    @FXML
    private Label lbl_reasonForAbsence;
    @FXML
    private Label lbl_teacherName;
    @FXML
    private TableView<Student> students1;
    @FXML
    private TableColumn<Student, String> tableview_absent;
    @FXML
    private JFXButton btn_search;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbl_popup.setVisible(false);
//        text1.setVisible(false);
        lbl_reasonForAbsence.setVisible(false);
//        text2.setVisible(false);
        percentageOfAbsence.setVisible(false);
//        text3.setId("text3");
        btn_close.setId("exit");
        lbl_reasonForAbsence.setId("reasonForAbsence");
        className.setId("className");
        lbl_teacherName.setId("teacherName");
        lbl_teacherName.setText(teacherName);
     
      
     
      //  System.out.println(a);
        
        
//        ObservableList<Student> tableItems = FXCollections.observableArrayList(manager.getAllStudents());
//        studentsColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
//        students.setItems(tableItems);
    }

    @FXML
    private void selectedStudent(MouseEvent event) {/*
        String studentName = students.getSelectionModel().getSelectedItem().getName();
        String value = calendar.getValue().toString();
        if (studentName != null && value != null) {
            if (studentName.equals("Rocio") || studentName.equals("Nadia") || studentName.equals("Francesco")) {
                text2.setVisible(true);
                text1.setVisible(false);
                reasonForAbsence.setVisible(false);
                percentageOfAbsence.setVisible(true);
                lblStatus.setId("lblStatusGreen");
                reasonForAbsence.setText("");
                percentageOfAbsence.setText("12");
            } else {
                text1.setVisible(true);
                reasonForAbsence.setVisible(true);
                text2.setVisible(true);
                percentageOfAbsence.setVisible(true);
                reasonForAbsence.setText("I was sick");
                lblStatus.setId("lblStatusRed");
                percentageOfAbsence.setText("24");
            }
        } else {
            lbl_popup.setVisible(true);
        }
*/
    }

  

    @FXML
    private void click_close(ActionEvent event) {
        Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void click_search(ActionEvent event) {
         String date = datePicker.getValue().toString();
          presentStudents =   FXCollections.observableArrayList(TeacherMng.getStudentsOnCondition(date, 1));
        absentStudents =   FXCollections.observableArrayList(TeacherMng.getStudentsOnCondition(date, 0));
       tableview_present.setCellValueFactory(new PropertyValueFactory<>("firstName"));
       tableview_absent.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        students.setItems(presentStudents);
        students1.setItems(absentStudents);
        
    }
    public void getTeacherName(String name){
        this.teacherName=name;
    }

}
