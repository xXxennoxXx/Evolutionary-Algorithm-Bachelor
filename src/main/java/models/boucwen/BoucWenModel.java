package models.boucwen;

import data.Data;
import data.Sample;
import exceptions.WrongParametersSetsException;
import models.Model;
import models.ParametersSet;

public class BoucWenModel implements Model {

    @Override
    public Double calculate(ParametersSet parametersSet, Data data) throws WrongParametersSetsException {
        BoucWenParameterSet ps;
        try {
            ps = (BoucWenParameterSet) parametersSet;
        } catch (Exception e) {
            throw new WrongParametersSetsException("Expected to find BoucWenParametersSet, find: " + parametersSet.getClass());
        }

        Double z = 1.0,
                v = 0.0,
                x = 0.0,
                zp = 0.0,
                deltat = 0.01,
                force = null;

        Double wt = ps.wt,
                ksit = ps.ksit,
                alfat = ps.alfat,
                gammat = ps.gammat,
                at = ps.at,
                betat = ps.betat,
                nt = ps.nt,
                wc = ps.wc,
                ksic = ps.ksic,
                alfac = ps.alfac,
                gammac = ps.gammac,
                ac = ps.ac,
                betac = ps.betac,
                nc = ps.nc,
                curentParametrDeflection = 0.0;

        int n = data.getSampleData().size();

        for (int j = 0; j < n; j++) {
            Sample nextPoint = null;
            Sample point = data.getSampleData().get(j);
            x = point.x;

            if (j == 0) {
                point.z = z;
                nextPoint = data.getSampleData().get(j + 1);
                point.v = (x - nextPoint.x) / deltat;
                v = point.v;
            } else if (j != n - 1) {
                nextPoint = data.getSampleData().get(j + 1);
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
                nextPoint = data.getSampleData().get(j + 1);
                nextPoint.z = zp * deltat + z;
            } else {

            }
            point.zp = zp;

            if (v > 0) {
                force = 2 * ksit * wt * v + alfat * Math.pow(wt, 2) * x + (1 - alfat) * Math.pow(wt, 2) * z;
            } else if (v <= 0) {
                force = 2 * ksic * wc * v + alfac * Math.pow(wc, 2) * x + (1 - alfac) * Math.pow(wc, 2) * z;
            } else {
                break;
            }

            point.F = force;
            Double p = Math.pow(point.expF - force, 2);
            curentParametrDeflection = curentParametrDeflection + p;
        }
        return curentParametrDeflection;
    }

    @Override
    public ParametersSet getNewParametersSet() {
        return new BoucWenParameterSet();
    }
}
