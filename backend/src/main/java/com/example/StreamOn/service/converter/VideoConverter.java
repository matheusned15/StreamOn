package com.example.StreamOn.service.converter;

import com.example.StreamOn.entities.Video;
import com.example.StreamOn.entities.dto.VideoDTO;

public class VideoConverter {
    public VideoDTO ConvertToDTO(Video video){
        VideoDTO dto = new VideoDTO();
        dto.setId(video.getId());
        dto.setTitle(video.getTitle());
        dto.setUrl(video.getUrl());
        dto.setDescription(video.getDescription());

        return dto;
    }
}
