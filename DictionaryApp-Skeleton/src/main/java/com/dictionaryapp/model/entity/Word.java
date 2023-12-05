package com.dictionaryapp.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "words")
public class Word extends BaseEntity {
    @Column(nullable = false)
    private String term;
    @Column(nullable = false)
    private String translation;
    @Column(nullable = false)
    private String example;
    @Column(nullable = false, name = "input_date")
    private LocalDate inputDate;
    @ManyToOne
    private Language language;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;


}
