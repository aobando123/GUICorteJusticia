/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers;

import Logic.GestorSecretario;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
public class EditSecretarioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private int idSecretario;
    @FXML
    private JFXTextField nombre;

    @FXML
    private JFXTextField apellido;

    @FXML
    private JFXTextField cedula;

    @FXML
    private JFXTextField telefono;

    @FXML
    private JFXTextArea direccion;
        @FXML
    private JFXTextField NUsuario;
            @FXML
    private JFXButton update;
            
            private GestorSecretario gs = new GestorSecretario();
        
        
    public void setId(int id){
        idSecretario = id;
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    @FXML
     private void regresar(MouseEvent mEvent) throws IOException {
        Parent loginEmpView;

        loginEmpView = (AnchorPane) FXMLLoader.load(getClass().getResource("/GUI/Views/CRUDSecretario.fxml"));
        Scene logScene = new Scene(loginEmpView);

        Stage curStage = (Stage) ((Node) mEvent.getSource()).getScene().getWindow();
        curStage.setScene(logScene);
        System.out.print(idSecretario);

        curStage.show();
    }
     
     public void setForm() {
         try {
             String[] secre = gs.getSecretario(idSecretario);
             update.setId(idSecretario+"");
             nombre.setText(secre[2]);
             apellido.setText(secre[3]);
             cedula.setText(secre[1]);
             telefono.setText(secre[4]);
             direccion.setText(secre[5]);
             NUsuario.setText(secre[6]);
             
         } catch (Exception e) {
             System.out.print(e.toString());
         }
         
         
     
     }
}
