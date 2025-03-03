package pl.xenox;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ParametrPoint {
    public Double wt;
    public Double ksit;
    public Double alfat;
    public Double gammat;
    public Double at;
    public Double betat;
    public Double nt;
    public Double wc;
    public Double ksic;
    public Double alfac;
    public Double gammac;
    public Double ac;
    public Double betac;
    public Double nc;
    public Double deflection;

    public ParametrPoint(ParametrPoint old) {
        this.wt = old.wt;
        this.ksit = old.ksit;
        this.alfat = old.alfat;
        this.gammat = old.gammat;
        this.at = old.at;
        this.betat = old.betat;
        this.nt = old.nt;
        this.wc = old.wc;
        this.ksic = old.ksic;
        this.alfac = old.alfac;
        this.gammac = old.gammac;
        this.ac = old.ac;
        this.betac = old.betac;
        this.nc = old.nc;
    }

    public void generateParameters() {

        generateWt();
        generateKsit();
        generateAlfat();
        generateGammat();
        generateAt();
        generateBetat();
        generateNt();
        generateWc();
        generateKsic();
        generateAlfac();
        generateGammac();
        generateAc();
        generateBetac();
        generateNc();
    }

    @Override
    public String toString() {
        return "wt= " + wt + "; ksit= " + ksit + "; alfat= " + alfat + "; gammat= " + gammat + "; at= " + at
                + "; betat= " + betat + "; nt= " + nt + "; wc= " + wc + "; ksic= " + ksic + "; alfac= " + alfac
                + "; gammac= " + gammac + "; ac= " + ac + "; betac= " + betac + "; nc= " + nc + "; deflection= "
                + deflection;
    }

    public void mutate() {
        int i = new Random().nextInt(14);
        switch (i) {
            case 0:
                generateWt();
                break;
            case 1:
                generateKsit();
                break;
            case 2:
                generateAlfat();
                break;
            case 3:
                generateGammat();
                break;
            case 4:
                generateAt();
                break;
            case 5:
                generateBetat();
                break;
            case 6:
                generateNt();
                break;
            case 7:
                generateWc();
                break;
            case 8:
                generateKsic();
                break;
            case 9:
                generateAlfac();
                break;
            case 10:
                generateGammac();
                break;
            case 11:
                generateAc();
                break;
            case 12:
                generateBetac();
                break;
            case 13:
                generateNc();
                break;
        }
    }

    public static void cross(ParametrPoint par1, ParametrPoint par2) {
        int r = new Random().nextInt(13) + 1;
        switch (r) {
            case 0:
                Double a = par1.ac;
                par1.ac = par2.ac;
                par2.ac = a;
                break;
            case 1:
                Double b = par1.alfac;
                par1.alfac = par2.alfac;
                par2.alfac = b;
                break;
            case 2:
                Double c = par1.betac;
                par1.betac = par2.betac;
                par2.betac = c;
                break;
            case 3:
                Double d = par1.gammac;
                par1.gammac = par2.gammac;
                par2.gammac = d;
                break;
            case 4:
                Double e = par1.ksic;
                par1.ksic = par2.ksic;
                par2.ksic = e;
                break;
            case 5:
                Double f = par1.nc;
                par1.nc = par2.nc;
                par2.nc = f;
                break;
            case 6:
                Double g = par1.wc;
                par1.wc = par2.wc;
                par2.wc = g;
                break;
            case 7:
                Double h = par1.at;
                par1.at = par2.at;
                par2.at = h;
                break;
            case 8:
                Double i = par1.alfat;
                par1.alfat = par2.alfat;
                par2.alfat = i;
                break;
            case 9:
                Double j = par1.betat;
                par1.betat = par2.betat;
                par2.betat = j;
                break;
            case 10:
                Double k = par1.gammat;
                par1.gammat = par2.gammat;
                par2.gammat = k;
                break;
            case 11:
                Double l = par1.ksit;
                par1.ksit = par2.ksit;
                par2.ksit = l;
                break;
            case 12:
                Double m = par1.nt;
                par1.nt = par2.nt;
                par2.nt = m;
                break;
            case 13:
                Double n = par1.wt;
                par1.wt = par2.wt;
                par2.wt = n;
                break;

            default:
                break;
        }
    }


    private void generateWt() {
        this.wt = ThreadLocalRandom.current().nextDouble(-5.0, 5.0);
    }

    private void generateKsit() {
        this.ksit = ThreadLocalRandom.current().nextDouble(-5.0, 10.0);
    }

    private void generateAlfat() {
        this.alfat = ThreadLocalRandom.current().nextDouble(0.0, 100.0);
    }

    private void generateGammat() {
        this.gammat = ThreadLocalRandom.current().nextDouble(-20.0, 20.0);
    }

    private void generateAt() {
        this.at = ThreadLocalRandom.current().nextDouble(-20.0, 30.0);
    }

    private void generateBetat() {
        this.betat = ThreadLocalRandom.current().nextDouble(0.0, 25.0);
    }

    private void generateNt() {
        this.nt = ThreadLocalRandom.current().nextDouble(-3.0, 3.0);
    }

    private void generateWc() {
        this.wc = ThreadLocalRandom.current().nextDouble(-5.0, 5.0);
    }

    private void generateKsic() {
        this.ksic = ThreadLocalRandom.current().nextDouble(-5.0, 10.0);
    }

    private void generateAlfac() {
        this.alfac = ThreadLocalRandom.current().nextDouble(0.0, 100.0);
    }

    private void generateGammac() {
        this.gammac = ThreadLocalRandom.current().nextDouble(-20.0, 20.0);
    }

    private void generateAc() {
        this.ac = ThreadLocalRandom.current().nextDouble(-20.0, 30.0);
    }

    private void generateBetac() {
        this.betac = ThreadLocalRandom.current().nextDouble(0.0, 25.0);
    }

    private void generateNc() {
        this.nc = ThreadLocalRandom.current().nextDouble(-3.0, 3.0);
    }

}
