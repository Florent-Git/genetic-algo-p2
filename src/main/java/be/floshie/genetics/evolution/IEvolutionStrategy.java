package be.floshie.genetics.evolution;

import be.floshie.genetics.individuals.Individual;
import io.vavr.collection.List;

public interface IEvolutionStrategy {
    List<Individual> evolve(List<Individual> individuals);
}
