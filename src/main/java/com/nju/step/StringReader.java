package com.nju.step;

import com.nju.common.LogConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

/**
 * @author maw-b
 * @date 2021/1/31 22:42
 */
@Slf4j
public class StringReader implements ItemReader<String> {

    private String[] messages = {"aaa1","aaa2","aaa3","aaa4"};
    private int count = 0;

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(count < messages.length){
            String message = messages[count++];
            log.debug(LogConstants.LOG_TAG + "read data:"+message);
            return message;
        }else{
            log.debug(LogConstants.LOG_TAG + "read data end.");
            count = 0;
        }

        return null;
    }
}
