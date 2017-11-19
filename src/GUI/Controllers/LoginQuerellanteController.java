/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers;

import Logic.GestorLogin;
import com.jfoenix.controls.JFXTextField;
import formValidaton.FormValidation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
 * FXML Controller class
 *
 * @author mean
 */
public class LoginQuerellanteController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTextField cedulaQ;
     
     
    @FXML
    private Label loginError;
    
    private GestorLogin gl =  new GestorLogin();
     
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
    private void goToRegistro(ActionEvent mEvent) throws IOException{
      Parent loginEmpView;
       
        loginEmpView = (AnchorPane) FXMLLoader.load(getClass().getResource("/GUI/Views/registrarQuerellante.fxml"));
        Scene logScene = new Scene(loginEmpView);
        
        Stage curStage = (Stage) ((Node) mEvent.getSource()).getScene().getWindow();
        curStage.setScene(logScene);
        
        curStage.show();
    }
    @FXML
    private void validate(){
        
    }
    
    @FXML
    private void iniciarSesion(ActionEvent mEvent) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
    {
        String cedulaUser = cedulaQ.getText();
        FormValidation validation = new FormValidation();
        String message = validation.validTextField(cedulaQ);
        if(message.isEmpty())
        {
            boolean allowAcces = gl.InicioSesion(cedulaUser, null);
            if(!allowAcces)
                loginError.setText("¡Error!, La cédula no está registrada");
            else
                goToCasos(mEvent);
        }
        else
        {
            loginError.setText(message);
        }
        
    }
    
        @FXML    
    private void goToCasos(ActionEvent mEvent) throws IOException{
      Parent loginEmpView;
       
        loginEmpView = (AnchorPane) FXMLLoader.load(getClass().getResource("/GUI/Views/CasosQuerellante.fxml"));
        Scene logScene = new Scene(loginEmpView);
        
        Stage curStage = (Stage) ((Node) mEvent.getSource()).getScene().getWindow();
        curStage.setScene(logScene);
        
        curStage.show();
    }
}
