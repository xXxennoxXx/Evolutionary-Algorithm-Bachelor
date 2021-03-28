package algorithms;

public enum AlgorithmType {
    TAB("Tabular"), RANK("Ranking"), RANK_WITH_PROB("Ranking With Probability");

    private String name;

    AlgorithmType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AlgorithmType: " + name;
    }
}
