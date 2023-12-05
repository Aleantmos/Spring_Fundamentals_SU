package com.dictionaryapp.model.entity;

import com.dictionaryapp.model.enums.LanguageNameENUM;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "language")
public class Language extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private LanguageNameENUM languageNameENUM;

    @Column(nullable = false)
    private String Description;

    @OneToMany(mappedBy = "language")
    private List<Word> words;
}
