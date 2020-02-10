package application;

public class Point {
	public Double x;
	public Double expF;
	public Double F;
	public Double z;
	public Double v;
	public Double a;
	public Double zp;

	@Override
	public String toString() {
		return String.format("%s", F);
	}

//	@Override
//	public String toString() {
//		return "Point [x=" + String.format("%10.2f", x) + ", expF=" + String.format("%10.2f", expF) + ", F="
//				+ String.format("%10.2f", F) + ", z=" + String.format("%10.2f", z) + ", v=" + String.format("%10.2f", v)
//				+ ", a=" + String.format("%10.2f", a) + ", zp=" + String.format("%10.2f", zp) + "]";
//	}

}
