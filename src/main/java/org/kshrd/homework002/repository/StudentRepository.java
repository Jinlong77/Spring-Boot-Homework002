package org.kshrd.homework002.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.homework002.model.dto.request.StudentRequest;
import org.kshrd.homework002.model.entity.StudentEntity;

import java.util.List;

@Mapper
public interface StudentRepository {

    @Select("""
        SELECT * FROM students
        OFFSET #{size} * (#{page} - 1)
        LIMIT #{size}
    """)
    @Results(id = "studentResult", value = {
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "studentName", column = "student_name"),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNumber", column = "phone"),
            @Result(property = "courses", column = "student_id",
                    many = @Many(select = "org.kshrd.homework002.repository.CourseRepository.findCourseByStudentId")
            )
    })
    List<StudentEntity> findAllStudentsByPagination(@Param("page") int page, @Param("size") int size);

    @Select("""
        SELECT * FROM students
        WHERE student_id = #{id}
    """)
    @ResultMap("studentResult")
    StudentEntity findById(int id);

    @Select("""
        INSERT INTO students(student_name, email, phone)
        VALUES (#{s.studentName}, #{s.email}, #{s.phoneNumber})
        RETURNING *
    """)
    @ResultMap("studentResult")
    StudentEntity save(@Param("s") StudentRequest student);

    @Delete("""
        DELETE FROM students
        WHERE student_id = #{id}
    """)
    @ResultMap("studentResult")
    boolean deleteById(int id);

    @Select("""
        SELECT * FROM students s
        INNER JOIN student_courses sc
        ON s.student_id = sc.student_id
        WHERE course_id = #{courseId}
    """)
    @ResultMap("studentResult")
    List<StudentEntity> findAllStudentsByCourseId(int courseId);

    @Insert("""
        INSERT INTO student_courses(student_id, course_id)
        VALUES (#{studentId}, #{courseId})
    """)
    void insertIntoStudentCourse(int studentId, int courseId);


    @Delete("""
        DELETE FROM student_courses
        WHERE student_id = #{studentId}
    """)
    void deleteStudentCourse(int studentId);
}