package be.floshie;

import be.floshie.genetics.SimpleGeneticAlgorithm;
import be.floshie.genetics.fitness.BasicFitnessStrategy;
import be.floshie.genetics.fitness.MemoizedFitnessStrategyDecorator;
import lombok.val;

public class Main {
    public static void main(String[] args) {
        String solution = "1011000100000100010000100000100111001000000100000100000000001111";

        val fitnessStrategy = MemoizedFitnessStrategyDecorator.builder()
                .fitnessStrategy(new BasicFitnessStrategy(solution))
                .build();

        val ga = SimpleGeneticAlgorithm.builder()
                .fitnessStrategy(fitnessStrategy)
                .geneSize(solution.length())
                .build();

        ga.init(0.5f, 0.025f, 50, 5);
        ga.runAlgorithm();
    }
}