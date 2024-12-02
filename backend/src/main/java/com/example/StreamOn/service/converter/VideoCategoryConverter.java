package com.example.StreamOn.service.converter;

import com.example.StreamOn.entities.VideoCategory;
import com.example.StreamOn.entities.dto.VideoCategoryDTO;

public class VideoCategoryConverter {
    public VideoCategoryDTO convertToDTO(VideoCategory videoCategory){
        VideoCategoryDTO dto = new VideoCategoryDTO();
        dto.setVideoId(videoCategory.getVideoId());
        dto.setCategoryId(videoCategory.getCategoryId());

        return dto;
    }
}
