/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers;

import Logic.GestorSecretario;
import com.jfoenix.controls.JFXButton;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableObjectValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import javafx.stage.Stage;

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
    @FXML
    private AnchorPane aPane;

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
        JFXTreeTableColumn<Secretario, HBox> colEdit = new JFXTreeTableColumn<>("Acciones");
        colEdit.setCellValueFactory((TreeTableColumn.CellDataFeatures<Secretario, HBox> param) -> param.getValue().getValue().actions);
        colEdit.setPrefWidth(200);
        fillList();
        final TreeItem<Secretario> root = new RecursiveTreeItem<>(secres, RecursiveTreeObject::getChildren);
        tblSecretario.getColumns().setAll(colCedula, colNombre, colApellido, colTele, colDir, colUsuario, colEdit);
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

    private JFXButton setEditButton() {
        JFXButton btn = new JFXButton("Editar");
        btn.getStyleClass().add("btn-edit");
        btn.setPrefSize(90, 40);

        btn.setOnMouseClicked((MouseEvent event) -> {
            int id = Integer.parseInt(btn.getId());

           Parent loginEmpView = null;
FXMLLoader loader = null;
            try {
                  loader = new FXMLLoader(getClass().getResource("/GUI/Views/EditSecretario.fxml"));

                loginEmpView = (AnchorPane) loader.load();
                EditSecretarioController controller = loader.<EditSecretarioController>getController();
                controller.setId(id);
                controller.setForm();

            } catch (IOException ex) {
                Logger.getLogger(CRUDSecretarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        Scene logScene = new Scene(loginEmpView);

        Stage curStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        curStage.setScene(logScene);
        EditSecretarioController controller = loader.<EditSecretarioController>getController();
                controller.setId(id);
                controller.setForm();

        curStage.show();
        });
        return btn;
    }

    private JFXButton setDeleteButton() {
        JFXButton btn = new JFXButton("Eliminar");
        btn.getStyleClass().add("btn-delete");
        btn.setPrefSize(90, 40);
        btn.setOnMouseClicked((event) -> {
            String id = btn.getId();
            System.out.print("Borrare " + id);
        });
        return btn;
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
        JFXButton editbtn;
        JFXButton deletebtn;
        ObjectProperty<HBox> actions;

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
            editbtn = setEditButton();
            deletebtn = setDeleteButton();
            editbtn.setId(idPersona);
            deletebtn.setId(idPersona);

            actions = new SimpleObjectProperty<HBox>(new HBox(editbtn, deletebtn));

        }

    }
}
