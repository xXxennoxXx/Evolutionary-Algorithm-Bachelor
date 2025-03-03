package pl.xenox.testPrawdo;

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
		int o = 0;
		double a = (double) 100 / 1000;
		for (int i = 0; i < 1000; i++) {
			double z = (double) o * a;
			prob.add(z);
			o++;
		}
		for (int k = 0; k < 1000; k++) {
			for (int i = 0; i < 1000; i++) {
				double c = ThreadLocalRandom.current().nextDouble(0, 100);
				if (c < prob.get(i)) {
					data.add(i);
				}
			}
		}
		for (int k = 0; k < 1000; k++) {
			for (int i = 0; i < 1000; i++) {
				double c = ThreadLocalRandom.current().nextDouble(0, 100);
				if (c < prob.get(i)) {
					int e = new Random().nextInt(1000);
					double d = ThreadLocalRandom.current().nextDouble(0, 100);
					if (d < prob.get(e)) {
						data.add(i);
						data.add(e);
					}
				}

			}
		}
		try (BufferedWriter out = new BufferedWriter(new FileWriter("a"))) {
			for (Object p : data) {
				out.write(p.toString());
				out.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try (BufferedWriter out = new BufferedWriter(new FileWriter("b"))) {
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
	public  void main(String[] args) throws InterruptedException {
		A a = new A();
		a.a();

	}
}
