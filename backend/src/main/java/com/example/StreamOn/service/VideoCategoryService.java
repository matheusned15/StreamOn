package com.example.StreamOn.service;

import com.example.StreamOn.entities.VideoCategory;

import com.example.StreamOn.entities.dto.VideoCategoryDTO;
import com.example.StreamOn.repository.VideoCategoryRepository;
import com.example.StreamOn.service.converter.VideoCategoryConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VideoCategoryService {

    private final VideoCategoryRepository videoCategoryRepository;
    private final VideoCategoryConverter categoryConverter;

    public VideoCategoryService(VideoCategoryRepository videoCategoryRepository, VideoCategoryConverter categoryConverter) {
        this.videoCategoryRepository = videoCategoryRepository;
        this.categoryConverter = categoryConverter;
    }

    public List<VideoCategoryDTO> findAll() {
        List<VideoCategory> videoCategories = videoCategoryRepository.findAll();
        return videoCategories.stream()
                .map(categoryConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<VideoCategory> findById(Long id) {
        return videoCategoryRepository.findById(id);
    }

    public void deleteByVideoId(Long videoId) {
        videoCategoryRepository.deleteByVideoId(videoId);
    }

    public VideoCategoryDTO assignCategoryToVideo(VideoCategoryDTO videoCategoryDTO) {
        VideoCategory videoCategory = new VideoCategory();
        videoCategory.setId(videoCategoryDTO.getVideoId());
        videoCategory.setCategoryId(videoCategoryDTO.getCategoryId());

        VideoCategory savedEntity = videoCategoryRepository.save(videoCategory);

        return new VideoCategoryDTO(
                savedEntity.getId(),
                savedEntity.getVideoId(),
                savedEntity.getCategoryId()
        );
    }

    public VideoCategoryDTO updateVideoCategory(Long id, VideoCategoryDTO dto) {
        Optional<VideoCategory> videoCategoryOptional = videoCategoryRepository.findById(id);
        if (videoCategoryOptional.isPresent()) {
            VideoCategory videoCategory = videoCategoryOptional.get();
            videoCategory.setVideoId(dto.getVideoId());
            videoCategory.setCategoryId(dto.getCategoryId());
            return categoryConverter.convertToDTO(videoCategory);
        }
        throw new RuntimeException("Video Category not found");
    }
}
