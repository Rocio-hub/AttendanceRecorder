/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendancerecorder.gui.controller;

import attendancerecorder.be.Course;
import attendancerecorder.be.Student;
import attendancerecorder.bll.interfaces.IStudentManager;
import attendancerecorder.bll.managers.StudentManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rtlop
 */
public class RecordAndOverallAttendanceController implements Initializable {

    IStudentManager iStudentManager = new StudentManager();
    private ObservableList<Course> courseLst;
    int idFromLogin;
    boolean isPresent = false;
    String studentFirstName;
    String studentLastName;

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
    private Label lbl_popup1;
    @FXML
    private Label lbl_popup2;
    @FXML
    private PieChart overAllChart;
    @FXML
    private Label lbl_showStatus;
    @FXML
    private JFXButton btn_search;
    @FXML
    private JFXTextArea txt_absentMessage;
    @FXML
    private JFXButton btn_confirm;
    @FXML
    private JFXButton btn_close;
    @FXML
    private JFXDatePicker datePicker_record;
    @FXML
    private JFXDatePicker datePicker_sort;
    @FXML
    private Label lbl_absentMessage;
    @FXML
    private Label lbl_presentPercentage;
    @FXML
    private Label lbl_absentPercentage;
    @FXML
    private Label lbl_name;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txt_absentMessage.visibleProperty().set(false);
        lbl_popup.setVisible(false);
        lbl_popup1.setVisible(false);
        lbl_popup2.setVisible(false);
        initCoursesTable();
       
    }

    private void initCoursesTable() {
        courseLst = FXCollections.observableArrayList(iStudentManager.getAllCourses());
        tc_courses.setCellValueFactory(new PropertyValueFactory<>("name"));
        tv_courses.setItems(courseLst);
    }

    private boolean enableConfirmation() {
        if (datePicker_record.getValue() == null) {
            lbl_popup.setVisible(true);
            return false;
        }
        if (!cb_present.isSelected() && !cb_absent.isSelected()) {
            lbl_popup1.setVisible(true);
            return false;
        }
        /* if (tv_courses.getSelectionModel().getSelectedItem() == null) {
            lbl_popup2.setVisible(true);
            return false;
        }*/
        return true;
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
        String date = datePicker_sort.getValue().toString();
        Student student = iStudentManager.getReasonForAbsence(idFromLogin, date);
        lbl_absentMessage.setText(student.getMessage());
        if (student.getStatus() == 0) {
            lbl_showStatus.setText("ABSENT");
        } else {
            lbl_showStatus.setText("PRESENT");
        }

    }

    public void getEmailFromLogin(int id) {
        this.idFromLogin = id;
         initOverallChart();
    }
    
    public void getStudentName(String firstName, String lastName){
        this.studentFirstName=firstName;
        this.studentLastName=lastName;
        lbl_name.setText(studentFirstName +" "+studentLastName);
    }

    private void addNewAttendance(){
        int status;
        String date = datePicker_record.getValue().toString();
        String message = null;
        
        if(iStudentManager.checkAlreadyExistingAttendance(idFromLogin, date)){
            iStudentManager.deleteAttendanceByIdANDDate(idFromLogin, date);
        }        
        
        if (cb_present.isSelected()) {
            status = 1;
        } else {
            status = 0;
            message = txt_absentMessage.getText();
        }
        iStudentManager.addNewAttendance(idFromLogin, status, date, message);
    }

    private void initOverallChart() {
        double presentPercentage = calculateOverallPresentAttendance();
        double absentPercentage = 100 - calculateOverallPresentAttendance();
        ObservableList<PieChart.Data> overallChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Present", presentPercentage),
                        new PieChart.Data("Absent", absentPercentage));
        overAllChart.setData(overallChartData);
        
        
        lbl_absentPercentage.setText(Double.toString(absentPercentage));
        lbl_presentPercentage.setText(Double.toString(presentPercentage));
    }

    private double calculateOverallPresentAttendance() {
        List<Student> studentLst = new ArrayList();
        studentLst = iStudentManager.getAllAttendancesById(idFromLogin);
        double counterPresent = 0;
        double counterAbsent = 0;
        double sum;

        for (Student student : studentLst) {
            if (student.getStatus() == 1) {
                counterPresent++;
            } else {
                counterAbsent++;
            }
        }

        sum = counterPresent + counterAbsent;
        double total = ((counterPresent * 100) / sum);
        return Math.floor(total*100) / 100;
       
    }

    @FXML
    private void mouse_confirm(MouseEvent event
    ) {
        if (enableConfirmation()) {
            Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void click_confirm(ActionEvent event) throws IOException {
        if (enableConfirmation()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendancerecorder/gui/view/Confirmation.fxml"));
            Parent root = loader.load();
            ConfirmationController cctrl = loader.getController();
            addNewAttendance();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            alert();
        }
    }

    @FXML
    private void clickClose(ActionEvent event
    ) {
        Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }
    
    public void alert() {        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText(null);
        if(cb_present.isSelected()){
        alert.setContentText("YOU HAVE SELECTED:\nDate: "+ datePicker_record.getValue().toString()+"\nStatus: PRESENT" );
        }
        else{
            alert.setContentText("YOU HAVE SELECTED:\nDate: "+ datePicker_record.getValue().toString()+"\nStatus: ABSENT\nMessage: "+txt_absentMessage.getText());
        }
        alert.showAndWait();
        initOverallChart(); //Calls again the method that reads from DB, calculates the percentages and sets the Pie charts, as, by the time the alert is displayed, the DB has already be altered with the new recorded attendance and so the pie charts have different data than the one they had the first time they read.
    }

}
