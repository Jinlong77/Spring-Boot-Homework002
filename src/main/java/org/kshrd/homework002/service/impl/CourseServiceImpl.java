package org.kshrd.homework002.service.impl;

import lombok.RequiredArgsConstructor;
import org.kshrd.homework002.exception.ApiException;
import org.kshrd.homework002.exception.ResourceNotFoundException;
import org.kshrd.homework002.model.dto.request.CourseRequest;
import org.kshrd.homework002.model.entity.CourseEntity;
import org.kshrd.homework002.repository.CourseRepository;
import org.kshrd.homework002.service.CourseService;
import org.kshrd.homework002.service.InstructorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final InstructorService instructorService;

    @Override
    public List<CourseEntity> getAllCoursesByPagination(int page, int size) {
        return courseRepository.findAllByPagination(page, size);
    }

    @Override
    public CourseEntity getCourseById(int id) {
        var course = courseRepository.findById(id);
        if (course == null) throw new ResourceNotFoundException("Course with ID " + id + " not found.");
        return course;
    }

    @Override
    public CourseEntity createCourse(CourseRequest courseRequest) {
        instructorService.getInstructorById(courseRequest.getInstructorId());
        var course = courseRepository.save(courseRequest);
        if (course == null) throw new ApiException("Course cannot be created.");
        return course;
    }

    @Override
    public CourseEntity updateCourse(int id, CourseRequest courseRequest) {
        instructorService.getInstructorById(courseRequest.getInstructorId());
        getCourseById(id);
        var course = courseRepository.update(id, courseRequest);
        if (course == null) throw new ApiException("Course cannot be updated.");
        return course;
    }

    @Override
    public void deleteCourse(int id) {
        getCourseById(id);
        var courseDeleted = courseRepository.deleteById(id);
        if (!courseDeleted) throw new ApiException("Course cannot be deleted.");
    }
}
