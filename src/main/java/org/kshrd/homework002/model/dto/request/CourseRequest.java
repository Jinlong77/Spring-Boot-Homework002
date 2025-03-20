package org.kshrd.homework002.model.dto.request;

import lombok.Data;

@Data
public class CourseRequest {

    private String courseName;
    private String description;
    private Integer instructorId;
}
