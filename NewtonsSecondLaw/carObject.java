/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NewtonsSecondLaw;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import templates.Vector2D;
import templates.animationObject;


public class carObject extends animationObject {

    protected Rectangle rectangle;
    protected Circle circle;
    double v = 1;

    public carObject(Vector2D position, Vector2D velocity, Vector2D acceleration, double radius) {
        super(position, velocity, acceleration);
        circle = new Circle(0.0, 0.0, radius);
        circle.setLayoutX(position.getX());
        circle.setLayoutY(position.getY());
    }
    
    public carObject(Vector2D position, Vector2D velocity, Vector2D acceleration, int width, int height) {
        super(position, velocity, acceleration);
        this.rectangle = new Rectangle(position.getX(),   position.getY(), width, height);

    }
    
    public carObject(Vector2D acceleration, int width, int height){
        super(new Vector2D(), new Vector2D(), acceleration);
        this.rectangle = new Rectangle(width, height);
    }
        

    public carObject() {
    }
    public double getAccelerationX(){
        return super.getAcceleration().getX();
        
    }

    public Circle getCircle() {
        return circle;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
    
//    public void updateCircle(double dt)
//    {
//        // Euler Integration
//        // Update velocity
//        Vector2D frameAcceleration = acceleration.mul(dt);
//        velocity = velocity.add(frameAcceleration);
//
//        // Update position
//        position = position.add(velocity.mul(dt));
//        circle.setLayoutX(position.getX());
//        circle.setLayoutY(position.getY());
//
//        //Rotate ball
//        circle.setRotate(v++);
//
//        //Make it bounce!
//        AnchorPane p = (AnchorPane)circle.getParent();
//        if (position.getY() > p.getHeight() - circle.getRadius())
//        {
//            double absVelocityY = Math.abs(velocity.getY());
//            absVelocityY *= 0.8;
//            velocity.setY(-absVelocityY);
//        }
//    }
    
    public void updateRectangle(double dt)
    {
        // Euler Integration
        // Update velocity
        Vector2D frameAcceleration = acceleration.mul(dt);
        velocity = velocity.add(frameAcceleration);

        // Update position
        position = position.add(velocity.mul(dt));
        rectangle.setX(position.getX());
//        rectangle.setY(position.getY());

    }

}
