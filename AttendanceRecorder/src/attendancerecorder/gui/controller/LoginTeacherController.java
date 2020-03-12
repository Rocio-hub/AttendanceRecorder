/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendancerecorder.gui.controller;

import attendancerecorder.be.Teacher;
import attendancerecorder.bll.interfaces.ITeacherManager;
import attendancerecorder.bll.managers.TeacherManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginTeacherController implements Initializable {

    ITeacherManager teacherMan = new TeacherManager();
    @FXML
    private JFXTextField user;
    @FXML
    private JFXButton loginT;
    @FXML
    private JFXPasswordField pass;
    @FXML
    private Label wrongpass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        wrongpass.setId("wrongpass");
    }

    @FXML
    private void makeLoginTeacher(ActionEvent event) throws IOException {
        boolean found = false;
        List<Teacher> teacherList = teacherMan.getTeacherLoginData();
        for (Teacher teacher : teacherList) {
            if (user.getText().equals(teacher.getEmail()) && pass.getText().equals(teacher.getPassword())) {
                found = true;
            }
        }
        if (found) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendancerecorder/gui/view/TeacherAttendanceOverview.fxml"));

            Parent root = loader.load();
            TeacherAttendanceOverviewController tactrl = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } else {
            wrongpass.setText("Wrong password or username");
        }
        loginT.pressedProperty();
    }

    @FXML
    private void clickLogin(MouseEvent event) {
        boolean found = false;
        List<Teacher> teacherList = teacherMan.getTeacherLoginData();
        for (Teacher teacher : teacherList) {
            if (user.getText().equals(teacher.getEmail()) && pass.getText().equals(teacher.getPassword())) {
                found = true;
            }
        }
        if (found) {
            Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
            stage.close();
        }
    }
}
