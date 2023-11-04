package com.example.VodReco.dto.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
@Getter
@Component
@Setter
@RequiredArgsConstructor
public class ToGenreModelDto {
    private List<EveryGenre> genre_data;
}
