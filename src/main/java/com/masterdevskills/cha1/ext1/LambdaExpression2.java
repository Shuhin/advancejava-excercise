package com.masterdevskills.cha1.ext1;

import static java.lang.System.currentTimeMillis;


/**
 * @author A N M Bazlur Rahman @bazlur_rahman
 * @since 04 August 2020
 */
public class LambdaExpression2 {

    /**
     * TODO Create a functional interface called Executable
     * Add a method called execute()
     * it doesn't take anything and returns void
     * use this functional interface as argument of the following method and log
     * the time it takes to execute the method
     */
    public void executionTime(Executable executable) {
        Executable x = () -> System.out.println("Execution Duration: " + currentTimeMillis());
        x.execute();
    }

    /* TODO: use the above of method here
     */
    public void run() {
        //executionTime();
        executionTime(() -> {
            System.out.println("Executing...");
        });
    }
}
@FunctionalInterface
    interface Executable {
        void execute();
}