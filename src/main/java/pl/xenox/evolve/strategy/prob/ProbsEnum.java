package pl.xenox.evolve.strategy.prob;

import java.util.function.Supplier;

public enum ProbsEnum {
    NONE(null),
    LINIOWY(Liniowy::new),
    LINIOWY_Z_PRAWDO(LiniowyZPrawdo::new),
    GAUSSA(Gaussa::new),
    GAUSSA_Z_PRAWDO(GaussaZPrawdo::new);

    private final Supplier<Probs> supplier;

    ProbsEnum(Supplier<Probs> supplier) {
        this.supplier = supplier;
    }

    public Probs get() {
        return supplier.get();
    }
}
