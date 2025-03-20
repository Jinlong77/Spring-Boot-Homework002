package org.kshrd.homework002.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.kshrd.homework002.model.dto.request.CourseRequest;
import org.kshrd.homework002.model.dto.response.domain.Response;
import org.kshrd.homework002.model.entity.CourseEntity;
import org.kshrd.homework002.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.kshrd.homework002.constant.Constants.COURSE_API;
import static org.kshrd.homework002.utils.RequestUtils.getResponse;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(COURSE_API)
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<Response<List<CourseEntity>>> getAllCourses(@RequestParam(value = "page", defaultValue = "1") @Positive int page, @RequestParam(value = "size", defaultValue = "3") @Positive int size) {
        var courses = courseService.getAllCoursesByPagination(page, size);
        return ResponseEntity.ok(getResponse("Courses have been successfully fetched.", OK,  courses));
    }

    @GetMapping("{course-id}")
    public ResponseEntity<Response<CourseEntity>> getCourseById(@PathVariable("course-id") @Positive int id) {
        var course = courseService.getCourseById(id);
        return ResponseEntity.ok(getResponse("Course has been successfully fetched.", OK, course));
    }

    @PostMapping
    public ResponseEntity<Response<CourseEntity>> createCourse(@RequestBody @Valid CourseRequest courseRequest) {
        var course = courseService.createCourse(courseRequest);
        return ResponseEntity.created(null).body(getResponse("Course has been successfully created.", OK, course));
    }

    @PutMapping("{course-id}")
    public ResponseEntity<Response<CourseEntity>> updateCourse(@PathVariable("course-id") @Positive int id, @RequestBody @Valid CourseRequest courseRequest) {
        var course = courseService.updateCourse(id, courseRequest);
        return ResponseEntity.ok(getResponse("Course has been successfully updated.", OK, course));
    }

    @DeleteMapping("{course-id}")
    public ResponseEntity<Response<CourseEntity>> deleteCourse(@PathVariable("course-id") @Positive int id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok(getResponse("Course has been successfully deleted.", OK, null));
    }
}
