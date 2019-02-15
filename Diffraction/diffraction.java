/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Diffraction;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import static main.PhysicsSimulations.animationP;
import static main.PhysicsSimulations.controls;
import static main.PhysicsSimulations.graph;
import static main.PhysicsSimulations.options;

public class diffraction {

    //Buttons
    public static ArrayList<Button> diffractionControl = new ArrayList();
    Button btnStart = new Button("Start");
    Button btnStop = new Button("Stop");
    Button btnReset = new Button("Reset");
    Button btnClose = new Button("Close");
    Button btnMenu = new Button("Menu");
    Button btnHelp = new Button("Help");

    //Texts
    public static ArrayList<Text> textDiffraction = new ArrayList();
    Text playerMediumText = new Text("Player's Medium:");
    Text targetMediumText = new Text("target's Medium:");
    Text angleText = new Text("Angle(°):");

    //TextField
    public static ArrayList<TextField> textFieldDiffraction = new ArrayList();
    TextField angleTf = new TextField();

    //ComboBox
    public static ArrayList<ComboBox> comboBoxDiffraction = new ArrayList();
    ComboBox playerBox = new ComboBox();
    ComboBox targetBox = new ComboBox();

//LineChart<Double,Double> lineChart;
    private double lastFrameTime = 0.0;
    private double xc = 20;
    private double yc = 680;
    private double yc2 = 360;
    //private double angle2; 
    private double n1 = 1.22;
    private double n2 = 1;
    private double critical;
    ArrayList<Line> lineArray = new ArrayList();
    private AnimationTimer diffractionAnimation;
    private Line line;
    private Line l;

    //Graph
    public static NumberAxis xAxis = new NumberAxis();
    public static NumberAxis yAxis = new NumberAxis();
    public static final LineChart<Number, Number> diffractionChart
            = new LineChart<Number, Number>(xAxis, yAxis);
    XYChart.Series position = new XYChart.Series();

