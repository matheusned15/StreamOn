package com.example.StreamOn.entities.dto;

public class VideoCategoryDTO {
    private Long videoId;
    private Long categoryId;

    public VideoCategoryDTO(Long videoId, Long categoryId, Long id) {
        this.videoId = videoId;
        this.categoryId = categoryId;
    }

    public VideoCategoryDTO() {

    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
