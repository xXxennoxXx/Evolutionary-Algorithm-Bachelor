package gui;

import algorithms.Algorithm;
import application.ParametrPoint;
import application.Point;
import application.Task;
import data.Data;
import data.InOut;
import data.Sample;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import utils.Timer;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

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

    private Task appCore;
    private Data data;

    public void open(ActionEvent event) {
        data = new Data(InOut.load(new FileChooser().showOpenDialog(null)));

        //Drawing force-displacement chart
        XYChart.Series<Number, Number> seriesx = new Series<Number, Number>();
        seriesx.setName("Experimental Data");
        for (Sample p : data.getSampleData())
            seriesx.getData().add(new XYChart.Data<Number, Number>(p.x, p.expF));
        fxChart.getData().add(seriesx);

        //Drawing force-time chart
        XYChart.Series<Number, Number> seriest = new Series<Number, Number>();
        seriest.setName("Experimental Data");
        double t = 0.0;
        for (Sample p : data.getSampleData()) {
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

    }

    public void clearCharts(ActionEvent event) {
        fxChart.getData().clear();
        ftChart.getData().clear();
        XYChart.Series<Number, Number> seriesx = new Series<Number, Number>();
        seriesx.setName("Experimental Data");
        for (Sample p : data.getSampleData()) {
            seriesx.getData().add(new XYChart.Data<Number, Number>(p.x, p.expF));
        }
        fxChart.getData().add(seriesx);
        XYChart.Series<Number, Number> seriest = new Series<Number, Number>();
        seriest.setName("Experimental Data");
        double t = 0.0;
        for (Sample p : data.getSampleData()) {
            seriest.getData().add(new XYChart.Data<Number, Number>(t, p.expF));
            t = t + 0.01;
        }
        ftChart.getData().add(seriest);

    }

    public void calculate(ActionEvent event) {

        //Measuring time starts
        Timer timer = new Timer();

        //Preparing GUI
        fxChart.getYAxis().setAutoRanging(false);
        ftChart.getYAxis().setAutoRanging(false);
        pbCal.setDisable(false);
        pbCal.progressProperty().set(0);

        //Algorithm parameters
        Integer sizeOfPopulation,
                numberOfPopulation,
                crossChance,
                mutateChance;

        //Reading parameters
        if (txtBoxChanceToMutate.getText() != null)
            mutateChance = Integer.parseInt(txtBoxChanceToMutate.getText());
        if (textBoxChanceToCross.getText() != null)
            crossChance = Integer.parseInt(textBoxChanceToCross.getText());
        if (textBoxNumberOfPopulation.getText() != null)
            numberOfPopulation = Integer.parseInt(textBoxNumberOfPopulation.getText());
        if (textBoxSizeOfPopulation.getText() != null)
            sizeOfPopulation = Integer.parseInt(textBoxSizeOfPopulation.getText());

        //Reading type of algorithm
        Algorithm algorithm = null;








        //Drawing best results
        XYChart.Series<Number, Number> seriesx = new Series<Number, Number>();
        seriesx.setName("Best result");
        for (
                Point p : data.listOfPoint)
            seriesx.getData().

                    add(new XYChart.Data<Number, Number>(p.x, p.F));
        fxChart.getData().

                add(seriesx);

        XYChart.Series<Number, Number> seriest = new Series<Number, Number>();
        seriest.setName("Best result");
        double t = 0.0;
        for (
                Point p : data.listOfPoint) {
            seriest.getData().add(new XYChart.Data<Number, Number>(t, p.F));
            t = t + 0.01;
        }
        ftChart.getData().

                add(seriest);


        // Printing parameters sets from last population
        lvParameterList.getItems().

                clear();
        for (
                ParametrPoint p : data.listOfParametrs)
            lvParameterList.getItems().

                    add(p.toString());

        //Calculations time
        System.out.println(timer.getTime());

//    Save some statistics
//        data.save(data.def, deflection);
//        data.save(data.listOfParametrs, parameter);
//        data.save(data.listOfPoint, scores);
//        data.save(data.choice, choice);
//        data.save(data.prob, "c");
//        Runnable sound1 = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
//        sound1.run();
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
