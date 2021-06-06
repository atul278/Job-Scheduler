package com.assignment.scheduler;


import com.assignment.scheduler.model.Jobs;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobsService {

    @Autowired
    SchedulerFactory schedulerFactory;


    @Autowired
    SpringBeanJobFactory springBeanJobFactory;

    @Autowired
    JobsRepository jobsRepository;

    public String startJob(String jobTitle){
        try {

            Optional<Jobs> jobs = jobsRepository.findByTitle(jobTitle);



            Scheduler sched = schedulerFactory.getScheduler();

            JobDetail job = JobBuilder.newJob(AviateJob.class)
                    .withIdentity(jobs.get().getTitle(), "group")
                    .usingJobData("title", jobs.get().getTitle())
                    .build();


            CronTrigger triggerNew = TriggerBuilder.newTrigger()
                    .withIdentity(jobs.get().getTitle(), "group1trigger")
                    .withSchedule(CronScheduleBuilder.cronSchedule(jobs.get().getCronExpression()))
                    .forJob(jobs.get().getTitle(), "group")
                    .build();


            sched.setJobFactory(springBeanJobFactory);
            sched.scheduleJob(job, triggerNew);
            sched.start();


        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        return " JOBS";
    }
}


