package com.music.love.app.service;

import java.nio.channels.Channel;
import java.util.List;

import org.springframework.stereotype.Service;

import com.music.love.app.dto.ChannelResponseDTO;
import com.music.love.app.entity.MyChannel;
import com.music.love.app.repository.MyChannelRepository;

@Service
public class MyChannelService {
    
    private final MyChannelRepository myChannelRepository;

    public MyChannelService(MyChannelRepository myChannelRepository) {
        this.myChannelRepository = myChannelRepository;
    }

    // get all channels for user with username username
    public List<MyChannel> getAllChannelsForUser(String username) {
        // Implementation to fetch channels for the user
        return myChannelRepository.findByMyuser_Username(username);
    }   

    public MyChannel saveChannel(MyChannel channel) {
        return myChannelRepository.save(channel);
    }

    public MyChannel getChannelByName(String channelName) {
        return myChannelRepository.findByChannelName(channelName);
    }

    public void deleteByChannelNameAndUser(String channelName, String username) {
        myChannelRepository.deleteByChannelNameAndMyuser_Username(channelName, username);
    }

}
