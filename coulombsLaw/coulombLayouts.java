/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coulombsLaw;

import static coulombsLaw.CoulombLaw.btnAdd;
import static coulombsLaw.CoulombLaw.textFields;
import static coulombsLaw.CoulombLaw.texts;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class coulombLayouts {

    //Top Right
    public static VBox optionPane = new VBox();
    HBox wordsAndFields = new HBox();
    VBox textPane = new VBox();
    VBox textField = new VBox();
    Text optionText = new Text("Options");

    public coulombLayouts() {

        //Option Text
        optionText.setStyle("-fx-font: 60px Serif;");
        
        //Create Button
        btnAdd.setAlignment(Pos.BASELINE_CENTER);

        //Text Pane
        textPane.setAlignment(Pos.CENTER);
        textPane.setPrefSize(150, 250);
        textPane.setSpacing(20);
        for(int i = 0; i < texts.size(); i++) {
            textPane.getChildren().add(texts.get(i));
            texts.get(i).setStyle("-fx-font: 25px Serif;");
            
        }
        
        //Text Fields
        textField.setAlignment(Pos.CENTER);
        textField.setPrefSize(200, 250);
        textField.setSpacing(23);
        for(int i = 0; i < textFields.size(); i++){
            textField.getChildren().add(textFields.get(i));
            textField.setSpacing(23);
        }
        
        //wordsAndFields 
        wordsAndFields.setPrefSize(450, 250);
        wordsAndFields.getChildren().addAll(textPane,textField);

        //OptionPane
        optionPane.setMinSize(400, 350);
        optionPane.setMaxSize(400, 350);
        optionPane.setAlignment(Pos.TOP_CENTER);
        optionPane.getChildren().addAll(optionText,wordsAndFields, btnAdd);
        
    }
}
