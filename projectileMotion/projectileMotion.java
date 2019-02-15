/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectileMotion;

import static assets.assetManager.tankNoseImage;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import static main.PhysicsSimulations.animationP;
import static main.PhysicsSimulations.controls;
import static main.PhysicsSimulations.graph;
import static main.PhysicsSimulations.options;
import templates.Vector2D;

public class projectileMotion {

    //Buttons
    public static ArrayList<Button> buttonsControlProjectile = new ArrayList();
    Button btnStart = new Button("Start");
    Button btnStop = new Button("Stop");
    Button btnReset = new Button("Reset");
    Button btnClose = new Button("Close");
    Button btnMenu = new Button("Menu");
    Button btnHelp = new Button("Help");
    public static Button btnFire = new Button("Fire!");

    //Texts
    public static ArrayList<Text> textProjectile = new ArrayList();
    Text velocityText = new Text("Velocity(m/s):");
    Text MassText = new Text("Angle(°):");

    //Text Fields
    public static ArrayList<TextField> textFieldProjectile = new ArrayList();
    TextField velocityTf = new TextField();
    TextField angleTf = new TextField();

    //Animation
    public static ArrayList<LineChart<Number, Number>> projectileMotionGraphs = new ArrayList();
    static LineChart<Number, Number> lineChartPosition;
    static LineChart<Number, Number> lineChartVelocity;
    private ArrayList<canonBall> cannonballs = new ArrayList<>();
    private int magnitude;
    private Vector2D cPosition;
    private Vector2D cVelocity;
    private Vector2D cAcceleration;
    private double lastFrameTime = 0.0;
    private double count = 0;
    public AnimationTimer projectileAnimation;
    public static ImageView tankNose = new ImageView(tankNoseImage);
    final XYChart.Series yPositionData;
    final XYChart.Series yVelocityData;

