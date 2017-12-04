package org.art.soft.lambdaexceptions;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class LambdaExceptions {

    public static void main(String[] args) {

        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 0);
        integerList.forEach(integer -> System.out.println("Integer : " + integer));
        integerList.forEach(arithmeticExceptionLambdaWrapper(integer -> System.out.println(50 / integer)));

        integerList.forEach(
                consumerExceptionHandler(integer -> System.out.println(50 / integer), ArithmeticException.class));
    }

    static Consumer<Integer> arithmeticExceptionLambdaWrapper(Consumer<Integer> consumer) {

        return integer -> {

            try {
                consumer.accept(integer);
            } catch (ArithmeticException arithmeticException) {
                System.err.println("Sorry guy, don't divide by zero !");
            }

        };

    }

    static <T, E extends Exception> Consumer<T> consumerExceptionHandler(Consumer<T> consumer, Class<E> clazz) {

        return i -> {

            try {
                consumer.accept(i);
            } catch (Exception exc) {
                try {
                    E exCast = clazz.cast(exc);
                    System.err.println("Exception occured : " + exCast.getMessage());
                } catch (ClassCastException exception) {
                    throw exception;
                }
            }
        };
    }

    static <T, E extends Exception> Consumer<T> handlingConsumerWrapper(ThrowingConsumer<T, E> throwingConsumer,
            Class<E> exceptionClass) {

        return i -> {

            try {
                throwingConsumer.accept(i);
            } catch (Exception e) {

                try {
                    E exCast = exceptionClass.cast(e);
                    System.err.println("Exception occured : " + exCast.getMessage());
                } catch (ClassCastException ccExc) {
                    throw new RuntimeException(ccExc);
                }
            }
        };
    }
}
