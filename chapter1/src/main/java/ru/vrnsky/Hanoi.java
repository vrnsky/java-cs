package ru.vrnsky;

import java.util.Stack;

public class Hanoi {

    private final int numDisc;
    public final Stack<Integer> towerA = new Stack<>();
    public final Stack<Integer> towerB = new Stack<>();
    public final Stack<Integer> towerC = new Stack<>();

    public Hanoi(int num) {
        this.numDisc = num;
        for (int index = 1; index <= numDisc; index++) {
            towerA.push(index);
        }
    }

    private void move(Stack<Integer> begin, Stack<Integer> end, Stack<Integer> temp, int numDisc) {
        if (numDisc == 1) {
            end.push(begin.pop());
        } else {
            move (begin, temp, end, numDisc - 1);
            move(begin, end, temp, 1);
            move(temp, end, begin, numDisc - 1);
        }
    }

    public void solve() {
        this.move(towerA, towerC, towerB, numDisc);
    }

    public static void main(String[] args) {
        Hanoi hanoi = new Hanoi(3);
        hanoi.solve();

        System.out.println(hanoi.towerA);
        System.out.println(hanoi.towerB);
        System.out.println(hanoi.towerC);
    }
}
