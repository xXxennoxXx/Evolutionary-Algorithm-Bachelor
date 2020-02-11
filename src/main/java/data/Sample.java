package data;

public class Sample {
    public Double x,
            expF,
            F,
            z,
            v,
            a,
            zp;


    @Override
    public String toString() {
        return "Point [x=" + String.format("%10.2f", x) + ", expF=" + String.format("%10.2f", expF) + ", F="
                + String.format("%10.2f", F) + ", z=" + String.format("%10.2f", z) + ", v=" + String.format("%10.2f", v)
                + ", a=" + String.format("%10.2f", a) + ", zp=" + String.format("%10.2f", zp) + "]";
    }

}
