package gui;

import algorithms.Algorithm;
import algorithms.AlgorithmType;
import algorithms.genetic.types.TabGeneticAlgorithm;
import algorithms.genetic.types.ranking.ProbType;
import algorithms.genetic.types.ranking.RankingGeneticAlgorithm;
import algorithms.genetic.types.rankingwithprob.RankProbType;
import algorithms.genetic.types.rankingwithprob.RankingWithProbabilityGeneticAlgorithm;
import application.Task;
import data.Data;
import data.InOut;
import data.Sample;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
import models.Model;
import models.ModelType;
import utils.Timer;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private ListView<ModelType> lvModel;
    @FXML
    private ListView lvParameterList;
    @FXML
    private ListView lvAlgorithms;
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
    @FXML
    private ComboBox cbProbType;
    @FXML
    private VBox vbInput;


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
        vbInput.setDisable(true);

        //Preparing GUI
        fxChart.getYAxis().setAutoRanging(false);
        ftChart.getYAxis().setAutoRanging(false);
        pbCal.setDisable(false);
        pbCal.progressProperty().set(0);

        //Algorithm parameters
        Integer sizeOfPopulation = null,
                numberOfPopulation = null,
                crossChance = null,
                mutateChance = null;

        //Reading parameters
        if (txtBoxChanceToMutate.getText() != null && !txtBoxChanceToMutate.getText().isEmpty())
            mutateChance = Integer.parseInt(txtBoxChanceToMutate.getText());
        if (textBoxChanceToCross.getText() != null && !textBoxChanceToCross.getText().isEmpty())
            crossChance = Integer.parseInt(textBoxChanceToCross.getText());
        if (textBoxNumberOfPopulation.getText() != null && !textBoxNumberOfPopulation.getText().isEmpty())
            numberOfPopulation = Integer.parseInt(textBoxNumberOfPopulation.getText());
        if (textBoxSizeOfPopulation.getText() != null && !textBoxSizeOfPopulation.getText().isEmpty())
            sizeOfPopulation = Integer.parseInt(textBoxSizeOfPopulation.getText());

        //Reading type of algorithm

        Algorithm algorithm = null;

        Model model = lvModel.getSelectionModel().getSelectedItem().newModel();


        if (lvAlgorithms.getSelectionModel().getSelectedItem() == AlgorithmType.TAB)
            algorithm = new TabGeneticAlgorithm(numberOfPopulation,
                    sizeOfPopulation,
                    model);
        else if (lvAlgorithms.getSelectionModel().getSelectedItem() == AlgorithmType.RANK)
            algorithm = new RankingGeneticAlgorithm(numberOfPopulation,
                    sizeOfPopulation,
                    model,
                    (ProbType) cbProbType.getSelectionModel().getSelectedItem());
        else if (lvAlgorithms.getSelectionModel().getSelectedItem() == AlgorithmType.RANK_WITH_PROB)
            algorithm = new RankingWithProbabilityGeneticAlgorithm(numberOfPopulation,
                    sizeOfPopulation,
                    model,
                    (RankProbType) cbProbType.getSelectionModel().getSelectedItem(),
                    mutateChance,
                    crossChance);
        else
            ;
        algorithm.apply();


        //Drawing best results
        XYChart.Series<Number, Number> seriesx = new Series<Number, Number>();
        seriesx.setName("Best result");
        for (Sample p : data.getSampleData())
            seriesx.getData().add(new XYChart.Data<Number, Number>(p.x, p.F));
        fxChart.getData().add(seriesx);

        XYChart.Series<Number, Number> seriest = new Series<Number, Number>();
        seriest.setName("Best result");
        double t = 0.0;
        for (Sample p : data.getSampleData()) {
            seriest.getData().add(new XYChart.Data<Number, Number>(t, p.F));
            t = t + 0.01;
        }
        ftChart.getData().

                add(seriest);


        // Printing parameters sets from last population
        lvParameterList.getItems().clear();
//        for (ParametrPoint p : data.listOfParametrs) lvParameterList.getItems().add(p.toString());

        //Calculations time
        System.out.println(timer.getTime());
        vbInput.setDisable(true);

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
//        try {
//            ObservableList<Integer> index = lvParameterList.getSelectionModel().getSelectedIndices();
//            for (Integer ind : index) {
//                data.calculate(ind);
//                XYChart.Series<Number, Number> seriesx = new Series<Number, Number>();
//                seriesx.setName("Result" + ind);
//                for (Point p : data.listOfPoint) {
//                    seriesx.getData().add(new XYChart.Data<Number, Number>(p.x, p.F));
//                }
//                fxChart.getData().add(seriesx);
//                XYChart.Series<Number, Number> seriest = new Series<Number, Number>();
//                seriest.setName("Result" + ind);
//                double t = 0.0;
//                for (Point p : data.listOfPoint) {
//                    seriest.getData().add(new XYChart.Data<Number, Number>(t, p.F));
//                    t = t + 0.01;
//                }
//                ftChart.getData().add(seriest);
//            }
//        } catch (Exception e) {
//        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lvParameterList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        lvAlgorithms.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lvAlgorithms.setItems(FXCollections.observableArrayList(Arrays.asList(AlgorithmType.TAB, AlgorithmType.RANK, AlgorithmType.RANK_WITH_PROB)));
        lvAlgorithms.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AlgorithmType>() {
            @Override
            public void changed(ObservableValue<? extends AlgorithmType> observableValue, AlgorithmType algorithmType, AlgorithmType t1) {
                if (t1 == AlgorithmType.TAB)
                    cbProbType.setDisable(true);
                else {
                    cbProbType.setDisable(false);
                    if (t1 == AlgorithmType.RANK)
                        cbProbType.setItems(FXCollections.observableArrayList(RankProbType.LINEAR, RankProbType.GAUSS));
                    else
                        cbProbType.setItems(FXCollections.observableArrayList(ProbType.LINEAR, ProbType.GAUSS));
                    cbProbType.getSelectionModel().selectFirst();
                }
            }
        });

        lvModel.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lvModel.setItems(FXCollections.observableArrayList(ModelType.values()));
    }
}
