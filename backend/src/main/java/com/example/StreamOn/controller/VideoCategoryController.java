package com.example.StreamOn.controller;



import com.example.StreamOn.entities.dto.VideoCategoryDTO;
import com.example.StreamOn.service.VideoCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/video-categories")
public class VideoCategoryController {

    private final VideoCategoryService videoCategoryService;

    public VideoCategoryController(VideoCategoryService videoCategoryService) {
        this.videoCategoryService = videoCategoryService;
    }

    @PostMapping
    public ResponseEntity<VideoCategoryDTO> assignCategoryToVideo(@RequestBody VideoCategoryDTO videoCategoryDTO) {
        return ResponseEntity.ok(videoCategoryService.assignCategoryToVideo(videoCategoryDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(videoCategoryService.findById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody VideoCategoryDTO dto){
        try {
            VideoCategoryDTO updateUser = videoCategoryService.updateVideoCategory(id,dto);
            return ResponseEntity.ok(updateUser);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<VideoCategoryDTO>> getAllUsers() {
        return ResponseEntity.ok(videoCategoryService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        return videoCategoryService.findById(id).map(entity -> {
            videoCategoryService.deleteByVideoId(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }).orElseGet(() ->
                new ResponseEntity("User not found", HttpStatus.BAD_REQUEST));
    }
}
