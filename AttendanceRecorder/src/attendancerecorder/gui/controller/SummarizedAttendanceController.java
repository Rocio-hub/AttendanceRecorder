package attendancerecorder.gui.controller;

import attendancerecorder.be.Student;
import attendancerecorder.bll.interfaces.ITeacherManager;
import attendancerecorder.bll.interfaces.IbllFacade;
import attendancerecorder.bll.managers.TeacherManager;
import attendancerecorder.bll.managers.bllFacade;
import com.jfoenix.controls.JFXButton;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 */
public class SummarizedAttendanceController implements Initializable {

    //Instance for the business logic layer
    IbllFacade bllFacade = new bllFacade();

    //Needed variables
    private ObservableList<Student> studentLst;
    private int mondayAbsence = 0;
    private int tuesdayAbsence = 0;
    private int wednesdayAbsence = 0;
    private int thursdayAbsence = 0;
    private int fridayAbsence = 0;

    @FXML
    private TableView<Student> tv_summarizedAttendance;
    @FXML
    private TableColumn<Student, String> tc_name;
    @FXML
    private TableColumn<Student, String> tc_percentage;
    @FXML
    private BarChart<String, Number> chart_weeklyAbsence;
    @FXML
    private NumberAxis numberAxis_daysAbsent;
    @FXML
    private CategoryAxis categoryAxis_weekDays;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        studentLst = FXCollections.observableArrayList(bllFacade.getAllStudentsForAbsenceOverview());
        tc_name.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tc_percentage.setCellValueFactory(new PropertyValueFactory<>("absencePercentage"));
        tv_summarizedAttendance.setItems(studentLst);
    }

    @FXML
    private void click_contact(ActionEvent event) throws URISyntaxException, IOException {
        Desktop desktop;
        String email = tv_summarizedAttendance.getSelectionModel().getSelectedItem().getEmail();
        if (Desktop.isDesktopSupported()
                && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
            URI mailto = new URI("mailto:" + email + "?subject=STUDENT'S%20ATTENDANCE");
            desktop.mail(mailto);
        } else {
            throw new RuntimeException("desktop doesn't support mailto");
        }
    }

    public void setChartData(int mondayAbsence, int tuesdayAbsence, int wednesdayAbsence, int thursdayAbsence, int fridayAbsence) {
        chart_weeklyAbsence.getData().clear();
        int arrayForSorting[] = {mondayAbsence, tuesdayAbsence, wednesdayAbsence, thursdayAbsence, fridayAbsence};
        bubbleSort(arrayForSorting);
        numberAxis_daysAbsent.setAutoRanging(false); //Avoids the Y-Axis to decide by itself the tick amount between values
        numberAxis_daysAbsent.setTickUnit(1.0); //We set the tick to one
        numberAxis_daysAbsent.setLowerBound(0); //We set the lower bound to 0
        numberAxis_daysAbsent.setUpperBound(arrayForSorting[0] + 2); //We set the upper bound to the most absent day plus some margin
        numberAxis_daysAbsent.setLabel("Days of Absence");
        categoryAxis_weekDays.setCategories(FXCollections.<String>observableArrayList(Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")));
        categoryAxis_weekDays.setLabel("Weekdays");
        XYChart.Series<String, Number> seriesAbsence = new XYChart.Series<>();
        seriesAbsence.setName("Absence");
        seriesAbsence.getData().add(new XYChart.Data<>("Monday", mondayAbsence));
        seriesAbsence.getData().add(new XYChart.Data<>("Tuesday", tuesdayAbsence));
        seriesAbsence.getData().add(new XYChart.Data<>("Wednesday", wednesdayAbsence));
        seriesAbsence.getData().add(new XYChart.Data<>("Thursday", thursdayAbsence));
        seriesAbsence.getData().add(new XYChart.Data<>("Friday", fridayAbsence));
        chart_weeklyAbsence.getData().addAll(seriesAbsence);
    }

    @FXML
    private void checkAbsenceDays(ActionEvent event) {
        List<Student> absentStudent = new ArrayList(bllFacade.getDaysOfAbsenceById(tv_summarizedAttendance.getSelectionModel().getSelectedItem().getId()));
        mondayAbsence = 0;
        tuesdayAbsence = 0;
        wednesdayAbsence = 0;
        thursdayAbsence = 0;
        fridayAbsence = 0;
        for (Student student : absentStudent) {
            if (student.getDayOfWeek().equals("MONDAY")) {
                mondayAbsence++;
            } else if (student.getDayOfWeek().equals("TUESDAY")) {
                tuesdayAbsence++;
            } else if (student.getDayOfWeek().equals("WEDNESDAY")) {
                wednesdayAbsence++;
            } else if (student.getDayOfWeek().equals("THURSDAY")) {
                thursdayAbsence++;
            } else if (student.getDayOfWeek().equals("FRIDAY")) {
                fridayAbsence++;
            }
        }
        setChartData(mondayAbsence, tuesdayAbsence, wednesdayAbsence, thursdayAbsence, fridayAbsence);
    }

    public void bubbleSort(int[] arr) {
        int n = arr.length;
        int temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (arr[j - 1] < arr[j]) {
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }

            }
        }
    }
}
