package main;

import Circuit.circuitBuilder;
import Circuit.circuitBuilderLayouts;
import Diffraction.diffraction;
import Diffraction.diffractionLayout;
import NewtonsSecondLaw.newtonsSecondLaw;
import NewtonsSecondLaw.newtonsSecondLawLayout;
//import static assets.assetManager.gifBackground;
import static assets.assetManager.mainPage;
import coulombsLaw.CoulombLaw;
import coulombsLaw.coulombLayouts;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import projectileMotion.projectileMotion;
import projectileMotion.projectileMotionLayout;
import templates.animationPane;
import templates.controlPane;
import templates.graphPane;
import templates.optionPane;
import waves.waves;
import waves.wavesLayout;


public class PhysicsSimulations extends Application {

    public static graphPane graph;
    public static animationPane animationP;
    public static controlPane controls;
    public static optionPane options;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        new CoulombLaw();
        new coulombLayouts();
        new circuitBuilder();
        new circuitBuilderLayouts();
        new newtonsSecondLaw();
        new newtonsSecondLawLayout();
        new projectileMotion();
        new projectileMotionLayout();
        new diffraction();
        new diffractionLayout();
        new waves();
        new wavesLayout();

        //Main Panel 
        Pane root = new Pane();
        Scene scene = new Scene(root, 1290, 740);

        //Animation Pane
        animationP = new animationPane();
        ImageView background = new ImageView(mainPage);
        background.setFitHeight(720);
        background.setFitWidth(900);
        animationP.getChildren().addAll(background);

        //Bottom Control Panel
        controls = new controlPane();

        //Top Option Menu
        options = new optionPane();

        //Bottom Option Menu
        graph = new graphPane();

        //Adding All
        root.getChildren().addAll(animationP, controls, options, graph);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
