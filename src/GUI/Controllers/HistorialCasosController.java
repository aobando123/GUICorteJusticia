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
public class HistorialCasosController implements Initializable {

     @FXML
    private JFXTreeTableView<Historial> tableCasos;
     
    private int crearCasoIdPersona;
    private ObservableList<Historial> casosColection = FXCollections.observableArrayList();
    private GestorCasos gc = new GestorCasos();
    private String fxml;
    private int juezId= 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     @FXML
    public void mostrarCasos(int id , int juez)
    {
        juezId = juez;
        JFXTreeTableColumn<Historial, String> colApellido = new JFXTreeTableColumn<>("Fecha de modificación");
        colApellido.setPrefWidth(150);
        colApellido.setCellValueFactory((TreeTableColumn.CellDataFeatures<Historial, String> param) -> param.getValue().getValue().fechaCreacion);
        JFXTreeTableColumn<Historial, String> colTele = new JFXTreeTableColumn<>("Estado");
        colTele.setPrefWidth(150);
        colTele.setCellValueFactory((TreeTableColumn.CellDataFeatures<Historial, String> param) -> param.getValue().getValue().estado);
        fillList(id);
        final TreeItem<Historial> root = new RecursiveTreeItem<>(casosColection, RecursiveTreeObject::getChildren);
        tableCasos.getColumns().setAll(colApellido, colTele);
        tableCasos.setRoot(root);
        tableCasos.setShowRoot(false);        
    }
    
    private void fillList(int id) {
        crearCasoIdPersona = id;
        casosColection  = FXCollections.observableArrayList();
        ArrayList<String[]> list;
        try {
            list = gc.getHistorialCaso(id);
            for (String[] lis : list) {
            casosColection.add(new Historial(lis[0], lis[1]));
        }
        } catch (Exception ex) {
            Logger.getLogger(CasosQuerellanteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setFXML(String redirect)
    {
        fxml = redirect;
    }
    
    private class Historial extends RecursiveTreeObject<Historial> {
        StringProperty fechaCreacion;
        StringProperty estado;

        public Historial(String fechaCreacion, String estado) {
            this.fechaCreacion = new SimpleStringProperty(fechaCreacion);
            this.estado = new SimpleStringProperty(estado);
        }     
    }
   
    @FXML
    void regresarJuez(MouseEvent event) {

        Parent loginEmpView = null;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/"+ fxml+".fxml"));
        try {
            loginEmpView = (AnchorPane) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(CasoFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene logScene = new Scene(loginEmpView);

        Stage curStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        curStage.setScene(logScene);

        CasosJuezController controller = loader.<CasosJuezController>getController();
        controller.mostrarCasos(juezId);
        
        curStage.show();

    }

}
