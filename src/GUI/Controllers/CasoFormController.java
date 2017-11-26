/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers;

import Logic.GestorCasos;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import formValidaton.FormValidation;
import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
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
public class CasoFormController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    private GestorCasos gc = new GestorCasos();

    @FXML
    private JFXTextField cedula;

    @FXML
    private JFXButton crear;

    @FXML
    private JFXTextArea descripcion;

    @FXML
    private Label labelDescripcion;
    @FXML
    private MaterialDesignIconView back;

    private String fxml;

    public void setCaso(int id, String fx) {
        crear.setId(id + "");
        fxml = fx;

        back.setOnMouseClicked((event) -> {
            regresar(event);
        });

    }

    public void setCasoQuere(int id, String fx) {
        crear.setId(id + "");
        fxml = fx;
        back.setOnMouseClicked((event) -> {
            regresar(event, id);
        });

    }

    @FXML
    void crearCaso(MouseEvent event) throws IOException, SQLException {
        FormValidation fv = new FormValidation();
        if (fv.validatedArea(descripcion, labelDescripcion)) {

            gc.createCaso(Integer.parseInt(crear.getId()), descripcion.getText());
            regresar(event);

        }
    }

    @FXML
    void regresar(MouseEvent event) {
        try {
            Parent loginEmpView;
            
            loginEmpView = (AnchorPane) FXMLLoader.load(getClass().getResource("/GUI/Views/" + fxml + ".fxml"));
            Scene logScene = new Scene(loginEmpView);
            
            Stage curStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            curStage.setScene(logScene);
            
            curStage.show();
        } catch (IOException ex) {
            Logger.getLogger(CasoFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void regresar(MouseEvent event, int id) {

        Parent loginEmpView = null;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/CasosQuerellante.fxml"));
        try {
            loginEmpView = (AnchorPane) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(CasoFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene logScene = new Scene(loginEmpView);

        Stage curStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        curStage.setScene(logScene);

        CasosQuerellanteController controller = loader.<CasosQuerellanteController>getController();
        controller.mostrarCasos(id);
        controller.setFXML("LoginQuerellante");

        curStage.show();

    }

}
