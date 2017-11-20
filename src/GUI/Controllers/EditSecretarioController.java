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

    @FXML
    private Label labelNombre;

    @FXML
    private Label labelTelefono;

    @FXML
    private Label labelApellido;

    @FXML
    private Label labelCedula;

    @FXML
    private Label labelDireccion;

    @FXML
    private Label nombreUsu;

    private GestorSecretario gs = new GestorSecretario();

    public void setUpdate(int id) {
        idSecretario = id;
        try {
            setForm();
        } catch (IOException ex) {
            Logger.getLogger(EditSecretarioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EditSecretarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        update.setText("Actualizar");
        update.setOnMouseClicked((event) -> {
            try {
                actuali(event);
            } catch (IOException ex) {
                Logger.getLogger(EditSecretarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }
    
    public void setCreate(){
        
        update.setText("Registrar");
        update.setOnMouseClicked((event) -> {
            try {
                crear(event);
            } catch (IOException ex) {
                Logger.getLogger(EditSecretarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
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

    @FXML
    void actuali(MouseEvent mEvent) throws IOException {
        String[] secretario = new String[7];
        if (validateForm()) {
            secretario[0] = update.getId();
            secretario[1] = nombre.getText();
            secretario[2] = apellido.getText();
            secretario[3] = telefono.getText();
            secretario[4] = cedula.getText();
            secretario[5] = direccion.getText();
            secretario[6] = NUsuario.getText();

            try {
                gs.update(secretario);
            } catch (SQLException | IOException ex) {

            } finally {
                regresar(mEvent);
            }

        }
    }
    
    void crear(MouseEvent mEvent)throws IOException{
        String[] secretario = new String[6];
             if (validateForm()) {
            
            secretario[0] = nombre.getText();
            secretario[1] = apellido.getText();
            secretario[2] = telefono.getText();
            secretario[3] = cedula.getText();
            secretario[4] = direccion.getText();
            secretario[5] = NUsuario.getText();

            try {
                gs.update(secretario);
            } catch (SQLException | IOException ex) {

            } finally {
                regresar(mEvent);
            }

        }

    }

    private boolean validateForm() throws IOException {
        FormValidation formquere = new FormValidation();

        JFXTextField[] arrayText = {nombre, apellido, telefono, cedula, NUsuario};
        Label[] arrayLabel = {labelNombre, labelApellido, labelTelefono, labelCedula, nombreUsu, labelDireccion};
        int[] arraySonNumFields = {2, 3};

        return formquere.validateForm(arrayText, arraySonNumFields, direccion, arrayLabel);
    }

    public void setForm() throws IOException, SQLException {

        String[] secre = gs.getSecretario(idSecretario);
        update.setId(idSecretario + "");
        nombre.setText(secre[2]);
        apellido.setText(secre[3]);
        cedula.setText(secre[1]);
        telefono.setText(secre[4]);
        direccion.setText(secre[5]);
        NUsuario.setText(secre[6]);

    }
}
