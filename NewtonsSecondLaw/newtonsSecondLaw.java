/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NewtonsSecondLaw;

import assets.assetManager;
import static assets.assetManager.trackBackground;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import static main.PhysicsSimulations.animationP;
import static main.PhysicsSimulations.controls;
import static main.PhysicsSimulations.graph;
import static main.PhysicsSimulations.options;
import templates.Vector2D;

public class newtonsSecondLaw {

    //Buttons
    public static ArrayList<Button> buttonsControlSecondlaw = new ArrayList();
    Button btnStart = new Button("Start");
    Button btnStop = new Button("Stop");
    Button btnReset = new Button("Reset");
    Button btnClose = new Button("Close");
    Button btnMenu = new Button("Menu");
    Button btnHelp = new Button("Help");
    Button btnAdd = new Button("Add");

    //Texts
    public static ArrayList<Text> textsSecondlaw = new ArrayList();
    Text forceText = new Text("Force(N):");
    Text MassText = new Text("Mass(Kg):");

    //Text Fields
    public static ArrayList<TextField> textFieldsSecondlaw = new ArrayList();
    TextField forceTf = new TextField();
    TextField massTf = new TextField();

    //AnimationTimer 
    public AnimationTimer secondLawAnimation;
    private double lastFrameTime = 0.0;
    private double count = 0.0;
    private int carCount = 0;
    private long initialTime;

    //Car Objects
    private carObject[] carList = new carObject[2];
    private Vector2D cPosition;
    private Vector2D cVelocity;
    private Vector2D cAcceleration;

    //Graphs
    public static ArrayList<LineChart<Number, Number>> secondLawGraphs = new ArrayList();

