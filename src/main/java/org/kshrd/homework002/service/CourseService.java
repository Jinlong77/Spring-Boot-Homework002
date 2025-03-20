package org.kshrd.homework002.service;

import org.kshrd.homework002.model.dto.request.CourseRequest;
import org.kshrd.homework002.model.entity.CourseEntity;

import java.util.List;

public interface CourseService {

    List<CourseEntity> getAllCoursesByPagination(int page, int size);

    CourseEntity getCourseById(int id);

    CourseEntity createCourse(CourseRequest courseRequest);

    CourseEntity updateCourse(int id, CourseRequest courseRequest);

    void deleteCourse(int id);
}
