package attendancerecorder.gui.controller;

import attendancerecorder.be.Course;
import attendancerecorder.be.Student;
import attendancerecorder.bll.interfaces.IbllFacade;
import attendancerecorder.bll.managers.bllFacade;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.EventObject;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 */
public class RecordAndOverallAttendanceController implements Initializable {

    //Instance for the business logic layer
    private IbllFacade bllFacade = new bllFacade();

    //Needed variables
    private ObservableList<Course> courseLst;
    private String studentFirstName, studentLastName;
    private int idFromLogin;
    private boolean isStudent;
    private boolean isPresent = false;

    @FXML
    private JFXCheckBox cb_present;
    @FXML
    private JFXCheckBox cb_absent;
    @FXML
    private TableView<Course> tv_courses;
    @FXML
    private TableColumn<Course, String> tc_courses;
    @FXML
    private Label lbl_popup;
    @FXML
    private PieChart overAllChart;
    @FXML
    private Label lbl_showStatus;
    @FXML
    private JFXTextArea txt_absentMessage;
    @FXML
    private JFXDatePicker datePicker_record;
    @FXML
    private JFXDatePicker datePicker_sort;
    @FXML
    private Label lbl_presentPercentage;
    @FXML
    private Label lbl_absentPercentage;
    @FXML
    private GridPane gridpane;
    @FXML
    private Label lbl_title;
    @FXML
    private Label lbl_smallTitle;
    @FXML
    private JFXButton btn_small;
    @FXML
    private TextArea textarea;
    @FXML
    private Label lbl1;
    @FXML
    private Label lbl2;
    @FXML
    private JFXButton btn_smallFontSize;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //labels are not visible at first and the table is filled
        initCoursesTable();
        txt_absentMessage.visibleProperty().set(false);
        lbl_popup.setVisible(false);
        datePicker_record.setEditable(false);
        cb_absent.setDisable(true);
        cb_present.setDisable(true);
        lbl1.setVisible(false);
        lbl2.setVisible(false);
        textarea.setVisible(false);
        textarea.setEditable(false);
    }

    private void initCoursesTable() {
        courseLst = FXCollections.observableArrayList(bllFacade.getAllCourses());
        tc_courses.setCellValueFactory(new PropertyValueFactory<>("name"));
        tv_courses.setItems(courseLst);
    }

    private boolean enableConfirmation() {
        if (datePicker_record.getValue() == null) {
            lbl_popup.setVisible(true);
            return false;
        }
        if (!cb_present.isSelected() && !cb_absent.isSelected()) {
            return false;
        }
        return true;
    }

    public void getEmailFromLogin(int id) {
        this.idFromLogin = id;
        initOverallChart();
    }

    public void getStudentName(String firstName, String lastName) {
        this.studentFirstName = firstName;
        this.studentLastName = lastName;
        lbl_title.setText("Hello, " + studentFirstName + " " + studentLastName);
    }

    private void addNewAttendance() {
        int status = 1;
        String date = datePicker_record.getValue().toString();
        String message = null;
        if (cb_absent.isSelected()) {
            status = 0;
            message = txt_absentMessage.getText();
        }

        if (bllFacade.checkAlreadyExistingAttendance(idFromLogin, date)) {
            confirmationOverwritingAttendance(date, status, message);
        } else {
            bllFacade.addNewAttendance(idFromLogin, status, date, message, getDayOfWeek());
            confirmationAttendanceAlert();
        }
    }

    private void initOverallChart() {
        float presentPercentage = (float) (Math.round((100 - updateAbsencePercentage()) * 100.0) / 100.0);
        float absentPercentage = (float) (Math.round(updateAbsencePercentage() * 100.0) / 100.0);
        ObservableList<PieChart.Data> overallChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Present", presentPercentage),
                        new PieChart.Data("Absent", absentPercentage));
        overAllChart.setData(overallChartData);

        lbl_absentPercentage.setText(Float.toString(absentPercentage));
        lbl_presentPercentage.setText(Float.toString(presentPercentage));
    }

    private float updateAbsencePercentage() {
        List<Student> studentLst = bllFacade.getAllAttendancesById(idFromLogin);
        float counterPresent = 0;
        float counterAbsent = 0;
        float sum;

        for (Student student : studentLst) {
            if (student.getStatus() == 1) {
                counterPresent++;
            } else {
                counterAbsent++;
            }
        }

        sum = counterPresent + counterAbsent;
        float absencePercentage = (counterAbsent * 100) / sum;

        bllFacade.updateAbsencePercentageById(idFromLogin, absencePercentage);
        return absencePercentage;
    }

    private void confirmationAttendanceAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText(null);
        if (cb_present.isSelected()) {
            alert.setContentText("YOU HAVE SELECTED:\nDate: " + datePicker_record.getValue().toString() + "\nStatus: PRESENT");
        } else {
            alert.setContentText("YOU HAVE SELECTED:\nDate: " + datePicker_record.getValue().toString() + "\nStatus: ABSENT\nMessage: " + txt_absentMessage.getText());
        }
        alert.showAndWait();
        //Calls again the method that reads from DB, calculates the percentages and sets the Pie charts, as, by the time the alert is displayed, the DB has already be altered with the new recorded attendance and so the pie charts have different data than the one they had the first time they read.
        initOverallChart();
    }

    private void confirmationOverwritingAttendance(String date, int status, String message) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("OVERWRITE ALERT");
        alert.setContentText("Are you sure you want to overwrite your previous attendance?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (bllFacade.checkAlreadyExistingAttendance(idFromLogin, date)) {
                bllFacade.deleteAttendanceByIdANDDate(idFromLogin, date);
                if (cb_present.isSelected()) {
                    status = 1;
                } else {
                    status = 0;
                    message = txt_absentMessage.getText();
                }
                bllFacade.addNewAttendance(idFromLogin, status, date, message, getDayOfWeek());
            } else {
                if (cb_present.isSelected()) {
                    status = 1;
                } else {
                    status = 0;
                    message = txt_absentMessage.getText();
                }
                bllFacade.addNewAttendance(idFromLogin, status, date, message, getDayOfWeek());
            }
            confirmationAttendanceAlert();
        }
    }

    public void getIsStudent(boolean isStudent) {
        this.isStudent = isStudent;
    }

    public String getDayOfWeek() {
        LocalDate ld = datePicker_record.getValue();
        if (ld == null) {
            return "";
        }
        String dayOfWeek = ld.getDayOfWeek().toString();
        return dayOfWeek;
    }

    public LocalDate getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate now = LocalDate.now();
        return now;
    }

    @FXML
    private void click_present(ActionEvent event) {
        cb_absent.setSelected(false);
        isPresent = true;
    }

    @FXML
    private void click_absent(ActionEvent event) {
        cb_present.setSelected(false);
        txt_absentMessage.visibleProperty().bind(cb_absent.selectedProperty());
    }

    @FXML
    private void click_search(ActionEvent event) {
        lbl1.setVisible(true);

        String date = datePicker_sort.getValue().toString();
        Student student = bllFacade.getReasonForAbsence(idFromLogin, date);
        textarea.setText(student.getMessage());
        if (student.getStatus() == 0) {
            lbl_showStatus.setText("ABSENT");
            lbl2.setVisible(true);
            textarea.setVisible(true);
        } else {
            lbl_showStatus.setText("PRESENT");
            lbl2.setVisible(false);
            textarea.setVisible(false);
        }
    }

    @FXML
    private void mouse_confirm(MouseEvent event) {
        if (enableConfirmation()) {
            Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void click_confirm(ActionEvent event) throws IOException {
        if (enableConfirmation()) {
            addNewAttendance();
        }
    }

    @FXML
    private void clickClose(ActionEvent event) {
        Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void click_changePassword(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendancerecorder/gui/view/ChangePassword.fxml"));
        Parent root = loader.load();
        //The following three lines are created so both idFromLogin and isStudent are accesible from ChangePasswordController
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
    private void dp_hiding(Event event) {
        cb_absent.setSelected(false);
        cb_present.setSelected(false);

        LocalDate chosenDate = datePicker_record.getValue();
        LocalDate today = getCurrentDate();

        if (chosenDate == null || chosenDate.equals(today)) {
            if (!getDayOfWeek().equals("SATURDAY") && !getDayOfWeek().equals("SUNDAY")) {
                cb_absent.setDisable(false);
                cb_present.setDisable(false);
            } else {
                cb_absent.setDisable(true);
                cb_present.setDisable(true);
            }
        } else {
            cb_present.setDisable(true);

            if (chosenDate.compareTo(today) > 0) {
                if (!getDayOfWeek().equals("SATURDAY") && !getDayOfWeek().equals("SUNDAY")) {
                    cb_absent.setDisable(false);
                    cb_present.setDisable(true);
                } else {
                    cb_absent.setDisable(true);
                    cb_present.setDisable(true);
                }
            }
            if (chosenDate.compareTo(today) < 0) {
                cb_absent.setDisable(true);
                cb_present.setDisable(true);
            }
        }
    }
}
