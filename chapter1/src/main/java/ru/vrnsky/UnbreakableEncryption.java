package ru.vrnsky;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;

public class UnbreakableEncryption {

    public static class KeyPair {
        private final byte[] key1;
        private final byte[] key2;

        public KeyPair(byte[] key1, byte[] dummyData) {
            this.key1 = key1;
            this.key2 = dummyData;
        }

        public byte[] getKey1() {
            return key1;
        }

        public byte[] getKey2() {
            return key2;
        }
    }

    public byte[] getDummy(int length) {
        byte[] dummy = new byte[length];
        Random random = new Random();
        random.nextBytes(dummy);
        return dummy;
    }

    public KeyPair encrypt(String data) {
        byte[] encrypted = new byte[data.length()];
        byte[] secretData = data.getBytes(StandardCharsets.UTF_8);
        byte[] dummy = getDummy(data.length());
        for (int index = 0; index < encrypted.length; index++) {
            encrypted[index] = (byte) (secretData[index] ^ dummy[index]);
        }
        return new KeyPair(encrypted, dummy);
    }

    public String decrypt(KeyPair keyPair) {
        byte[] secretData = keyPair.getKey1();
        byte[] dummyData = keyPair.getKey2();
        byte[] decrypted = new byte[secretData.length];
        for (int index = 0; index < dummyData.length; index++) {
            decrypted[index] = (byte) (secretData[index] ^ dummyData[index]);
        }
        return new String(decrypted);
    }

    public static void main(String[] args) {
        UnbreakableEncryption unbreakableEncryption = new UnbreakableEncryption();
        KeyPair encrypted = unbreakableEncryption.encrypt("There is my implementation of encryption. It is really intresting");
        System.out.println("ENCRYPTED DATA");
        System.out.println(Arrays.toString(encrypted.getKey1()));
        System.out.println(Arrays.toString(encrypted.getKey2()));
        String decrypted = unbreakableEncryption.decrypt(encrypted);
        System.out.println("DECRYPTED DATA");
        System.out.println(decrypted);
    }
}
