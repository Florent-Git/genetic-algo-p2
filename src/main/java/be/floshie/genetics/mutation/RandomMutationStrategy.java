package be.floshie.genetics.mutation;

import be.floshie.genetics.individuals.Individual;
import io.vavr.collection.List;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.BitSet;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Builder
@RequiredArgsConstructor
public class RandomMutationStrategy implements IMutationStrategy {
    private final float mutationRate;

    @Override
    public Individual mutate(Individual individual) {
        log.trace("Mutating {}", individual);
        var geneSize = individual.geneSize();

        var random = ThreadLocalRandom.current();
        var randomValues = List.fill(geneSize, random::nextFloat);

        var copiedBitset = (BitSet) individual.gene().clone();

        randomValues.zipWithIndex()
                .forEach(t -> {
                    val threshold = t._1;
                    val index = t._2;

                    if (threshold <= mutationRate) {
                        copiedBitset.flip(index);
                    }
                });

        return new Individual(copiedBitset, geneSize);
    }
}
