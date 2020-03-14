/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendancerecorder.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StudentRecordAttendanceController implements Initializable {

    @FXML
    private JFXCheckBox presentbtn;
    @FXML
    private JFXCheckBox absentbtn;
    private JFXListView<String> listview;
    @FXML
    private JFXTextArea absenttext;
    private JFXDatePicker dp;
    @FXML
    private Label lbl_popup;
    @FXML
    private JFXDatePicker datePicker;
    @FXML
    private JFXListView<?> listview_coursesTable;
    @FXML
    private JFXButton btn_confirm;
    @FXML
    private Label lbl_popup1;
    @FXML
    private Label lbl_popup2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        absenttext.visibleProperty().set(false);
        lbl_popup.setVisible(false);
        listview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listview.getItems().add("DB/OS");
        listview.getItems().add("ITO2");
        listview.getItems().add("SCO2");
        listview.getItems().add("SDE2");

    }

    private void clickPresent(ActionEvent event) {
        absentbtn.setSelected(false);
        if (dp.getValue() == null) {
            lbl_popup.setVisible(true);
        } else {
            lbl_popup.setVisible(false);
        }
    }

    private void clickAbsent(ActionEvent event) {
        presentbtn.setSelected(false);
        absenttext.visibleProperty().bind(absentbtn.selectedProperty()); //The best line ever!!!
    }

    private void clickConfirm(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendancerecorder/gui/view/Confirmation.fxml"));
        Parent root = loader.load();
        ConfirmationController cctrl = loader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void makeConfirm(MouseEvent event) {
        Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void click_present(ActionEvent event) {
    }

    @FXML
    private void click_absent(ActionEvent event) {
    }

    @FXML
    private void click_confirm(ActionEvent event) {
    }
}
