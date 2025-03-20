package org.kshrd.homework002.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentEntity {

    private Integer studentId;
    private String studentName;
    private String email;
    private String phoneNumber;
    private List<CourseEntity> courses;
}
