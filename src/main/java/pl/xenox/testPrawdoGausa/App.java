package pl.xenox.testPrawdoGausa;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class A {
    ArrayList<Double> prob = new ArrayList<>();
    ArrayList<Integer> data = new ArrayList<>();

    public void a() {
        int population = 1000;
        int sigma = population / 3;

        for (int i = 0; i < population; i++) {
            Double f = 1 / (sigma * Math.sqrt(Math.PI * 2))
                    * Math.exp(-Math.pow(i - population, 2) / (2 * Math.pow(sigma, 2)));
            prob.add(f);
        }
        double max = prob.get(prob.size() - 1);
        System.out.println(max);

        for (int k = 0; k < population; k++) {
            for (int i = 0; i < population; i++) {
                double c = ThreadLocalRandom.current().nextDouble(0, max);
                if (c < prob.get(i)) {
                    data.add(i);
                }
            }
        }
        for (int k = 0; k < population; k++) {
            for (int i = 0; i < population; i++) {
                double c = ThreadLocalRandom.current().nextDouble(0, max);
                if (c < prob.get(i)) {
                    int e = new Random().nextInt(population);
                    double d = ThreadLocalRandom.current().nextDouble(0, max);
                    if (d < prob.get(e)) {
                        data.add(i);
                        data.add(e);
                    }
                }

            }
        }
        try (BufferedWriter out = new BufferedWriter(new FileWriter("a.txt"))) {
            for (Object p : data) {
                out.write(p.toString());
                out.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter out = new BufferedWriter(new FileWriter("b.txt"))) {
            for (Object p : prob) {
                out.write(p.toString());
                out.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class App {
    public void main(String[] args) throws InterruptedException {
        A a = new A();
        a.a();

    }
}
