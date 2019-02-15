/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectileMotion;

import javafx.scene.shape.Circle;
import templates.Vector2D;
import templates.animationObject;

public class canonBall extends animationObject {
    
    Circle canonBall;
    
    public canonBall(){
        
    }
    
    public canonBall(Vector2D position, Vector2D velocity, Vector2D acceleration, int radiusOfCanon){
        
        super(position, velocity, acceleration);
        canonBall = new Circle(0.0, 0.0, radiusOfCanon);
        canonBall.setLayoutX(position.getX());
        canonBall.setLayoutY(position.getY());
    }
    
    public Circle getCircle(){
        return canonBall;
    }

    public Vector2D getVelocity(){
        return super.velocity;
    }
    public void updateCircle(double dt)
    {
        // Euler Integration
        // Update velocity
        Vector2D frameAcceleration = acceleration.mul(dt);
        velocity = velocity.add(frameAcceleration);

        // Update position
        position = position.add(velocity.mul(dt));
        canonBall.setLayoutX(position.getX());
        canonBall.setLayoutY(position.getY());

    }
}
