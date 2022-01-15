package com.nju.util;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

/**
 * @author maw-b
 * @date 2021/1/31 23:18
 */
public class JobUtil {


    /**
     * 以当前时间作为参数，构建JobParameters
     * @return
     */
    public static JobParameters makeJobParameters() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time",System.currentTimeMillis())
                .toJobParameters();
        return jobParameters;
    }

}
