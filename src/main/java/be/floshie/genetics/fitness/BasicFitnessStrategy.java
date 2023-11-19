package be.floshie.genetics.fitness;

import be.floshie.genetics.individuals.Individual;
import lombok.Builder;
import lombok.val;

import java.nio.charset.StandardCharsets;
import java.util.BitSet;

public class BasicFitnessStrategy implements IFitnessStrategy {
    private final BitSet solution;
    private final int solutionSize;

    @Builder
    public BasicFitnessStrategy(String solution) {
        this.solution = new BitSet(solution.length());
        this.solutionSize = solution.length();

        val reversedSolution = new StringBuilder(solution).reverse().toString();
        val byteSolution = reversedSolution.getBytes(StandardCharsets.US_ASCII);

        for (int i = 0; i < byteSolution.length; i++) {
            this.solution.set(i, byteSolution[i] == (byte) '1');
        }
    }

    @Override
    public int calculateFitness(Individual individual) {
        val copiedIndividualGene = (BitSet) individual.gene().clone();
        copiedIndividualGene.xor(solution);

        return individual.geneSize() - copiedIndividualGene.cardinality();
    }

    @Override
    public int getMaximumFitness() {
        return solutionSize;
    }
}
