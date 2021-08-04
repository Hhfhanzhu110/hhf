package com.exm.demo.yeb.position.mapper;

import com.exm.demo.yeb.position.domain.Position;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PositionMapper {

    @Select("select * from tb_position")
    public List<Position> getPositionList();

    @Insert("insert into tb_position(name,create_by,create_time,update_by,update_time) " +
            "values (#{name},#{createBy},#{createTime,jdbcType=TIMESTAMP},#{updateBy},#{updateTime,jdbcType=TIMESTAMP})")
    public int addPosition(Position position);

    @Update("update tb_position set name = #{name},update_time=#{updateTime} where id = #{id}")
    public int putPosition(Position position);

    int deletePositionsByIds(List<Integer> ids);
}
