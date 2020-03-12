/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendancerecorder.gui.controller;

import attendancerecorder.bll.managers.SimpleManager;
import attendancerecorder.be.Student;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TeacherAttendanceOverviewController implements Initializable {

    //SimpleManager manager = new SimpleManager();

    @FXML
    private JFXDatePicker calendar;
    @FXML
    private Label reasonForAbsence;
    @FXML
    private Button exit;
    @FXML
    private Label percentageOfAbsence;
    @FXML
    private Label teacherName;
    @FXML
    private AnchorPane pane;
    @FXML
    private Label className;
    @FXML
    private TableView<Student> students;
    @FXML
    private TableColumn<String, String> studentsColumn;
    @FXML
    private Label text2;
    @FXML
    private Label text1;
    @FXML
    private Label text3;
    @FXML
    private Label lblStatus;
    @FXML
    private Label lbl_popup;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbl_popup.setVisible(false);
        text1.setVisible(false);
        reasonForAbsence.setVisible(false);
        text2.setVisible(false);
        percentageOfAbsence.setVisible(false);
        text3.setId("text3");
        exit.setId("exit");
        reasonForAbsence.setId("reasonForAbsence");
        className.setId("className");
        teacherName.setId("teacherName");
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
    private void clickClose(ActionEvent event) {
        Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }
}
