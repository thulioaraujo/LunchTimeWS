package com.lunchtime.util;

/**
 * This class prints on console logs information about the application
 *
 * @author Thúlio Araújo (thuliolins@gmail.com)
 * @since 2/2/2017
 * @version 1.0
 */
public class Logger {
	
	public static final Boolean LOGGING = true;
	
    /**
     * Print INFO-level logging statement
     *
     * @param text Text to log
     */
    public static void info(String text) {
        if (LOGGING) {
            System.out.println("LunchTimeWS Info: " + text);
        }
    }

    /**
     * Print DEBUG-level logging statement
     *
     * @param text Text to log
     */
    public static void debug(String text) {
        if (LOGGING) {
        	System.out.println("LunchTimeWS Debug: " + text);
        }
    }

    /**
     * Print ERROR-level logging statement
     *
     * @param text Text to log
     */
    public static void error(String text) {
        if (LOGGING) {
        	System.out.println("LunchTimeWS Error: " + text);
        }
    }
}