package com.nju.step;

import com.nju.common.LogConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * @author maw-b
 * @date 2021/1/31 22:51
 */
@Slf4j
public class ConsoleWriter implements ItemWriter<String> {

    @Override
    public void write(List<? extends String> list) {
        for (String msg :list) {
            log.debug(LogConstants.LOG_TAG + "write data: "+msg);
        }
    }
}
