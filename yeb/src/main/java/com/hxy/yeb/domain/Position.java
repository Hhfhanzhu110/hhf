package com.hxy.yeb.domain;

import com.hxy.yeb.common.entity.BaseEntity;
import lombok.Data;

@Data
public class Position extends BaseEntity {
    private Long id;
    private Integer enable;
    private String name;
}
