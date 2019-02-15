package main;

import static Circuit.circuitBuilder.buttonsControlEM;
import static Circuit.circuitBuilder.ohmsAnimationPane;
import Circuit.circuitBuilderLayouts;
import static Circuit.circuitBuilderLayouts.ohmsGraphPane;
import static Diffraction.diffraction.diffractionControl;
import static Diffraction.diffractionLayout.diffractionGraphPane;
import static Diffraction.diffractionLayout.diffractionOptionPane;
import static NewtonsSecondLaw.newtonsSecondLaw.buttonsControlSecondlaw;
import NewtonsSecondLaw.newtonsSecondLawLayout;
import static NewtonsSecondLaw.newtonsSecondLawLayout.secondLawGraphPane;
import static assets.assetManager.coilImage;
import static assets.assetManager.coulombBackground;
import static assets.assetManager.projectileBackgroundImage;
import static assets.assetManager.tankBackground;
import static assets.assetManager.trackBackground;
import static coulombsLaw.CoulombLaw.buttonsControlCoulomb;
import static coulombsLaw.CoulombLaw.coulombLineChart;
import coulombsLaw.coulombLayouts;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import static main.PhysicsSimulations.animationP;
import static main.PhysicsSimulations.controls;
import static main.PhysicsSimulations.graph;
import static main.PhysicsSimulations.options;
import static projectileMotion.projectileMotion.buttonsControlProjectile;
import static projectileMotion.projectileMotionLayout.projectileMotionOptionPane;
import static waves.waves.wavesControl;
import static waves.wavesLayout.wavesOptionPane;
import static projectileMotion.projectileMotion.tankNose;
import static projectileMotion.projectileMotionLayout.projectileMotionGraphPane;
import static waves.wavesLayout.wavesGraph;


public class templateController {

    VBox optionPane = new VBox();

    public templateController() {
        optionPane.setMinSize(400, 350);
        optionPane.setMaxSize(400, 350);
    }

    public static void addMechanics() {
        removeSubjects();
        Button projectileMotion = new Button("Projectile Motion");
        projectileMotion.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                removeAll();
                for (int i = 0; i < buttonsControlProjectile.size(); i++) {
                    controls.getChildren().add(buttonsControlProjectile.get(i));
                    buttonsControlProjectile.get(i).setPrefSize(100, 30);
                    buttonsControlProjectile.get(i).setLayoutX(100 * i);
                }
                options.getChildren().clear();
                options.getChildren().add(projectileMotionOptionPane);
                graph.getChildren().add(projectileMotionGraphPane);
                ImageView background = new ImageView(projectileBackgroundImage);
                background.setFitHeight(720);
                background.setFitWidth(900);
                ImageView tank = new ImageView(tankBackground);
                tank.setFitHeight(60);
                tank.setFitWidth(90);
                tank.setY(560);
                tank.setX(30);
                tankNose.setY(505);
                tankNose.setX(50);
                tankNose.setFitHeight(120);
                tankNose.setFitWidth(100);
                animationP.getChildren().addAll(background,tank,tankNose);
    
            }
        });

        Button newtonsSecondLaw = new Button("Newton's Second Law");
        newtonsSecondLaw.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                removeAll();
                for (int i = 0; i < buttonsControlSecondlaw.size() - 1; i++) {
                    controls.getChildren().add(buttonsControlSecondlaw.get(i));
                    buttonsControlSecondlaw.get(i).setPrefSize(100, 30);
                    buttonsControlSecondlaw.get(i).setLayoutX(100 * i);
                }
                options.getChildren().clear();
                options.getChildren().add(newtonsSecondLawLayout.optionPaneNewtons);
                graph.getChildren().add(secondLawGraphPane);
                ImageView background = new ImageView(trackBackground);
                background.setFitHeight(720);
                background.setFitWidth(900);
                animationP.getChildren().addAll(background);
            }
        });
        Text subjectText = new Text("Subject");
        subjectText.setStyle("-fx-font: 60px Serif;");
        subjectText.setUnderline(true);
