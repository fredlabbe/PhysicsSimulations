package templates;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;


public class graphPane extends VBox{

    public double sizeX = 400;
    public double sizeY = 400;
    
    public graphPane(){
        super();
        super.setPrefSize(sizeX, sizeY);
        this.setStyle("-fx-background-color: rgb(120, 120, 120);");
        this.setStyle("-fx-border-color: black;");
        setLayoutX(900);
        setLayoutY(350);
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(80);
    }
    
    //initial Options 
    
    public void addInitial(){
        
    }
}
