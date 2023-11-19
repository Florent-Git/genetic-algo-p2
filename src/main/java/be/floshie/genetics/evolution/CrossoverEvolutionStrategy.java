package be.floshie.genetics.evolution;

import be.floshie.genetics.crossover.ICrossoverStrategy;
import be.floshie.genetics.individuals.Individual;
import be.floshie.genetics.selection.ISelectionStrategy;
import io.vavr.collection.List;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Builder
@RequiredArgsConstructor
public class CrossoverEvolutionStrategy implements IEvolutionStrategy {
    private final ICrossoverStrategy crossoverStrategy;
    private final ISelectionStrategy selectionStrategy;

    @Override
    public List<Individual> evolve(List<Individual> individuals) {
        val individual01 = selectionStrategy.selectIndividual(individuals);
        val individual02 = selectionStrategy.selectIndividual(individuals);

        return List.of(crossoverStrategy.crossover(individual01, individual02));
    }
}
