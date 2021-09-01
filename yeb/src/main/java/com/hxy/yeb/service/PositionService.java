package com.hxy.yeb.service;

import com.hxy.yeb.domain.Position;

import java.util.List;

public interface PositionService {
    List<Position> getPositionList();

    String addPosition(Position position);

    int putPosition(Position position);

    int deletePositionsByIds(List<Integer> ids);
}
