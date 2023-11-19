package be.floshie.genetics.fitness;

import be.floshie.genetics.individuals.Individual;

public interface IFitnessStrategy {
    int calculateFitness(Individual individual);

    int getMaximumFitness();
}
