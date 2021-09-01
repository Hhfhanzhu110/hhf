package com.hxy.yeb.service.impl;

import com.hxy.yeb.domain.Position;
import com.hxy.yeb.mapper.PositionMapper;
import com.hxy.yeb.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    PositionMapper positionMapper;

    @Override
    public List<Position> getPositionList() {
        return positionMapper.getPositionList();

    }

    @Override
    public String addPosition(Position position) {
        position.setUpdateTime(new Date());
        position.setCreateTime(new Date());
        int n = positionMapper.addPosition(position);
        if (n==1){
            return "ok";
        } else {
            return "error";
        }
    }

    @Override
    public int putPosition(Position position) {
        position.setUpdateTime(new Date());
        //position.setUpdateBy(UserContext.getCurreentUser().getUsername());
        return positionMapper.putPosition(position);
    }

    @Override
    public int deletePositionsByIds(List<Integer> ids) {
        return positionMapper.deletePositionsByIds(ids);
    }
}
