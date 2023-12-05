package com.dictionaryapp.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddWordDTO {

    @Size(min = 2, max = 40, message = "Term length must be between 2 and 40 characters (inclusive of 2 and 40).")
    private String term;
    @Size(min = 2, max = 80, message = "Translation length must be between 2 and 80 characters (inclusive of 2 and 80).")
    private String translation;
    @Size(min = 2, max = 200, message = "Example length must be between 2 and 200 characters (inclusive of 2 and 200).")
    private String example;
    @PastOrPresent(message = "The input date must be a date in the past or present.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate inputDate;
    @NotBlank
    private String language;

    private Long addedById;


}
