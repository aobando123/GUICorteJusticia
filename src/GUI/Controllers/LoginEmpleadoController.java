/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mean
 */
public class LoginEmpleadoController implements Initializable {

    @FXML
    private JFXPasswordField contra;
    @FXML
    private JFXTextField nombre;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void regresar(MouseEvent mEvent) throws IOException{
      Parent loginEmpView;
       
        loginEmpView = (AnchorPane) FXMLLoader.load(getClass().getResource("/GUI/Views/StartMenu.fxml"));
        Scene logScene = new Scene(loginEmpView);
        
        Stage curStage = (Stage) ((Node) mEvent.getSource()).getScene().getWindow();
        curStage.setScene(logScene);
        
        curStage.show();
    }
    
    @FXML
    private void hola(MouseEvent mEvent)throws IOException{
        System.out.println("hola");
    }
    
}
