/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers;

import Logic.GestorLogin;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import formValidaton.FormValidation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class LoginEmpleadoController implements Initializable {

    @FXML
    private JFXPasswordField contra;
    @FXML
    private JFXTextField nombre;
    
    @FXML
    private Label userNameError;
    
    @FXML
    private Label errorContra;
    
    GestorLogin gl = new GestorLogin();

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
    private void iniciarSesion(MouseEvent mEvent)
    {
        String nombreUsuario = nombre.getText();
        String password = contra.getText();
        FormValidation fv = new FormValidation();
        try {
            String resultNombre = fv.validTextField(nombre);
            String resultPassword = fv.validTextField(contra);
            if(!resultNombre.isEmpty() || !resultPassword.isEmpty())
            {
                userNameError.setText(resultNombre);
                errorContra.setText(resultPassword);
            }
            else
            {
                boolean allow = gl.InicioSesion(nombreUsuario, password);
                if(!allow)
                    errorContra.setText("¡Error!, Usuario o contraseña incorrectos");
                else
                    goToCasos(mEvent);
            }        
        } catch (Exception ex) {
            Logger.getLogger(LoginEmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
      
    private void goToCasos(MouseEvent mEvent) throws IOException{
        Parent loginEmpView;
        String fileName = "Secretario".equals(gl.getUserType()) ? "BuscarQuerellante.fxml": "Casos"+ gl.getUserType() + ".fxml";
        if(gl.getUserType().equals("Admin") ){
         fileName="MenuAdmin.fxml";
        }
               
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/"+ fileName));
        loginEmpView = (AnchorPane) loader.load();
        Scene logScene = new Scene(loginEmpView);
        
        Stage curStage = (Stage) ((Node) mEvent.getSource()).getScene().getWindow();
        curStage.setScene(logScene);
        
        if(gl.getUserType().equals("Juez")  ){
        CasosJuezController controller = loader.<CasosJuezController>getController();
                controller.mostrarCasos(gl.getCurrentUser().getIdPersona());
        }
        curStage.show();
    }
}
