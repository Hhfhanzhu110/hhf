package com.hxy.yeb.service;


import com.hxy.yeb.domain.JobLevel;

import java.util.List;

public interface JobLevelService {
    String addJobLevel(JobLevel jobLevel);

    List<JobLevel> getJobLevelList();

    int deleteJobLevelByIds(List<Integer> id);

    int putJobLevel(JobLevel jobLevel);

}
