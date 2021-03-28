package data;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private List<Sample> sampleData;

    private List<Integer> choice = new ArrayList<Integer>();
    private List<Double> probabilityDensity = new ArrayList<Double>();


    public List<Integer> getChoice() {
        return choice;
    }

    public List<String> getDeflection() {
        return deflection;
    }

    public void setDeflection(List<String> deflection) {
        this.deflection = deflection;
    }

    private List<String> deflection = new ArrayList<String>();


    public Data(List<Sample> sampleData) {
        this.sampleData = sampleData;
    }

    public List<Sample> getSampleData() {
        return sampleData;
    }
}
