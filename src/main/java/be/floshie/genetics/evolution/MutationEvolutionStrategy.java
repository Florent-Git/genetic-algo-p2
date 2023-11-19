package be.floshie.genetics.evolution;

import be.floshie.genetics.individuals.Individual;
import be.floshie.genetics.mutation.IMutationStrategy;
import io.vavr.collection.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MutationEvolutionStrategy implements IEvolutionStrategy {
    private final IMutationStrategy mutationStrategy;

    @Override
    public List<Individual> evolve(List<Individual> individuals) {
        return individuals.map(mutationStrategy::mutate);
    }
}
