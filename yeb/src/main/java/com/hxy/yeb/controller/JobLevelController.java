package com.hxy.yeb.controller;

import com.hxy.yeb.common.entity.AxiosResult;
import com.hxy.yeb.domain.JobLevel;
import com.hxy.yeb.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class JobLevelController {

    @Autowired
    JobLevelService jobLevelService;

    @RequestMapping("/jobLevels/getJobLevelList")
    @ResponseBody
    public AxiosResult getJobLevelList(){
        List<JobLevel> JobLevels = jobLevelService.getJobLevelList();
        return AxiosResult.success(JobLevels);
    }

    @RequestMapping("/jobLevels/addJobLevel")
    @ResponseBody
    public AxiosResult addJobLevel(JobLevel jobLevel){
        String msg = jobLevelService.addJobLevel(jobLevel);
        if (msg.equals("ok")){
            msg = "添加成功";
        } else {
            msg = "添加失败";
        }
        return AxiosResult.success(msg);
    }

    @RequestMapping(value = {"/jobLevels/{ids}"},method = {RequestMethod.DELETE})
    @ResponseBody
    public AxiosResult deleteJobLevelByIds(@PathVariable("ids") List<Integer> ids){
        try {
            int n = jobLevelService.deleteJobLevelByIds(ids);
            return AxiosResult.success();
        } catch (Exception e){
            e.printStackTrace();
            return AxiosResult.error();
        }
    }

    @RequestMapping(value = {"/jobLevels/put"})
    @ResponseBody
    public AxiosResult putJobLevel(JobLevel jobLevel){
        try {
            int n = jobLevelService.putJobLevel(jobLevel);
            return AxiosResult.success();
        } catch (Exception e){
            return AxiosResult.error();
        }
    }

}
