package com.hxy.yeb.service.impl;

import com.hxy.yeb.domain.JobLevel;
import com.hxy.yeb.mapper.JobLevelMapper;
import com.hxy.yeb.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JobLevelServiceImpl implements JobLevelService {

    @Autowired
    JobLevelMapper jobLevelMapper;

    @Override
    public List<JobLevel> getJobLevelList() {
        return jobLevelMapper.getJobLevelList();

    }

    @Override
    public String addJobLevel(JobLevel jobLevel) {
        jobLevel.setUpdateTime(new Date());
        jobLevel.setCreateTime(new Date());
        //jobLevel.setUpdateBy(UserContext.getCurreentUser().getUsername());
        //jobLevel.setCreateBy(UserContext.getCurreentUser().getUsername());
        int n = jobLevelMapper.addJobLevel(jobLevel);
        if (n == 1) {
            return "ok";
        } else {
            return "error";
        }
    }

    @Override
    public int deleteJobLevelByIds(List<Integer> ids) {
        return jobLevelMapper.deleteJobLevelByIds(ids);
    }

    @Override
    public int putJobLevel(JobLevel jobLevel) {
        jobLevel.setUpdateTime(new Date());
        //jobLevel.setUpdateBy(UserContext.getCurreentUser().getUsername());
        return jobLevelMapper.putJobLevel(jobLevel);
    }

}
