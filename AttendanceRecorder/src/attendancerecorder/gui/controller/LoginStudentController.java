/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendancerecorder.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mac
 */
public class LoginStudentController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXButton login;
    @FXML
    private JFXPasswordField password;
    @FXML
    private Label lbl_wrong;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbl_wrong.setId("lbl_wrong");
    }

    @FXML
    private void makeLogin(ActionEvent event) throws IOException {
        String user = username.getText();
        String pass = password.getText();
        if (user.equals("Student") && pass.equals("password")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendancerecorder/gui/view/StudentRecordAttendance.fxml"));
            Parent root = loader.load();
            StudentRecordAttendanceController srctrl = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } else {
            lbl_wrong.setText("Wrong password or username");
        }
        login.pressedProperty();
    }

    @FXML
    private void clickLogin(MouseEvent event) {
        String user = username.getText();
        String pass = password.getText();
        if (user.equals("Student") && pass.equals("password")) {
            Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
            stage.close();
        }
    }
}
