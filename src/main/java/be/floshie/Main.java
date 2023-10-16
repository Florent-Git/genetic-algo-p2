package be.floshie;

import be.floshie.ga.baeldung.SimpleGeneticAlgorithm;

public class Main {
    public static void main(String[] args) {
        var ga = new SimpleGeneticAlgorithm();
        ga.runAlgorithm(50, "1011000100000100010000100000100111001000000100000100000000001111");
    }
}