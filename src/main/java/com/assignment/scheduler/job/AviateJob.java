package com.assignment.scheduler.job;

import com.assignment.scheduler.repository.SchedulerLogRepository;
import com.assignment.scheduler.model.SchedulerLog;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

// Main Jobs functionality class
@Component
public class AviateJob implements Job {

    @Autowired
    SchedulerLogRepository schedulerLogRepository;

    // timeout fot particular thread is configure from configuration
    @Value("${jobs.timeout}")
    Long jobsTimeout;

    long startTime;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        // Get the Job Title and log the start of of Job
        startTime= System.currentTimeMillis();
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String jobSays = dataMap.getString("title");
        SchedulerLog schedulerLog = new SchedulerLog();
        schedulerLog.setJobTitle(jobSays);
        schedulerLog.setStartTime(new Timestamp(System.currentTimeMillis()));
        schedulerLog.setStatus("WIP");
        SchedulerLog schedulerLogCreated = schedulerLogRepository.save(schedulerLog);

        /*
        *       ADD Your Job Functionality in thi block with condition chekcing the timeout shoud not be more than the current timeout
        *
        *
        * */
        for (int i=0;i<10;i++){
            System.out.println("Job says: >>>>>>>>>|" + jobSays + "|<<<<<current time ===> "+new Timestamp(System.currentTimeMillis()));

            // Break if it exceeds time out
            if ((System.currentTimeMillis()-startTime)>jobsTimeout){
                break;
            }

        }
        // JOB functionality ends here LOG Success  and time for JoB
        schedulerLogCreated.setStatus("SUCCESS");
        schedulerLogCreated.setEndTime((new Timestamp(System.currentTimeMillis()+100000)));
        schedulerLogRepository.save(schedulerLogCreated);

    }
}
