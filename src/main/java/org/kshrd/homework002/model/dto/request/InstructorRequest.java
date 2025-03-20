package org.kshrd.homework002.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class InstructorRequest {

    @NotNull(message = "Instructor name is required")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Invalid instructor name")
    private String instructorName;

    @NotNull(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

}
