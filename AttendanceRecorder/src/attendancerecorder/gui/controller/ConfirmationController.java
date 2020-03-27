/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendancerecorder.gui.controller;

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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ConfirmationController implements Initializable {

    
    @FXML
    private Button btn_close;
    @FXML
    private Button btn_view_overall_attendance;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_view_overall_attendance.setId("btn_view_overall_attendance");
        
    }

    @FXML
    private void clickOverallAttendance(ActionEvent event) throws IOException {
        if (btn_view_overall_attendance != null) {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendancerecorder/gui/view/OverallAttendance.fxml"));
//            Parent root = loader.load();
//            OverallAttendanceController oactrl = loader.getController();
//
//            Scene scene = new Scene(root);
//            Stage stage = new Stage();
//            stage.setScene(scene); FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendancerecorder/gui/view/OverallAttendance.fxml"));
//            Parent root = loader.load();
//            OverallAttendanceController oactrl = loader.getController();
//
//            Scene scene = new Scene(root);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.show();
//            stage.show();
        }
    }

    @FXML
    private void makeOverallAttendance(MouseEvent event) {
        Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clickClose(ActionEvent event) {
        Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }
}
