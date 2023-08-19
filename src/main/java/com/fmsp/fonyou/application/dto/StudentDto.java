package com.fmsp.fonyou.application.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentDto {
    private Long id;
    private String name;
    private Integer age;
    private String city;
    private String utc;
}
