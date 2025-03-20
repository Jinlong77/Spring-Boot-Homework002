package org.kshrd.homework002.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.kshrd.homework002.model.dto.request.StudentRequest;
import org.kshrd.homework002.model.dto.response.domain.Response;
import org.kshrd.homework002.model.entity.StudentEntity;
import org.kshrd.homework002.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.kshrd.homework002.constant.Constants.STUDENT_API;
import static org.kshrd.homework002.utils.RequestUtils.getResponse;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(STUDENT_API)
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<Response<List<StudentEntity>>> getAllStudents(@RequestParam(value = "page", defaultValue = "1") @Positive int page, @RequestParam(value = "size", defaultValue = "3") @Positive int size) {
        var students = studentService.getAllStudentsPagination(page, size);
        return ResponseEntity.ok(getResponse("All students have been successfully fetched.", OK, students));
    }

    @GetMapping("{student-id}")
    public ResponseEntity<Response<StudentEntity>> getStudentById(@PathVariable("student-id") @Positive int id) {
        var student = studentService.getStudentById(id);
        return ResponseEntity.ok(getResponse("Student with ID " + id + " has been successfully fetched.", OK, student));
    }

    @PostMapping
    public ResponseEntity<Response<StudentEntity>> createStudent(@RequestBody @Valid StudentRequest student) {
        var studentCreated = studentService.createStudent(student);
        return ResponseEntity.created(null).body(getResponse("Student has been successfully created.", OK, studentCreated));
    }

    @PutMapping("{student-id}")
    public ResponseEntity<Response<StudentEntity>> updateStudent(@PathVariable("student-id") @Positive int id, @RequestBody @Valid StudentRequest student) {
        var studentUpdated = studentService.updateStudent(id, student);
        return ResponseEntity.ok(getResponse("Student with ID " + id + " has been successfully updated.", OK, studentUpdated));
    }

    @DeleteMapping("{student-id}")
    public ResponseEntity<Response<StudentEntity>> deleteStudent(@PathVariable("student-id") @Positive int id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok(getResponse("Student with ID " + id + " has been successfully deleted.", OK, null));
    }
}
