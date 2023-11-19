package be.floshie.genetics.population;

import be.floshie.genetics.evolution.IEvolutionStrategy;
import be.floshie.genetics.fitness.IFitnessStrategy;
import be.floshie.genetics.individuals.Individual;
import io.vavr.collection.List;
import lombok.Builder;
import lombok.Singular;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.Map;

@Slf4j
@Builder
public class Population {
    private final List<Individual> individuals;
    private final IFitnessStrategy fitnessStrategy;

    @Singular
    private final java.util.List<IEvolutionStrategy> evolutionStrategies;
    private final IEvolutionStrategy defaultEvolutionStrategy;

    public Individual findFittest() {
        val individual = individuals.toJavaStream().parallel()
                .map(v -> Map.entry(v, fitnessStrategy.calculateFitness(v)))
                .max(Map.Entry.comparingByValue())
                .orElseThrow(() -> new RuntimeException("There are no individuals in the population"));

        log.trace("Fittest individual {} with {} fitness", individual.getKey(), individual.getValue());
        return individual.getKey();
    }

    public int getFitness(Individual individual) {
        return fitnessStrategy.calculateFitness(individual);
    }

    public int getMaximumFitness() {
        return fitnessStrategy.getMaximumFitness();
    }

    public Population evolvePopulation() {
        List<Individual> newIndividuals = List.empty();

        for (val evolutionStrategy : evolutionStrategies) {
            val evolvedIndividuals = evolutionStrategy.evolve(individuals);
            newIndividuals = newIndividuals.appendAll(evolvedIndividuals);
        }

        val evolvedIndividuals = defaultEvolutionStrategy.evolve(
                    individuals.shuffle()
                            .subSequence(newIndividuals.size())
        );

        newIndividuals = newIndividuals.appendAll(evolvedIndividuals);

        return Population.builder()
                .fitnessStrategy(fitnessStrategy)
                .defaultEvolutionStrategy(defaultEvolutionStrategy)
                .evolutionStrategies(evolutionStrategies)
                .individuals(newIndividuals)
                .build();
    }
}
