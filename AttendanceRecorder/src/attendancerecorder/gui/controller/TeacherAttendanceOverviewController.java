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
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
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

    IStudentManager studentMng = new StudentManager();
    ITeacherManager TeacherMng = new TeacherManager();
    private ObservableList<Student> presentStudents;
    private ObservableList<Student> absentStudents;
    String teacherName;

    @FXML
    private AnchorPane pane;
    @FXML
    private Label className;
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
    private TableColumn<Student, String> tableview_absent;
    @FXML
    private JFXButton btn_search;
    @FXML
    private Label lbl_messageForAbsence;
    @FXML
    private TableView<Student> tc_present;
    @FXML
    private TableView<Student> tc_absent;
    @FXML
    private Label lbl_percentageOfAbsence;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbl_popup.setVisible(false);
        lbl_reasonForAbsence.setVisible(false);
        lbl_messageForAbsence.setVisible(false);
        lbl_percentageOfAbsence.setVisible(true);
        btn_close.setId("exit");
        lbl_messageForAbsence.setId("messageForAbsence");
        className.setId("className");
        lbl_teacherName.setId("teacherName");
    }

    @FXML
    private void click_close(ActionEvent event) {
        Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void click_search(ActionEvent event) {
        String date = datePicker.getValue().toString();
        presentStudents = FXCollections.observableArrayList(TeacherMng.getStudentsOnCondition(date, 1));
        absentStudents = FXCollections.observableArrayList(TeacherMng.getStudentsOnCondition(date, 0));
        tableview_present.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tableview_absent.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tc_present.setItems(presentStudents);
        tc_absent.setItems(absentStudents);
    }

    public void getTeacherName(String name) {
        this.teacherName = name;
        lbl_teacherName.setText(teacherName);
    }

    private void click_showStudentReason(ActionEvent event) {
        if (tc_absent.getSelectionModel().getSelectedItem() != null) {
            lbl_reasonForAbsence.setVisible(true);
            lbl_messageForAbsence.setVisible(true);
            lbl_messageForAbsence.setText(tc_absent.getSelectionModel().getSelectedItem().getMessage());
        } else {
            lbl_reasonForAbsence.setVisible(false);
            lbl_messageForAbsence.setVisible(false);
        }
    }

    @FXML
    private void click_selectedPresentStudent(MouseEvent event) {
        tc_absent.getSelectionModel().clearSelection();
        lbl_reasonForAbsence.setVisible(false);
        lbl_messageForAbsence.setVisible(false);
        calculateOverallAbsentAttendanceById(tc_present.getSelectionModel().getSelectedItem().getId());
    }

    @FXML
    private void click_selectedAbsentStudent(MouseEvent event) {
        tc_present.getSelectionModel().clearSelection();
        lbl_reasonForAbsence.setVisible(true);
        lbl_messageForAbsence.setVisible(true);
        lbl_messageForAbsence.setText(tc_absent.getSelectionModel().getSelectedItem().getMessage());
        calculateOverallAbsentAttendanceById(tc_absent.getSelectionModel().getSelectedItem().getId());
    }

    private void calculateOverallAbsentAttendanceById(int id) {
        List<Student> studentLst = new ArrayList();
        studentLst = studentMng.getAllAttendancesById(id);
        double counterPresent = 0;
        double counterAbsent = 0;
        double sum;

        for (Student student : studentLst) {
            if (student.getStatus() == 1) {
                counterPresent++;
            } else {
                counterAbsent++;
            }
        }

        sum = counterPresent + counterAbsent;
        double absentPercentage = 100 - ((counterPresent * 100) / sum);
        absentPercentage = Math.floor(absentPercentage * 100) / 100;
        lbl_percentageOfAbsence.setText(String.valueOf(absentPercentage));
    }
}
