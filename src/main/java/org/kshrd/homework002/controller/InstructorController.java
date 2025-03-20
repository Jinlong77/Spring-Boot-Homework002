package org.kshrd.homework002.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.kshrd.homework002.model.dto.request.InstructorRequest;
import org.kshrd.homework002.model.dto.response.domain.Response;
import org.kshrd.homework002.model.entity.InstructorEntity;
import org.kshrd.homework002.service.InstructorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.kshrd.homework002.constant.Constants.INSTRUCTOR_API;
import static org.kshrd.homework002.utils.RequestUtils.getResponse;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(INSTRUCTOR_API)
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping
    public ResponseEntity<Response<List<InstructorEntity>>> getAllInstructors(@RequestParam(value = "page", defaultValue = "1") @Positive int page, @RequestParam(value = "size", defaultValue = "3") @Positive int size) {
        var instructors = instructorService.getAllInstructorsByPagination(page, size);
        return ResponseEntity.ok(getResponse("All instructors have been successfully fetched.",OK ,instructors));
    }

    @GetMapping("{instructor-id}")
    public ResponseEntity<Response<InstructorEntity>> getInstructorById(@PathVariable("instructor-id") @Positive int id) {
        return ResponseEntity.ok(getResponse("Instructor has been successfully fetched.", OK, instructorService.getInstructorById(id)));
    }

    @PostMapping
    public ResponseEntity<Response<InstructorEntity>> createInstructor(@RequestBody @Valid InstructorRequest instructorRequest) {
        return ResponseEntity.created(null).body(getResponse("Instructor has been successfully created.", OK, instructorService.createInstructor(instructorRequest)));
    }

    @PutMapping("{instructor-id}")
    public ResponseEntity<Response<InstructorEntity>> updateInstructor(@PathVariable("instructor-id") @Positive int id, @RequestBody @Valid InstructorRequest instructorRequest) {
        var instructor = instructorService.updateInstructor(id, instructorRequest);
        return ResponseEntity.ok(getResponse("Instructor has been successfully updated.", OK, instructor));
    }

    @DeleteMapping("{instructor-id}")
    public ResponseEntity<Response<InstructorEntity>> deleteInstructor(@PathVariable("instructor-id") int id) {
        instructorService.deleteInstructorById(id);
        return ResponseEntity.ok(getResponse("Instructor has been successfully deleted.", OK, null));
    }
}
