package attendancerecorder.gui.controller;

import attendancerecorder.be.Student;
import attendancerecorder.bll.interfaces.IStudentManager;
import attendancerecorder.bll.interfaces.ITeacherManager;
import attendancerecorder.bll.managers.StudentManager;
import attendancerecorder.bll.managers.TeacherManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 */
public class TeacherAttendanceOverviewController implements Initializable {

    //Instances for the business logic layer
    IStudentManager studentMng = new StudentManager();
    ITeacherManager teacherMng = new TeacherManager();

    //Needed variables
    private ObservableList<Student> presentStudents;
    private ObservableList<Student> absentStudents;
    String teacherName;
    int idFromLogin;
    boolean isStudent;

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
    private TableColumn<Student, String> tableview_present;
    @FXML
    private Label lbl_reasonForAbsence;
    @FXML
    private Label lbl_teacherName;
    @FXML
    private TableColumn<Student, String> tableview_absent;
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
        //labels are not visible at first and alert is set
        lbl_popup.setVisible(false);
        lbl_reasonForAbsence.setVisible(false);
        lbl_messageForAbsence.setVisible(false);
        lbl_percentageOfAbsence.setVisible(true);
        text2.setVisible(false);
//        absentStudentsAlert();
    }

    @FXML
    private void click_close(ActionEvent event) {
        Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void click_search(ActionEvent event) {
        String date = datePicker.getValue().toString();
        presentStudents = FXCollections.observableArrayList(teacherMng.getStudentsOnCondition(date, 1));
        absentStudents = FXCollections.observableArrayList(teacherMng.getStudentsOnCondition(date, 0));
        tableview_present.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tableview_absent.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tc_present.setItems(presentStudents);
        tc_absent.setItems(absentStudents);
    }

    public void getTeacherName(String name) {
        teacherName = name;
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
        text2.setVisible(true);
        getAbsenceById(tc_present.getSelectionModel().getSelectedItem().getId());
    }

    @FXML
    private void click_selectedAbsentStudent(MouseEvent event) {
        tc_present.getSelectionModel().clearSelection();
        lbl_reasonForAbsence.setVisible(true);
        lbl_messageForAbsence.setVisible(true);
        text2.setVisible(true);
        lbl_messageForAbsence.setText(tc_absent.getSelectionModel().getSelectedItem().getMessage());
        getAbsenceById(tc_absent.getSelectionModel().getSelectedItem().getId());
    }

    
    /*WORKING ALERT. COMMENTED BC ANNOYING
      private void absentStudentsAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informational dialog");
        alert.setHeaderText("There are some students with the absent percentage above X%");
        List<String> list = new ArrayList();
        for (int i = 0; i < 32; i++) {
            if (calculateOverallAbsentAttendanceById(i) >= 40) {

                String normal = "Student with id: " + i + "has " + calculateOverallAbsentAttendanceById(i) + " % of absence\n";
                list.add(normal);
            }
        }
        alert.setContentText(list.toString());
        alert.showAndWait();
    }*/
    
    private void getAbsenceById(int id) {
        float absenceById = teacherMng.getAbsenceById(id);
        lbl_percentageOfAbsence.setText(String.valueOf(absenceById));
    }

    @FXML
    private void click_summarizedAttendance(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendancerecorder/gui/view/SummarizedAttendance.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Summarized Attendance");
        stage.show();
    }

    public void getTeacherId(int id) {
        idFromLogin = id;
    }

    @FXML
    private void click_changePassword(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendancerecorder/gui/view/ChangePassword.fxml"));
        Parent root = loader.load();
        ChangePasswordController cpctrl = loader.getController();
        cpctrl.getStudentId(idFromLogin);
        cpctrl.getIsStudent(isStudent);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Change Password");
        stage.show();
    }

    public void getIsStudent(boolean isStudent) {
        this.isStudent = isStudent;
    }

    @FXML
    private void click_addNewStudent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendancerecorder/gui/view/AddNewStudent.fxml"));
        Parent root = loader.load();


        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Add a new student");
        stage.show();
    }
}
