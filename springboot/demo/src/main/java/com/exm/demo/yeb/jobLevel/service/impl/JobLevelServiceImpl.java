package com.exm.demo.yeb.jobLevel.service.impl;

import com.exm.demo.utils.UserContext;
import com.exm.demo.yeb.jobLevel.domain.JobLevel;
import com.exm.demo.yeb.jobLevel.mapper.JobLevelMapper;
import com.exm.demo.yeb.jobLevel.service.JobLevelService;
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
        jobLevel.setUpdateBy(UserContext.getCurreentUser().getLoginName());
        jobLevel.setCreateBy(UserContext.getCurreentUser().getLoginName());
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
        jobLevel.setUpdateBy(UserContext.getCurreentUser().getLoginName());
        return jobLevelMapper.putJobLevel(jobLevel);
    }

}
