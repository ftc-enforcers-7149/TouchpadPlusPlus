package Utils;

public class Scale {

    private final double lowerIn, upperIn, lowerOut, upperOut;

    public Scale(double lowerIn, double upperIn, double lowerOut, double upperOut) {
        this.lowerIn = lowerIn;
        this.upperIn = upperIn;
        this.lowerOut = lowerOut;
        this.upperOut = upperOut;
    }

    public double output(double input) {
        double scaleFactor = (upperOut - lowerOut) / (upperIn - lowerIn);
        return scaleFactor * (input - lowerIn) + lowerOut;
    }
}