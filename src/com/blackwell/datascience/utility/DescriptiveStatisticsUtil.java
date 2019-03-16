package com.blackwell.datascience.utility;

import com.blackwell.datascience.entity.DataContainer;
import com.blackwell.datascience.entity.DataPoint;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DescriptiveStatisticsUtil {

    private static final Logger LOG = Logger.getLogger(DescriptiveStatisticsUtil.class);


    /**
     * Find mode in array.
     * @param nums array
     * @return mode or modes
     */
    public static float[] findModes(float[] nums) {
        LOG.info("Get array: " + Arrays.toString(nums));

        DataContainer container = new DataContainer();
        List<Float> returnList = new ArrayList<>();

        // setting data to container
        for(float num : nums)
            container.add(new DataPoint<>(num));

        Collections.sort(container);
        LOG.debug("Sorted container: \n" + container);

        // find mode
        float maxCount = 0;
        for(DataPoint num : container){
            if (maxCount == 0)
                maxCount = num.getCount();

            if (maxCount == num.getCount())
                returnList.add(num.getNum().floatValue());
        }


        // convert mode list to array
        float[] arr = new float[returnList.size()];
        for(int i=0; i<arr.length; ++i)
            arr[i] = returnList.get(i);
        ArrayUtils.reverse(arr);

        LOG.info("mode: " + Arrays.toString(arr));
        return arr;
    }

}
