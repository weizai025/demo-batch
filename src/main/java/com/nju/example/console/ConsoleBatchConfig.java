package com.nju.example.console;

import com.nju.example.console.listener.ConsoleJobEndListener;
import com.nju.listener.CommonStepListener;
import com.nju.step.ConsoleWriter;
import com.nju.step.ConvertProcessor;
import com.nju.step.StringReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * console batch任务配置
 * @author maw-b
 * @date 2021/1/31 22:56
 */
@Configuration
@EnableBatchProcessing
public class ConsoleBatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job consoleJob(Step consoleStep, JobExecutionListener consoleListener){
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return jobBuilderFactory.get(funcName)
                .listener(consoleListener)
                .flow(consoleStep)
                .end().build();
    }


    @Bean
    public Step consoleStep(ItemReader stringReader, ItemProcessor convertProcessor
            , ItemWriter consoleWriter, CommonStepListener commonStepListener){
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return stepBuilderFactory.get(funcName)
                .listener(commonStepListener)
                .<String,String>chunk(3)
                .reader(stringReader)
                .processor(convertProcessor)
                .writer(consoleWriter)
                .build();
    }

    @Bean
    public ItemReader stringReader(){
        return new StringReader();
    }

    @Bean
    public ItemWriter consoleWriter(){
        return new ConsoleWriter();
    }

    @Bean
    public ItemProcessor convertProcessor(){
        return new ConvertProcessor();
    }

    @Bean
    public JobExecutionListener consoleListener(){
        return new ConsoleJobEndListener();
    }
}
