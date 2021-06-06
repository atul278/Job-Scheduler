package com.assignment.scheduler;

import com.assignment.scheduler.model.SchedulerLog;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class AviateJob implements Job {

    @Autowired
    SchedulerLogRepository schedulerLogRepository;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String jobSays = dataMap.getString("title");


        SchedulerLog schedulerLog = new SchedulerLog();
        schedulerLog.setJobTitle(jobSays);
        schedulerLog.setStartTime(new Timestamp(System.currentTimeMillis()));
        schedulerLog.setStatus("WIP");
        SchedulerLog schedulerLogCreated = schedulerLogRepository.save(schedulerLog);

        System.out.println("Job says: >>>>>>>>>|" + jobSays + "|<<<<<current time ===> "+new Timestamp(System.currentTimeMillis()));

        schedulerLogCreated.setStatus("SUCCESS");
        schedulerLogCreated.setEndTime((new Timestamp(System.currentTimeMillis()+100000)));
        schedulerLogRepository.save(schedulerLogCreated);

    }
}
