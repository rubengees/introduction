package com.rubengees.introduction.exception;

/**
 * Todo: Describe Class
 *
 * @author Ruben Gees
 */
public class ConfigurationException extends RuntimeException {

    public ConfigurationException() {
        super("Don't call this method while constructing the IntroductionBuilder");
    }
}
