package attendancerecorder.gui.controller;

import attendancerecorder.be.Teacher;
import attendancerecorder.bll.interfaces.ITeacherManager;
import attendancerecorder.bll.managers.TeacherManager;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 */

public class LoginTeacherController implements Initializable {

    //Instance for the business logic layer
    ITeacherManager teacherMng = new TeacherManager();

    //Needed variables
    String teacherName;
    int idFromLogin;
    boolean isStudent = false;

    @FXML
    private JFXTextField txt_email;
    @FXML
    private JFXButton btn_login;
    @FXML
    private JFXPasswordField txt_password;
    @FXML
    private Label lbl_wrongpassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }

    @FXML
    private void mouse_login(MouseEvent event) {
        List<Teacher> teacherLst = teacherMng.getTeacherLoginData();
        for (Teacher teacher : teacherLst) {
            if (txt_email.getText().equals(teacher.getEmail()) && txt_password.getText().equals(teacher.getPassword())) {
                Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
                stage.close();
            }
        }
    }

    @FXML
    private void click_login(ActionEvent event) throws IOException {
        boolean found = false;
        List<Teacher> teacherLst = teacherMng.getTeacherLoginData();
        for (Teacher teacher : teacherLst) {
            if (txt_email.getText().equals(teacher.getEmail()) && txt_password.getText().equals(teacher.getPassword())) {
                found = true;
                teacherName = teacher.getName();
                idFromLogin = teacher.getId();
            }
        }
        //We created this found variable so we made sure that the application first sets the values of idFromLogin, studentFirstName, studentLastName before opening the new window
        if (found) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendancerecorder/gui/view/TeacherAttendanceOverview.fxml"));

            Parent root = loader.load();
            TeacherAttendanceOverviewController tactrl = loader.getController();
            tactrl.getTeacherName(teacherName);
            tactrl.getTeacherId(idFromLogin);
            tactrl.getIsStudent(isStudent);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("View Students Attendance");
            stage.show();
        } else {
            lbl_wrongpassword.setText("Wrong password or username");
        }
        btn_login.pressedProperty();
    }
}
