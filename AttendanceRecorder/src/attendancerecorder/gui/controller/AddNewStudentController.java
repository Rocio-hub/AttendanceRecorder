
package attendancerecorder.gui.controller;

import attendancerecorder.bll.interfaces.ITeacherManager;
import attendancerecorder.bll.managers.TeacherManager;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 */
public class AddNewStudentController implements Initializable {
    
    ITeacherManager teacherMng = new TeacherManager();

    @FXML
    private JFXTextField txtfield_firstName;
    @FXML
    private JFXTextField txtfield_lastName;
    @FXML
    private JFXTextField txtfield_email;
    @FXML
    private JFXTextField txtfield_password;
    @FXML
    private Label lbl_addFirstName;
    @FXML
    private Label lbl_addLastName;
    @FXML
    private Label lbl_addEmail;
    @FXML
    private Label lbl_addPassword;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void click_confirm(ActionEvent event) {
        String firstName = txtfield_firstName.getText();
        String lastName = txtfield_lastName.getText();
        String email = txtfield_email.getText();
        String password = txtfield_password.getText();
        teacherMng.addNewStudent(firstName, lastName, email, password);
    }

    @FXML
    private void mouse_confirm(MouseEvent event) {
        Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }
        
    @FXML
    private void click_cancel(ActionEvent event) {
        Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }
    
}
