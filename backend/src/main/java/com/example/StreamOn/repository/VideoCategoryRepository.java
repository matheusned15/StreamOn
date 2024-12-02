package com.example.StreamOn.repository;

import com.example.StreamOn.entities.VideoCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoCategoryRepository extends JpaRepository<VideoCategory, Long> {
    void deleteByVideoId(Long videoId);

    void deleteByCategoryId(Long categoryId);
}
