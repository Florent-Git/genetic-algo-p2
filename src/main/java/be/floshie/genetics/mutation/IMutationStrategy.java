package be.floshie.genetics.mutation;

import be.floshie.genetics.individuals.Individual;

public interface IMutationStrategy {
    Individual mutate(Individual individual);
}
