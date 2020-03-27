/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendancerecorder.gui.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class WelcomeWindowController implements Initializable {

//    private Label lbl1;
//    private Label lbl2;
    @FXML
    private Button btn_close;
    @FXML
    private AnchorPane pane_1;
    @FXML
    private JFXButton btn_student;
    @FXML
    private JFXButton btn_teacher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_student.setId("btn_student");
        btn_teacher.setId("btn_teacher");
        btn_close.setId("btn_close");

//        lbl1.setId("lbl1");
//        lbl2.setId("lbl2");
    }

    @FXML
    private void click_student(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendancerecorder/gui/view/LoginStudent.fxml"));
        Parent root = loader.load();
        LoginStudentController ctrls = loader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Student Log-in");
        stage.show();
    }

    @FXML
    private void click_teacher(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendancerecorder/gui/view/LoginTeacher.fxml"));
        Parent root = loader.load();
        LoginTeacherController ctrl = loader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Teacher Log-in");
        stage.show();
    }

    @FXML
    private void click_close(ActionEvent event) {
        Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }
}
