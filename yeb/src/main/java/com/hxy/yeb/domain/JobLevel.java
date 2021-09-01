package com.hxy.yeb.domain;

import com.hxy.yeb.common.entity.BaseEntity;
import lombok.Data;

@Data
public class JobLevel extends BaseEntity {
    private Long id;
    private Boolean enable;
    private String name;
    private String titleLevel;
}
