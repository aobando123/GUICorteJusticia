/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers;

import Logic.GestorQuerellante;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
public class CRUDQuerellanteController implements Initializable {

    @FXML
    private JFXTreeTableView<Querellante> tblQuerellante;
    
    private GestorQuerellante gq = new GestorQuerellante();
    private ObservableList<Querellante> queres = FXCollections.observableArrayList();

    @FXML
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
                controller.setFXML("CRUDQuerellante");
                curStage.show();

            } catch (IOException ex) {
                Logger.getLogger(CRUDSecretarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
    }
    private void initTable(){
        JFXTreeTableColumn<Querellante, String> colCedula = new JFXTreeTableColumn<>("Cédula");
        colCedula.setPrefWidth(150);
        colCedula.setCellValueFactory((TreeTableColumn.CellDataFeatures<Querellante, String> param) -> param.getValue().getValue().cedula);
        JFXTreeTableColumn<Querellante, String> colNombre = new JFXTreeTableColumn<>("Nombre");
        colNombre.setPrefWidth(150);
        colNombre.setCellValueFactory((TreeTableColumn.CellDataFeatures<Querellante, String> param) -> param.getValue().getValue().nombre);
        JFXTreeTableColumn<Querellante, String> colApellido = new JFXTreeTableColumn<>("Apellido");
        colApellido.setPrefWidth(150);
        colApellido.setCellValueFactory((TreeTableColumn.CellDataFeatures<Querellante, String> param) -> param.getValue().getValue().apellido);
        JFXTreeTableColumn<Querellante, String> colTele = new JFXTreeTableColumn<>("Teléfono");
        colTele.setPrefWidth(150);
        colTele.setCellValueFactory((TreeTableColumn.CellDataFeatures<Querellante, String> param) -> param.getValue().getValue().telefono);
        JFXTreeTableColumn<Querellante, String> colDir = new JFXTreeTableColumn<>("Dirección");
        colDir.setPrefWidth(150);
        colDir.setCellValueFactory((TreeTableColumn.CellDataFeatures<Querellante, String> param) -> param.getValue().getValue().direccion);
        JFXTreeTableColumn<Querellante, HBox> colEdit = new JFXTreeTableColumn<>("Acciones");
        colEdit.setCellValueFactory((TreeTableColumn.CellDataFeatures<Querellante, HBox> param) -> param.getValue().getValue().actions);
        colEdit.setPrefWidth(200);
        fillList();
        final TreeItem<Querellante> root = new RecursiveTreeItem<>(queres, RecursiveTreeObject::getChildren);
        tblQuerellante.getColumns().setAll(colCedula, colNombre, colApellido, colTele, colDir, colEdit);
        tblQuerellante.setRoot(root);
        tblQuerellante.setShowRoot(false);
    }


    private void fillList() {
        queres  = FXCollections.observableArrayList();

        try {
            ArrayList<String[]> list = gq.getQuerellantes();
            for (String[] lis : list) {
                queres.add(new Querellante(lis[0], lis[1],
                        lis[2], lis[3], lis[4], lis[5]));
            }
        } catch (SQLException | IOException e) {
        }

    }
    @FXML
    private void regresar(MouseEvent event){
        MenuAdminController ma = new MenuAdminController();
        ma.goTo(event, "MenuAdmin");
    }
    
    @FXML
    private void logout(MouseEvent event){
     MenuAdminController ma = new MenuAdminController();
     ma.goTo(event, "LoginEmpleado");
    }
    
    private JFXButton setEditButton() {
        JFXButton btn = new JFXButton("Editar");
        btn.getStyleClass().add("btn-edit");
        btn.setPrefSize(90, 40);

        btn.setOnMouseClicked((MouseEvent event) -> {
            int id = Integer.parseInt(btn.getId());

            Parent loginEmpView = null;

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/registrarQuerellante.fxml"));
                loginEmpView = (AnchorPane) loader.load();
                Scene logScene = new Scene(loginEmpView);

                Stage curStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                curStage.setScene(logScene);

                RegistrarQuerellanteController controller = loader.<RegistrarQuerellanteController>getController();
                controller.setUpdate(id);
                controller.setFXML("CRUDQuerellante");
                curStage.show();

            } catch (IOException ex) {
                Logger.getLogger(CRUDSecretarioController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        return btn;
    }

    private JFXButton setDeleteButton() {
        JFXButton btn = new JFXButton("Eliminar");
        
        btn.getStyleClass().add("btn-delete");
        btn.setPrefSize(90, 40);
        btn.setOnMouseClicked((event) -> {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Eliminar Querellante");
            alerta.setHeaderText("¿Está seguro de eliminar este querellante?");
            ButtonType yesBtn = new ButtonType("Si");
            
            alerta.getButtonTypes().setAll(yesBtn,ButtonType.NO);
            
            Optional<ButtonType> result = alerta.showAndWait();
            if (result.get() == yesBtn){
                deleteRow(Integer.parseInt(btn.getId()));
            }
        });
        return btn;
    }
    
    private void deleteRow(int i){
        try {
           gq.delete(i);
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Se elimino querellante");
            alerta.setHeaderText("El querellante ha sido eliminado exitosamente");
            alerta.showAndWait();
            initTable();
        } catch (SQLException ex) {
            Logger.getLogger(CRUDSecretarioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CRUDSecretarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 private class Querellante extends RecursiveTreeObject<Querellante> {

        StringProperty idPersona;
        StringProperty cedula;
        StringProperty nombre;
        StringProperty apellido;
        StringProperty telefono;
        StringProperty direccion;
        
        JFXButton editbtn;
        JFXButton deletebtn;
        ObjectProperty<HBox> actions;

        public Querellante(String idPersona, String cedula, String nombre, String apellido,
                String telefono, String direccion) {
            this.idPersona = new SimpleStringProperty(idPersona);
            this.cedula = new SimpleStringProperty(cedula);
            this.nombre = new SimpleStringProperty(nombre);
            this.apellido = new SimpleStringProperty(apellido);
            this.telefono = new SimpleStringProperty(telefono);
            this.direccion = new SimpleStringProperty(direccion);
            
            editbtn = setEditButton();
            deletebtn = setDeleteButton();
            editbtn.setId(idPersona);
            deletebtn.setId(idPersona);

            actions = new SimpleObjectProperty<HBox>(new HBox(editbtn, deletebtn));

        }

    }
}
