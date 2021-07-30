package com.exm.demo.yeb.position.domain;

import com.exm.demo.entity.BaseEntity;
import lombok.Data;

@Data
public class Position extends BaseEntity {
    private Long id;
    private Integer enable;
    private String name;
}
