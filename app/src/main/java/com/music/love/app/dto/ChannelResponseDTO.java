package com.music.love.app.dto;

public record ChannelResponseDTO(String Channel_name, 
    String Channel_url,
    long subscriber_count,
    long videos_count) {
    
}
