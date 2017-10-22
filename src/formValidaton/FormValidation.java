/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formValidaton;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 *
 * @author mean
 */
public class FormValidation {

    public FormValidation() {
    }

    private String validTextField(JFXTextField input) {
        String errorMsgs = "";
        int i = 0;
        Color error = Color.web("#F44436");

        if (input.getText().equals("")) {

            input.setUnFocusColor(error);

            errorMsgs = "Campo Requerido";
        }
        return errorMsgs;
    }

    private String validTextArea(JFXTextArea input) {
        String errorMsgs = "";
        int i = 0;
        Color error = Color.web("#F44436");
        if (input.getText().equals("")) {

            input.setUnFocusColor(error);

            errorMsgs = "Campo Requerido";
        }
        return errorMsgs;
    }

    private String validNumber(JFXTextField numb) {
        try {
            int textNumber = Integer.parseInt(numb.getText());
        } catch (NumberFormatException e) {
            return "Solamente numeros";
        }
        return "";
    }

    public void validateForm(JFXTextField[] arrayFields, Label[] labels) {

    }

    public void validateForm(JFXTextField[] arrayFields, int[] numFields, Label[] labels) {

    }

    public void validateForm(JFXTextField[] arrayFields, int[] numFields, JFXTextArea ta, Label[] labels) {
        String error;

        for (int i = 0; i < arrayFields.length; i++) {

            error = validTextField(arrayFields[i]);
            if (!error.equals("")) {
                labels[i].setText(error);
            } else {
                for (int numField : numFields) {
                    if (numField == i) {
                        error = validNumber(arrayFields[i]);
                        if (!error.equals("")) {
                            labels[i].setText(error);
                        }
                    }
                }
            }
        }
        error = validTextArea(ta);
        if (!error.equals("")) {
            labels[labels.length-1].setText(error);
        }

    }

}
