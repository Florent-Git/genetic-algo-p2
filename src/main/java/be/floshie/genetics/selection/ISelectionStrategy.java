package be.floshie.genetics.selection;

import be.floshie.genetics.individuals.Individual;
import io.vavr.collection.List;

public interface ISelectionStrategy {
    Individual selectIndividual(List<Individual> individuals);
}
