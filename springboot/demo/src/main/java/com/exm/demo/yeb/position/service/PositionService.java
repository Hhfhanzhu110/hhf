package com.exm.demo.yeb.position.service;

import com.exm.demo.yeb.position.domain.Position;

import java.util.List;

public interface PositionService {
    List<Position> getPositionList();

    String addPosition(Position position);

    int putPosition(Position position);

    int deletePositionsByIds(List<Integer> ids);
}
