package ma.ensaj.dem1.model;

public class Airplane {
    private double x = 0;
    private double y = 0;
    private double altittude;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getAltittude() {
        return altittude;
    }

    public void setAltittude(double altittude) {
        this.altittude = altittude;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    private double speed = 0;

    public Airplane(double x, double y, double altittude, double speed) {
        this.x = x;
        this.y = y;
        this.altittude = altittude;
        this.speed = speed;
    }




}
