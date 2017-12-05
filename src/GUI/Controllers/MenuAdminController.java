/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mean
 */
public class MenuAdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
        @FXML
    void goToJueces(MouseEvent event) {
            goTo(event, "CRUDJuez");
    }

    @FXML
    void goToQuerellante(MouseEvent event) {
          goTo(event, "CRUDQuerellante");
    }

    @FXML
    public void goToSecretarios(MouseEvent event) {
        goTo(event, "CRUDSecretario");
    }
    
    public void goTo(MouseEvent event, String fxml){
      Parent loginEmpView;
      try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/"+fxml+".fxml"));
                loginEmpView = (AnchorPane) loader.load();
                Scene logScene = new Scene(loginEmpView);

                Stage curStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                curStage.setScene(logScene);

               
                curStage.show();

            } catch (Exception ex) {
                System.out.println(ex);
            }
     }
    
     @FXML
    private void logout(MouseEvent event){
     MenuAdminController ma = new MenuAdminController();
     ma.goTo(event, "LoginEmpleado");
    }
    
    
    
}
