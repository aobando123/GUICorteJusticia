/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers;

import Logic.GestorSecretario;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author mean
 */
public class CRUDSecretarioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTreeTableView<Secretario> tblSecretario;
    private ObservableList<Secretario> secres = FXCollections.observableArrayList();
     private GestorSecretario gS = new GestorSecretario();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        JFXTreeTableColumn<Secretario, String> colCedula = new JFXTreeTableColumn<>("Cedula");
        colCedula.setPrefWidth(150);
        colCedula.setCellValueFactory((TreeTableColumn.CellDataFeatures<Secretario, String> param) -> param.getValue().getValue().cedula);
        JFXTreeTableColumn<Secretario, String> colNombre = new JFXTreeTableColumn<>("Nombre");
        colNombre.setPrefWidth(150);
        colNombre.setCellValueFactory((TreeTableColumn.CellDataFeatures<Secretario, String> param) -> param.getValue().getValue().nombre);
        JFXTreeTableColumn<Secretario, String> colApellido = new JFXTreeTableColumn<>("Apellido");
        colApellido.setPrefWidth(150);
        colApellido.setCellValueFactory((TreeTableColumn.CellDataFeatures<Secretario, String> param) -> param.getValue().getValue().apellido);
        JFXTreeTableColumn<Secretario, String> colTele = new JFXTreeTableColumn<>("Telefono");
        colTele.setPrefWidth(150);
        colTele.setCellValueFactory((TreeTableColumn.CellDataFeatures<Secretario, String> param) -> param.getValue().getValue().telefono);
        JFXTreeTableColumn<Secretario, String> colDir = new JFXTreeTableColumn<>("Direccion");
        colDir.setPrefWidth(150);
        colDir.setCellValueFactory((TreeTableColumn.CellDataFeatures<Secretario, String> param) -> param.getValue().getValue().direccion);
        JFXTreeTableColumn<Secretario, String> colUsuario = new JFXTreeTableColumn<>("Nombre de usuario");
        colUsuario.setPrefWidth(150);
        colUsuario.setCellValueFactory((TreeTableColumn.CellDataFeatures<Secretario, String> param) -> param.getValue().getValue().nombreUsuario);
        fillList();
        final TreeItem<Secretario> root = new RecursiveTreeItem<Secretario>(secres, RecursiveTreeObject::getChildren);
        tblSecretario.getColumns().setAll(colCedula,colNombre,colApellido,colTele,colDir,colUsuario);
        tblSecretario.setRoot(root);
        tblSecretario.setShowRoot(false);

    }

    private void fillList() {

        try {
            ArrayList<String[]> list = gS.getSecretarios();
            for (String[] lis : list) {
                secres.add(new Secretario(lis[0], lis[1],
                        lis[2], lis[3], lis[4], lis[5], lis[6],
                        lis[7]));
            }
        } catch (SQLException | IOException e) {
        }

    }

    private class Secretario extends RecursiveTreeObject<Secretario> {

        StringProperty idPersona;
        StringProperty cedula;
        StringProperty nombre;
        StringProperty apellido;
        StringProperty telefono;
        StringProperty direccion;
        StringProperty nombreUsuario;
        StringProperty idUsuario;

        public Secretario(String idPersona, String cedula, String nombre, String apellido,
                String telefono, String direccion, String nombreUsuario, String idUsuario) {
            this.idPersona = new SimpleStringProperty(idPersona);
            this.cedula = new SimpleStringProperty(cedula);
            this.nombre = new SimpleStringProperty(nombre);
            this.apellido = new SimpleStringProperty(apellido);
            this.telefono = new SimpleStringProperty(telefono);
            this.direccion = new SimpleStringProperty(direccion);
            this.nombreUsuario = new SimpleStringProperty(nombreUsuario);
            this.idUsuario = new SimpleStringProperty(idUsuario);
        }

    }
}
