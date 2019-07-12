package com.example.batch;

import org.springframework.batch.core.*;
import org.springframework.lang.Nullable;

public class StepLoggingListener implements StepExecutionListener {

    public void afterJob(JobExecution jobExecution) {
        System.out.println(jobExecution.getJobInstance()
                .getJobName()
                + " has completed with the status " +
                jobExecution.getStatus() + " ===");
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("Step [" + stepExecution.getStepName()
                + "] is beginning execution ===");
    }

    @Nullable
    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("Step [" + stepExecution.getStepName()
                + "] is ending execution ===");

        return stepExecution.getExitStatus();
    }
}