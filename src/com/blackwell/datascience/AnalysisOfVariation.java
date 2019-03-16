package com.blackwell.datascience;

import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dmitryblackwell
 * Class for ananlys of variation.
 * For more details visit this page
 * https://math.semestr.ru/group/prim.php
 */
class AnalysisOfVariation {

    private static final Logger LOG = Logger.getLogger(AnalysisOfVariation.class);

    /** map significant level number*/
    private static final Map<Float, Float> FACTORIAL_UTTER = new HashMap<>();

    static {
        FACTORIAL_UTTER.put(0.01f, 5.95f);
        FACTORIAL_UTTER.put(0.05f, 3.49f);
        FACTORIAL_UTTER.put(0.1f, 2.61f);
    }

    /**
     * Returns the sum of columns.
     * @param data array
     * @param isSquares if true then numbers are squared
     * @return sum of each column in array
     */
    private float[] getSum(float[][] data, boolean isSquares){
        float[] squares = new float[data[0].length];
        for (float[] aData : data)
            for (int j = 0; j < aData.length; ++j)
                squares[j] += isSquares ? Math.pow(aData[j], 2) : aData[j];
        return squares;
    }

    /**
     * Returns average of each column.
     * @param data array
     * @return average for each column
     */
    private float[] getAverage(float[][] data){
        float[] average = new float[data[0].length];
        for (float[] aData : data)
            for (int j = 0; j < aData.length; ++j)
                average[j] += aData[j];

        for (int i=0; i<average.length; ++i)
            average[i] /= data.length;

        return average;
    }

    /**
     * Returning sum of all elements in arrays.
     * @param arr array
     * @param isSquares if true then numbers are squared
     * @return sum
     */
    private float getSumOfArray(float[] arr, boolean isSquares){
        float sum = 0;
        for (float anArr : arr)
            sum += isSquares ? Math.pow(anArr, 2) : anArr;
        return sum;
    }

    /**
     * Calls {@link #getSumOfArray(float[], boolean)} with false flag.
     * @param arr array
     * @return sum
     */
    private float getSumOfArray(float[] arr){
        return this.getSumOfArray(arr, false);
    }

    /**
     * Checks the null hypothesis of equality of group average.
     * @param data two-dimension array with data
     * @param a significant level
     * @return is group average equals
     */
    boolean isGroupAverageEquals(float[][] data, float a) {
        // getting average of each column
        float[] average = getAverage(data);
        LOG.info("Average: "+Arrays.toString(average));

        // getting sum of each column
        float[] sum = getSum(data, false);
        LOG.info("Sum: "+Arrays.toString(sum));

        // getting sum of squares of each column
        float[] sumOfSquares = getSum(data, true);
        LOG.info("Sum of squares: "+Arrays.toString(sumOfSquares));

        // getting total sum of squares
        float totalSumOfSquares = (float) (getSumOfArray(sumOfSquares) -
                (Math.pow(getSumOfArray(sum), 2)) / (data[0].length*data.length));
        LOG.info("Counting total sum of squares: "+ getSumOfArray(sumOfSquares) +
                " - (" + Math.pow(getSumOfArray(sum), 2) + " / " + (data[0].length*data.length) + ")");
        LOG.info("Total sum of squares: " + totalSumOfSquares);

        // getting factorial sum
        float factorialSum = (float) ((getSumOfArray(sum,true) / data.length) -
                (Math.pow(getSumOfArray(sum), 2)) / (data[0].length*data.length));
        LOG.info("Counting factorial sum: (" + getSumOfArray(sum,true) + " / " + data.length + ") - ("
                + Math.pow(getSumOfArray(sum), 2) + " / " + data[0].length*data.length + ")");
        LOG.info("Factorial sum: " + factorialSum);

        // getting sum residual
        float sumResidual = totalSumOfSquares - factorialSum;
        LOG.info("Counting sum residual: " + totalSumOfSquares + " - " + factorialSum);
        LOG.info("Sum residual: " + sumResidual);

        // getting residual dispersion
        float residualDispersion = sumResidual / ((data.length-1)*data[0].length);
        LOG.info("Counting residual dispersion: " + sumResidual + " / " + (data.length-1)*data[0].length);
        LOG.info("Residual dispersion: " + residualDispersion);

        // getting factorial dispersion
        float factorialDispersion = factorialSum / (data[0].length - 1);
        LOG.info("Counting factorial dispersion: " + factorialSum + " / " + (data[0].length-1));
        LOG.info("Factorial dispersion: " + factorialDispersion);

        // getting factorial observable
        float factorialObservable = factorialDispersion / residualDispersion;
        LOG.info("Factorial observable: " + factorialObservable);

        // getting result
        boolean result = factorialObservable < FACTORIAL_UTTER.get(a);
        LOG.info("Result: " + result);

        return result;
    }
}

