package com.music.love.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.music.love.app.entity.MyChannel;

import jakarta.transaction.Transactional;

public interface MyChannelRepository extends JpaRepository<MyChannel, Long> {
    // find all channels for user with username username
    List<MyChannel> findByMyuser_Username(String username); 
    
    // find channel by Channel_name
    MyChannel findByChannelName(String channelName);

    // delete channel by Channel_name
    @Transactional
    @Modifying
    void deleteByChannelNameAndMyuser_Username(String channelName, String username);

    
}
