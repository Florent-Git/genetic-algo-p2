package be.floshie.genetics.evolution;

import be.floshie.genetics.fitness.IFitnessStrategy;
import be.floshie.genetics.individuals.Individual;
import io.vavr.collection.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ElitismEvolutionStrategy implements IEvolutionStrategy {
    private final IFitnessStrategy fitnessStrategy;

    @Override
    public List<Individual> evolve(List<Individual> individuals) {
        return individuals.zip(individuals.map(fitnessStrategy::calculateFitness))
                .maxBy(t -> t._2)
                .map(t -> t._1)
                .toList();
    }
}
