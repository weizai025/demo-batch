package com.nju.listener;

import com.nju.common.LogConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 * @author maw-b
 * @date 2021/1/31 22:54
 */
@Slf4j
@Component
public class CommonStepListener extends StepExecutionListenerSupport {

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        int readCount = stepExecution.getReadCount();
        int filterCount = stepExecution.getFilterCount();
        int commitCount = stepExecution.getCommitCount();
        int writeCount = stepExecution.getWriteCount();
        log.info(LogConstants.LOG_TAG + " read:" + readCount + ", filter:" + filterCount + ",commit:"
                + commitCount + ",write:" + writeCount);

        return stepExecution.getExitStatus();
    }
}
