package com.blackwell.datascience.utility;

import java.util.Arrays;

public class InputUtil {
    public static float[][] tableToArray(String dataStr, int rows, int cols) {
        float[] data = new float[rows*cols];
        String[] dataStrSplit = dataStr.split(" ");
        for(int i=0; i<data.length; ++i)
            data[i] = Float.parseFloat(dataStrSplit[i]);

        float[][] array = new float[rows][cols];
        for (int i = 0; i < cols; ++i) {
            for (int j = 0; j < rows; ++j)
                array[j][i] = data[i * rows + j];
        }
        return array;
    }
    public static String tableToArrayString(String dataStr, int rows, int cols){
        float[][] array = tableToArray(dataStr, rows, cols);
        String result = Arrays.deepToString(array);
        result = result.replace('[', '{');
        result = result.replace(']', '}');
        result = result.replace(", ", "f, ");
        result = result.replace("}f, ", "f}, ");
        result = result.replace("}}", "f}}");
        return result;
    }
}
