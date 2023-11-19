package be.floshie.genetics.individuals;

import java.util.BitSet;

public record Individual(
    BitSet gene,
    Integer geneSize
) { }
