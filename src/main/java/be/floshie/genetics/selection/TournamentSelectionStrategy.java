package be.floshie.genetics.selection;

import be.floshie.genetics.fitness.IFitnessStrategy;
import be.floshie.genetics.individuals.Individual;
import io.vavr.collection.List;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Builder
@Slf4j
public class TournamentSelectionStrategy implements ISelectionStrategy {
    private final IFitnessStrategy fitnessStrategy;
    private final int tournamentSize;

    @Override
    public Individual selectIndividual(final List<Individual> individuals) {
        log.trace("Tournament between {}", individuals);

        val successfulIndividual = individuals.shuffle()
                .subSequence(0, tournamentSize)
                .zip(individuals.map(fitnessStrategy::calculateFitness))
                .maxBy((t) -> t._2)
                .map((t) -> t._1)
                .get();

        log.trace("Tournament won by {}", successfulIndividual);
        return successfulIndividual;
    }
}
