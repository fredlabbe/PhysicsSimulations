/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NewtonsSecondLaw;

import static NewtonsSecondLaw.newtonsSecondLaw.buttonsControlSecondlaw;
import static NewtonsSecondLaw.newtonsSecondLaw.secondLawGraphs;
import static NewtonsSecondLaw.newtonsSecondLaw.textFieldsSecondlaw;
import static NewtonsSecondLaw.newtonsSecondLaw.textsSecondlaw;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class newtonsSecondLawLayout {

    public static VBox optionPaneNewtons = new VBox();
    public static BorderPane secondLawGraphPane = new BorderPane();
    HBox wordsAndFields = new HBox();
    VBox textPane = new VBox();
    VBox textField = new VBox();
    Text optionText = new Text("Options");

    public newtonsSecondLawLayout() {
        
        secondLawGraphPane.setPrefSize(400, 400);
        TabPane tabPane = new TabPane();

        //Tabs 
        Tab tabA = new Tab();
        tabA.setText("Position");
        tabPane.getTabs().add(tabA);
        tabA.setClosable(false);
        tabA.setContent(secondLawGraphs.get(0));

        Tab tabB = new Tab();
        tabB.setText("Velocity");
        tabPane.getTabs().add(tabB);
        tabB.setClosable(false);
        tabB.setContent(secondLawGraphs.get(1));

        Tab tabC = new Tab();
        tabC.setText("Acceleration");
        tabPane.getTabs().add(tabC);
        tabC.setClosable(false);
        tabC.setContent(secondLawGraphs.get(2));

        secondLawGraphPane.setCenter(tabPane);

        //Option Text
        optionText.setStyle("-fx-font: 60px Serif;");

        //Set Button
        buttonsControlSecondlaw.get(6).setAlignment(Pos.BASELINE_CENTER);

        //Text Pane
        textPane.setAlignment(Pos.CENTER);
        textPane.setPrefSize(150, 250);
        textPane.setSpacing(20);
        for (int i = 0; i < textsSecondlaw.size(); i++) {
            textPane.getChildren().add(textsSecondlaw.get(i));
            textsSecondlaw.get(i).setStyle("-fx-font: 25px Serif;");
        }
        //Text Fields
        textField.setAlignment(Pos.CENTER);
        textField.setPrefSize(200, 250);
        textField.setSpacing(23);
        for (int i = 0; i < textFieldsSecondlaw.size(); i++) {
            textField.getChildren().add(textFieldsSecondlaw.get(i));
            textField.setSpacing(23);
        }

        //wordsAndFields 
        wordsAndFields.setPrefSize(450, 250);
        wordsAndFields.getChildren().addAll(textPane, textField);

        //OptionPane
        optionPaneNewtons.setMinSize(400, 350);
        optionPaneNewtons.setMaxSize(400, 350);
        optionPaneNewtons.setAlignment(Pos.TOP_CENTER);
        optionPaneNewtons.getChildren().addAll(optionText, wordsAndFields, buttonsControlSecondlaw.get(6));

    }
}
