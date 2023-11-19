package be.floshie.genetics.crossover;

import be.floshie.genetics.individuals.Individual;
import io.vavr.collection.List;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.BitSet;
import java.util.concurrent.ThreadLocalRandom;

@Builder
@Slf4j
public class RandomCrossoverStrategy implements ICrossoverStrategy {

    private final float crossoverRate;

    @Override
    public Individual crossover(Individual individual1, Individual individual2) {
        log.trace("Crossover between " + individual1 + " and " + individual2);
        val geneSize = individual2.geneSize();

        val random = ThreadLocalRandom.current();
        val randomValues = List.fill(geneSize, random::nextFloat);

        val newBitSet = new BitSet(geneSize);

        randomValues.zipWithIndex()
                .forEach(t -> {
                    val threshold = t._1;
                    val index = t._2;

                    val value = threshold <= crossoverRate
                            ? individual1.gene().get(index)
                            : individual2.gene().get(index);

                    newBitSet.set(index, value);
                });

        val child = new Individual(newBitSet, geneSize);
        log.trace("Children is {}", child);

        return child;
    }
}
