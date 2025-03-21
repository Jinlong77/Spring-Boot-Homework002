package org.kshrd.homework002.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
public class StudentRequest {

    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Invalid student name")
    private String studentName;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

    @NotNull(message = "Phone number is required")
    @Pattern(regexp = "^0\\d{8,9}$", message = "Invalid phone number")
    private String phoneNumber;

    @NotNull(message = "Course Ids are required")
    private List<Integer> courseIds;
}
