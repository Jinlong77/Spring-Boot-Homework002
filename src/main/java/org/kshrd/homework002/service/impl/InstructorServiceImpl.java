package org.kshrd.homework002.service.impl;

import lombok.RequiredArgsConstructor;
import org.kshrd.homework002.exception.ApiException;
import org.kshrd.homework002.exception.ResourceNotFoundException;
import org.kshrd.homework002.model.dto.request.InstructorRequest;
import org.kshrd.homework002.model.entity.InstructorEntity;
import org.kshrd.homework002.repository.InstructorRepository;
import org.kshrd.homework002.service.InstructorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;

    @Override
    public List<InstructorEntity> getAllInstructorsByPagination(int page, int size) {
        return instructorRepository.findAllByPagination(page, size);
    }

    @Override
    public InstructorEntity getInstructorById(int id) {
        var instructors = instructorRepository.findById(id);
        if (instructors == null) throw new ResourceNotFoundException("Instructor with ID " + id + " not found.");
        return instructors;
    }

    @Override
    public void deleteInstructorById(int id) {
        getInstructorById(id);
        boolean instructorDeleted = instructorRepository.deleteById(id);
        if (!instructorDeleted) throw new ResourceNotFoundException("Instructor cannot be deleted.");
    }

    @Override
    public InstructorEntity createInstructor(InstructorRequest instructorEntity) {
        var instructor = instructorRepository.save(instructorEntity);
        if (instructor == null) throw new ResourceNotFoundException("Instructor cannot be created.");
        return instructor;
    }

    @Override
    public InstructorEntity updateInstructor(int id, InstructorRequest instructorEntity) {
        getInstructorById(id);
        var instructor = instructorRepository.update(instructorEntity, id);
        if (instructor == null) throw new ApiException("Instructor cannot be updated.");
        return instructor;
    }
}
