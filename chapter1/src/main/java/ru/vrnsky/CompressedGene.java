package ru.vrnsky;

import java.util.BitSet;

public class CompressedGene {

    private int length;
    private BitSet bitSet;

    public CompressedGene(String gene) {
        compress(gene);
    }

    private void compress(String gene) {
        length = gene.length();
        bitSet = new BitSet(length * 2);
        for (int index = 0; index < length; index++) {
            final int firstPosition = index * 2;
            final int secondPosition = index * 2 + 1;
            switch (gene.charAt(index)) {
                case 'A':
                    bitSet.set(firstPosition, false);
                    bitSet.set(secondPosition, false);
                    break;
                case 'C':
                    bitSet.set(firstPosition, false);
                    bitSet.set(secondPosition, true);
                    break;
                case 'G':
                    bitSet.set(firstPosition, true);
                    bitSet.set(secondPosition, false);
                    break;
                case 'T':
                    bitSet.set(firstPosition, true);
                    bitSet.set(secondPosition, true);
                    break;
            }
        }
    }

    public String decompress() {
        if (bitSet == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int index = 0; index < length * 2; index += 2) {
            int firstByte = (bitSet.get(index) ? 1 : 0);
            int secondByte = (bitSet.get(index + 1) ? 1 : 0);
            int lastBits = firstByte << 1 | secondByte;
            switch (lastBits) {
                case 0b00:
                    stringBuilder.append("A");
                    break;
                case 0b01:
                    stringBuilder.append("C");
                    break;
                case 0b10:
                    stringBuilder.append("G");
                    break;
                case 0b11:
                    stringBuilder.append("T");
                    break;
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String original = "ACGT";
        CompressedGene compressedGene = new CompressedGene(original);
        System.out.println(compressedGene.decompress());
        System.out.println(original.equalsIgnoreCase(compressedGene.decompress()));
    }
}
