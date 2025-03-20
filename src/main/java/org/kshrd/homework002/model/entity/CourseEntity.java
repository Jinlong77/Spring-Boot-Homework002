package org.kshrd.homework002.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseEntity {

    private Integer courseId;
    private String courseName;
    private String description;
    private InstructorEntity instructor;
}
