package com.exm.demo.yeb.jobLevel.domain;

import com.exm.demo.entity.BaseEntity;
import lombok.Data;

@Data
public class JobLevel extends BaseEntity {
    private Long id;
    private Boolean enable;
    private String name;
    private String titleLevel;
}
