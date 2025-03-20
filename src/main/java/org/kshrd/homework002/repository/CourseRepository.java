package org.kshrd.homework002.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.homework002.model.dto.request.CourseRequest;
import org.kshrd.homework002.model.entity.CourseEntity;

import java.util.List;

@Mapper
public interface CourseRepository {

    @Select("""
        SELECT * FROM courses
        OFFSET #{size} * (#{page} - 1) LIMIT #{size}
    """)
    @Results(id = "courseEntity", value = {
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "description", column = "description"),
            @Result(property = "instructor", column = "instructor_id",
                    one = @One(select = "org.kshrd.homework002.repository.InstructorRepository.findById")
            )
    })
    List<CourseEntity> findAllByPagination(int page, int size);


    @Select("""
        SELECT * FROM courses
        WHERE course_id = #{id}
    """)
    @ResultMap("courseEntity")
    CourseEntity findById(int id);

    @Select("""
        INSERT INTO courses (course_name, description, instructor_id)
        VALUES (#{c.courseName}, #{c.description}, #{c.instructorId})
        RETURNING *
    """)
    @ResultMap("courseEntity")
    CourseEntity save(@Param("c") CourseRequest courseRequest);

    @Delete("""
        DELETE FROM courses
        WHERE course_id = #{id}
    """)
    @ResultMap("courseEntity")
    boolean deleteById(int id);

    @Select("""
        UPDATE courses
        SET course_name = #{c.courseName}, description = #{c.description}, instructor_id = #{c.instructorId}
        WHERE course_id = #{id}
        RETURNING *
    """)
    @ResultMap("courseEntity")
    CourseEntity update(int id, @Param("c") CourseRequest courseRequest);

    @Select("""
        SELECT * FROM courses c
        INNER JOIN student_courses sc
        ON c.course_id = sc.course_id
        WHERE student_id = #{studentId}
    """)
    @ResultMap("courseEntity")
    List<CourseEntity> findCourseByStudentId(int studentId);
}
