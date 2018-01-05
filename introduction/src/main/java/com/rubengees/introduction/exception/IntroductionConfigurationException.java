package com.rubengees.introduction.exception;

/**
 * An Exception, thrown when methods are called in the wrong order.
 *
 * @author Ruben Gees
 */
public class IntroductionConfigurationException extends RuntimeException {

    public IntroductionConfigurationException(String message) {
        super(message);
    }
}
