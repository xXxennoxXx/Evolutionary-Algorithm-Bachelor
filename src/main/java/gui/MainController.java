package gui;

import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import application.Data;
import application.ParametrPoint;
import application.Point;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainController implements Initializable {
	Data data;

	@FXML
	private ListView lvParameterList;
	@FXML
	private TextField textBoxNumberOfPopulation;
	@FXML
	private TextField textBoxSizeOfPopulation;
	@FXML
	private TextField txtBoxChanceToMutate;
	@FXML
	private TextField textBoxChanceToCross;
	@FXML
	private TextField textBoxStayPercent;
	@FXML
	private ScatterChart<Number, Number> fxChart;
	@FXML
	private ScatterChart<Number, Number> ftChart;
	@FXML
	private Button btnCalculate;
	@FXML
	private ProgressBar pbCal;
	@FXML
	private HBox hbFrequency;
	@FXML
	private VBox vbTextBox;
	@FXML
	private Button btnClear;
	@FXML
	private Button btnDraw;

	public void open(ActionEvent event) {
		try {
			data = new Data();
			data.load();
			XYChart.Series<Number, Number> seriesx = new Series<Number, Number>();
			seriesx.setName("Experimental Data");
			for (Point p : data.listOfPoint) {
				seriesx.getData().add(new XYChart.Data<Number, Number>(p.x, p.expF));
			}
			fxChart.getData().add(seriesx);
			XYChart.Series<Number, Number> seriest = new Series<Number, Number>();
			seriest.setName("Experimental Data");
			double t = 0.0;
			for (Point p : data.listOfPoint) {
				seriest.getData().add(new XYChart.Data<Number, Number>(t, p.expF));
				t = t + 0.01;
			}
			ftChart.getData().add(seriest);

			fxChart.setDisable(false);
			ftChart.setDisable(false);
			btnCalculate.setDisable(false);
			btnClear.setDisable(false);
			btnDraw.setDisable(false);
			vbTextBox.setDisable(false);
		} catch (Exception e) {
		}

	}

	public void clearCharts(ActionEvent event) {
		fxChart.getData().clear();
		ftChart.getData().clear();
		XYChart.Series<Number, Number> seriesx = new Series<Number, Number>();
		seriesx.setName("Experimental Data");
		for (Point p : data.listOfPoint) {
			seriesx.getData().add(new XYChart.Data<Number, Number>(p.x, p.expF));
		}
		fxChart.getData().add(seriesx);
		XYChart.Series<Number, Number> seriest = new Series<Number, Number>();
		seriest.setName("Experimental Data");
		double t = 0.0;
		for (Point p : data.listOfPoint) {
			seriest.getData().add(new XYChart.Data<Number, Number>(t, p.expF));
			t = t + 0.01;
		}
		ftChart.getData().add(seriest);

	}

	public void calculate(ActionEvent event) {
		for (int z = 1; z < 11; z++) {
			for (int w = 0; w < data.zadanie.length; w++) {
				data.prob.clear();
				data.def.clear();
				data.choice.clear();
				data.listOfParametrs.clear();
				GregorianCalendar cal = new GregorianCalendar();
				long t1 = cal.getTimeInMillis();
				int numberOfProb = data.zadanie[w][0];
				String choice = "Wybor" + numberOfProb + "_" + z + ".txt";
				String time = "Czas" + numberOfProb + "_" + z + ".txt";
				String deflection = "Deflection" + numberOfProb + "_" + z + ".txt";
				String parameter = "Parametry" + numberOfProb + "_" + z + ".txt";
				String scores = "Wynik" + numberOfProb + "_" + z + ".txt";
				fxChart.getYAxis().setAutoRanging(false);
				ftChart.getYAxis().setAutoRanging(false);
				pbCal.setDisable(false);
				pbCal.progressProperty().set(0);
				int sizeOfPopulation = data.zadanie[w][0];
				int numberOfPopulation = 10000;
				// data.crossChance = ;
				// data.mutateChance = ;
				if (txtBoxChanceToMutate.getText() != null) {
					try {
						data.mutateChance = Integer.parseInt(txtBoxChanceToMutate.getText());
					} catch (Exception e) {
					}
				}
				if (textBoxChanceToCross.getText() != null) {
					try {
						data.crossChance = Integer.parseInt(textBoxChanceToCross.getText());
					} catch (Exception e) {
					}
				}
				if (textBoxNumberOfPopulation.getText() != null) {
					try {
						numberOfPopulation = Integer.parseInt(textBoxNumberOfPopulation.getText());
					} catch (Exception e) {
					}
				}

				if (textBoxSizeOfPopulation.getText() != null) {
					try {
						sizeOfPopulation = Integer.parseInt(textBoxSizeOfPopulation.getText());
					} catch (Exception e) {
					}
				}

				for (int i = 0; i < sizeOfPopulation; i++) {
					data.setParameters();
				}
				if (true) { // liniowy
					int o = 0;
					double a = (double) 100 / sizeOfPopulation;
					for (int i = 0; i < sizeOfPopulation; i++) {
						double v = (double) o * a;
						data.prob.add(v);
						o++;
					}
				} else if (false) {// gaussa
					int sigma = sizeOfPopulation / 3;
					data.prob.add(0.0);
					double range = (double) 100 / sizeOfPopulation;
					for (int i = 0; i < sizeOfPopulation - 1; i++) {
						Double f = 1 / (sigma * Math.sqrt(Math.PI * 2))
								* Math.exp(-Math.pow(i - sizeOfPopulation, 2) / (2 * Math.pow(sigma, 2)));
						double aa = (double) f * range;
						data.prob.add(aa);
					}
				} else if (false) { // liniowy z prawdo
					data.prob.add(0.0);
					double a = (double) 100 / sizeOfPopulation;
					for (int i = 1; i < sizeOfPopulation; i++) {
						double v = (double) i * a + data.prob.get(i - 1);
						data.prob.add(v);
					}
				} else if (false) {// gaussa z prawdo
					int sigma = sizeOfPopulation / 3;
					data.prob.add(0.0);
					double range = (double) 100 / sizeOfPopulation;
					double abc = 0.0;
					for (int i = 1; i < sizeOfPopulation - 1; i++) {
						Double f = 1 / (sigma * Math.sqrt(Math.PI * 2))
								* Math.exp(-Math.pow(i - sizeOfPopulation, 2) / (2 * Math.pow(sigma, 2)));
						abc = (double) f * range + data.prob.get(i - 1);
						data.prob.add(abc);
					}
				}

				for (int j = 0; j < numberOfPopulation; j++) {
					for (int i = 0; i < sizeOfPopulation; i++) {
						data.calculate(i);
						System.out.println(j + "   " + i);
					}
					data.sortParameters();
					if (j != numberOfPopulation - 1) {
						data.evolveRanks(sizeOfPopulation);
					}
					data.def.add(data.listOfParametrs.get(0).deflection.toString());

				}
				data.calculate(0);

				XYChart.Series<Number, Number> seriesx = new Series<Number, Number>();
				seriesx.setName("Best result");
				for (Point p : data.listOfPoint) {
					seriesx.getData().add(new XYChart.Data<Number, Number>(p.x, p.F));
				}
				fxChart.getData().add(seriesx);
				XYChart.Series<Number, Number> seriest = new Series<Number, Number>();
				seriest.setName("Best result");
				double t = 0.0;
				for (Point p : data.listOfPoint) {
					seriest.getData().add(new XYChart.Data<Number, Number>(t, p.F));
					t = t + 0.01;
				}
				ftChart.getData().add(seriest);
				lvParameterList.getItems().clear();

				for (ParametrPoint p : data.listOfParametrs) {
					lvParameterList.getItems().add(p.toString());
				}
				System.out.println("zapis");
				GregorianCalendar cal2 = new GregorianCalendar();
				long t2 = cal2.getTimeInMillis();
				long tim = t2 - t1;
				try (BufferedWriter out = new BufferedWriter(new FileWriter(time))) {

					out.write(Long.toString(tim));

				} catch (

				IOException e) {
					e.printStackTrace();
				}
				data.save(data.def, deflection);
				data.save(data.listOfParametrs, parameter);
				data.save(data.listOfPoint, scores);
				data.save(data.choice, choice);
				data.save(data.prob, "c");
				Runnable sound1 = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
				sound1.run();
			}
		}

	}

	public void drawCharts(ActionEvent event) {
		try {
			ObservableList<Integer> index = lvParameterList.getSelectionModel().getSelectedIndices();
			for (Integer ind : index) {
				data.calculate(ind);
				XYChart.Series<Number, Number> seriesx = new Series<Number, Number>();
				seriesx.setName("Result" + ind);
				for (Point p : data.listOfPoint) {
					seriesx.getData().add(new XYChart.Data<Number, Number>(p.x, p.F));
				}
				fxChart.getData().add(seriesx);
				XYChart.Series<Number, Number> seriest = new Series<Number, Number>();
				seriest.setName("Result" + ind);
				double t = 0.0;
				for (Point p : data.listOfPoint) {
					seriest.getData().add(new XYChart.Data<Number, Number>(t, p.F));
					t = t + 0.01;
				}
				ftChart.getData().add(seriest);
			}
		} catch (Exception e) {
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lvParameterList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
}
