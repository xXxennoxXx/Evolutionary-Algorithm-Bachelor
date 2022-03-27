package application;

import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
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

}