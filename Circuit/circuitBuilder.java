package Circuit;

import static assets.assetManager.closedSwitchImage;
import static assets.assetManager.getpositivePattern;
import static assets.assetManager.openSwitchImage;
import static Circuit.circuitBuilder.xyz;
import static Circuit.circuitBuilderLayouts.ohmsAnimation;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import static main.PhysicsSimulations.animationP;
import static main.PhysicsSimulations.controls;
import static main.PhysicsSimulations.graph;
import static main.PhysicsSimulations.options;


public class circuitBuilder {

    //Control Buttons
    public static ArrayList<Button> buttonsControlEM = new ArrayList();
    Button btnStart = new Button("Start");
    Button btnStop = new Button("Stop");
    Button btnReset = new Button("Reset");
    Button btnClose = new Button("Close");
    Button btnMenu = new Button("Menu");
    Button btnHelp = new Button("Help");

    public static Pane ohmsAnimationPane = new Pane();
    static boolean alreadyStarted = false;
    static ImageView switchImage;
    static int speed = 2;
    public static NumberAxis xAxis = new NumberAxis();
    public static NumberAxis yAxis = new NumberAxis();
    public static final LineChart<Number, Number> ohmsLineChart
            = new LineChart<Number, Number>(xAxis, yAxis);
    public static Circle xyz[];
    public static ArrayList<Slider> sliders = new ArrayList();
    Slider voltage = new Slider();
    Slider resistance = new Slider();
    Slider intensity = new Slider();

    public static ArrayList<Text> sliderTexts = new ArrayList();
    Text voltageText = new Text("Voltage");
    Text resistanceText = new Text("Resistance");
    Text intenistyText = new Text("Intensity");

    public static ArrayList<CheckBox> constantsCheck = new ArrayList();
    CheckBox voltageConstant = new CheckBox("Constant");

    CheckBox resistanceConstant = new CheckBox("Constant");
    CheckBox intensityConstant = new CheckBox("Constant");
    XYChart.Series voltageResistance = new XYChart.Series();
    XYChart.Series voltageIntensity = new XYChart.Series();

    static LineChart<Number, Number> VoltageToResistance;
    static LineChart<Number, Number> IntensityToResistance;
    static LineChart<Number, Number> VoltageToIntensity;
    final XYChart.Series VoltageToResistanceData;
    final XYChart.Series IntensityToResistanceData;
    final XYChart.Series VoltageToIntensityData;
    

