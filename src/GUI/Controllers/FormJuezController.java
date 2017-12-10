/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers;

import Logic.GestorJuez;
import Logic.GestorSala;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import formValidaton.FormValidation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author mean
 */
public class FormJuezController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTextField nombre;

    @FXML
    private JFXTextField apellido;

    @FXML
    private JFXTextField cedula;

    @FXML
    private JFXTextField telefono;

    @FXML
    private JFXButton btnAccion;

    @FXML
    private JFXTextArea direccion;

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
    private JFXTextField NUsuario;

    @FXML
    private Label titulo;

    @FXML
    private Label nombreUsu;
     @FXML
    private JFXTextField nJuez;

    @FXML
    private Label labelNJuez;

    @FXML
    private JFXComboBox<String> salas;
     @FXML
    private JFXPasswordField contra;
    @FXML
    private Label labelContra;

    GestorSala gs = new GestorSala();
    GestorJuez gj = new GestorJuez();

    
    void actuali(MouseEvent event) throws IOException {
        String[] juez = new String[9];
        if (validateForm()) {
            juez[0] = btnAccion.getId();
            juez[1] = nombre.getText();
            juez[2] = apellido.getText();
            juez[3] = telefono.getText();
            juez[4] = cedula.getText();
            juez[5] = direccion.getText();
            juez[6] = NUsuario.getText();
            juez[7] = salas.getValue();
            juez[8] = nJuez.getText();
            

            try {
                gj.update(juez);
            } catch (SQLException | IOException ex) {

            } finally {
                regresar(event);
            }

        }
    }

    @FXML
    void regresar(MouseEvent event) {
        MenuAdminController ma = new MenuAdminController();
                ma.goToJueces(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setActualizar(int idPersona) {
        setBox();
        fillForm(idPersona);
        titulo.setText("Actulizar Juez");
        btnAccion.setText("Actualizar");
        contra.setVisible(false);
        btnAccion.setOnMouseClicked((event) -> {
            try {
                actuali(event);
            } catch (IOException ex) {
                Logger.getLogger(FormJuezController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }
    public void setCreate(){
    setBox();
    titulo.setText("Crear Juez");
    btnAccion.setText("Registrar");
    btnAccion.setOnMouseClicked((event) -> {
            try {
                create(event);
            } catch (IOException ex) {
                Logger.getLogger(FormJuezController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    void create(MouseEvent event) throws IOException{
         String[] juez = new String[9];
        if (validateForm()) {
            
            juez[0] = nombre.getText();
            juez[1] = apellido.getText();
            juez[2] = telefono.getText();
            juez[3] = cedula.getText();
            juez[4] = direccion.getText();
            juez[5] = NUsuario.getText();
            juez[6] = salas.getValue();
            juez[7] = nJuez.getText();
            juez[8] = contra.getText();
            

            try {
                gj.create(juez);
            } catch (SQLException | IOException ex) {

            } finally {
                regresar(event);
            }

        }
    
    }

    public void fillForm(int idPersona) {
        try {
            String[] juez = gj.getJuez(idPersona);
            nombre.setText(juez[1]);
            cedula.setText(juez[2]);
            apellido.setText(juez[3]);
            telefono.setText(juez[4]);
            direccion.setText(juez[5]);
            NUsuario.setText(juez[6]);
            salas.getSelectionModel().select(juez[7]);
            btnAccion.setId(juez[0]);
            nJuez.setText(juez[8]);

        } catch (SQLException ex) {
            Logger.getLogger(FormJuezController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FormJuezController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setBox() {

        try {
            ArrayList<String[]> strSalas = gs.getSalas();
            for (String[] strSala : strSalas) {
                salas.getItems().add(strSala[1]);

            }
            salas.getSelectionModel().select(0);
        } catch (IOException ex) {
            Logger.getLogger(FormJuezController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FormJuezController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private boolean validateForm() throws IOException {
        FormValidation formquere = new FormValidation();

        JFXTextField[] arrayText = {nombre, apellido, telefono, cedula, NUsuario,nJuez};
        Label[] arrayLabel = {labelNombre, labelApellido, labelTelefono, labelCedula, nombreUsu,labelNJuez, labelDireccion};
        int[] arraySonNumFields = {2, 3,5};

        if (!btnAccion.getText().equals("Actualizar")) {
      if(!formquere.validPassword(contra)){
           labelContra.setText("favor llenar el campo");
           return formquere.validateForm(arrayText, arraySonNumFields, direccion, arrayLabel)&& false;
           
           }
           return formquere.validateForm(arrayText, arraySonNumFields, direccion, arrayLabel)&& true;
        }
         return formquere.validateForm(arrayText, arraySonNumFields, direccion, arrayLabel);
      }

}
