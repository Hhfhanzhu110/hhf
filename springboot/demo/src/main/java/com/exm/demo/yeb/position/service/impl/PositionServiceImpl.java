package com.exm.demo.yeb.position.service.impl;

import com.exm.demo.utils.UserContext;
import com.exm.demo.yeb.position.domain.Position;
import com.exm.demo.yeb.position.mapper.PositionMapper;
import com.exm.demo.yeb.position.service.PositionService;
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
        position.setUpdateBy(UserContext.getCurreentUser().getUsername());
        return positionMapper.putPosition(position);
    }

    @Override
    public int deletePositionsByIds(List<Integer> ids) {
        return positionMapper.deletePositionsByIds(ids);
    }
}