    public circuitBuilder() {

        NumberAxis xAxis1 = new NumberAxis();
        xAxis1.setLabel("Resistance");
        NumberAxis yAxis1 = new NumberAxis();
        yAxis1.setLabel("Voltage");

        NumberAxis xAxis2 = new NumberAxis();
        xAxis2.setLabel("Intensity");
        NumberAxis yAxis2 = new NumberAxis();
        yAxis2.setLabel("Resistance");

        NumberAxis xAxis3 = new NumberAxis();
        xAxis3.setLabel("Voltage");
        NumberAxis yAxis3 = new NumberAxis();
        yAxis3.setLabel("Intensity");

        VoltageToResistance = new LineChart<Number, Number>(xAxis1, yAxis1);
        IntensityToResistance = new LineChart<Number, Number>(xAxis2, yAxis2);
        VoltageToIntensity = new LineChart<Number, Number>(xAxis3, yAxis3);

        VoltageToResistanceData = new XYChart.Series();
        IntensityToResistanceData = new XYChart.Series();
        VoltageToIntensityData = new XYChart.Series();
        
        VoltageToResistance.getData().add(VoltageToResistanceData);
        IntensityToResistance.getData().add(IntensityToResistanceData);
        VoltageToIntensity.getData().add(VoltageToIntensityData);
        
        buttonsControlEM.add(btnStart);
        buttonsControlEM.add(btnStop);
        buttonsControlEM.add(btnReset);
        buttonsControlEM.add(btnClose);
        buttonsControlEM.add(btnMenu);
        buttonsControlEM.add(btnHelp);
        btnStop.setDisable(true);
        xyz = new Circle[79];
        speed = 5;

        //Adding Sliders
        sliders.add(voltage);
        sliders.add(resistance);
        sliders.add(intensity);
        voltage.setMax(40);
        voltage.setMin(0);
        voltage.setValue(20);
        voltage.setMaxWidth(200);
        resistance.setMax(40);
        resistance.setMin(0);
        resistance.setValue(4);
        resistance.setMaxWidth(200);
        intensity.setMax(40);
        intensity.setMin(0);
        intensity.setValue(5);
        intensity.setMaxWidth(200);

        // V = I R 
        voltage.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {

                if (intensity.isDisabled()) {
                    double currentVoltage = new_val.doubleValue();
                    double currentIntenisty = intensity.getValue();
                    double currentResistance = currentVoltage / currentIntenisty;
                    resistance.setValue(currentResistance);
                    VoltageToResistanceData.getData().add(new XYChart.Data(currentVoltage,currentResistance));

                }

                if (resistance.isDisabled()) {
                    double currentVoltage = new_val.doubleValue();
                    double currentResistance = resistance.getValue();
                    double currentIntenisty = currentVoltage / currentResistance;
                    intensity.setValue(currentIntenisty);
                    speed = (int) (speed / 3 + currentIntenisty);
                    xAxis.setLabel("Voltage");
                    voltageResistance.getData().add(new XYChart.Data(currentVoltage, currentIntenisty));
                    voltageIntensity.setName("Voltage to Intensity");
                    VoltageToIntensityData.getData().add(new XYChart.Data(currentVoltage,currentIntenisty));

                }
            }
        });


        ohmsLineChart.getData().add(voltageResistance);
        ohmsLineChart.getData().add(voltageIntensity);

        //Adding Texts
        sliderTexts.add(voltageText);
        sliderTexts.add(resistanceText);
        sliderTexts.add(intenistyText);
        for (int i = 0; i < sliderTexts.size(); i++) {
            sliderTexts.get(i).setStyle("-fx-font: 25px Serif;");
        }

        //Adding CheckBoxes
        constantsCheck.add(voltageConstant);
        constantsCheck.add(resistanceConstant);
        constantsCheck.add(intensityConstant);

        voltageConstant.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov,
                    Boolean old_val, Boolean new_val) {
                voltage.setDisable(true);
                resistance.setDisable(false);
                intensity.setDisable(false);
                resistanceConstant.setSelected(false);
                intensityConstant.setSelected(false);
            }
        });

        resistanceConstant.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov,
                    Boolean old_val, Boolean new_val) {
                resistance.setDisable(true);
                voltage.setDisable(false);
                intensity.setDisable(false);
                voltageConstant.setSelected(false);
                intensityConstant.setSelected(false);
            }
        });

        intensityConstant.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov,
                    Boolean old_val, Boolean new_val) {
                intensity.setDisable(true);
                resistance.setDisable(false);
                voltage.setDisable(false);
                resistanceConstant.setSelected(false);
                voltageConstant.setSelected(false);
            }
        });

        btnStart.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                if (!alreadyStarted) {

                    for (int i = 0; i < xyz.length; i++) {
                        //add Top
                        if (i <= 22) {
                            xyz[i] = new Circle(18);
                            ohmsAnimationPane.getChildren().add(xyz[i]);
                            xyz[i].setCenterX(85 + i * 32);
                            xyz[i].setCenterY(83);
                            xyz[i].setFill(getpositivePattern());
                            xyz[i].toBack();

                        }//Add bottom 
                        else if (i > 22 && i <= 45) {
                            xyz[i] = new Circle(18);
                            ohmsAnimationPane.getChildren().add(xyz[i]);
                            xyz[i].setCenterX(85 + (i - 23) * 32);
                            xyz[i].setCenterY(635);
                            xyz[i].setFill(getpositivePattern());
                            xyz[i].toBack();

                        } //add left
                        else if (i > 45 && i <= 62) {
                            xyz[i] = new Circle(18);
                            ohmsAnimationPane.getChildren().add(xyz[i]);
                            xyz[i].setCenterX(85);
                            xyz[i].setCenterY(90 + (i - 45) * 32);
                            xyz[i].setFill(getpositivePattern());
                            xyz[i].toBack();
                            System.out.println(i);

                        } //add right
                        else if (i > 62 && i <= 79) {
                            xyz[i] = new Circle(18);
                            ohmsAnimationPane.getChildren().add(xyz[i]);
                            xyz[i].setCenterX(810);
                            xyz[i].setCenterY(90 + (i - 62) * 32);
                            xyz[i].setFill(getpositivePattern());
                            xyz[i].toBack();
                        }
                    }
                }
                ohmsAnimation.start();
                btnStart.setDisable(true);
                btnStop.setDisable(false);
                alreadyStarted = true;
                switchImage.setImage(closedSwitchImage);
            }
        });

        btnStop.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                ohmsAnimation.stop();
                btnStart.setDisable(false);
                btnStop.setDisable(true);
                switchImage.setImage(openSwitchImage);

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
                ohmsAnimation.stop();
                btnStop.fire();
                xyz = new Circle[79];
            }
        });

        btnHelp.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Help");
                alert.setHeaderText("Ohm's Law: Help");
                alert.setContentText("This simulation is a visual representation of ohm’s law."
                        + " The user will input values for the resistance and the intensity of"
                        + " the circuit as well as being able to open and close the circuit."
                        + " Once they begin the simulation, there will be a light bulb that will"
                        + " light up a specific amount depending on the values they entered.  ");
                alert.showAndWait();
            }
        });
    }

}
