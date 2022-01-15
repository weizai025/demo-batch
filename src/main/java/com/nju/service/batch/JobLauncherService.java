package com.nju.service.batch;

import cn.hutool.core.collection.CollUtil;
import com.nju.common.LogConstants;
import com.nju.common.SyncConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.Collection;
import java.util.Map;

/**
 * @author maw-b
 * @date 2021/1/31 23:19
 */
@Slf4j
@Service
public class JobLauncherService {

    @Autowired
    private JobLauncher jobLauncher;

    /**
     * 启动spring batch任务
     * @param job
     * @param jobParameters
     * @return
     * @throws JobParametersInvalidException
     * @throws JobExecutionAlreadyRunningException
     * @throws JobRestartException
     * @throws JobInstanceAlreadyCompleteException
     */
    public Map<String,Object> startJob(Job job, JobParameters jobParameters) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        Map<String,Object> resultMap = CollUtil.newHashMap();
        //计时
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(job.getName());
        //执行任务
        JobExecution run = jobLauncher.run(job, jobParameters);
        //返回结果
        StringBuffer stringBuffer = new StringBuffer();
        Collection<StepExecution> stepExecutions = run.getStepExecutions();
        stepExecutions.forEach(stepExecution -> {
            stringBuffer.append("readCount:" + stepExecution.getCommitCount() + LogConstants.LOG_STR_COMMA);
            stringBuffer.append("filterCount:" + stepExecution.getFilterCount() + LogConstants.LOG_STR_COMMA);
            stringBuffer.append("commitCount:" + stepExecution.getCommitCount() + LogConstants.LOG_STR_COMMA);
            stringBuffer.append("writeCount:" + stepExecution.getWriteCount());
        });
        stopWatch.stop();
        ExitStatus exitStatus = run.getExitStatus();
        String returnStr = System.lineSeparator() +"resultCount: "+ stringBuffer.toString()
                + System.lineSeparator() +"exitStatus: "+exitStatus
                + System.lineSeparator()+ "timeInfo: "+stopWatch.prettyPrint();
        log.info(returnStr);
        resultMap.put(SyncConstants.STR_RETURN_RESULT,returnStr);
        resultMap.put(SyncConstants.STR_RETURN_EXITSTATUS,exitStatus);
        return resultMap;
    }
}
