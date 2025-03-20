package org.kshrd.homework002.service;

import org.kshrd.homework002.model.dto.request.StudentRequest;
import org.kshrd.homework002.model.entity.StudentEntity;

import java.util.List;

public interface StudentService {

    List<StudentEntity> getAllStudentsPagination(int page, int size);

    StudentEntity getStudentById(int id);

    void deleteStudent(int id);

    StudentEntity createStudent(StudentRequest studentRequest);

    StudentEntity updateStudent(int id, StudentRequest studentRequest);
}
