/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers;

import Logic.GestorCasos;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mauricio
 */
public class CasosQuerellanteController implements Initializable {

    @FXML
    private JFXTreeTableView<Caso> tblCasos;
    
    private String previusPage;
    
     private ObservableList<Caso> casosColection = FXCollections.observableArrayList();
     
    private int crearCasoIdPersona;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    @FXML
    public void mostrarCasos(int id)
    {
        JFXTreeTableColumn<Caso, String> colCedula = new JFXTreeTableColumn<>("Número de Caso");
        colCedula.setPrefWidth(150);
        colCedula.setCellValueFactory((TreeTableColumn.CellDataFeatures<Caso, String> param) -> param.getValue().getValue().idCaso);
        JFXTreeTableColumn<Caso, String> colNombre = new JFXTreeTableColumn<>("Descripción");
        colNombre.setPrefWidth(250);
        colNombre.setCellValueFactory((TreeTableColumn.CellDataFeatures<Caso, String> param) -> param.getValue().getValue().descripcion);
        JFXTreeTableColumn<Caso, String> colApellido = new JFXTreeTableColumn<>("Fecha de Creación");
        colApellido.setPrefWidth(150);
        colApellido.setCellValueFactory((TreeTableColumn.CellDataFeatures<Caso, String> param) -> param.getValue().getValue().fechaCreacion);
        JFXTreeTableColumn<Caso, String> colTele = new JFXTreeTableColumn<>("Estado");
        colTele.setPrefWidth(150);
        colTele.setCellValueFactory((TreeTableColumn.CellDataFeatures<Caso, String> param) -> param.getValue().getValue().estado);
        JFXTreeTableColumn<Caso, HBox> colEdit = new JFXTreeTableColumn<>("Acciones");
        colEdit.setCellValueFactory((TreeTableColumn.CellDataFeatures<Caso, HBox> param) -> param.getValue().getValue().actions);
        colEdit.setPrefWidth(200);
        fillList(id);
        final TreeItem<Caso> root = new RecursiveTreeItem<>(casosColection, RecursiveTreeObject::getChildren);
        tblCasos.getColumns().setAll(colCedula, colNombre, colApellido, colTele, colEdit);
        tblCasos.setRoot(root);
        tblCasos.setShowRoot(false);
        
    }
    
    public void setFXML(String ret){
         previusPage = ret;
     }
    
    private void fillList(int id) {
        crearCasoIdPersona = id;
        casosColection  = FXCollections.observableArrayList();
        GestorCasos gc = new GestorCasos();
        ArrayList<String[]> list;
        try {
            list = gc.getQuerellantes(crearCasoIdPersona);
            for (String[] lis : list) {
            casosColection.add(new Caso(lis[0], lis[1],
                    lis[2], lis[3]));
        }
        } catch (Exception ex) {
            Logger.getLogger(CasosQuerellanteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    private class Caso extends RecursiveTreeObject<Caso> {

        StringProperty idCaso;
        StringProperty descripcion;
        StringProperty fechaCreacion;
        StringProperty estado;
        
        JFXButton verSolucion;
        ObjectProperty<HBox> actions;

        public Caso(String idCaso, String descripcion, String fechaCreacion, String estado) {
            this.idCaso = new SimpleStringProperty(idCaso);
            this.descripcion = new SimpleStringProperty(descripcion);
            this.fechaCreacion = new SimpleStringProperty(fechaCreacion);
            this.estado = new SimpleStringProperty(estado);
            
            verSolucion = mostarSolucion();
            verSolucion.setId(idCaso);

            actions = new SimpleObjectProperty<HBox>(new HBox(verSolucion));
        }
    }
    
    private JFXButton mostarSolucion()
    {
        JFXButton btn = new JFXButton("Ver Solución");
        btn.getStyleClass().add("btn-edit");
        btn.setPrefSize(200, 40);

        btn.setOnMouseClicked((MouseEvent event) -> {
            int id = Integer.parseInt(btn.getId());

            Parent loginEmpView = null;

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/ActualizarCasoJuez.fxml"));
                loginEmpView = (AnchorPane) loader.load();
                Scene logScene = new Scene(loginEmpView);

                Stage curStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                curStage.setScene(logScene);

                ActualizarCasoJuezController controller = loader.<ActualizarCasoJuezController>getController();
                controller.actualizarCaso(crearCasoIdPersona,id, true);
                curStage.show();

            } catch (IOException ex) {
                Logger.getLogger(CRUDSecretarioController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(CasosQuerellanteController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        return btn;
    }  
    
    @FXML
    void crearCaso(MouseEvent ev) throws IOException
    {
        BuscarQuerellanteController crearCasoFuntion = new BuscarQuerellanteController();
        crearCasoFuntion.creaCaso(ev, crearCasoIdPersona, true);   
    }
    
    @FXML
    private void logout(MouseEvent event){
     MenuAdminController ma = new MenuAdminController();
     ma.goTo(event, "LoginQuerellante");
    }
}
