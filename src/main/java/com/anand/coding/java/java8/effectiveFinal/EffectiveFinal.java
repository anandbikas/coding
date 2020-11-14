package com.anand.coding.java.java8.effectiveFinal;

/**
 *
 */
public class EffectiveFinal {

    public static void main(String... args) {
        String text = "effectivelyFinal";

        // Variable used in lambda expression should be final or effectively final
        // Modifying text = "something" will break effective final;
        new Thread(() -> System.out.println(text))
                .start();
    }
}
