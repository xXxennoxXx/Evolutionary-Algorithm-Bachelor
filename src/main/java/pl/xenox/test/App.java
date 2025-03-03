package pl.xenox.test;

public class App {

	public  void main(String[] args) {
		int sigma = 33;
		int sizeOfPopulation = 100;
		double max = 10 / (sigma * Math.sqrt(Math.PI * 2))
		* Math.exp(-Math.pow(sizeOfPopulation - sizeOfPopulation, 2) / (2 * Math.pow(sigma, 2)));
		System.out.println(max);
	}

}
