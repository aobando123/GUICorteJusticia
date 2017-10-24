/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author mean
 */
public class StartMenuController implements Initializable {
    
    @FXML
    private Label label;
    
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
     @FXML
    private void goToLoginEmp(MouseEvent event) throws IOException{
        Parent loginEmpView;
        
        loginEmpView = (AnchorPane) FXMLLoader.load(getClass().getResource("LoginEmpleado.fxml"));
        Scene logScene = new Scene(loginEmpView);
        
        Stage curStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        curStage.setScene(logScene);
        
        curStage.show();
    }
     @FXML
    private void goToLogQuere(MouseEvent event) throws IOException{
        Parent loginEmpView;
       
        loginEmpView = (AnchorPane) FXMLLoader.load(getClass().getResource("LoginQuerellante.fxml"));
        Scene logScene = new Scene(loginEmpView);
        
        Stage curStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        curStage.setScene(logScene);
        
        curStage.show();
    }
    @FXML
    private void regresar(MouseEvent mEvent) throws IOException{
      Parent loginEmpView;
       
        loginEmpView = (AnchorPane) FXMLLoader.load(getClass().getResource("StartMenu.fxml"));
        Scene logScene = new Scene(loginEmpView);
        
        Stage curStage = (Stage) ((Node) mEvent.getSource()).getScene().getWindow();
        curStage.setScene(logScene);
        
        curStage.show();
    }
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
