/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers;

import Logic.GestorCasos;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mauricio
 */
public class ActualizarCasoJuezController implements Initializable {
    @FXML
    private MaterialDesignIconView back;
    /**
     * Initializes the controller class.
     */
    @FXML
    private Label numeroCaso;

    @FXML
    private JFXTextArea descripcion;

    @FXML
    private Label fecha;

    @FXML
    private Label querellante;
    
    @FXML
    private ComboBox<String> cb;
    
    private String juezId;
    
    @FXML
    private VBox solucion;

    private GestorCasos gc = new GestorCasos();
    
    @FXML
    private JFXButton actualizarCaso;
    
    TextArea txA;    
    private int id;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
        
    @FXML
    public void actualizarCaso(int juez, int idCaso, boolean isQuerellante) throws IOException, SQLException
    {        
        String[] result = gc.getCaso(idCaso);
        numeroCaso.setText(result[0]);
        descripcion.setText(result[1]);
        fecha.setText(result[2]);
        String id = idCaso+"";
        List c =  gc.getCheckBox(result[4], id);
        cb.getItems().addAll(c);
        cb.getSelectionModel().select(result[4]);        
        querellante.setText(result[3]);
        actualizarCaso.setId(id);
        juezId = juez+"";   
        cb.valueProperty().addListener(new ChangeListener<String>(){
        @Override public void changed(ObservableValue ov, String t, String t1)
        {
            if(t1.equals("Resuelto"))
            {
                Label lbSol = new Label();
                lbSol.setText("Resolución:");
                txA = new JFXTextArea();
                solucion.getChildren().add(lbSol);
                solucion.getChildren().add(txA);
            }
        }
        });
        if(result[4].equals("Resuelto") || isQuerellante )
        {
            actualizarCaso.setDisable(true);
                Label lbSol = new Label();
                lbSol.setText("Resolución:");
                txA = new JFXTextArea();
                txA.setText(result[5]);
                solucion.getChildren().add(lbSol);
                txA.setDisable(true);
                cb.setDisable(true);
                solucion.getChildren().add(txA);
        }
        if(isQuerellante)
        {
            back.setOnMouseClicked((event) -> {
            regresarQuerellante(event);
            });
             cb.setDisable(true);
        }
        else
        {
            back.setOnMouseClicked((event) -> {
            regresar(event);
            });
        }
    }

    
    @FXML
    public void actualizar(MouseEvent mEvent) throws SQLException, IOException
    {
        Parent loginEmpView;
        gc.updateEstadoCaso(actualizarCaso.getId(), cb.getSelectionModel().getSelectedItem());
        if(cb.getSelectionModel().getSelectedItem().equals("Resuelto"))
        {
            gc.agregarSolucion(actualizarCaso.getId(),txA.getText());
        }        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/CasosJuez.fxml"));
        loginEmpView = (AnchorPane) loader.load();
        Scene logScene = new Scene(loginEmpView);
        CasosJuezController controller = loader.<CasosJuezController>getController();
        controller.mostrarCasos(Integer.parseInt(juezId));
        Stage curStage = (Stage) ((Node) mEvent.getSource()).getScene().getWindow();
        curStage.setScene(logScene);
        curStage.show();
    }    
    
     void regresar(MouseEvent event) {
        Parent loginEmpView = null;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/CasosJuez.fxml"));
        try {
            loginEmpView = (AnchorPane) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(CasoFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene logScene = new Scene(loginEmpView);

        Stage curStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        curStage.setScene(logScene);

        CasosJuezController controller = loader.<CasosJuezController>getController();
        controller.mostrarCasos(Integer.parseInt(juezId));
        
        curStage.show();
    }

    void regresarQuerellante(MouseEvent event) {

        Parent loginEmpView = null;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/CasosQuerellante.fxml"));
        try {
            loginEmpView = (AnchorPane) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(CasoFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene logScene = new Scene(loginEmpView);

        Stage curStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        curStage.setScene(logScene);

        CasosQuerellanteController controller = loader.<CasosQuerellanteController>getController();
        controller.mostrarCasos(Integer.parseInt(juezId));
        controller.setFXML("LoginQuerellante");

        curStage.show();

    }
}
