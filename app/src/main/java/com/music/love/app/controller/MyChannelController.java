package com.music.love.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.music.love.app.dto.ChannelResponseDTO;
import com.music.love.app.entity.MyChannel;
import com.music.love.app.entity.MyUser;
import com.music.love.app.service.MyChannelService;
import com.music.love.app.service.UserService;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/channels")
public class MyChannelController {
    
    private final MyChannelService myChannelService;
    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(MyChannelController.class);

    public MyChannelController(MyChannelService myChannelService, UserService userService) {
        this.myChannelService = myChannelService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<ChannelResponseDTO>> getChannelsForAuthenticatedUser() {
        // Placeholder for fetching channels for the authenticated user
        if(!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String authenticatedUsername = SecurityContextHolder.getContext().getAuthentication().getName();


        // Fetch channels for the user
        var channels = myChannelService.getAllChannelsForUser(authenticatedUsername);

        return ResponseEntity.ok(
            channels.stream()
                .map(channel -> new ChannelResponseDTO(
                    channel.getChannelName(),
                    channel.getChannelUrl(),
                    channel.getSubscriberCount(),
                    channel.getVideosCount()
                ))
                .toList()
        );
    }

    // create order for authenticated user
    @PostMapping
    public ResponseEntity<ChannelResponseDTO> createChannelForAuthenticatedUser(@RequestBody final ChannelResponseDTO  channel) {
        // Placeholder for creating a channel for the authenticated user
        if(!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<MyUser> user = userService.findByUsername(username);
        if(user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }       

        MyChannel newChannel = new MyChannel();
        newChannel.setChannelName(channel.Channel_name());
        newChannel.setChannelUrl(channel.Channel_url());   
        newChannel.setSubscriberCount(channel.subscriber_count());
        newChannel.setVideosCount(channel.videos_count());
        newChannel.setMyuser(user.get());
        myChannelService.saveChannel(newChannel);
        
        MyUser fetchedUser = user.get();
        fetchedUser.getChannels().add(newChannel);
        
        userService.saveUser(fetchedUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(
            new ChannelResponseDTO(
                newChannel.getChannelName(),
                newChannel.getChannelUrl(),
                newChannel.getSubscriberCount(),
                newChannel.getVideosCount()
            )
        );  
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllChannelsForAuthenticatedUser() {
        // Placeholder for deleting all channels for the authenticated user
        if(!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<MyUser> user = userService.findByUsername(username);
        if(user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }       

        MyUser fetchedUser = user.get();
        fetchedUser.getChannels().clear();
        
        userService.saveUser(fetchedUser);

        return ResponseEntity.noContent().build();
    }  

    @DeleteMapping("/{channelName}")
    @Transactional
    public ResponseEntity<Void> deleteChannelByNameForAuthenticatedUser(@PathVariable String channelName) {
        // Placeholder for deleting a channel by name for the authenticated user
        if(!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<MyUser> user = userService.findByUsername(username);
        if(user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }       

        // MyUser fetchedUser = user.get();
        // MyChannel channelToRemove = myChannelService.getChannelByName(channelName);
        // logger.debug("channel to remove: " + channelToRemove);
        // if(channelToRemove != null && fetchedUser.getChannels().contains(channelToRemove)) {
        //     fetchedUser.getChannels().remove(channelToRemove);
        //     userService.saveUser(fetchedUser);
        // }
        // logger.debug("remaining channels: " + fetchedUser.getChannels());
        MyChannel channelToRemove = user.get().getChannels().stream()
            .filter(channel -> channel.getChannelName().equals(channelName))
            .findFirst()
            .orElse(null); 
        myChannelService.deleteByChannelNameAndUser(channelName, username);

        if (channelToRemove != null) {
            // This is the key part: remove the channel from the parent's collection
            user.get().getChannels().remove(channelToRemove);
            
            // Because of 'orphanRemoval=true' on the user entity, 
            // JPA will automatically generate the DELETE statement for the channel.
            // You don't even need to call save, as the user is a managed entity
            // within a @Transactional context.
        } else {
            // It's good practice to let the client know if the channel didn't exist
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }   
    
    @PatchMapping
    public ResponseEntity<Void> PatchMapping(@RequestBody final ArrayList<ChannelResponseDTO>  channels) {
        // Placeholder for deleting all channels for the authenticated user
        if(!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<MyUser> user = userService.findByUsername(username);
        if(user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }       

        MyUser fetchedUser = user.get();
        HashMap<String, ChannelResponseDTO> channelMap = new HashMap<>();
        for(var channel : channels) {
            channelMap.put(channel.Channel_name(), channel);
        }        
        for(var channel : fetchedUser.getChannels()) {
            channel.setSubscriberCount(channelMap.get(channel.getChannelName()).subscriber_count());
            channel.setVideosCount(channelMap.get(channel.getChannelName()).videos_count());
            channel.setChannelUrl(channelMap.get(channel.getChannelName()).Channel_url());
            myChannelService.saveChannel(channel);
        }   
        userService.updateUser(fetchedUser.getId(), fetchedUser);

        return ResponseEntity.ok().build();
    }  

}
