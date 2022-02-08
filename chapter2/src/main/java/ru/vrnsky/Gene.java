package ru.vrnsky;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Gene {

    public enum Nucleotide {
        A, C, G, T
    }

    public static class Codon implements Comparable<Codon> {
        private final Nucleotide first, second, third;
        private final Comparator<Codon> comparator = Comparator.comparing((Codon c) -> c.first)
                .thenComparing((Codon c) -> c.second)
                .thenComparing((Codon c) -> c.third);

        public Codon(String codon) {
            this.first = Nucleotide.valueOf(codon.substring(0, 1));
            this.second = Nucleotide.valueOf(codon.substring(1, 2));
            this.third = Nucleotide.valueOf(codon.substring(2, 3));
        }

        @Override
        public int compareTo(Codon o) {
            return comparator.compare(this, o);
        }
    }

    private List<Codon> codons = new ArrayList<>();

    public Gene(String gene) {
        for (int index = 0; index < gene.length() - 3; index += 3) {
            codons.add(new Codon(gene.substring(index, index + 3)));
        }
    }

    public static void main(String[] args) {
        final String geneStr = "ACGTGGCTCTCTCTAAGATATATAGAT";
        Gene gene = new Gene(geneStr);
        Codon acg = new Codon("ACG");
        Codon gat = new Codon("GAT");
        Optional<Codon> acgCodon = gene.codons.stream().filter(codon -> codon.compareTo(acg) == 0).findFirst();
        Optional<Codon> gatCodon = gene.codons.stream().filter(codon -> codon.compareTo(gat) == 0).findFirst();
        if (acgCodon.isPresent()) {
            System.out.println("ACG codon found");
        }
        if (gatCodon.isPresent()) {
            System.out.println("GAT codon found");
        }

    }
}
