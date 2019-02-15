/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package templates;

import static assets.assetManager.mainPage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import static main.PhysicsSimulations.animationP;
import main.templateController;


public class optionPane extends VBox {

    public double sizeX = 400;
    public double sizeY = 350;
    
    public optionPane(){
        
        super();
        super.setPrefSize(sizeX, sizeY);
        this.setStyle("-fx-border-color: black;");
        this.setStyle("-fx-background-color: rgb(120, 120, 120);");
        setLayoutX(900);
        addingInitialButtons();
         
   }

    public void addingInitialButtons() {
        this.setAlignment(Pos.TOP_CENTER);
        Text courseText = new Text("Course");
        courseText.setStyle("-fx-font: 65px Serif;");
        courseText.setUnderline(true);
        
        //Adding The Buttons
        Button mechanics = new Button("Mechanics");
        Button waves = new Button("Waves");
        Button eM = new Button("E&M");
        
        mechanics.setAlignment(Pos.BASELINE_CENTER);
        this.getChildren().addAll(courseText,mechanics,waves,eM);
        this.setSpacing(30);
        //Adding the Action Events
        
        mechanics.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                templateController.addMechanics();
            }
        });
        
        waves.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                templateController.addWaves();
            }
        });
        
        eM.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                templateController.addEM();
            }
        });
        ImageView background = new ImageView(mainPage);
        background.setFitHeight(720);
        background.setFitWidth(900);
        animationP.getChildren().addAll(background);
        
    }
}
