package com.example.StreamOn.entities.dto;

public class PlaybackLogDTO {
    private Long id;
    private Long userId;
    private Long contentId;
    private String playbackStartTime;
    private String playbackEndTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public String getPlaybackStartTime() {
        return playbackStartTime;
    }

    public void setPlaybackStartTime(String playbackStartTime) {
        this.playbackStartTime = playbackStartTime;
    }

    public String getPlaybackEndTime() {
        return playbackEndTime;
    }

    public void setPlaybackEndTime(String playbackEndTime) {
        this.playbackEndTime = playbackEndTime;
    }
}
