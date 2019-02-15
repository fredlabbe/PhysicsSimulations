/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package templates;

import javafx.scene.layout.HBox;


public class controlPane extends HBox{

    double sizeX = 900;
    double sizeY = 30;
    
    public controlPane(){
        super();
        super.setPrefSize(sizeX, sizeY);
        super.setSpacing(4);
        this.setStyle("-fx-border-color: black;");
        this.setStyle("-fx-background-color: rgb(90, 90, 90);");
        setLayoutY(720);
      
    }
}

