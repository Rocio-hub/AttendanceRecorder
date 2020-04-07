package attendancerecorder.gui.controller;

import attendancerecorder.be.Student;
import attendancerecorder.bll.interfaces.ITeacherManager;
import attendancerecorder.bll.managers.TeacherManager;
import com.jfoenix.controls.JFXButton;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 */
public class SummarizedAttendanceController implements Initializable {

    //Instance for the business logic layer
    ITeacherManager teacherMng = new TeacherManager();
    
    //Needed variables
    private ObservableList<Student> studentLst;
    
    @FXML
    private TableView<Student> tv_summarizedAttendance;
    @FXML
    private TableColumn<Student, String> tc_name;
    @FXML
    private TableColumn<Student, String> tc_percentage;
//    @FXML
//    private JFXButton btn_contact;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        studentLst = FXCollections.observableArrayList(teacherMng.getAllStudentsForAbsenceOverview());
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
            URI mailto = new URI("mailto:"+email+"?subject=STUDENT'S%20ATTENDANCE");
            desktop.mail(mailto);
        } else {
            throw new RuntimeException("desktop doesn't support mailto");
        }
    }
}
