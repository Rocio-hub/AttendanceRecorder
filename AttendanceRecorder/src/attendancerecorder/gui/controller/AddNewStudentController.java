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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 */
public class AddNewStudentController implements Initializable {

    private IbllFacade teacherMng = new bllFacade();
    private String firstName, lastName, email, password;

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
    @FXML
    private GridPane gridpane;
    @FXML
    private Label lbl_title;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        lbl_addFirstName.setVisible(false);
        lbl_addLastName.setVisible(false);
        lbl_addEmail.setVisible(false);
        lbl_addPassword.setVisible(false);

    }

    @FXML
    private void click_confirm(ActionEvent event) {
        firstName = txtfield_firstName.getText();
        lastName = txtfield_lastName.getText();
        email = txtfield_email.getText();
        password = txtfield_password.getText();
        if (enableConfirmation()) {
            teacherMng.addNewStudent(firstName, lastName, email, password);
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
    private void click_cancel(ActionEvent event) {
        Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }

    private boolean enableConfirmation() {
        if (txtfield_firstName.getText().trim().equals("")) {
            lbl_addFirstName.setVisible(true);
            return false;
        } else {
            lbl_addFirstName.setVisible(false);
        }
        if (txtfield_lastName.getText().trim().equals("")) {
            lbl_addLastName.setVisible(true);
            return false;
        } else {
            lbl_addLastName.setVisible(false);
        }
        if (txtfield_email.getText().trim().equals("")) {
            lbl_addEmail.setVisible(true);
            return false;
        } else {
            lbl_addEmail.setVisible(false);
        }
        if (txtfield_password.getText().trim().equals("")) {
            lbl_addPassword.setVisible(true);
            return false;
        } else {
            lbl_addPassword.setVisible(false);
        }
        return true;
    }

}
