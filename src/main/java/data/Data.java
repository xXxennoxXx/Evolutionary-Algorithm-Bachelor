package data;

import java.util.Date;
import java.util.List;

public class Data {
    private List<Sample> sampleData;

    public Data(List<Sample> sampleData) {
        this.sampleData = sampleData;
    }

    public List<Sample> getSampleData() {
        return sampleData;
    }
}
