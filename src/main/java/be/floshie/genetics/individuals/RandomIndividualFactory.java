package be.floshie.genetics.individuals;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.BitSet;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@RequiredArgsConstructor
public class RandomIndividualFactory implements IIndividualFactory {
    private final int geneSize;
    private final static ThreadLocalRandom rnd = ThreadLocalRandom.current();

    @Override
    public Individual createIndividual() {
        byte[] gene = new byte[(geneSize + 7) / 8];
        rnd.nextBytes(gene);

        val geneSet = BitSet.valueOf(gene);
        geneSet.clear(geneSize, geneSize + 7);

        val newIndividual = new Individual(geneSet, geneSize);
        log.trace("Creating new individual: " + newIndividual);
        return newIndividual;
    }
}
