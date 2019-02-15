package coulombsLaw;

import static Circuit.circuitBuilder.xAxis;
import static Circuit.circuitBuilder.yAxis;
import static assets.assetManager.getpositivePattern;
import static assets.assetManager.getnegativePattern;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import static main.PhysicsSimulations.animationP;
import static main.PhysicsSimulations.controls;
import static main.PhysicsSimulations.graph;
import static main.PhysicsSimulations.options;


public class CoulombLaw {

    //Buttons
    public static ArrayList<Button> buttonsControlCoulomb = new ArrayList();
    Button btnStart = new Button("Start");
    Button btnStop = new Button("Stop");
    Button btnReset = new Button("Reset");
    Button btnClose = new Button("Close");
    Button btnMenu = new Button("Menu");
    Button btnHelp = new Button("Help");
    public static Button btnAdd = new Button("Add");

    //Texts
    public static ArrayList<Text> texts = new ArrayList();
    Text radius = new Text("Radius: ");
    Text charge = new Text("Charge: ");
    Text mass = new Text("Mass: ");
    Text positionX = new Text("PositionX: ");
    Text positionY = new Text("PositionY: ");

    //Text Fields
    public static ArrayList<TextField> textFields = new ArrayList();
    TextField radiusTf = new TextField();
    TextField chargeTf = new TextField();
    TextField massTf = new TextField();
    TextField positionXTf = new TextField();
    TextField positionYTf = new TextField();

    //Charged Objects
    private ArrayList<chargedObject> objectList = new ArrayList<chargedObject>();

    //AnimationTimer 
    private AnimationTimer animation;

    //Constants
    private double kConstant = (9 * Math.pow(10, 9));

    //Charts
    public static final LineChart<Number, Number> coulombLineChart
            = new LineChart<Number, Number>(xAxis, yAxis);
    XYChart.Series series = new XYChart.Series();
    private long lastFrameTime = (long) 0.0f;
    private long count = 0;

