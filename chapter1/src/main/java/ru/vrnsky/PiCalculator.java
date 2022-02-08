package ru.vrnsky;

public class PiCalculator {

    public double calculate(int nTerms) {
        double numerator = 4.0;
        double denominator = 1.0;
        double operation = 1.0;
        double pi = 0.0;

        for (int index = 0; index < nTerms; index++) {
            pi += operation * (numerator / denominator);
            denominator += 2.0;
            operation *= -1.0;
        }

        return pi;
    }

    public static void main(String[] args) {
        System.out.println(new PiCalculator().calculate(1500));
    }
}
