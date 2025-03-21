package org.kshrd.homework002.service.impl;

import lombok.RequiredArgsConstructor;
import org.kshrd.homework002.exception.ResourceNotFoundException;
import org.kshrd.homework002.model.dto.request.StudentRequest;
import org.kshrd.homework002.model.entity.StudentEntity;
import org.kshrd.homework002.repository.StudentRepository;
import org.kshrd.homework002.service.CourseService;
import org.kshrd.homework002.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseService courseService;

    @Override
    public List<StudentEntity> getAllStudentsPagination(int page, int size) {
        return studentRepository.findAllStudentsByPagination(page, size);
    }

    @Override
    public StudentEntity getStudentById(int id) {
        StudentEntity student = studentRepository.findById(id);
        if (student == null) throw new ResourceNotFoundException("Student with ID " + id + " not found.");
        return student;
    }

    @Override
    public void deleteStudent(int id) {
        var studentDeleted = studentRepository.deleteById(id);
        if (!studentDeleted) throw new ResourceNotFoundException("Student with ID " + id + " not found.");
    }

    @Override
    public StudentEntity createStudent(StudentRequest studentRequest) {
        var student = studentRepository.save(studentRequest);
        if (student == null) throw new ResourceNotFoundException("Student with cannot be created.");
        studentRequest.getCourseIds().forEach(courseId -> {
            var course = courseService.getCourseById(courseId);
            if (course != null) studentRepository.insertIntoStudentCourse(student.getStudentId(), courseId);
        });
        return studentRepository.findById(student.getStudentId());
    }

    @Override
    public StudentEntity updateStudent(int id, StudentRequest studentRequest) {
        getStudentById(id);
        studentRepository.deleteStudentCourse(id);
        studentRequest.getCourseIds().forEach(courseId -> {
            var course = courseService.getCourseById(courseId);
            if (course != null) studentRepository.insertIntoStudentCourse(id, courseId);
        });
        return studentRepository.findById(id);
    }
}
