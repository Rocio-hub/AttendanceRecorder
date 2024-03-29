package attendancerecorder.gui.controller;

import attendancerecorder.be.Student;
import attendancerecorder.bll.interfaces.IbllFacade;
import attendancerecorder.bll.managers.bllFacade;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 */
public class TeacherAttendanceOverviewController implements Initializable {

    //Instances for the business logic layer
    private IbllFacade bllFacade = new bllFacade();

    //Needed variables
    private ObservableList<Student> presentStudents;
    private ObservableList<Student> absentStudents;
    private String teacherName;
    private int idFromLogin;
    private boolean isStudent;

    @FXML
    private AnchorPane pane;
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
    private TableView<Student> tc_present;
    @FXML
    private TableView<Student> tc_absent;
    @FXML
    private Label lbl_percentageOfAbsence;
    @FXML
    private JFXButton btn_small;
    @FXML
    private JFXButton btn_smallFontSize;
    @FXML
    private TextArea textarea;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //labels are not visible at first and alert is set
        lbl_popup.setVisible(false);
        lbl_reasonForAbsence.setVisible(false);
        textarea.setVisible(false);
        textarea.setEditable(false);
        lbl_percentageOfAbsence.setVisible(true);
        absentStudentsAlert();
    }

    public String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate now = LocalDate.now();
        return now.toString();
    }

    public void getTeacherName(String name) {
        teacherName = name;
        lbl_teacherName.setText("Hello, " + teacherName);
    }

    private void getAbsenceById(int id) {
        float absenceById = bllFacade.getAbsenceById(id);
        lbl_percentageOfAbsence.setText("Total absence percentage: " + String.valueOf(absenceById) + " %");
    }

    public void getIsStudent(boolean isStudent) {
        this.isStudent = isStudent;
    }

    private void click_showStudentReason(ActionEvent event) {
        if (tc_absent.getSelectionModel().getSelectedItem() != null) {
            lbl_reasonForAbsence.setVisible(true);
            textarea.setVisible(true);
            textarea.setText(tc_absent.getSelectionModel().getSelectedItem().getMessage());
        } else {
            lbl_reasonForAbsence.setVisible(false);
            textarea.setVisible(false);
        }
    }

    public void getTeacherId(int id) {
        idFromLogin = id;
    }

    private void absentStudentsAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informational dialog");
        alert.setHeaderText("There are some students with the absent percentage above 40%");
        List<Student> allStudents = bllFacade.getAllStudentsForAbsenceOverview();
        List<Student> mostAbsentStudents = new ArrayList();
        List<String> list = new ArrayList();
        for (Student student : allStudents) {
            if (student.getAbsencePercentage() >= 40) {
                mostAbsentStudents.add(student);
            }
        }

        for (Student student : mostAbsentStudents) {
            String normal = "Student  " + student.getFirstName() + " has " + student.getAbsencePercentage() + " % of absence\n";
            list.add(normal);
        }

        alert.setContentText(list.toString());
        alert.showAndWait();
    }

    @FXML
    private void click_close(ActionEvent event) {
        Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void click_search(ActionEvent event) {
        String date = datePicker.getValue().toString();
        presentStudents = FXCollections.observableArrayList(bllFacade.getStudentsOnCondition(date, 1));
        absentStudents = FXCollections.observableArrayList(bllFacade.getStudentsOnCondition(date, 0));
        tableview_present.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tableview_absent.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tc_present.setItems(presentStudents);
        tc_absent.setItems(absentStudents);
    }

    @FXML
    private void click_selectedPresentStudent(MouseEvent event) {
        tc_absent.getSelectionModel().clearSelection();
        lbl_reasonForAbsence.setVisible(false);
        textarea.setVisible(false);
        getAbsenceById(tc_present.getSelectionModel().getSelectedItem().getId());
    }

    @FXML
    private void click_selectedAbsentStudent(MouseEvent event) {
        tc_present.getSelectionModel().clearSelection();
        lbl_reasonForAbsence.setVisible(true);
        textarea.setVisible(true);
        textarea.setText(tc_absent.getSelectionModel().getSelectedItem().getMessage());
        getAbsenceById(tc_absent.getSelectionModel().getSelectedItem().getId());
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

    @FXML
    private void click_fromNullToAbsent(ActionEvent event) {
        List<Integer> allIdList = bllFacade.getAllStudentsIds();
        List<Integer> recordedIdList = bllFacade.getAllAttendancesIdsByDate(getCurrentDate());
        List<Integer> notRecordedList = new ArrayList();
        boolean found = false;
        for (Integer studentId : allIdList) {
            for (Integer recordedId : recordedIdList) {
                if (studentId == recordedId) {
                    found = true;
                    break;
                } else {
                    found = false;
                }
            }
            if (!found) {
                notRecordedList.add(studentId);
            }
        }
        bllFacade.insertNewStatus(notRecordedList);

    }

}
