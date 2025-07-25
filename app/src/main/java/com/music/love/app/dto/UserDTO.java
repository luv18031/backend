package com.music.love.app.dto;

public record UserDTO(Long id,String username, String email, 
                      String register_as,String phoneNumber, String country,
                      String city, String address, String state, String pinCode,
                      byte[] profilePicture, byte[] governmentPictureId) {
    

            //             UserDTO(user.getId(),user.getUsername(),user.getEmail(),
            // user.getRegister_as(), user.getPhoneNumber(), user.getCountry(), 
            // user.getCity(), user.getAddress(), user.getState(), user.getPinCode(),
            // user.getProfilePicture());

    //  String register_as,
    //  String phoneNumber,
    //  String country,
    //  String city,
    //  String address,
    //  String state,
    //  String pinCode,
    //  byte[] profilePicture,
    //  byte[] governmentPictureId,
}
