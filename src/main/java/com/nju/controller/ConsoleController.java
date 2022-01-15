package com.nju.controller;

import com.nju.common.SyncConstants;
import com.nju.dto.ResponseResult;
import com.nju.service.batch.JobLauncherService;
import com.nju.util.JobUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author maw-b
 * @date 2021/2/1 14:49
 */
@RestController
@RequestMapping("/console")
public class ConsoleController {

    @Autowired
    private JobLauncherService jobLauncherService;

    @Autowired
    private Job consoleJob;

    @ApiOperation(value = "运行任务", tags = "console", notes = "使用spring batch运行任务")
    @GetMapping("/run_job1")
    public ResponseResult<String> runJob1() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters jobParameters = JobUtil.makeJobParameters();
        Map<String, Object> stringObjectMap = jobLauncherService.startJob(consoleJob, jobParameters);
        Object resultStr = stringObjectMap.get(SyncConstants.STR_RETURN_RESULT);
        return ResponseResult.ok(resultStr);
    }
}
