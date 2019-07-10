package com.example.batch;

import com.google.common.collect.Iterables;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfig extends DefaultBatchConfigurer {

    @Autowired
    private JobBuilderFactory jobBuilders;

    @Autowired
    private StepBuilderFactory stepBuilders;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private ApplicationArguments applicationArguments;

    @Scheduled(fixedRate = 5000)
    public void run() throws Exception {
        String message = Iterables.getFirst(Arrays.asList(applicationArguments.getSourceArgs()), null);

        JobExecution execution = jobLauncher.run(
                helloWorldJob(),
                new JobParametersBuilder().
                        addString("message", message).
                        addLong("uniqueness", System.nanoTime()).toJobParameters()
        );
    }

    @Bean
    public Job helloWorldJob() {
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