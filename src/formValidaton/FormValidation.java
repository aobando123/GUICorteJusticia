/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formValidaton;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 *
 * @author mean
 */
public class FormValidation {
        final Color error = Color.web("#F44436");
        final Color normal = Color.web("#4d4d4d");

    public FormValidation() {
    }

    public String validTextField(JFXTextField input) {
        String errorMsgs = "";
        int i = 0;
        
        if (input.getText().equals("")) {

            input.setUnFocusColor(error);

            errorMsgs = "Campo Requerido";
        } else {
            input.setUnFocusColor(normal);
        }
        return errorMsgs;
    }
    
      public String validTextField(JFXPasswordField input) {
        String errorMsgs = "";
        int i = 0;
        
        if (input.getText().equals("")) {

            input.setUnFocusColor(error);

            errorMsgs = "Campo Requerido";
        } else {
            input.setUnFocusColor(normal);
        }
        return errorMsgs;
    }

    private String validTextArea(JFXTextArea input) {
        String errorMsgs = "";
        int i = 0;

        if (input.getText().equals("")) {

            input.setUnFocusColor(error);

            errorMsgs = "Campo Requerido";
        } else {
            input.setUnFocusColor(normal);
        }
        return errorMsgs;
    }

    private String validNumber(JFXTextField numb) {
 
        try {
            int textNumber = Integer.parseInt(numb.getText());
            numb.setUnFocusColor(normal);
        } catch (NumberFormatException e) {
            numb.setUnFocusColor(error);
            return "Solamente numeros";
        }
        return "";
    }

    public void validateForm(JFXTextField[] arrayFields, Label[] labels) {

    }

    public void validateForm(JFXTextField[] arrayFields, int[] numFields, Label[] labels) {

    }

    public boolean validateForm(JFXTextField[] arrayFields, int[] numFields, JFXTextArea ta, Label[] labels) {
        String error;
        boolean valid = true;

        for (int i = 0; i < arrayFields.length; i++) {

            error = validTextField(arrayFields[i]);
            if (!error.equals("")) {
                labels[i].setText(error);
                valid = false;
            } else {

                labels[i].setText("");
                //Verificar campos numeros
                for (int numField : numFields) {
                    if (numField == i) {
                        error = validNumber(arrayFields[i]);
                        if (!error.equals("")) {
                            labels[i].setText(error);
                            valid = false;
                        }

                    }
                }
                //Fin del Verificar campos numeros
            }
        }
        error = validTextArea(ta);
        if (!error.equals("")) {
            labels[labels.length - 1].setText(error);
            valid = false;
        }else{
             labels[labels.length - 1].setText("");
        }
        return valid;

    }

}
