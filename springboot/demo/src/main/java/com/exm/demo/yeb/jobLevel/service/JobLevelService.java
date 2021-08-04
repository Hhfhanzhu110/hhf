package com.exm.demo.yeb.jobLevel.service;

import com.exm.demo.yeb.jobLevel.domain.JobLevel;

import java.util.List;

public interface JobLevelService {
    String addJobLevel(JobLevel jobLevel);

    List<JobLevel> getJobLevelList();

    int deleteJobLevelByIds(List<Integer> id);

    int putJobLevel(JobLevel jobLevel);

}
