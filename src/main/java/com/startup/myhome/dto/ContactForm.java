package com.startup.myhome.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactForm {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 20 characters")
    private String name;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "\\d{10}", message = "Invalid phone number format. It should contain 10 digits.")
    private String phoneNumber;

    @NotBlank(message = "Message cannot be blank")
    @Size(max = 500, message = "Message cannot exceed 500 characters")
    private String message;
}
