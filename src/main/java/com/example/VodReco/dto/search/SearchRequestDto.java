package com.example.VodReco.dto.search;

import lombok.Builder;

public class SearchRequestDto {
    private String searchTerm;

    @Builder
    public SearchRequestDto(String searchTerm){
        this.searchTerm = searchTerm;
    }
}
