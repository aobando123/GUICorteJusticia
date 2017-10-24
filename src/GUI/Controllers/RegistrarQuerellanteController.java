/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers;

import com.jfoenix.controls.JFXTextArea;
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
import formValidaton.FormValidation;
import java.util.ArrayList;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author mean
 */
public class RegistrarQuerellanteController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane regQuerellante;

    @FXML
    private JFXTextField nombre;

    @FXML
    private JFXTextField apellido;

    @FXML
    private JFXTextField telefono;
    @FXML
    private JFXTextField cedula;

    @FXML
    private JFXTextArea direccion;

    @FXML
    private Label labelNombre;

    @FXML
    private Label labelApellido;
    @FXML
    private Label labelTelefono;
    @FXML
    private Label labelCedula;
    @FXML
    private Label labelDireccion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void regresar(MouseEvent mEvent) throws IOException {
        Parent loginEmpView;

        loginEmpView = (AnchorPane) FXMLLoader.load(getClass().getResource("/GUI/Views/LoginQuerellante.fxml"));
        Scene logScene = new Scene(loginEmpView);

        Stage curStage = (Stage) ((Node) mEvent.getSource()).getScene().getWindow();
        curStage.setScene(logScene);

        curStage.show();
    }

    @FXML
    private void registrar(MouseEvent e) throws IOException {
        
        validateForm();
    }

    private void validateForm() throws IOException {
        FormValidation formquere = new FormValidation();
     
        JFXTextField[] arrayText = {nombre, apellido, telefono, cedula};
        Label[] arrayLabel = {labelNombre, labelApellido, labelTelefono, labelCedula, labelDireccion};
        int[] arraySonNumFields = {2, 3};
        
        formquere.validateForm(arrayText, arraySonNumFields, direccion, arrayLabel);
    }

}
