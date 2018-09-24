package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SpecimenDto {
    private Long id;
    private String status;
    private Book book;
}
