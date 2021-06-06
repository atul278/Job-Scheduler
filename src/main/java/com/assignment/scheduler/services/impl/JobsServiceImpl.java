package com.assignment.scheduler.services.impl;

import com.assignment.scheduler.job.AviateJob;
import com.assignment.scheduler.repository.JobsRepository;
import com.assignment.scheduler.model.Jobs;
import com.assignment.scheduler.services.JobsService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class JobsServiceImpl implements JobsService {

    @Autowired
    SchedulerFactory schedulerFactory;

    @Autowired
    SpringBeanJobFactory springBeanJobFactory;

    @Autowired
    JobsRepository jobsRepository;


    // Creates A new Job with the payload
    @Override
    public Jobs createJob(Map<String,String> requestBody) {
        Jobs jobs= new Jobs();
        jobs.setTitle(requestBody.get("title"));
        jobs.setCronExpression(requestBody.get("cronExpression"));
        return jobsRepository.save(jobs);
    };

    // fetches all the Jobs config
    @Override
    public List<Jobs> getAllJob() {
        return jobsRepository.findAll();
    }

    // starts a particular job with title passed in params
    @Override
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

        return "Started";
    }





}


