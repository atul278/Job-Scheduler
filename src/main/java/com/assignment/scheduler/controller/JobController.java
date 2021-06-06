package com.assignment.scheduler.controller;

import com.assignment.scheduler.repository.JobsRepository;
import com.assignment.scheduler.model.Jobs;
import com.assignment.scheduler.services.JobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    JobsRepository jobsRepository;

    @Autowired
    JobsService jobsService;

    // Creates A new Job with the payload
    @PostMapping("")
    public Jobs createJob(@RequestBody Map<String,String> requestBody) {
        return jobsService.createJob(requestBody);
    };

    // fetches all the Jobs config
    @GetMapping("")
    public List<Jobs> getAllJob() {
        return jobsService.getAllJob();
    }

    // starts a particular job with title passed in params
    @PutMapping("")
    public String startJob(@RequestParam String title) {
        return jobsService.startJob(title);
    }

}
