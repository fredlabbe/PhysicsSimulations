/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectileMotion;

import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import static projectileMotion.projectileMotion.btnFire;
import static projectileMotion.projectileMotion.lineChartPosition;
import static projectileMotion.projectileMotion.lineChartVelocity;
import static projectileMotion.projectileMotion.textFieldProjectile;
import static projectileMotion.projectileMotion.textProjectile;


public class projectileMotionLayout {

    public static VBox projectileMotionOptionPane = new VBox();
    public static BorderPane projectileMotionGraphPane = new BorderPane();
    HBox wordsAndFields = new HBox();
    VBox textPane = new VBox();
    VBox textField = new VBox();
    Text optionText = new Text("Options");

    public projectileMotionLayout() {

        TabPane tabPane = new TabPane();

        Tab tabA = new Tab();
        tabA.setText("Vertical Position");
        tabPane.getTabs().add(tabA);
        tabA.setClosable(false);
        tabA.setContent(lineChartPosition);
        
        Tab tabB = new Tab();
        tabB.setText("Vertical Velocity");
        tabPane.getTabs().add(tabB);
        tabB.setClosable(false);
        tabB.setContent(lineChartVelocity);
        
        projectileMotionGraphPane.setCenter(tabPane);
        //Text Pane
        textPane.setAlignment(Pos.CENTER);
        textPane.setPrefSize(150, 250);
        textPane.setSpacing(20);
        for (int i = 0; i < textProjectile.size(); i++) {
            textPane.getChildren().add(textProjectile.get(i));
            textProjectile.get(i).setStyle("-fx-font: 25px Serif;");
        }

        //Text Fields
        textField.setAlignment(Pos.CENTER);
        textField.setPrefSize(200, 250);
        textField.setSpacing(23);
        for (int i = 0; i < textFieldProjectile.size(); i++) {
            textField.getChildren().add(textFieldProjectile.get(i));
            textField.setSpacing(23);
        }

        //Option Text
        optionText.setStyle("-fx-font: 60px Serif;");

        //wordsAndFields 
        wordsAndFields.setPrefSize(450, 250);
        wordsAndFields.getChildren().addAll(textPane, textField);

        //OptionPane
        projectileMotionOptionPane.setMinSize(400, 350);
        projectileMotionOptionPane.setMaxSize(400, 350);
        projectileMotionOptionPane.setAlignment(Pos.TOP_CENTER);
        projectileMotionOptionPane.getChildren().addAll(optionText, wordsAndFields, btnFire);

    }

}