    public CoulombLaw() {

        //Charts
        xAxis.setLabel("Time");
        yAxis.setLabel("Energy (nJ)");
        coulombLineChart.setTitle("Energy as a function of Time");
        coulombLineChart.getData().add(series);

        //Adding Buttons
        buttonsControlCoulomb.add(btnStart);
        buttonsControlCoulomb.add(btnStop);
        buttonsControlCoulomb.add(btnReset);
        buttonsControlCoulomb.add(btnClose);
        buttonsControlCoulomb.add(btnMenu);
        buttonsControlCoulomb.add(btnHelp);
        //Adding Texts
        texts.add(radius);
        texts.add(charge);
        texts.add(mass);
        texts.add(positionX);
        texts.add(positionY);

        //Adding TextFields
        textFields.add(radiusTf);
        textFields.add(chargeTf);
        textFields.add(massTf);
        textFields.add(positionXTf);
        textFields.add(positionYTf);

        //Buttons Action Listeners
        btnStart.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                final long initialTime = System.nanoTime();
                series.getData().clear();

                animation = new AnimationTimer() {
                    @Override
                    public void handle(long now) {
                        long currentTime = (long) ((now - initialTime) / 1000000000.0f);
                        long frameDeltaTime = currentTime - lastFrameTime;
                        lastFrameTime = currentTime;
                        count += frameDeltaTime;

                        iterate(frameDeltaTime);
                        changePositions();
                        changeGraph(count);
                    }
                };
                animation.start();
                btnStart.setDisable(true);
                btnStop.setDisable(false);
            }
        });

        btnStop.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                animation.stop();
                btnStart.setDisable(false);
                btnStop.setDisable(true);
            }
        });

        btnReset.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                series.getData().clear();
                animation.stop();
                for (int y = 0; y < objectList.size(); y++) {
                    animationP.getChildren().remove(objectList.get(y).getCircle());
                }

                objectList.clear();
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

        btnAdd.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                try {
                    double radius = Double.parseDouble(radiusTf.getText());
                    double charge = Double.parseDouble(chargeTf.getText());
                    double mass = Double.parseDouble(massTf.getText());
                    double positionX = Double.parseDouble(positionXTf.getText());
                    double positionY = Double.parseDouble(positionYTf.getText());
                    chargedObject newObj = new chargedObject(radius, mass, (int) charge);
                    if (charge > 0) {
                        newObj.getCircle().setFill(getpositivePattern());
                    } else {
                        newObj.getCircle().setFill(getnegativePattern());
                    }

                    newObj.getCircle().setLayoutX(positionX);
                    newObj.getCircle().setLayoutY(positionY);
                    newObj.getCircle().setCenterX(positionX);
                    newObj.getCircle().setCenterY(positionY);
                    objectList.add(newObj);
                    animationP.getChildren().add(newObj.getCircle());
                } catch (Exception e) {
                    System.out.println("Did not Work");
                }
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

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Help");
                alert.setHeaderText("Coulomb's Law: Help");
                alert.setContentText("This simulation is a visual representation of Coulomb’s law. The user will"
                        + " be able to place as many particles as they would like on the board and give them"
                        + " specific masses, charges, radii and positions. With those values, once the user"
                        + " begins the animation, the particles will interact between each other and begin to"
                        + " move bases on the ruled of electricity and magnetism. ");
                alert.showAndWait();
            }
        });

    }

    public void iterate(long now) {

        double energyTotal = 0.0;
        long lastNow = now;

        for (int toIterate = 0; toIterate < objectList.size(); toIterate++) {

            for (int i = 0; i < objectList.size(); i++) {

                double mesuredCharge = objectList.get(toIterate).getCharge();
                double targetCharge = objectList.get(i).getCharge();

                double targetX = objectList.get(i).getCircle().getCenterX();
                double targetY = objectList.get(i).getCircle().getCenterY();
                double mesuredX = objectList.get(toIterate).getCircle().getCenterX();
                double mesuredY = objectList.get(toIterate).getCircle().getCenterY();

                double formulaCharge = mesuredCharge * targetCharge;

                if (toIterate != i) {

                    double distanceX = Math.abs(targetX - mesuredX);
                    double distanceXSquared = Math.pow(distanceX, 2);

                    double distanceY = Math.abs(targetY - mesuredY);
                    double distanceYSquared = Math.pow(distanceY, 2);

                    double finalDistance = Math.sqrt((distanceXSquared + distanceYSquared));
                    double finalDistanceSquared = Math.pow(finalDistance, 2);

                    double angle = (Math.atan((distanceY / distanceX)));
                    double force = kConstant * formulaCharge / finalDistanceSquared;

                    double forceXComponent = force * Math.cos(angle);
                    forceXComponent = Math.abs(forceXComponent);
                    double forceYComponent = force * Math.sin(angle);
                    forceYComponent = Math.abs(forceYComponent);

                    if (formulaCharge > 0) {

                        if (mesuredX > targetX) {
                            forceXComponent = forceXComponent * 1;
                        } else if (mesuredX < targetX) {
                            forceXComponent = forceXComponent * -1;
                        }

                        if (mesuredY > targetY) {
                            forceYComponent = forceYComponent * 1;
                        } else if (mesuredY < targetY) {
                            forceYComponent = forceYComponent * -1;
                        }

                    }
                    if (formulaCharge < 0) {

                        if (mesuredX > targetX) {
                            forceXComponent = forceXComponent * -1;
                        } else if (mesuredX < targetX) {
                            forceXComponent = forceXComponent * 1;
                        }

                        if (mesuredY > targetY) {
                            forceYComponent = forceYComponent * -1;
                        } else if (mesuredY < targetY) {
                            forceYComponent = forceYComponent * 1;
                        }

                    }

                    objectList.get(toIterate).addForce(forceXComponent, forceYComponent);

                }
            }
        }
    }

    public void changePositions() {

        for (int i = 0; i < objectList.size(); i++) {

            double currentX = objectList.get(i).getCircle().getCenterX();
            double currentY = objectList.get(i).getCircle().getCenterY();

            double currentForceX = objectList.get(i).getForce().getX();
            double currentForceY = objectList.get(i).getForce().getY();

            double currentVelocityX = objectList.get(i).getVelocity().getX(); // per second
            double currentVelocityY = objectList.get(i).getVelocity().getY(); // per second

            double mass = objectList.get(i).getMass() / 1000;

            double accelerationX = currentForceX / mass; //per second
            double accelerationY = currentForceY / mass; //per second

            double newVelocityX = currentVelocityX + accelerationX; //per second
            double newVelocityY = currentVelocityY + accelerationY; //per second

            //TODO PER SECOND 
            long currentTimeIndex = System.nanoTime();
            float ellapsedTime = 10000;

            double addedPositionX = ellapsedTime * newVelocityX;
            double addedPositionY = ellapsedTime * newVelocityY;

            double newPositionX = currentX + addedPositionX;
            double newPositionY = currentY + addedPositionY;

            //Change Values
            objectList.get(i).addVelocity(accelerationX, accelerationY);

            objectList.get(i).getCircle().setCenterX(newPositionX);
            objectList.get(i).getCircle().setCenterY(newPositionY);
        }
    }

    public void changeGraph(double count) {

        double energyTotal = 0;
        for (int q1 = 0; q1 < objectList.size(); q1++) {
            int q2 = 1;
            q2 += q1;
            for (; q2 < objectList.size(); q2++) {

                double chargeQ1 = objectList.get(q1).getCharge();
                double chargeQ2 = objectList.get(q2).getCharge();

                double q1X = objectList.get(q1).getCircle().getCenterX();
                double q1Y = objectList.get(q1).getCircle().getCenterY();

                double q2X = objectList.get(q2).getCircle().getCenterX();
                double q2Y = objectList.get(q2).getCircle().getCenterY();

                double distanceX = Math.abs(q1X - q2X);
                double distanceXSquared = Math.pow(distanceX, 2);

                double distanceY = Math.abs(q1Y - q2Y);
                double distanceYSquared = Math.pow(distanceY, 2);

                double finalDistance = Math.sqrt((distanceXSquared + distanceYSquared));

                energyTotal += ((Math.abs(chargeQ1 * chargeQ2 * kConstant) / finalDistance) * Math.pow(10, 9));
            }
        }
        series.getData().add(new XYChart.Data(count, energyTotal));
    }
}
