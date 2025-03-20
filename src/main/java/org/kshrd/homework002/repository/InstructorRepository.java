package org.kshrd.homework002.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.homework002.model.dto.request.InstructorRequest;
import org.kshrd.homework002.model.entity.InstructorEntity;

import java.util.List;

@Mapper
public interface InstructorRepository {

    @Select("""
        SELECT * FROM instructors
        OFFSET #{size} * (#{page} - 1) LIMIT #{size}
    """)
    @Results(id = "instructorMapper", value = {
            @Result(property = "instructorId", column = "instructor_id"),
            @Result(property = "instructorName", column = "instructor_name"),
            @Result(property = "email", column = "email")
    })
    List<InstructorEntity> findAllByPagination(int page, int size);

    @Select("""
        SELECT * FROM instructors
        WHERE instructor_id = #{id}
    """)
    @ResultMap("instructorMapper")
    InstructorEntity findById(int id);

    @Delete("""
        DELETE FROM instructors
        WHERE instructor_id = #{id}
    """)
    @ResultMap("instructorMapper")
    boolean deleteById(int id);

    @Select("""
        INSERT INTO instructors (instructor_name, email)
        VALUES (#{i.instructorName}, #{i.email})
        RETURNING *
    """)
    @ResultMap("instructorMapper")
    InstructorEntity save(@Param("i") InstructorRequest instructorRequest);

    @Select("""
        UPDATE instructors
        SET instructor_name = #{i.instructorName}, email = #{i.email}
        WHERE instructor_id = #{id}
        RETURNING *
    """)
    @ResultMap("instructorMapper")
    InstructorEntity update(@Param("i") InstructorRequest instructorRequest, @Param("id") int id);
}
