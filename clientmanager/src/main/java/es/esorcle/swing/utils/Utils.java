package es.esorcle.swing.utils;

import javax.swing.*;

public class Utils {

    /**
     * Método para comprobar si el texto introducido es un Integer.
     * @param string Texto a evaluar.
     * @return Si el valor introducido es un Integer o no.
     */
    public static boolean isInteger(String string){

        if(isEmptyString(string)) {
            return false;
        }
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }

    /**
     * Método para comprobar si el texto introducido es un Float.
     * @param string Texto a evaluar.
     * @return Si el valor introducido es un Float o no.
     */
    public static boolean isFloat(String string){

        if(isEmptyString(string)) {
            return false;
        }
        try {
            Float.parseFloat(string);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }

    /**
     * Método para comprobar si el texto introducido es un Double
     * @param string Texto a evaluar.
     * @return Si el valor introducido es un Float o no.
     */
    public static boolean isDouble(String string){

        if(isEmptyString(string)) {
            return false;
        }
        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }

    /**
     * Método para comprobar si el string introducido es nulo o vacío
     * @param string Texto a evaluar.
     * @return Si el valor introducido es un Float o no.
     */
    public static boolean isEmptyString (String string) {
        if ("".equals(string) || string == null) {
            return true;
        } else {
            return false;
        }
    }
}