    public newtonsSecondLaw() {

        //Graphs
        NumberAxis xAxis1 = new NumberAxis();
        xAxis1.setLabel("Time(s)");
        NumberAxis yAxis1 = new NumberAxis();
        yAxis1.setLabel("Position(m)");

        NumberAxis xAxis2 = new NumberAxis();
        xAxis2.setLabel("Time(s)");
        NumberAxis yAxis2 = new NumberAxis();
        yAxis2.setLabel("Velocity(m/s)");

        NumberAxis xAxis3 = new NumberAxis();
        xAxis3.setLabel("Time(s)");
        NumberAxis yAxis3 = new NumberAxis();
        yAxis3.setLabel("Acceleration(m/s^2)");

        LineChart<Number, Number> positionLineChart = new LineChart<Number, Number>(xAxis1, yAxis1);
        LineChart<Number, Number> velocityLineChart = new LineChart<Number, Number>(xAxis2, yAxis2);
        LineChart<Number, Number> accelerationLineChart = new LineChart<Number, Number>(xAxis3, yAxis3);
        secondLawGraphs.add(positionLineChart);
        secondLawGraphs.add(velocityLineChart);
        secondLawGraphs.add(accelerationLineChart);

        final XYChart.Series xPositionData1 = new XYChart.Series();
        final XYChart.Series xVelocityData1 = new XYChart.Series();
        final XYChart.Series xAccelerationData1 = new XYChart.Series();

        final XYChart.Series xPositionData2 = new XYChart.Series();
        final XYChart.Series xVelocityData2 = new XYChart.Series();
        final XYChart.Series xAccelerationData2 = new XYChart.Series();

        positionLineChart.getData().add(xPositionData1);
        velocityLineChart.getData().add(xVelocityData1);
        accelerationLineChart.getData().add(xAccelerationData1);

        positionLineChart.getData().add(xPositionData2);
        velocityLineChart.getData().add(xVelocityData2);
        accelerationLineChart.getData().add(xAccelerationData2);

        btnStart.setDisable(true);
        btnStop.setDisable(true);
        btnReset.setDisable(true);

        secondLawAnimation = new AnimationTimer() {
            @Override

            public void handle(long now) {
                double currentTime = (now - initialTime) / 1000000000.0;
                double frameDeltaTime = currentTime - lastFrameTime;
                lastFrameTime = currentTime;
                count += frameDeltaTime;

                /**
                 * Updates location
                 */
                for (int i = 0; i < carList.length; i++) {

                    if (carList[i].getRectangle().getX() < 810) {
                        carList[i].updateRectangle(Math.abs(frameDeltaTime));

                        /**
                         * Updates graph
                         */
                        if (900 > carList[i].getRectangle().getX()) {
                            if (i == 0) {
                                xPositionData1.getData().add(new XYChart.Data(count, carList[i].getRectangle().getX()));
                                xVelocityData1.getData().add(new XYChart.Data(count, carList[i].getVelocity().getX()));
                                xAccelerationData1.getData().add(new XYChart.Data(count, carList[i].getAccelerationX()));
                            } else {
                                xPositionData2.getData().add(new XYChart.Data(count, carList[i].getRectangle().getX()));
                                xVelocityData2.getData().add(new XYChart.Data(count, carList[i].getVelocity().getX()));
                                xAccelerationData2.getData().add(new XYChart.Data(count, carList[i].getAccelerationX()));
                            }
                        }
                    } else {
                        animationP.getChildren().remove(carList[i].getRectangle());
                    }
                }
            }
        };

        //Adding Texts
        textsSecondlaw.add(forceText);
        textsSecondlaw.add(MassText);

        //Adding TextFields
        textFieldsSecondlaw.add(forceTf);
        textFieldsSecondlaw.add(massTf);

        //Adding Buttons
        buttonsControlSecondlaw.add(btnStart);
        buttonsControlSecondlaw.add(btnStop);
        buttonsControlSecondlaw.add(btnReset);
        buttonsControlSecondlaw.add(btnClose);
        buttonsControlSecondlaw.add(btnMenu);
        buttonsControlSecondlaw.add(btnHelp);
        buttonsControlSecondlaw.add(btnAdd);

        btnStart.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                lastFrameTime = 0.0;
                xPositionData1.getData().clear();
                xVelocityData1.getData().clear();
                xAccelerationData1.getData().clear();
                xPositionData2.getData().clear();
                xVelocityData2.getData().clear();
                xAccelerationData2.getData().clear();

                initialTime = System.nanoTime();
                secondLawAnimation.start();
                btnStart.setDisable(true);
                btnStop.setDisable(false);
            }
        });

        btnStop.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                secondLawAnimation.stop();
                btnStart.setDisable(false);
                btnReset.setDisable(false);
                btnStop.setDisable(true);
            }
        });

        btnAdd.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                try{
                if (carCount < 2) {
                    cAcceleration = returnCarAcceleration();
                    if(cAcceleration.getX() > 0){
                    carObject car = new carObject(cAcceleration, 80, 50);
                    carList[carCount] = car;
                    carCount++;
                    car.getRectangle().setY(animationP.getHeight() - (car.getRectangle().getHeight()) * (Math.pow(carCount, 2)) - 30);
                    car.getRectangle().setX(4);
                    car.getRectangle().setFill(assetManager.getCarPattern());
                    animationP.getChildren().add(car.getRectangle());

                    if (carCount == 2) {
                        btnAdd.setDisable(true);
                        btnStart.setDisable(false);
                    }
                    }
                }
                }
                catch(Exception e){
                }
                    
            }
        });

        btnClose.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                System.exit(0);
            }
        });

        btnReset.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                count = 0;
                carList[0] = null;
                carList[1] = null;
                carCount = 0;
                btnAdd.setDisable(false);
                btnStart.setDisable(true);
                btnStop.setDisable(true);
                btnReset.setDisable(true);
                animationP.getChildren().clear();
                ImageView background = new ImageView(trackBackground);
                background.setFitHeight(720);
                background.setFitWidth(900);
                animationP.getChildren().addAll(background);
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
                alert.setContentText("This mechanics simulation will show 2 cars with different velocity,"
                        + " acceleration and time, and the program will find out at what distance will "
                        + "these 2 cars be at the same position provided that they start at the same place."
                        + " The user will be asked to input an acceleration in m/s^2, an initial velocity "
                        + "in m/s, and the time at which the car will start moving in seconds (s). "
                        + "The program will use all 3 of these entries in the formula: D=Vi * T + 1/2A*"
                        + "(T+T2)^2 to calculate the displacement of the car at any given moment. ");
                alert.showAndWait();
            }
        });

    }

    public Vector2D returnCarAcceleration() {
        try {
            if(Double.parseDouble(forceTf.getText()) > 0 && Double.parseDouble(massTf.getText()) > 0){
            return new Vector2D(Double.parseDouble(forceTf.getText())
                    / Double.parseDouble(massTf.getText()), 0);
            }
        } catch (Exception e) {
            System.out.println("No value is entered in the text fields or the entered values are not numbers.");
            return null;

        }
        return null;
    }
}
