package com.example.StreamOn.service;

import com.example.StreamOn.entities.Video;
import com.example.StreamOn.entities.dto.VideoDTO;
import com.example.StreamOn.repository.VideoRepository;
import com.example.StreamOn.service.converter.VideoConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VideoService {

    private final VideoRepository videoRepository;
    private final VideoConverter videoConverter;

    public VideoService(VideoRepository videoRepository, VideoConverter videoConverter) {
        this.videoRepository = videoRepository;
        this.videoConverter = videoConverter;
    }

    public VideoDTO createVideo(VideoDTO dto) {
        Video video = convertDtoToEntity(dto);
        videoRepository.save(video);
        return videoConverter.ConvertToDTO(video);
    }

    private Video convertDtoToEntity(VideoDTO dto) {
        Video video = new Video();
        video.setUrl(dto.getUrl());
        video.setDescription(dto.getDescription());
        video.setTitle(dto.getTitle());
        return video;
    }

    public VideoDTO updateVideo(Long id, VideoDTO video) {
        return videoRepository.findById(id).map(existingVideo -> {
            existingVideo.setTitle(video.getTitle());
            existingVideo.setDescription(video.getDescription());
            existingVideo.setUrl(video.getUrl());
            Video updatedVideo = videoRepository.save(existingVideo);
            return new VideoDTO(
                    updatedVideo.getId(),
                    updatedVideo.getTitle(),
                    updatedVideo.getDescription(),
                    updatedVideo.getUrl()
            );
        }).orElseThrow(() -> new RuntimeException("Video not found with ID: " + id));
    }

    public Optional<Video> getVideoById(Long id) {
        return videoRepository.findById(id);
    }

    public List<VideoDTO> getAllVideos() {
        List<Video> videos = videoRepository.findAll();
        return videos.stream()
                .map(videoConverter::ConvertToDTO)
                .collect(Collectors.toList());
    }

    public void deleteUser(Long id) {
        videoRepository.deleteById(id);
    }
}