//        graph.setSpacing(30);graph.setAlignment(Pos.TOP_CENTER);
        graph.getChildren().addAll(subjectText, projectileMotion, newtonsSecondLaw);
    }

    public static void addWaves() {
        removeSubjects();
        Button diffraction = new Button("Difraction");
       
        diffraction.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                removeAll();
                for (int i = 0; i < diffractionControl.size(); i++) {
                    controls.getChildren().add(diffractionControl.get(i));
                    diffractionControl.get(i).setPrefSize(100, 30);
                    diffractionControl.get(i).setLayoutX(100 * i);
                }
                options.getChildren().clear();
                options.getChildren().add(diffractionOptionPane);
                Rectangle targetMedium = new Rectangle(900, 360);
                targetMedium.setY(360);
                targetMedium.setFill(Color.color(.224, .237, .248, 0.1));
                Line l = new Line(0, 360, 900, 360);
                animationP.getChildren().addAll(targetMedium, l);
                graph.getChildren().add(diffractionGraphPane);
                
            }
        });

        Button waves = new Button("Waves");

        waves.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                removeAll();
                for (int i = 0; i < wavesControl.size(); i++) {
                    controls.getChildren().add(wavesControl.get(i));
                    wavesControl.get(i).setPrefSize(100, 30);
                    wavesControl.get(i).setLayoutX(100 * i);
                }
                options.getChildren().clear();
                options.getChildren().add(wavesOptionPane);
                graph.getChildren().add(wavesGraph);    
                
                ImageView background = new ImageView(coilImage);
                background.setFitHeight(720);
                background.setFitWidth(900);
                animationP.getChildren().addAll(background);
                
                ImageView coil1 = new ImageView(coilImage);
                coil1.setFitHeight(500);
                coil1.setFitWidth(200);
                coil1.setY(230);
                coil1.setX(20);
                animationP.getChildren().addAll(coil1);
                
                ImageView coil2 = new ImageView(coilImage);
                coil2.setY(230);
                coil2.setX(650);
                coil2.setFitHeight(500);
                coil2.setFitWidth(200);
                animationP.getChildren().addAll(coil2);
            }
        });

       
        Text subjectText = new Text("Subject");
        subjectText.setStyle("-fx-font: 60px Serif;");
        subjectText.setUnderline(true);
//        graph.setSpacing(30);graph.setAlignment(Pos.TOP_CENTER);
        graph.getChildren().addAll(subjectText, diffraction, waves);
    }

    public static void addEM() {
        removeSubjects();
        // E&M PART 1
        Button Coulomb = new Button("Coulomb's Law");
        Coulomb.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                removeAll();
                for (int i = 0; i < buttonsControlCoulomb.size(); i++) {
                    controls.getChildren().add(buttonsControlCoulomb.get(i));
                    buttonsControlCoulomb.get(i).setPrefSize(100, 30);
                    buttonsControlCoulomb.get(i).setLayoutX(100 * i);
                }
                options.getChildren().clear();
                options.getChildren().add(coulombLayouts.optionPane);
                graph.getChildren().add(coulombLineChart);
                ImageView background = new ImageView(coulombBackground);
                background.setFitHeight(720);
                background.setFitWidth(900);
                animationP.getChildren().addAll(background);
            }
        });

        // E&M PART 2
        Button ohms = new Button("Ohm's Law");
        ohms.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                removeAll();
                for (int i = 0; i < buttonsControlEM.size(); i++) {
                    controls.getChildren().add(buttonsControlEM.get(i));
                    buttonsControlEM.get(i).setPrefSize(100, 30);
                    buttonsControlEM.get(i).setLayoutX(100 * i);
                }
                options.getChildren().clear();
                options.getChildren().add(circuitBuilderLayouts.ohmsOptionPane);
                animationP.getChildren().add(ohmsAnimationPane);
                graph.getChildren().add(ohmsGraphPane);
            }
        });

        Text subjectText = new Text("Subject");
        subjectText.setStyle("-fx-font: 60px Serif;");
        subjectText.setUnderline(true);
//        graph.setSpacing(30);graph.setAlignment(Pos.TOP_CENTER);
        graph.getChildren().addAll(subjectText, Coulomb, ohms);
    }

    public static void removeAll() {
        graph.getChildren().clear();
        options.getChildren().clear();
        options.addingInitialButtons();
        animationP.getChildren().clear();
    }
    public static void removeSubjects(){
        graph.getChildren().clear();
    }
}
