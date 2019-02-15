/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waves;

import static assets.assetManager.coilImage;
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
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import static main.PhysicsSimulations.animationP;
import static main.PhysicsSimulations.controls;
import static main.PhysicsSimulations.graph;
import static main.PhysicsSimulations.options;


public class waves {

    //Buttons
    public static ArrayList<Button> wavesControl = new ArrayList();
    Button btnStart = new Button("Start");
    Button btnStop = new Button("Stop");
    Button btnReset = new Button("Reset");
    Button btnClose = new Button("Close");
    Button btnMenu = new Button("Menu");
    Button btnHelp = new Button("Help");

    //Texts
    public static ArrayList<Text> textWaves = new ArrayList();
    Text amplitudeText = new Text("Amplitude:");
    Text periodText = new Text("period(/s):");

    //Text Fields
    public static ArrayList<TextField> textFieldWaves = new ArrayList();
    TextField amplitudeTf = new TextField();
    TextField periodTf = new TextField();

    //Waves Animation
    public static AnimationTimer wavesAnimation;
    private ArrayList<Line> lineArray;
    private TextField periodField;
    private TextField amplitudeField;

    //Graph
    public static NumberAxis xAxis = new NumberAxis();
    public static NumberAxis yAxis = new NumberAxis();
    public static final LineChart<Number, Number> wavesChart
            = new LineChart<Number, Number>(xAxis, yAxis);
    XYChart.Series position = new XYChart.Series();
    

    public waves() {
        
        xAxis.setLabel("x Position");
        yAxis.setLabel("y position");
        wavesChart.getData().add(position);
        wavesChart.setTitle("Position");
        
        
        wavesControl.add(btnStart);
        wavesControl.add(btnStop);
        wavesControl.add(btnReset);
        wavesControl.add(btnClose);
        wavesControl.add(btnMenu);
        wavesControl.add(btnHelp);

        textWaves.add(amplitudeText);
        textWaves.add(periodText);
        textFieldWaves.add(amplitudeTf);
        textFieldWaves.add(periodTf);

        btnStart.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try{
                position.getData().clear();
                animate();
                wavesAnimation.start();
                btnStart.setDisable(true);
                btnStop.setDisable(false);
                btnReset.setDisable(true);
                }catch(Exception e){
                    
                }
            }
        });

        btnStop.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                wavesAnimation.stop();
                btnStart.setDisable(false);
                btnStop.setDisable(true);
                btnReset.setDisable(false);
            }
        });

        btnReset.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                wavesAnimation.stop();
                amplitudeTf.clear();
                periodTf.clear();
                animationP.getChildren().clear();
                
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
                
                btnStart.setDisable(false);
                btnStop.setDisable(true);
                btnReset.setDisable(true);
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
                alert.setContentText(" Your character has to attack the enemy, but this time with an attack "
                        + "that follows a simple wave pattern. First, the user will have to enter the amplitude"
                        + " (A) at which the attack’s wave will have. Then, he will have to enter a value for "
                        + "the period (T). The formula is y = A sin(wt + φ), where w is (2π/T). The time (t) "
                        + "is the time since the start of the simulation that will be recuperated in the code"
                        + ". The phase constant (φ) will be determined in the code also since it does not "
                        + "affect the simulation directly because the focus is on the displacement. All this "
                        + "is used to calculate the displacement in y of the wave according to the time. ");
                alert.showAndWait();
            }
        });
    }

    public void animate() {
        try{
        double amplitude = Double.parseDouble(amplitudeTf.getText());
        double period = Double.parseDouble(periodTf.getText());
        if(amplitude > 0 && period > 0){
        lineArray = new ArrayList<Line>();
        Line line = new Line();
        lineArray.add(line);
        line.setStartX(140);
        line.setStartY(270);
        line.setEndX(140);
        line.setEndY(270);
        //line.setStrokeWidth(10);
        animationP.getChildren().add(line);

        //defining the axes
        //final NumberAxis xAxis = new NumberAxis();
        //final NumberAxis yAxis = new NumberAxis();
        //xAxis.setLabel("Displacement in x");  
        XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
        //creating the chart

        wavesAnimation = new AnimationTimer() {
            
            double x = 140;
            double y = 230;
            double test = 0;

            @Override
            public void handle(long now) {
                
                

                Line l = new Line();
                lineArray.add(l);
                l.setStrokeWidth(5);
                if (x < 740) {

                    l.setStartX(lineArray.get(lineArray.size() - 2).getEndX());
                    l.setStartY(lineArray.get(lineArray.size() - 2).getEndY());
                    x++;
                    y = amplitude * Math.sin(((2 * Math.PI) / period) * x) + 300;
                    l.setEndY(y);
                    l.setEndX(x++);
                    animationP.getChildren().add(l);
                    position.getData().add(new XYChart.Data(x, y));
                    
                                    } else {
                    btnStop.fire();
                    l.setEndX(x);
                    l.setEndY(y);
                }

                //series.getData().add(new XYChart.Data<Number,Number>(x,y));
            }
        };

//        for(int i=0; i<lineArray.size(); i++){ 
//        //series.getData().add(new XYChart.Data<>(Double.toString(lineArray.get(i).getStartX()),Double.toString(lineArray.get(i).getStartY()))); 
//        //lineChart.getData().addAll(series); 
//        double v = lineArray.get(i).getStartX(); 
//        double p = lineArray.get(i).getStartY(); 
//        series.getData().add(new XYChart.Data(Double.toString(v),p));
//    }
        //series.getData().add(new XYChart.Data(Double.toString(period),amplitude));
        //lineChart.getData().addAll(series);
    }
        }
    
    catch(Exception e){
    
}
}
}
