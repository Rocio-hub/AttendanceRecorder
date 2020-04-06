/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendancerecorder.gui.controller;

import attendancerecorder.bll.interfaces.IStudentManager;
import attendancerecorder.bll.managers.StudentManager;
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
 *
 * @author rtlop
 */
public class ChangePasswordController implements Initializable {
    IStudentManager studentMng = new StudentManager();
    int idFromLogin;

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
    
    public void getStudentId(int id){
        this.idFromLogin=id;
        
    }
    @FXML
    private void click_confirm(ActionEvent event) {
        if(txtfield_password!=null)
        studentMng.updatePasswordById(idFromLogin, txtfield_password.getText());
    }

    @FXML
    private void mouse_confirm(MouseEvent event) {
         Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
            stage.close();
    }
    
}
