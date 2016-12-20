package com.mycompany.myapp.domain.util;

/**
 * Created by ibara on 12/14/2016.
 */
public class HelperClass {

    public static double format2Decimal(double input) {
        String[] inputAsArray = ("" + (input * 100)).split("[.]");
        return Double.parseDouble(inputAsArray[0]) / 100;
    }
}
