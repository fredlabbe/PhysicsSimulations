/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Diffraction;

import static Diffraction.diffraction.comboBoxDiffraction;
import static Diffraction.diffraction.diffractionChart;
import static Diffraction.diffraction.textDiffraction;
import static Diffraction.diffraction.textFieldDiffraction;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class diffractionLayout {

    public static VBox diffractionOptionPane = new VBox();
    public static BorderPane diffractionGraphPane = new BorderPane();
    HBox wordsAndFields = new HBox();
    VBox textPane = new VBox();
    VBox textField = new VBox();
    Text optionText = new Text("Options");

    public diffractionLayout() {

        
        diffractionGraphPane.setCenter(diffractionChart);
        //Text Pane
        textPane.setAlignment(Pos.CENTER);
        textPane.setPrefSize(150, 250);
        textPane.setSpacing(20);
        for (int i = 0; i < textDiffraction.size(); i++) {
            textPane.getChildren().add(textDiffraction.get(i));
            textDiffraction.get(i).setStyle("-fx-font: 25px Serif;");
        }
        //Text Fields
        textField.setAlignment(Pos.CENTER);
        textField.setPrefSize(200, 250);
        textField.setSpacing(23);
        textField.getChildren().add(comboBoxDiffraction.get(0));
        textField.getChildren().add(comboBoxDiffraction.get(1));
        textField.getChildren().add(textFieldDiffraction.get(0));
        
        //Option Text
        optionText.setStyle("-fx-font: 60px Serif;");
        
        
         //wordsAndFields 
        wordsAndFields.setPrefSize(450, 250);
        wordsAndFields.getChildren().addAll(textPane, textField);

        //OptionPane
        diffractionOptionPane.setMinSize(400, 350);
        diffractionOptionPane.setMaxSize(400, 350);
        diffractionOptionPane.setAlignment(Pos.TOP_CENTER);
        diffractionOptionPane.getChildren().addAll(optionText, wordsAndFields);
    }

}
