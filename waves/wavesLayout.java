/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package waves;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import static waves.waves.textFieldWaves;
import static waves.waves.textWaves;
import static waves.waves.wavesChart;


public class wavesLayout {
    
    public static VBox wavesOptionPane = new VBox();
    public static BorderPane wavesGraph = new BorderPane();
    HBox wordsAndFields = new HBox();
    VBox textPane = new VBox();
    VBox textField = new VBox();
    Text optionText = new Text("Options");
    
    public wavesLayout(){
        
        wavesGraph.setCenter(wavesChart);
        
        
        
        //Text Pane
        textPane.setAlignment(Pos.CENTER);
        textPane.setPrefSize(150, 250);
        textPane.setSpacing(20);
        for (int i = 0; i < textWaves.size(); i++) {
            textPane.getChildren().add(textWaves.get(i));
            textWaves.get(i).setStyle("-fx-font: 25px Serif;");
        }
        
        //Text Fields
        textField.setAlignment(Pos.CENTER);
        textField.setPrefSize(200, 250);
        textField.setSpacing(23);
        for (int i = 0; i < textFieldWaves.size(); i++) {
            textField.getChildren().add(textFieldWaves.get(i));
            textField.setSpacing(23);
        }
        
        //Option Text
        optionText.setStyle("-fx-font: 60px Serif;");
        
        
         //wordsAndFields 
        wordsAndFields.setPrefSize(450, 250);
        wordsAndFields.getChildren().addAll(textPane, textField);

        //OptionPane
        wavesOptionPane.setMinSize(400, 350);
        wavesOptionPane.setMaxSize(400, 350);
        wavesOptionPane.setAlignment(Pos.TOP_CENTER);
        wavesOptionPane.getChildren().addAll(optionText, wordsAndFields);
        
    }
        
}
