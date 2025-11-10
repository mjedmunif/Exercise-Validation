package org.example.exercisevalidtioneventsystem.Modell;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Events {

    @NotNull
    @Min(value = 2 ,  message = "id should be more 2")
    private int id;

    @NotEmpty
    @Size(min = 15 , message = "description should be more than 15 digits")
    private String description;

    @NotNull
    @Min(value = 25 , message = "capacity should be more than 25")
    private int capacity;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDate;
}
