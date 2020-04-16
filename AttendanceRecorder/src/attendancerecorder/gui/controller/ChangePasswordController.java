package attendancerecorder.gui.controller;

import attendancerecorder.bll.interfaces.IbllFacade;
import attendancerecorder.bll.managers.bllFacade;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 */
public class ChangePasswordController implements Initializable {

    //Instances for the business logic layer
    private IbllFacade bllFacade = new bllFacade();
    
    //Needed variables
    private int idFromLogin;
    private boolean isStudent;

    @FXML
    private JFXTextField txtfield_password;
    @FXML
    private Button btn_confirm;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    //Gets the id of the student from the previous opened window
    public void getStudentId(int id) {
        this.idFromLogin = id;
    }
    
    //Gets wether the user is student or not from the previous opened window
    public void getIsStudent(boolean isStudent) {
        this.isStudent = isStudent;
    }

    @FXML
    private void click_confirm(ActionEvent event) {
        if (isStudent) {
            if (txtfield_password != null) {
                bllFacade.updateStudentPasswordById(idFromLogin, txtfield_password.getText());
            }
        } else {
            bllFacade.updateTeacherPasswordById(idFromLogin, txtfield_password.getText());
        }
    }

    @FXML
    private void mouse_confirm(MouseEvent event) {
        Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }
}
