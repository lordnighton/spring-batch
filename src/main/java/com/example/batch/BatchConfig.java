package com.example.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig extends DefaultBatchConfigurer {

    @Override
    public void setDataSource(DataSource dataSource) {
        //This BatchConfigurer ignores ny DataSource
    }

    @Bean
    public Job helloWorldJob(JobBuilderFactory jobBuilders,
                             StepBuilderFactory stepBuilders) {
        return jobBuilders.get("helloWorldJob")
                .start(printHelloWorldStep(stepBuilders))
                .build();
    }

    @Bean
    public Step printHelloWorldStep(StepBuilderFactory stepBuilders) {
        return stepBuilders.get("helloWorldStep")
                .tasklet(helloTasklet()).build();
    }

    @Bean
    public HelloTasklet helloTasklet() {
        return new HelloTasklet();
    }

}