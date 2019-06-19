package com.blackwell;

import com.blackwell.entity.DataContainer;
import com.blackwell.entity.DataPoint;
import com.blackwell.utility.DescriptiveStatisticsUtil;
import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

import java.util.Arrays;

/**
 * @author dmitryblackwell
 * Class with probability theory utils.
 * For mor details visit
 * http://yukhym.com/uk/vipadkovi-velichini/chislovi-kharakteristiki-statistichnogo-rozpodilu.html
 */
public class ProbabilityTheoryUtils {

    /**
     * Method for finding average number in array.
     * Example:
     * in:  [1, 2, 5, 9, 3, 2, 2]
     * out: 3.42
     * @param nums array of number
     * @return average
     */
    float average(float[] nums){
        float sum = 0;
        float length = nums.length;
        for(float num : nums)
            sum += num;

        return sum/length;
    }

    /**
     * Method for getting dispersion.
     * Using this formula (n1*x1^2 + n2*x2^2 + ... + nn*xn^2)/n - avr(sum(n))
     * Example:
     * in:  [1, 2, 5, 9, 3, 2, 2]
     * out: 6.53
     * @param nums array of nums
     * @return dispersion
     */
    float dispersion(float[] nums){
        DataContainer container = new DataContainer();

        for(float num : nums)
            container.add(new DataPoint<>(num));

        float sum = 0;
        float length = nums.length;
        for(DataPoint point : container)
            sum += Math.pow(point.getNum().floatValue(), 2) * point.getCount();

        return sum/length - (float) Math.pow(average(nums), 2);
    }

    /**
     * Find square deviation.
     * Using formula: sqrt(dispersion)
     * Example:
     * in:  [1, 2, 5, 9, 3, 2, 2]
     * out: 2.55
     * @param nums array for deviation
     * @return deviation
     */
    float squareDeviation(float[] nums){
       return (float) Math.sqrt(dispersion(nums));
    }

    /**
     * Method for finding median.
     * Median means that half of sort elements will be less than it and half will be more.
     * So we use next formula: if n is even (x[n/2] + x[n/2+2])/2 else x[(n+1)/2]
     * Example:
     * in:  [1, 2, 5, 9, 3, 2, 2]
     * out: 2
     * @param nums array for median
     * @return median
     */
    float median(float[] nums){
        final int n = nums.length;
        float[] copy = Arrays.copyOf(nums, n);
        Arrays.sort(copy);

        return n%2 == 0 ?
                (copy[n/2 - 1] + copy[n/2])/2 :
                copy[(n+1)/2 - 1];
    }

    /**
     * Method for finding mode.
     * Example:
     * in:  [1, 2, 2, 3, 4, 5]
     * out: 2
     * @param nums array in which we are counting mode
     * @return mode
     */
    float[] mode(float[] nums){
        return DescriptiveStatisticsUtil.findModes(nums);
    }

    /**
     * Biggest number in array.
     * @param nums array
     * @return max
     */
    float max(float[] nums){
        int n = nums.length;
        float[] copy = Arrays.copyOf(nums, n);
        Arrays.sort(copy);
        return copy[n-1];
    }

    /**
     * Smallest number in array.
     * @param nums array
     * @return min
     */
    float min(float[] nums){
        float[] copy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copy);
        return copy[0];
    }

    /**
     * Find difference between max and min numbers.
     * @param nums array
     * @return scope
     */
    float scope(float[] nums){
        return max(nums)-min(nums);
    }

    /**
     * Sample divided into equal-sized subgroups and returning element in q*10 subgroup.
     * For example if we want to find quantile of 0.25 or 25% we divided group on 4 subgroups
     * and returning first element of the second one. Or we can just sort array and take arr[q*(n+1)] element.
     * Example:
     * in:  [1, 2, 5, 9, 3, 2, 2], 0.5
     * out: 2
     * @param nums array
     * @param q quantile - must be bigger then 0 and less than 1
     * @return quantile
     * @throws WrongNumberArgsException if quantile is not correct
     */
    float quantile(float[] nums, float q) throws WrongNumberArgsException {
        if (q < 0 || q > 1)
            throw new WrongNumberArgsException
                    ("q can not be less than 0 and more than 1");
        int n = nums.length;
        float[] copy = Arrays.copyOf(nums, n);
        Arrays.sort(copy);
        return copy[(int) (q*(n+1)) - 1];
    }
}
