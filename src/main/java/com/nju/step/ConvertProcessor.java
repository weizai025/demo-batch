package com.nju.step;

import com.nju.common.LogConstants;
import com.nju.service.ConsoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author maw-b
 * @date 2021/1/31 22:48
 */
@Slf4j
public class ConvertProcessor implements ItemProcessor<String,String> { // i o

    @Autowired
    private ConsoleService consoleService;

    @Override
    public String process(String data) {
        String dataProcessed = consoleService.convert2UpperCase(data);
        log.debug(LogConstants.LOG_TAG + data +" process data --> " + dataProcessed);
        return dataProcessed;
    }
}
