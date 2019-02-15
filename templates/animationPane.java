package templates;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;


public class animationPane extends Pane{
    
    AnimationTimer animation;
    double sizeX = 900;
    double sizeY = 720;
    
    public animationPane(){
        super.setPrefSize(sizeX, sizeY);
        this.setStyle("-fx-background-color: rgb(140,140,140);");
        this.setStyle("-fx-border-color: black;");
    }
}
