package com.example.StreamOn.controller;


import com.example.StreamOn.entities.dto.VideoDTO;
import com.example.StreamOn.service.VideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping
    public ResponseEntity<VideoDTO> createVideo(@RequestBody VideoDTO videoDTO) {
        return ResponseEntity.ok(videoService.createVideo(videoDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity getVideoById(@PathVariable Long id) {
        return ResponseEntity.ok(videoService.getVideoById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody VideoDTO dto){
        try {
            VideoDTO updateUser = videoService.updateVideo(id,dto);
            return ResponseEntity.ok(updateUser);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<VideoDTO>> getAllVideos() {
        return ResponseEntity.ok(videoService.getAllVideos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        return videoService.getVideoById(id).map(entity -> {
             videoService.deleteUser(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }).orElseGet(() ->
                new ResponseEntity("User not found", HttpStatus.BAD_REQUEST));
    }
}
