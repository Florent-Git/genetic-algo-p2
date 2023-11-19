package be.floshie.genetics;

import be.floshie.genetics.crossover.RandomCrossoverStrategy;
import be.floshie.genetics.evolution.CrossoverEvolutionStrategy;
import be.floshie.genetics.evolution.ElitismEvolutionStrategy;
import be.floshie.genetics.evolution.MutationEvolutionStrategy;
import be.floshie.genetics.fitness.IFitnessStrategy;
import be.floshie.genetics.individuals.Individual;
import be.floshie.genetics.individuals.RandomIndividualFactory;
import be.floshie.genetics.mutation.RandomMutationStrategy;
import be.floshie.genetics.population.Population;
import be.floshie.genetics.selection.TournamentSelectionStrategy;
import io.vavr.collection.List;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Builder
@Slf4j
public class SimpleGeneticAlgorithm {
    private final static int SOLUTION_LENGTH = 64;

    private final int geneSize;

    private Population population;
    private IFitnessStrategy fitnessStrategy;

    public void init(
            float crossoverRate,
            float mutationRate,
            int populationSize,
            int tournamentSize
    ) {
        val individualFactory = new RandomIndividualFactory(SOLUTION_LENGTH);

        val crossoverStrategy = RandomCrossoverStrategy.builder()
                .crossoverRate(crossoverRate)
                .build();

        val mutationStrategy = RandomMutationStrategy.builder()
                .mutationRate(mutationRate)
                .build();

        val individuals = List.fill(populationSize, individualFactory::createIndividual);

        val selectionStrategy = TournamentSelectionStrategy.builder()
                .fitnessStrategy(fitnessStrategy)
                .tournamentSize(tournamentSize)
                .build();

        val elitismEvolutionStrategy = new ElitismEvolutionStrategy(fitnessStrategy);
        val crossoverEvolutionStrategy = new CrossoverEvolutionStrategy(crossoverStrategy, selectionStrategy);
        val mutationEvolutionStrategy = new MutationEvolutionStrategy(mutationStrategy);

        population = Population.builder()
                .fitnessStrategy(fitnessStrategy)
                .individuals(individuals)
                .evolutionStrategy(elitismEvolutionStrategy)
                .evolutionStrategy(crossoverEvolutionStrategy)
                .defaultEvolutionStrategy(mutationEvolutionStrategy)
                .build();
    }

    public void runAlgorithm() {
        var generationCount = 1;

        Individual fittestIndividual;
        do {
            population = population.evolvePopulation();
            fittestIndividual = population.findFittest();
            log.debug("Generation: {}, Fittest individual: {} with {} fitness", generationCount++, fittestIndividual, population.getFitness(fittestIndividual));
        } while (population.getFitness(fittestIndividual) < population.getMaximumFitness());

        log.info("Solution found with {} generations: {}", generationCount, fittestIndividual);
    }
}