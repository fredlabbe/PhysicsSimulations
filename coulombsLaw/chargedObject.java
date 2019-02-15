package coulombsLaw;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import templates.Vector2D;
import templates.animationObject;


public class chargedObject extends animationObject {

    private double mass;
    private double charge;
    private double radius;
    private Circle circle;

    public chargedObject(double radius, double mass, int charge) {
        super();
        circle = new Circle(radius);
        circle.setFill(Color.BLACK);
        this.mass = mass;
        this.charge = charge * Math.pow(10, -9);

    }

    public chargedObject(Vector2D position, Vector2D velocity) {
        super(position, velocity);
    }

    // Accessors and Mutators
    double getMass() {
        return mass;
    }

    double getCharge() {
        return charge;
    }

    Circle getCircle() {
        return circle;
    }

    //Adding to the Vectors
    void addPosition(double x, double y) {
        super.position.addComponents(x, y);
    }

    void addForce(double x, double y) {
        super.force.addComponents(x, y);
    }

    void addVelocity(double x, double y) {
        super.velocity.addComponents(x, y);
    }

    void addAcceleration(double x, double y) {
        super.velocity.addComponents(x, y);
    }

}
