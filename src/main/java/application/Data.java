package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Data {
	File file;
	public int mutateChance;
	public int crossChance;
	ArrayList<Integer> choice = new ArrayList<Integer>();
	ArrayList<Double> prob = new ArrayList<Double>();
	ArrayList<String> def = new ArrayList<String>();
	ArrayList<Point> listOfPoint = new ArrayList<Point>();
	ArrayList<ParametrPoint> listOfParametrs = new ArrayList<ParametrPoint>();
	Integer[][] zadanie = { { 10, 1 }, { 20, 1 }, { 50, 1 }, { 100, 1 }, { 200, 1 }, { 500, 1 }, };

	public void setParameters() {
		ParametrPoint parameters = new ParametrPoint();
		parameters.generateParameters();
		listOfParametrs.add(parameters);
	}

	public void calculate(int numberOfParameters) {
		Double z = 1.0;
		Double v = 0.0;
		Double x = 0.0;
		Double zp = 0.0;
		Double deltat = 0.01;
		Double force = null;
		int i = numberOfParameters;
		ParametrPoint parametr = listOfParametrs.get(i);
		Double wt = parametr.wt;
		Double ksit = parametr.ksit;
		Double alfat = parametr.alfat;
		Double gammat = parametr.gammat;
		Double at = parametr.at;
		Double betat = parametr.betat;
		Double nt = parametr.nt;
		Double wc = parametr.wc;
		Double ksic = parametr.ksic;
		Double alfac = parametr.alfac;
		Double gammac = parametr.gammac;
		Double ac = parametr.ac;
		Double betac = parametr.betac;
		Double nc = parametr.nc;
		Double curentParametrDeflection = 0.0;

		int n = listOfPoint.size();

		for (int j = 0; j < n; j++) {
			Point nextPoint = null;
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
				zp = (double) -gammat * Math.abs(v) * Math.pow(Math.abs(z), nt - 1) * z
						- betat * v * Math.pow(Math.abs(z), nt) + at * v;
			} else if (v <= 0) {
				zp = (double) -gammac * Math.abs(v) * Math.pow(Math.abs(z), nc - 1) * z
						- betac * v * Math.pow(Math.abs(z), nc) + ac * v;
			} else {
				break;
			}
			if (j != n - 1) {
				nextPoint = listOfPoint.get(j + 1);
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
		parametr.deflection = curentParametrDeflection;
	}

	public void evolveTab(int population) {
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

	public void evolveRanks(int population) {
		double max = prob.get(prob.size() - 1);
		for (int i = 0; i < population; i++) {
			double c = ThreadLocalRandom.current().nextDouble(0, max);
			if (c < prob.get(i)) {
				listOfParametrs.get(i).mutate();
				choice.add(i);
			}
		}
		for (int i = 0; i < population; i++) {
			double c = ThreadLocalRandom.current().nextDouble(0, max);
			if (c < prob.get(i)) {
				int e = new Random().nextInt(population);
				double d = ThreadLocalRandom.current().nextDouble(0, max);
				if (d < prob.get(e)) {
					ParametrPoint.cross(listOfParametrs.get(i), listOfParametrs.get(e));
					choice.add(i);
					choice.add(e);
				}
			}
		}
	}

	public void evolveRanksWithProb(int population) {
		int countMutate = population * mutateChance / 100;
		int countCross = population * crossChance / 100;
		for (int i = 0; i < countMutate; i++) {
			double c = ThreadLocalRandom.current().nextDouble(0, prob.get(prob.size() - 1));
			int j = 0;
			while (c > prob.get(j)) {
				j++;
			}
			listOfParametrs.get(j).mutate();
			choice.add(j);
		}
		for (int i = 0; i < countCross; i++) {
			double c = ThreadLocalRandom.current().nextDouble(0, prob.get(prob.size() - 1));
			double d = ThreadLocalRandom.current().nextDouble(0, prob.get(prob.size() - 1));
			int j = 0;
			int k = 0;
			while (c > prob.get(j)) {
				j++;
			}
			while (d > prob.get(k)) {
				k++;
			}
			ParametrPoint.cross(listOfParametrs.get(j), listOfParametrs.get(k));
			choice.add(j);
			choice.add(k);
		}
	}

	public void save(ArrayList list, String fileName) {
		try (BufferedWriter out = new BufferedWriter(new FileWriter(fileName))) {
			for (Object p : list) {
				out.write(p.toString());
				out.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(fileName);
	}

	public void sortParameters() {
		Collections.sort(listOfParametrs, new Comparator<ParametrPoint>() {
			@Override
			public int compare(ParametrPoint arg0, ParametrPoint arg1) {
				int ret = arg0.deflection.compareTo(arg1.deflection);
				return ret;
			}
		});
	}

	public void load() {
//		FileChooser fc = new FileChooser();
//		file = fc.showOpenDialog(null);
		Point point;
		try (BufferedReader in = new BufferedReader(new FileReader("dane.txt"))) {
			String line;
			while ((line = in.readLine()) != null) {
				if (!Pattern.compile("^-?\\d+,?\\d*\\s-?\\d+,?\\d*").matcher(line).find()) {
					continue;
				}
				point = new Point();
				try {
					Matcher matcher = Pattern.compile("-?\\d+,?\\d*").matcher(line);
					matcher.find();
					point.x = -1 * Double.parseDouble(matcher.group().replaceAll(",", "."));
					matcher.find();
					point.expF = Double.parseDouble(matcher.group().replaceAll(",", ".")) * 1000;
					listOfPoint.add(point);
				} catch (Exception e) {
					System.out.println(line);
					continue;
				}
			}
			System.out.println("File loaded.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
