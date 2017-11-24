/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers;

import Logic.GestorQuerellante;
import com.jfoenix.controls.JFXButton;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private Label title;
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
        @FXML
    private JFXButton btnQuere;
        
    private int idQuerellante;
    private String fxml;
    private GestorQuerellante gq = new GestorQuerellante();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
     public void setUpdate(int id) {
        idQuerellante = id;
        try {
            setForm();
            title.setText("Editar Querellante");
        } catch (IOException ex) {
            Logger.getLogger(RegistrarQuerellanteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarQuerellanteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnQuere.setText("Actualizar");
        btnQuere.setOnMouseClicked((event) -> {
            try {
                actuali(event);
            } catch (IOException ex) {
                Logger.getLogger(RegistrarQuerellanteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        

    }
     
     public void setFXML(String ret){
         fxml = ret;
     }
      public void setCreate(){
        
        btnQuere.setText("Registrar");
         title.setText("Registrar Querellante");
        btnQuere.setOnMouseClicked((event) -> {
            try {
                crear(event);
            } catch (IOException ex) {
                Logger.getLogger(RegistrarQuerellanteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    private void regresar(MouseEvent mEvent) throws IOException {
        Parent loginEmpView;

        loginEmpView = (AnchorPane) FXMLLoader.load(getClass().getResource("/GUI/Views/"+fxml+".fxml"));
        Scene logScene = new Scene(loginEmpView);

        Stage curStage = (Stage) ((Node) mEvent.getSource()).getScene().getWindow();
        curStage.setScene(logScene);

        curStage.show();
    }

    
    void actuali(MouseEvent mEvent) throws IOException {
        String[] quere = new String[6];
        if (validateForm()) {
            quere[0] = btnQuere.getId();
            quere[1] = nombre.getText();
            quere[2] = apellido.getText();
            quere[3] = telefono.getText();
            quere[4] = cedula.getText();
            quere[5] = direccion.getText();
            

            try {
                gq.update(quere);
            } catch (SQLException | IOException ex) {

            } finally {
                regresar(mEvent);
            }

        }
    }
    
    void crear(MouseEvent mEvent)throws IOException{
        String[] quere = new String[5];
             if (validateForm()) {
            
            quere[0] = nombre.getText();
            quere[1] = apellido.getText();
            quere[2] = telefono.getText();
            quere[3] = cedula.getText();
            quere[4] = direccion.getText();
           

            try {
                gq.create(quere);
            } catch (SQLException | IOException ex) {

            } finally {
                
                regresar(mEvent);
            }

        }

    }

    private boolean validateForm() throws IOException {
        FormValidation formquere = new FormValidation();
     
        JFXTextField[] arrayText = {nombre, apellido, telefono, cedula};
        Label[] arrayLabel = {labelNombre, labelApellido, labelTelefono, labelCedula, labelDireccion};
        int[] arraySonNumFields = {2, 3};
        
        return formquere.validateForm(arrayText, arraySonNumFields, direccion, arrayLabel);
    }
    
     public void setForm() throws IOException, SQLException {

        String[] quere = gq.getQuerellante(idQuerellante);
        btnQuere.setId(idQuerellante + "");
        nombre.setText(quere[2]);
        apellido.setText(quere[3]);
        cedula.setText(quere[1]);
        telefono.setText(quere[4]);
        direccion.setText(quere[5]);
        

    }

}
