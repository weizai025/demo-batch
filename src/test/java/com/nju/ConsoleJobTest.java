package com.nju;

import com.nju.common.SyncConstants;
import com.nju.example.console.ConsoleBatchConfig;
import com.nju.listener.CommonStepListener;
import com.nju.service.ConsoleService;
import com.nju.service.batch.JobLauncherService;
import com.nju.util.JobUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @author maw-b
 * @date 2021/1/31 23:04
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ConsoleBatchConfig.class
        ,JobLauncherService.class
        , ConsoleService.class
        , CommonStepListener.class})
@Slf4j
public class ConsoleJobTest {

    @Autowired
    private JobLauncherService jobLauncherService;

    @Autowired
    private Job consoleJob;

    @Test
    public void testConsoleJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters jobParameters = JobUtil.makeJobParameters();
        Map<String, Object> stringObjectMap = jobLauncherService.startJob(consoleJob, jobParameters);
        Assert.assertEquals(ExitStatus.COMPLETED,stringObjectMap.get(SyncConstants.STR_RETURN_EXITSTATUS));
    }
}
