package org.example.exercisevalidation.Modell;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tracker {

    @NotNull
    @Min(value = 2 , message = "id should be more than 2")
    private int id;

    @Size(min = 8 , message = "title should be more than 8")
    @NotEmpty
    private String title;

    @Size(min = 15 , message = "description should be more than 15")
    @NotEmpty
    private String description;

    @NotEmpty
    @Size(min = 6 , message = "company name should be more than 6")
    private String companyName;

    @NotEmpty
    @Pattern(regexp = "not started|in progress|completed" , message = "Status must be one of : completed or not Started or in progress ")
    private String status;


}
