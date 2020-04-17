package attendancerecorder.gui.controller;

import attendancerecorder.be.Student;
import attendancerecorder.bll.interfaces.IbllFacade;
import attendancerecorder.bll.managers.bllFacade;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.EventObject;
import java.util.List;
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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 */
public class LoginStudentController implements Initializable {

    //Instance for the business logic layer
    private IbllFacade bllFacade = new bllFacade();

    //Needed variables
   private String studentFirstName;
   private String studentLastName;
   private int idFromLogin;
   private boolean isStudent = true;

    @FXML
    private JFXTextField txt_email;
    @FXML
    private JFXButton btn_login;
    @FXML
    private JFXPasswordField txt_password;
    @FXML
    private Label lbl_wrongpassword;
    @FXML
    private GridPane gridpane;
    @FXML
    private Label lbl_title;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void mouse_login(MouseEvent event) {
        List<Student> studentLst = bllFacade.getAllStudents();
        for (Student student : studentLst) {
            if (txt_email.getText().equals(student.getEmail()) && txt_password.getText().equals(student.getPassword())) {
                Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
                stage.close();
            }
        }
    }

    @FXML
    private void click_login(ActionEvent event) throws IOException {
        boolean found = false;
        List<Student> studentLst = bllFacade.getAllStudents();
        for (Student student : studentLst) {
            if (txt_email.getText().equals(student.getEmail()) && txt_password.getText().equals(student.getPassword())) {
                found = true;
                idFromLogin = student.getId();
                studentFirstName = student.getFirstName();
                studentLastName = student.getLastName();
            }
        }
        //We created this found variable so we made sure that the application first sets the values of idFromLogin, studentFirstName, studentLastName before opening the new window
        if (found) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendancerecorder/gui/view/RecordAndOverallAttendance.fxml"));

            Parent root = loader.load();
            RecordAndOverallAttendanceController srac = loader.getController();
            srac.getEmailFromLogin(idFromLogin);
            srac.getStudentName(studentFirstName, studentLastName);
            srac.getIsStudent(isStudent);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Record Attendance and View Overall Attendance");
            stage.show();
        } else {
            lbl_wrongpassword.setText("Wrong e-mail or password");
        }
        btn_login.pressedProperty();
    }
}
