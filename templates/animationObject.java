package templates;


public class animationObject {
    
    protected Vector2D position;
    protected Vector2D force;
    protected Vector2D velocity;
    protected Vector2D acceleration;

    
    //Constructers 
    public animationObject(){
        position = new Vector2D();
        force = new Vector2D();
        velocity = new Vector2D();
        acceleration = new Vector2D();
    }
    
    public animationObject(Vector2D position, Vector2D velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    public animationObject(Vector2D position, Vector2D velocity, Vector2D acceleration) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    public animationObject(Vector2D position) {
        this.position = position;
    }
    
    //Adding to the Components
    public void changePosition(double x, double y) {
       position.addComponents(x,y);
    }
    
    public void changeForce(double x, double y) {
        force.addComponents(x,y); 
    }
    
    public void changeVelocity(double x, double y) {
        velocity.addComponents(x,y);
    }

    public void changeAcceleration(double x, double y) {
        acceleration.addComponents(x,y);
    }
    
    //Returning a Vector
    public Vector2D getPosition() {
        return position;
    }
    
    public Vector2D getForce() {
        return force;
    }
    
    public Vector2D getVelocity() {
        return position;
    }

    public Vector2D getAcceleration() {
        return acceleration;
    }
    
}
