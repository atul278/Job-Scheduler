package com.assignment.scheduler.controller;

import com.assignment.scheduler.AviateJob;
import com.assignment.scheduler.JobsRepository;
import com.assignment.scheduler.JobsService;
import com.assignment.scheduler.model.Jobs;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    JobsRepository jobsRepository;

    @Autowired
    JobsService jobsService;

    @PostMapping("")
    public Jobs createJob(@RequestBody Map<String,String> requestBody) {
        Jobs jobs= new Jobs();
        jobs.setTitle(requestBody.get("title"));
        jobs.setCronExpression(requestBody.get("cronExpression"));
        return jobsRepository.save(jobs);
    };

    @GetMapping("")
    public List<Jobs> getAllJob(@RequestBody Map<String,String> requestBody) {
        return jobsRepository.findAll();
    }


    @GetMapping("/start")
    public String startJob(@RequestParam String title) {
        return jobsService.startJob(title);
    }

}
