package com.example.VodReco.dto.search;

import lombok.Builder;

public class SearchRequestDto {
//    private String searchTerm;
//
//    @Builder
//    public SearchRequestDto(String searchTerm){
//        this.searchTerm = searchTerm;
//    }
    private String content_id;
    private String posterurl;
    private String title;

    @Builder
    public SearchRequestDto(String contentId, String posterurl, String title) {
        this.content_id = contentId;
        this.posterurl = posterurl;
        this.title = title;
    }
}
