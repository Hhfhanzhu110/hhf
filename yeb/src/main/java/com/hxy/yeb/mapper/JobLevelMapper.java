package com.hxy.yeb.mapper;

import com.hxy.yeb.domain.JobLevel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface JobLevelMapper {
    @Select("select * from tb_job_level")
    List<JobLevel> getJobLevelList();

    @Insert("insert into tb_job_level(name,create_by,create_time,update_by,update_time,title_level) " +
            "values (#{name},#{createBy},#{createTime,jdbcType=TIMESTAMP},#{updateBy},#{updateTime,jdbcType=TIMESTAMP}," +
            "#{titleLevel})")
    int addJobLevel(JobLevel jobLevel);

    int deleteJobLevelByIds(List<Integer> ids);

    @Update("update tb_job_level set name = #{name},update_time=#{updateTime},title_level=#{titleLevel},enable=#{enable} where id = #{id}")
    int putJobLevel(JobLevel jobLevel);

}