    public diffraction() {

        btnStop.setDisable(true);
        xAxis.setLabel("x Position");
        yAxis.setLabel("y position");
        diffractionChart.getData().add(position);
        diffractionChart.setTitle("Position");

        //Control Buttons
        diffractionControl.add(btnStart);
        diffractionControl.add(btnStop);
        diffractionControl.add(btnReset);
        diffractionControl.add(btnClose);
        diffractionControl.add(btnMenu);
        diffractionControl.add(btnHelp);
        //textField
        textFieldDiffraction.add(angleTf);
        //ComboBox 
        comboBoxDiffraction.add(playerBox);
        comboBoxDiffraction.add(targetBox);
        //Texts
        textDiffraction.add(playerMediumText);
        textDiffraction.add(targetMediumText);
        textDiffraction.add(angleText);

        ObservableList<String> playerOptions
                = FXCollections.observableArrayList(
                        "Water",
                        "Ice",
                        "Air"
                );

        ObservableList<String> targetOptions
                = FXCollections.observableArrayList(
                        "Air"
                );

        playerBox.setItems(playerOptions);
        targetBox.setItems(targetOptions);

        btnStart.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) { 
                btnStop.setDisable(false);
                try {
                    animate();
//                    diffractionAnimation.start();
                } catch (Exception e) {
                    reset();
                }
            }
        });

        btnStop.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                diffractionAnimation.stop();
                btnStart.setDisable(false);
                btnReset.setDisable(false);
                btnStop.setDisable(true);
            }
        });

        btnReset.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                diffractionAnimation.stop();
                for (int y = 0; y < lineArray.size(); y++) {

                    animationP.getChildren().remove(lineArray.get(y));
                    //lineArray.remove(y);
                }
                xc = 20;
                yc = 680;
                yc2 = 360;
                critical = 0;
                playerBox.setValue(null);
                targetBox.setValue(null);
                angleTf.clear();
                btnStart.setDisable(false);
                btnStop.setDisable(true);
                btnReset.setDisable(true);
                position.getData().clear();

            }
        });

        btnClose.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                System.exit(0);
            }
        });

        btnMenu.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                animationP.getChildren().clear();
                controls.getChildren().clear();
                options.getChildren().clear();
                graph.getChildren().clear();
                options.addingInitialButtons();

            }
        });

        btnHelp.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Help");
                alert.setHeaderText("Coulomb's Law: Help");
                alert.setContentText("Both formulas are joined in the same simulation, which is a small game "
                        + "where the players can select different attacks in order to beat the enemy player. "
                        + "For optics, the user will first have to select the laser attack, which will simulate"
                        + " a laser ray going in the desired position. Then, he will have to select in which "
                        + "medium the player’s character and the enemy will be in (n1). Finally, he will have "
                        + "to select at what angle his laser ray will start with and then press start to start "
                        + "the simulation. If it hits the wall, the ray is going to be reflected, but if it "
                        + "goes through the second medium, it will be refracted following the formula. If it "
                        + "successfully hits the enemy, it will lose a third of its health. Then, it will be the"
                        + " enemy’s turn, which will have random values in the same mediums and will follow the "
                        + "same principles. The simulation will then stop and the user will be able to chose his"
                        + " attack and the process will start over again until one of the two characters will "
                        + "have no health anymore. ");
                alert.showAndWait();
            }
        });
    }

    public void animate() {

        //Checks if boxes are not selected 
        if (playerBox.getValue() != null
                && !playerBox.getValue().toString().isEmpty() && targetBox.getValue() != null
                && !targetBox.getValue().toString().isEmpty() && Double.parseDouble(angleTf.getText()) >= 0 && Double.parseDouble(angleTf.getText()) <= 90) {

            btnStart.setDisable(true);
            btnStop.setDisable(false);
            btnReset.setDisable(true);
            if (playerBox.getSelectionModel().getSelectedItem().toString() == "Water") {
                n1 = 1.22;
                n2 = 1;
                critical = 50;
            }
            if (playerBox.getValue().toString() == "Ice") {
                n1 = 1.31;
                n2 = 1;
                critical = 48;
            }

            if (playerBox.getValue().toString() == "Air") {
                n1 = 1;
                n2 = 1;

            }

            //lastFrameTime = 0.0f;
            //long initialTime = System.nanoTime();
            double angle = Math.PI - (Double.parseDouble(angleTf.getText()) * Math.PI / 180);
            System.out.println("angle: " + angle);
            line = new Line();
            lineArray.add(line);
            line.setStartX(20);
            line.setStartY(680);
            line.setEndX(20);
            line.setEndY(680);
            line.setStrokeWidth(5);
            animationP.getChildren().add(line);

            double xf = -320 / Math.tan(angle) + 20;
            double dist = Math.sqrt(Math.pow((xf - 20), 2) + 102400);
            double unitX = (xf - 20) / dist;
            double unitY = -320 / dist;
            System.out.println("xf: " + xf + "\ndist: " + dist + "\nunitX: " + unitX + "\nunitY: " + unitY);
            l = new Line();
            lineArray.add(l);
            l.setStartX(xf);
            l.setStartY(360);
            l.setEndX(xf);
            l.setEndY(360);

            //l.setStrokeWidth(5); 
            animationP.getChildren().add(l);

            diffractionAnimation = new AnimationTimer() {
                double x = 20;
                double y = 680;
                double test = 0;

                @Override
                public void handle(long now) {

//                //Checks if boxes are not selected
//                if (playerBox.getValue() != null && 
//                    !playerBox.getValue().toString().isEmpty()&&targetBox.getValue() != null && 
//                            !targetBox.getValue().toString().isEmpty()){
//                }
//                else {
//                    //notification.setText("You have not selected a recipient!"); 
//                } 
                    if (yc > 360 && yc < 720 || playerBox.getValue().toString() == "Air") {
                        xc = xc + unitX;
                        yc = yc + unitY;
                        line.setEndX(xc);
                        line.setEndY(yc);
                        position.getData().add(new XYChart.Data(xc, 680 - yc));
                    }
                    if (yc <= 360 && playerBox.getValue().toString() != "Air") {
                        if (xc < 900 && yc2 > 0 && yc2 < 720) {

                            l.setStrokeWidth(5);
                            if (angle < Math.PI - critical * Math.PI / 180) {
                                double angle2 = Math.asin(((n1 * Math.sin(Math.PI / 2 - angle))) / n2);
                                double y2f = Math.tan(angle2) * (900 - xf) + 360;
                                double dist2 = Math.sqrt(Math.pow((900 - xf), 2) + Math.pow((y2f - 360), 2));
                                double unitX2 = (900 - xf) / dist;
                                double unitY2 = (y2f - 360) / dist;
                                xc = xc + unitX2;
                                yc = yc + unitY2;
                                l.setEndX(xc);
                                l.setEndY(yc);
                            }
                            if (angle == Math.PI - critical * Math.PI / 180) {
                                xc++;
                                l.setEndX(xc);
                                l.setEndY(yc);
                            }
                            if (angle > Math.PI - critical * Math.PI / 180) {
                                double angle2 = 2 * angle + Math.PI / 2;
                                double y2f = Math.tan(angle2) * (900 - xf) + 360;
                                double dist2 = Math.sqrt(Math.pow((900 - xf), 2) + Math.pow((y2f - 360), 2));
                                double unitX2 = (900 - xf) / dist;
                                double unitY2 = (y2f - 360) / dist;
                                xc = xc + unitX2;
                                yc2 = yc2 + unitY2;
                                l.setEndX(xc);
                                l.setEndY(yc2);
                            }
                        }
                        position.getData().add(new XYChart.Data(xc, 680 - yc2));

                    }

                }
            }; diffractionAnimation.start();

        } else {
            //notification.setText("You have not selected a recipient!"); 
        }
    }

    public void reset() {
        //diffractionAnimation.stop();
        position.getData().clear();
        btnStop.setDisable(true);
        for (int y = 0; y < lineArray.size(); y++) {

            animationP.getChildren().remove(lineArray.get(y));
            //lineArray.remove(y);
        }
        xc = 20;
        yc = 680;
        yc2 = 360;
        critical = 0;
        playerBox.setValue(null);
        targetBox.setValue(null);
        angleTf.clear();
        btnStart.setDisable(false);
        btnStop.setDisable(true);
        btnReset.setDisable(true);
    }
}
