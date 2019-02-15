package templates;


public class Vector2D {
    private double x;
    private double y;
    
    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }
    
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Accessors and Mutators
    public double getX(){ return x; }
    public double getY(){ return y; }
    
    public void  setX(double value){ x=value; }
    public void  setY(double value){ y=value; }
    
    public void addComponents(double x, double y){
        this.x += x;
        this.y += y;
    }
    
    // Operations
    public Vector2D add(Vector2D other) {
        return new Vector2D(x + other.x, y + other.y);
    }

    public Vector2D sub(Vector2D other) {
        return new Vector2D(x - other.x, y - other.y);
    }

    public Vector2D mul(Vector2D other) {
        return new Vector2D(x * other.x, y * other.y);
    }
    
    public Vector2D mul(double multiplier) {
        return new Vector2D(x * multiplier, y * multiplier);
    }
    
}
