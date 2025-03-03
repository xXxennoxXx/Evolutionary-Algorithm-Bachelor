package pl.xenox.algorithm;

import pl.xenox.ParametrPoint;
import pl.xenox.Point;

import java.util.List;

public class BoucWenAlgorithm implements Algorithm {

    @Override
    public void calculate(ParametrPoint parameter, List<Point> listOfPoint) {
        Double z = 1.0;
        Double v = 0.0;
        Double x;
        double zp;
        double deltat = 0.01;
        Double force;
        Double wt = parameter.wt;
        Double ksit = parameter.ksit;
        Double alfat = parameter.alfat;
        Double gammat = parameter.gammat;
        Double at = parameter.at;
        Double betat = parameter.betat;
        Double nt = parameter.nt;
        Double wc = parameter.wc;
        Double ksic = parameter.ksic;
        Double alfac = parameter.alfac;
        Double gammac = parameter.gammac;
        Double ac = parameter.ac;
        Double betac = parameter.betac;
        Double nc = parameter.nc;
        double curentParametrDeflection = 0.0;

        int n = listOfPoint.size();

        for (int j = 0; j < n; j++) {
            Point nextPoint;
            Point point = listOfPoint.get(j);
            x = point.x;

            if (j == 0) {
                point.z = z;
                nextPoint = listOfPoint.get(j + 1);
                point.v = (x - nextPoint.x) / deltat;
                v = point.v;
            } else if (j != n - 1) {
                nextPoint = listOfPoint.get(j + 1);
                point.v = (x - nextPoint.x) / deltat;
//				a = (point.v - nextPoint.v) / deltat;
//				nextPoint.z = zp * deltat + point.z;
                z = point.z;
                v = point.v;
            } else {
                point.z = z;
                point.v = v;
            }

            if (v > 0) {
                zp = -gammat * Math.abs(v) * Math.pow(Math.abs(z), nt - 1) * z
                        - betat * v * Math.pow(Math.abs(z), nt) + at * v;
            } else if (v <= 0) {
                zp = -gammac * Math.abs(v) * Math.pow(Math.abs(z), nc - 1) * z
                        - betac * v * Math.pow(Math.abs(z), nc) + ac * v;
            } else {
                break;
            }
            if (j != n - 1) {
                nextPoint = listOfPoint.get(j + 1);
                nextPoint.z = zp * deltat + z;
            }
            point.zp = zp;

            if (v > 0) {
                force = 2 * ksit * wt * v + alfat * Math.pow(wt, 2) * x + (1 - alfat) * Math.pow(wt, 2) * z;
            } else {
                force = 2 * ksic * wc * v + alfac * Math.pow(wc, 2) * x + (1 - alfac) * Math.pow(wc, 2) * z;
            }

            point.F = force;
            double p = Math.pow(point.expF - force, 2);
            curentParametrDeflection = curentParametrDeflection + p;
        }
        parameter.deflection = curentParametrDeflection;
    }

}
