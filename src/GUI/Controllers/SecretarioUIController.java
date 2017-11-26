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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mean
 */
public class SecretarioUIController implements Initializable {
    @FXML
    private JFXTreeTableView<Caso> tblCasos;
     private ObservableList<Caso> cases = FXCollections.observableArrayList();
     private GestorCasos gc = new GestorCasos();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
    }
    
     private void initTable(){
        
                
     JFXTreeTableColumn<Caso, String> colNumCaso = new JFXTreeTableColumn<>("Numero de Caso");
        colNumCaso.setPrefWidth(150);
        colNumCaso.setCellValueFactory((TreeTableColumn.CellDataFeatures<Caso, String> param) -> param.getValue().getValue().numeroCaso);
        JFXTreeTableColumn<Caso, String> colDesc = new JFXTreeTableColumn<>("Descripcion");
        colDesc.setPrefWidth(150);
        colDesc.setCellValueFactory((TreeTableColumn.CellDataFeatures<Caso, String> param) -> param.getValue().getValue().descripcion);
        JFXTreeTableColumn<Caso, String> colFecha = new JFXTreeTableColumn<>("Fecha");
        colFecha.setPrefWidth(150);
        colFecha.setCellValueFactory((TreeTableColumn.CellDataFeatures<Caso, String> param) -> param.getValue().getValue().FechaCfeacion);
        JFXTreeTableColumn<Caso, String> colEstado = new JFXTreeTableColumn<>("Estado");
        colEstado.setPrefWidth(150);
        colEstado.setCellValueFactory((TreeTableColumn.CellDataFeatures<Caso, String> param) -> param.getValue().getValue().Estado);
        JFXTreeTableColumn<Caso, String> colDenun = new JFXTreeTableColumn<>("Denunciante");
        colDenun.setPrefWidth(150);
        colDenun.setCellValueFactory((TreeTableColumn.CellDataFeatures<Caso, String> param) -> param.getValue().getValue().Denunciante);
       
        JFXTreeTableColumn<Caso, JFXButton> colAsig = new JFXTreeTableColumn<>("Acciones");
        colAsig.setCellValueFactory((TreeTableColumn.CellDataFeatures<Caso, JFXButton> param) -> param.getValue().getValue().actions);
        colAsig.setPrefWidth(200);
        fillList();
        final TreeItem<Caso> root = new RecursiveTreeItem<>(cases, RecursiveTreeObject::getChildren);
        tblCasos.getColumns().setAll(colNumCaso, colDesc, colFecha, colEstado, colDenun, colAsig);
        tblCasos.setRoot(root);
        tblCasos.setShowRoot(false);
    }


    private void fillList() {
        cases  = FXCollections.observableArrayList();

        try {
            ArrayList<String[]> list = gc.getCasosNull();
            for (String[] lis : list) {
                cases.add(new Caso(lis[0], lis[1],
                        lis[2], lis[3], lis[4]));
            }
        } catch (SQLException | IOException e) {
        }

    }
      private class Caso extends RecursiveTreeObject<Caso> {

        StringProperty numeroCaso;
        StringProperty descripcion;
        StringProperty FechaCfeacion;
        StringProperty Estado;
        StringProperty Denunciante;
        
        JFXButton agregarJuez;
        ObjectProperty<JFXButton> actions;
        
        
        public Caso(String numeroCaso, String descripcion, String FechaCfeacion,
                 String Denunciante, String Estado) {
            this.numeroCaso = new SimpleStringProperty(numeroCaso);
            this.descripcion = new SimpleStringProperty(descripcion);
            this.FechaCfeacion = new SimpleStringProperty(FechaCfeacion);
            this.Estado = new SimpleStringProperty(Estado);
            this.Denunciante = new SimpleStringProperty(Denunciante);
           
            agregarJuez = setAsignarBtn();
            
            agregarJuez.setId(numeroCaso);
            

            actions = new SimpleObjectProperty<JFXButton>(agregarJuez);

        }

        private JFXButton setAsignarBtn() {
        JFXButton btn = new JFXButton("Asignar");
        btn.getStyleClass().add("btn-edit");
        btn.setPrefSize(90, 40);

        btn.setOnMouseClicked((MouseEvent event) -> {
            int id = Integer.parseInt(btn.getId());

            

//            try {
//               //assign to judge
//
//            } catch (IOException ex) {
//                Logger.getLogger(CRUDSecretarioController.class.getName()).log(Level.SEVERE, null, ex);
//            }

        });
        return btn;
        }

    }
    
}
