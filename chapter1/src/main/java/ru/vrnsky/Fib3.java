package ru.vrnsky;

public class Fib3 {

    public static void main(String[] args) {
        System.out.println(fib3(20));
        System.out.println(fib3(40));
    }

    public static int fib3(int n) {
        int last = 0;
        int next = 1;
        for (int i = 0; i < n; i++) {
            int oldLast = last;
            last = next;
            next = oldLast + next;
        }
        return last;
    }
}
