package be.floshie.genetics.fitness;

import be.floshie.genetics.individuals.Individual;
import io.vavr.Function1;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import lombok.Builder;

@Builder
public class MemoizedFitnessStrategyDecorator implements IFitnessStrategy {
    private final Map<Individual, Integer> map = HashMap.empty();
    private final IFitnessStrategy fitnessStrategy;

    @Override
    public int calculateFitness(Individual individual) {
        return Function1.of(fitnessStrategy::calculateFitness)
                .memoized()
                .apply(individual);
    }

    @Override
    public int getMaximumFitness() {
        return fitnessStrategy.getMaximumFitness();
    }
}
