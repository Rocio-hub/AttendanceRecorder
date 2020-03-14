/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendancerecorder.gui.controller;

import attendancerecorder.be.Course;
import attendancerecorder.bll.interfaces.IStudentManager;
import attendancerecorder.bll.managers.StudentManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StudentRecordAttendanceController implements Initializable {

   IStudentManager iStudentManager = new StudentManager();
    private ObservableList<Course> courseLst;
    
    @FXML
    private JFXTextArea absenttext;
    @FXML
    private JFXDatePicker datePicker;
    @FXML
    private JFXButton btn_confirm;
    @FXML
    private Label lbl_popup;
    @FXML
    private Label lbl_popup1;
    @FXML
    private Label lbl_popup2;
    @FXML
    private JFXCheckBox cb_present;
    @FXML
    private JFXCheckBox cb_absent;
    @FXML
    private JFXListView<Course> lv_coursesTable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        absenttext.visibleProperty().set(false);
        lbl_popup.setVisible(false);
        lv_coursesTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        courseLst = FXCollections.observableArrayList(iStudentManager.getAllCourses());
      

//        lv_coursesTable.getItems().add("DB/OS");
//        lv_coursesTable.getItems().add("ITO2");
//        lv_coursesTable.getItems().add("SCO2");
//        lv_coursesTable.getItems().add("SDE2");
    }

    @FXML
    private void makeConfirm(MouseEvent event) {
        Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void click_present(ActionEvent event) {
        cb_absent.setSelected(false);
        if (datePicker.getValue() == null) {
            lbl_popup.setVisible(true);
        } else {
            lbl_popup.setVisible(false);
        }
    }

    @FXML
    private void click_absent(ActionEvent event) {
        cb_present.setSelected(false);
        absenttext.visibleProperty().bind(cb_absent.selectedProperty()); //The best line ever!!!
    }

    @FXML
    private void click_confirm(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendancerecorder/gui/view/Confirmation.fxml"));
        Parent root = loader.load();
        ConfirmationController cctrl = loader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
