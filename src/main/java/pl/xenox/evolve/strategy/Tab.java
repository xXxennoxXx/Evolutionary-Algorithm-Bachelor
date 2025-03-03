package pl.xenox.evolve.strategy;

import pl.xenox.ParametrPoint;

import java.util.List;
import java.util.Random;

public class Tab implements Evolution {

    @Override
    public void evolve(List<ParametrPoint> listOfParametrs) {
        int population = listOfParametrs.size();
        int stay = population / 4;
        for (int b = 0; b < population; b++) {
            for (int a = b + 1; a < population; a++) {
                if (listOfParametrs.get(a).equals(listOfParametrs.get(b))) {
                    listOfParametrs.get(a).mutate();
                }
            }
        }
        for (int c = 0; c < stay; c++) {
            int d = new Random().nextInt(stay - c) + c + 1;
            ParametrPoint par11 = new ParametrPoint(listOfParametrs.get(c));
            ParametrPoint par22 = new ParametrPoint(listOfParametrs.get(d));
            ParametrPoint.cross(par11, par22);
            for (int a = 0; a < 2; a++) {
                listOfParametrs.remove(stay);
            }
            listOfParametrs.add(par22);
            listOfParametrs.add(par11);
        }
        while (listOfParametrs.size() > population) {
            listOfParametrs.remove(population / 2);
        }
        for (int i = 0; i < stay; i++) {
            ParametrPoint par = new ParametrPoint(listOfParametrs.get(i));
            listOfParametrs.remove(stay);
            par.mutate();
            listOfParametrs.add(par);
        }
    }

}