    public projectileMotion() {

        btnStop.setDisable(true);

        textProjectile.add(velocityText);
        textProjectile.add(MassText);
        textFieldProjectile.add(velocityTf);
        textFieldProjectile.add(angleTf);

        buttonsControlProjectile.add(btnStart);
        buttonsControlProjectile.add(btnStop);
        buttonsControlProjectile.add(btnReset);
        buttonsControlProjectile.add(btnClose);
        buttonsControlProjectile.add(btnMenu);
        buttonsControlProjectile.add(btnHelp);

        NumberAxis xAxis1 = new NumberAxis();
        xAxis1.setLabel("Time(s)");
        NumberAxis yAxis1 = new NumberAxis();
        yAxis1.setLabel("Position(m)");

        NumberAxis xAxis2 = new NumberAxis();
        xAxis2.setLabel("Time(s)");
        NumberAxis yAxis2 = new NumberAxis();
        yAxis2.setLabel("Position(m)");

        lineChartPosition = new LineChart<Number, Number>(xAxis1, yAxis1);
        lineChartVelocity = new LineChart<Number, Number>(xAxis2, yAxis2);
        lineChartPosition.setTitle("Position");
        lineChartVelocity.setTitle("Velocity");

        yPositionData = new XYChart.Series();
        yVelocityData = new XYChart.Series();

        lineChartPosition.getData().add(yPositionData);
        lineChartVelocity.getData().add(yVelocityData);
        
        btnFire.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                try{
                    if(Integer.parseInt(angleTf.getText()) > 0 ){
                count = 0;
                yPositionData.getData().clear();
                yVelocityData.getData().clear();
                tankNose.setRotate(-1 * Integer.parseInt(angleTf.getText()));
                animate();
                projectileAnimation.start();
                btnFire.setDisable(true);
                btnStart.setDisable(true);
                btnStop.setDisable(false);
                    }
                }
                catch(Exception e){
                    
                }
            }
        });

        btnStart.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try{
                    if(Integer.parseInt(angleTf.getText()) > 0 ){
                count = 0;
                yPositionData.getData().clear();
                yVelocityData.getData().clear();
                tankNose.setRotate(-1 * Integer.parseInt(angleTf.getText()));
                animate();
                projectileAnimation.start();
                btnFire.setDisable(true);
                btnStart.setDisable(true);
                btnStop.setDisable(false);
                    }
                }
                catch(Exception e){
                    
                }
            }
        });

        btnStop.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                animate();
                projectileAnimation.stop();
                btnFire.setDisable(false);
                btnStart.setDisable(false);
                btnStop.setDisable(true);
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
                alert.setContentText("The first simulation for mechanics will show a tank, shooting "
                        + "bullets at a target. The user will enter the angle at which the cannon "
                        + "will be oriented and the force at which the bullet will leave the cannon."
                        + " (the force will in fact be the velocity in m/s and the angle will be in degrees)."
                        + " Once the user enters valid inputs, the program will calculate the velocity in x "
                        + "(Vx), and the velocity in y (Vy) by using trigonometry (given that we have the angle"
                        + " plus the hypotenuse which are both entered by the user) and it will use these components"
                        + " in the formula Vf= Vi+a*t, to calculate the final velocity for each frame during the "
                        + "animation. Once the object hits a Y component of 0, meaning that the object hit the ground,"
                        + " the final velocity (Vf) of each frame and the time in each frame will be added and used in"
                        + " the formula D=V*T where d is the displacement, V is the velocity calculated for each frame"
                        + " and T is time (time will be the total time of all frames that make up the animation). The "
                        + "result from this last formula compares the final displacement in X with the position of the "
                        + "target and will inform the user if he hit the target or not.");
                alert.showAndWait();
            }
        });
    }

    public void animate() {

        try {

            if ((Integer.parseInt(angleTf.getText()) < 0 || Integer.parseInt(angleTf.getText()) > 90
                    || (Integer.parseInt(velocityTf.getText()) < 0) || Integer.parseInt(velocityTf.getText()) > 60)) {
            }

            magnitude = (Integer.parseInt(velocityTf.getText()));

            cVelocity = calculateVelocity(Integer.parseInt(angleTf.getText()));

            cPosition = new Vector2D(85, 550);
            cAcceleration = new Vector2D(0.0, 98);

            lastFrameTime = 0.0f;
            final long initialTime = System.nanoTime();

            canonBall ball = new canonBall(cPosition, cVelocity, cAcceleration, 7);
            animationP.getChildren().add(ball.getCircle());
            ball.getCircle().setFill(Color.BLACK);

            projectileAnimation = new AnimationTimer() {

                @Override
                public void handle(long now) {
                    double currentTime = (now - initialTime) / 1000000000.0;
                    double frameDeltaTime = currentTime - lastFrameTime;
                    lastFrameTime = currentTime;

                    count += frameDeltaTime;

                    ball.updateCircle(frameDeltaTime);

                    if (900 > ball.getCircle().getLayoutX() && 720 > ball.getCircle().getLayoutY()) {
                        yPositionData.getData().add(new XYChart.Data(count, 720 - ball.getCircle().getLayoutY()));
                        yVelocityData.getData().add(new XYChart.Data(count, -ball.getVelocity().getY()));
                    }
                    if (900 < ball.getCircle().getLayoutX() || 720 < ball.getCircle().getLayoutY()) {
                        projectileAnimation.stop();
                        animationP.getChildren().remove(ball.getCircle());
                        btnFire.setDisable(false);
                        btnStart.setDisable(false);
                        btnStop.setDisable(true);

                    }
                }
            };

        } catch (Exception e) {

        }
    }

    

    public Vector2D calculateVelocity(double angle) {
        try{

        double angleInRadians = ((angle * 2 * Math.PI) / 360);
        double xVel = magnitude * (Math.cos(angleInRadians));
        double yVel = magnitude * (Math.sin(angleInRadians));

        return new Vector2D(xVel, -yVel);
        }
        catch(Exception e){
            
        }
        return null;
    }

    public void setUPAnimation() {

    }
}
