package gb.lesson1.sample;

import gb.lesson1.regular.Decorator;
import gb.lesson1.regular.MathOperation;

/**
 * Точка входа*/
public class Main {
    public static void main(String[] args) {
        System.out.println(Decorator.decorate(MathOperation.sum(5,5)));
        System.out.println(Decorator.decorate(MathOperation.diff(10,5)));
    }
}
