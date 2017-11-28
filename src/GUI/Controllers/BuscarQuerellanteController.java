/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers;

import Logic.GestorQuerellante;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import formValidaton.FormValidation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mean
 */
public class BuscarQuerellanteController implements Initializable {
      @FXML
    private JFXTextField cedula;

    @FXML
    private JFXButton btnBuscar;
    @FXML
    private Label message;
    
    private GestorQuerellante gq= new GestorQuerellante();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    void buscarQuerellante(MouseEvent mEvent){
        FormValidation fv = new FormValidation();
        if (fv.validateField(cedula, message)) {
            try {
                int id = gq.getId(Integer.parseInt(cedula.getText()));
                if(id==0){
                alertaCreaQuere(mEvent);
                }else{
                    creaCaso(mEvent, id, false);
                }
            } catch (SQLException  ex ) {
                Logger.getLogger(BuscarQuerellanteController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(BuscarQuerellanteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      
    }

    private void alertaCreaQuere(MouseEvent ev) {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("No se ha encontrado");
            alerta.setHeaderText("No se ha encontrado el querellante ¿desea crearlo?");
            ButtonType yesBtn = new ButtonType("Si");
            
            alerta.getButtonTypes().setAll(yesBtn,ButtonType.NO);
            
            Optional<ButtonType> result = alerta.showAndWait();
            if (result.get() == yesBtn){
                crearQuerellante(ev);
            }
    }
    
      void crearQuerellante(MouseEvent event) {
        Parent loginEmpView;

        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/registrarQuerellante.fxml"));
                loginEmpView = (AnchorPane) loader.load();
                Scene logScene = new Scene(loginEmpView);

                Stage curStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                curStage.setScene(logScene);

                RegistrarQuerellanteController controller = loader.<RegistrarQuerellanteController>getController();
                controller.setCreate();
                controller.setFXML("BuscarQuerellante");
                curStage.show();

            } catch (IOException ex) {
                Logger.getLogger(CRUDSecretarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
      
     void creaCaso(MouseEvent event, int id, boolean isQ){
         Parent loginEmpView;
      try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/CasoForm.fxml"));
                loginEmpView = (AnchorPane) loader.load();
                Scene logScene = new Scene(loginEmpView);

                Stage curStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                curStage.setScene(logScene);

                CasoFormController controller = loader.<CasoFormController>getController();
                if(isQ)
                {
                    controller.setCasoQuere(id, "CasosQuerellante");
                }
                else
                {
                    controller.setCaso(id, "BuscarQuerellante");
                }
                curStage.show();

            } catch (IOException ex) {
                Logger.getLogger(CRUDSecretarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
     }
     @FXML
    private void logout(MouseEvent event){
     MenuAdminController ma = new MenuAdminController();
     ma.goTo(event, "LoginEmpleado");
    }
}
