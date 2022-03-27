package application.evolve.strategy.prob;

import java.util.function.Supplier;

public enum ProbsEnum {
    None(null),
    Liniowy(Liniowy::new),
    LiniowyZPrawdo(LiniowyZPrawdo::new),
    Gaussa(Gaussa::new),
    GaussaZPrawdo(GaussaZPrawdo::new);


    private final Supplier<Probs> supplier;

    ProbsEnum(Supplier<Probs> supplier) {
        this.supplier = supplier;
    }

    public Probs get() {
        return supplier.get();
    }
}
