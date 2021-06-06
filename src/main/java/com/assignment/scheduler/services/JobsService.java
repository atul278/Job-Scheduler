package com.assignment.scheduler.services;

import com.assignment.scheduler.model.Jobs;

import java.util.List;
import java.util.Map;

public interface JobsService {
    public Jobs createJob(Map<String,String> requestBody);
    public List<Jobs> getAllJob();
    public String startJob(String jobTitle);
}
