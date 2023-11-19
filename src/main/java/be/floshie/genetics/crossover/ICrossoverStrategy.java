package be.floshie.genetics.crossover;

import be.floshie.genetics.individuals.Individual;

public interface ICrossoverStrategy {
    Individual crossover(Individual individual1, Individual individual2);
}
