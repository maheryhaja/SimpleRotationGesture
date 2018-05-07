package maheryhaja.mg.simplerotationgesture.data;

/**
 * Created by maheryHaja on 5/7/2018.
 */

public class SimplePoint {
    private float x = 0;
    private float y = 0;

    public SimplePoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public SimplePoint() {
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "SimplePoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
