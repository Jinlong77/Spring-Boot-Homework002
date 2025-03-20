package org.kshrd.homework002.service;

import org.kshrd.homework002.model.dto.request.InstructorRequest;
import org.kshrd.homework002.model.entity.InstructorEntity;

import java.util.List;

public interface InstructorService {

    List<InstructorEntity> getAllInstructorsByPagination(int page, int size);

    InstructorEntity getInstructorById(int id);

    void deleteInstructorById(int id);

    InstructorEntity createInstructor(InstructorRequest instructorEntity);

    InstructorEntity updateInstructor(int id, InstructorRequest instructorEntity);
}
