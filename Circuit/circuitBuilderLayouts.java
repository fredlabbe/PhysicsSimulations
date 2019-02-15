package Circuit;

import static Circuit.circuitBuilder.VoltageToIntensity;
import static Circuit.circuitBuilder.VoltageToResistance;
import static assets.assetManager.batteryImage;
import static assets.assetManager.openSwitchImage;
import static assets.assetManager.resistanceImage;
import static Circuit.circuitBuilder.constantsCheck;
import static Circuit.circuitBuilder.ohmsAnimationPane;
import static Circuit.circuitBuilder.sliderTexts;
import static Circuit.circuitBuilder.sliders;
import static Circuit.circuitBuilder.speed;
import static Circuit.circuitBuilder.switchImage;
import static Circuit.circuitBuilder.xyz;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class circuitBuilderLayouts {
    
    public static VBox ohmsOptionPane = new VBox();
    HBox namesAndElements = new HBox();
    VBox elementNames = new VBox();
    VBox circuitElements = new VBox();
    Text optionText = new Text("       Options");
    public static BorderPane ohmsGraphPane = new BorderPane();
    
    
    
    public static AnimationTimer ohmsAnimation = new AnimationTimer() {
       @Override
            public void handle(long now) {

                for (int i = 0; i < xyz.length; i++) {
                    
                    //going right
                    if (xyz[i].getCenterX() >= 90 && xyz[i].getCenterY() <= 83 && xyz[i].getCenterX() < 810) {
                        xyz[i].setCenterX(xyz[i].getCenterX() + speed);
                    }

                    //going down
                    if (xyz[i].getCenterX() >= 810 && xyz[i].getCenterY() >= 83 && xyz[i].getCenterY() < 635) {
                        xyz[i].setCenterY(xyz[i].getCenterY() + speed);
                    }

                    //going left
                    if (xyz[i].getCenterX() > 90 && xyz[i].getCenterY() >= 635) {
                        xyz[i].setCenterX(xyz[i].getCenterX() - speed);
                    }

                    //going up
                    if (xyz[i].getCenterX() <= 90 && xyz[i].getCenterY() > 83) {

                        xyz[i].setCenterY(xyz[i].getCenterY() - speed);
                    }

                    if (xyz[i].getCenterX() >= 810) {
                        xyz[i].setCenterX(810);
                    }
                    if (xyz[i].getCenterY() >= 810) {
                        xyz[i].setCenterY(810);
                    }

                    if (xyz[i].getCenterX() <= 90) {
                        xyz[i].setCenterX(90);
                    }
                    if (xyz[i].getCenterY() <= 83) {
                        xyz[i].setCenterY(83);
                    }
                }
            }

        };

    public circuitBuilderLayouts() {

        //Option Text
        optionText.setStyle("-fx-font: 60px Serif;");
        buildCircuit();
        ohmsOptionPane.getChildren().add(optionText);
        for(int i = 0; i < sliders.size(); i++){
            elementNames.getChildren().add(sliderTexts.get(i));
            elementNames.getChildren().add(sliders.get(i));
            elementNames.getChildren().add(constantsCheck.get(i));
            elementNames.setAlignment(Pos.BASELINE_CENTER);
            elementNames.setSpacing(5);
        }
        ohmsOptionPane.getChildren().add(elementNames);
        ohmsOptionPane.setSpacing(8);
        
       
        
        TabPane tabPane = new TabPane();

        Tab tabA = new Tab();
        tabA.setText("Voltage To Resistance");
        tabPane.getTabs().add(tabA);
        tabA.setClosable(false);
        tabA.setContent(VoltageToResistance);
        
        
        Tab tabC = new Tab();
        tabC.setText("Voltage To Intensity");
        tabPane.getTabs().add(tabC);
        tabC.setClosable(false);
        tabC.setContent(VoltageToIntensity);
        
         ohmsGraphPane.setCenter(tabPane);
    }

    
    public void buildCircuit() {

        Rectangle topOut = new Rectangle(797, 12);
        Rectangle bottomOut = new Rectangle(797, 12);
        Rectangle rightOut = new Rectangle(12, 622);
        Rectangle leftOut = new Rectangle(12, 622);

        Rectangle topIn = new Rectangle(667, 12);
        Rectangle bottomIn = new Rectangle(667, 12);
        Rectangle rightIn = new Rectangle(12, 507);
        Rectangle leftIn = new Rectangle(12, 507);

        ImageView battery = new ImageView();
        battery.setImage(batteryImage);
        battery.setFitHeight(140);
        battery.setFitWidth(250);

        battery.setX(330);
        battery.setY(14);
        battery.toFront();

        ImageView resistance = new ImageView();
        resistance.setImage(resistanceImage);
        resistance.setFitHeight(140);
        resistance.setFitWidth(250);

        resistance.setX(330);
        resistance.setY(560);
        resistance.toFront();

        switchImage = new ImageView();
        switchImage.setImage(openSwitchImage);
        switchImage.setFitHeight(250);
        switchImage.setFitWidth(100);

        switchImage.setX(35);
        switchImage.setY(200);
        switchImage.toFront();

        ohmsAnimationPane.setPrefSize(900, 720);

        bottomOut.setLayoutX(50);
        bottomOut.setLayoutY(665);

        topOut.setLayoutX(50);
        topOut.setLayoutY(50);

        rightOut.setLayoutX(50);
        rightOut.setLayoutY(50);

        leftOut.setLayoutX(835);
        leftOut.setLayoutY(55);

        topIn.setLayoutX(112);
        topIn.setLayoutY(105);

        bottomIn.setLayoutX(112);
        bottomIn.setLayoutY(600);

        rightIn.setLayoutX(770);
        rightIn.setLayoutY(105);

        leftIn.setLayoutX(112);
        leftIn.setLayoutY(105);

        ohmsAnimationPane.getChildren().add(topOut);
        ohmsAnimationPane.getChildren().add(bottomOut);
        ohmsAnimationPane.getChildren().add(rightOut);
        ohmsAnimationPane.getChildren().add(leftOut);

        ohmsAnimationPane.getChildren().add(topIn);
        ohmsAnimationPane.getChildren().add(bottomIn);
        ohmsAnimationPane.getChildren().add(rightIn);
        ohmsAnimationPane.getChildren().add(leftIn);
        ohmsAnimationPane.getChildren().add(battery);
        ohmsAnimationPane.getChildren().add(resistance);
        ohmsAnimationPane.getChildren().add(switchImage);

    }
    
}